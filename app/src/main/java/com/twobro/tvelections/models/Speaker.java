package com.twobro.tvelections.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Speaker implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("FIO")
    @Expose
    private String fIO;
    @SerializedName("post")
    @Expose
    private String post;
    @SerializedName("short_descriptions")
    @Expose
    private String shortDescriptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFIO() {
        return fIO;
    }

    public void setFIO(String fIO) {
        this.fIO = fIO;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getShortDescriptions() {
        return shortDescriptions;
    }

    public void setShortDescriptions(String shortDescriptions) {
        this.shortDescriptions = shortDescriptions;
    }

}
