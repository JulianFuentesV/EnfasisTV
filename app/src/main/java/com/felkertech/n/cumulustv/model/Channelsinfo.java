package com.felkertech.n.cumulustv.model;

/**
 * Created by jhon on 25/05/16.
 */
public class Channelsinfo {
    String id;
    String name;
    String urlImg;
    String urlStream;
    String channelnumber;
    String genres;



// region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }

    public String getChannelnumber() {
        return channelnumber;
    }

    public void setChannelnumber(String channelnumber) {
        this.channelnumber = channelnumber;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    //endregion


}
