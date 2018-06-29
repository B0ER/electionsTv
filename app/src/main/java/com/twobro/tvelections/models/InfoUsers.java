package com.twobro.tvelections.models;

import com.google.gson.annotations.SerializedName;

public class InfoUsers {
  @SerializedName("all_user")
  private int countAllUser;
  @SerializedName("absent_user")
  private int countAbsentUser;
  @SerializedName("questions_time")
  private int questionTime;

  public InfoUsers() {
  }

  public InfoUsers(int countAllUser, int countAbsentUser, int questionTime) {
    this.countAllUser = countAllUser;
    this.countAbsentUser = countAbsentUser;
    this.questionTime = questionTime;
  }

  public int getCountAllUser() {
    return countAllUser;
  }

  public void setCountAllUser(int countAllUser) {
    this.countAllUser = countAllUser;
  }

  public int getCountAbsentUser() {
    return countAbsentUser;
  }

  public void setCountAbsentUser(int countAbsentUser) {
    this.countAbsentUser = countAbsentUser;
  }

  public int getQuestionTime() {
    return questionTime;
  }

  public void setQuestionTime(int questionTime) {
    this.questionTime = questionTime;
  }
}