package com.example.finalwork;
import java.util.List;
public class News {


    private List<NewsInfo> stories;

    private List<NewsInfo> top_stories;


    public List<NewsInfo> getStories() {
        return stories;
    }

    public void setStories(List<NewsInfo> stories) {
        this.stories = stories;
    }

    public List<NewsInfo> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<NewsInfo> top_stories) {
        this.top_stories = top_stories;
    }
}