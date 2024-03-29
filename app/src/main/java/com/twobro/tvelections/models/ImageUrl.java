package com.twobro.tvelections.models;

import com.google.gson.annotations.SerializedName;

public class ImageUrl {
  @SerializedName("url")
  private String url;

  public ImageUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
