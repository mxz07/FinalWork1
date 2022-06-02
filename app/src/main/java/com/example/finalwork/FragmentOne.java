package com.example.finalwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//OkHttpClient+Handler请求网络，得到响应数据，并封装到List<NewsInfo>列表
public class FragmentOne extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG = "FragmentOne";
    private ListView listView;
    private List<NewsInfo> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //用Fragment_one来填充View视图
        View view = inflater.inflate(R.layout.activity_fragment_one, container, false);

        listView = view.findViewById(R.id.list_item);
        listView.setOnItemClickListener(this);
        //初始化加载新闻数据
        //api接口
        String path = "https://news-at.zhihu.com/api/4/news/latest";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(path).build();
        //2.通过前两个对象创建Call对象
        Call call = okHttpClient.newCall(request);
        //3.通过Call的enqueue(Callback)方法来提交异步请求，子线程
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
                    //2.把json对象转为java对象
                    News news =  (News)JsonUitl.stringToObject(jsonObject.toString(),News.class);
                    Log.d(TAG, news.toString());
                    Message message = new Message();
                    message.obj = news;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            News news = (News)msg.obj;
            //3).handlerMessage方法中获取List
            data = news.getStories();
            List<NewsInfo> data2 = news.getTop_stories();
            //2.用添加好的List创建列适配器，并给ListView添加适配器
            listView.setAdapter(new NewsAdapter(data,data2,getContext()));
        }
    };
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        //获取点击位置
        NewsInfo newsInfo = data.get(i);
        Intent intent = new Intent();
        intent.putExtra("id",newsInfo.getId());
        intent.setClass(getContext(),NewsDetailActivity.class);
        startActivity(intent);
    }
}