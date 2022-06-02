package com.example.finalwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsDetailActivity extends AppCompatActivity {
    private static final String TAG = "NewsDetailActivity";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail2);
        webView = (WebView) findViewById(R.id.web_view);
        //设置可以执行JS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //加载数据
        //api接口
        String path = "http://news-at.zhihu.com/api/4/news/";
        Intent intent = getIntent();
        long id = intent.getLongExtra("id",0);
        path += id;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i(TAG, str);
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    //2.通过Gson把json字符串转换成java对象
                    NewsDetail newsDetail  =  (NewsDetail) JsonUitl.stringToObject(jsonObject.toString(),NewsDetail.class);
                    Message message = new Message();
                    message.obj = newsDetail;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            NewsDetail newsDetail = (NewsDetail)msg.obj;
            //用webview加载网页
            webView.loadUrl(newsDetail.getShare_url());
        }
    };
}