package com.felkertech.n.cumulustv.model;

/**
 * Created by jhon on 31/05/16.
 */
public class Extrainfo {
    String id;
    String title;
    String description;
    String imgurl;
    String msgnot;
//region getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMsgnot() {
        return msgnot;
    }

    public void setMsgnot(String msgnot) {
        this.msgnot = msgnot;
    }

    //endregion
}
