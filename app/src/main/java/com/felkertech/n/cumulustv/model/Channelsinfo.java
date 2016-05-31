package com.felkertech.n.cumulustv.model;

/**
 * Created by jhon on 25/05/16.
 */
public class Channelsinfo {
    String id;
    String name;
    String urlimg;
    String urlstream;
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

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

    public String getUrlstream() {
        return urlstream;
    }

    public void setUrlstream(String urlstream) {
        this.urlstream = urlstream;
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
