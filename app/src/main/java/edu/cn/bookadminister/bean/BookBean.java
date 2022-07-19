package edu.cn.bookadminister.bean;

import java.io.Serializable;

public class BookBean implements Serializable {
    String title;
    String jianjie;
    String content;
    int icon;
    boolean isVideo;
    boolean isSound;

    public BookBean(String title, String jianjie,String content, int icon, boolean isVideo, boolean isSound) {
        this.title = title;
        this.jianjie = jianjie;
        this.content = content;
        this.icon = icon;
        this.isVideo = isVideo;
        this.isSound = isSound;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public boolean isSound() {
        return isSound;
    }

    public void setSound(boolean sound) {
        isSound = sound;
    }
}
