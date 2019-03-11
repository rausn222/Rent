package com.example.raushan.rent;

/**
 * Created by Raushan on 3/15/2018.
 */

public class Insta {
    private String title,desc,image,status;
    public Insta(){

    }
    public Insta(String title,String desc, String image)
    {
        this.desc = desc;
        this.image = image;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}