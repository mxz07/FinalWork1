package com.example.finalwork;
import java.util.Arrays;
public class NewsInfo {
    private String[] images;
    private String image;
    private long id;
    private String title;

    public String[] getImages() {
        return images;
    }
    public void setImages(String[] images) {
        this.images = images;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }
    @Override
    public String toString() {
        return "NewsInfo{" +
                "images=" + Arrays.toString(images) +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}