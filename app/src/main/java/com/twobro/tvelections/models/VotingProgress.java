package com.twobro.tvelections.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VotingProgress implements Serializable{

    @SerializedName("for")
    private int behind;

    @SerializedName("against")
    private int against;

    @SerializedName("resisted")
    private int resisted;

    public VotingProgress() {

    }

    public VotingProgress(int behind, int against, int resisted) {
        this.behind = behind;
        this.against = against;
        this.resisted = resisted;
    }

    public int getBehind() {
        return behind;
    }

    public void setBehind(int behind) {
        this.behind = behind;
    }

    public int getAgainst() {
        return against;
    }

    public void setAgainst(int against) {
        this.against = against;
    }

    public int getResisted() {
        return resisted;
    }

    public void setResisted(int resisted) {
        this.resisted = resisted;
    }
}
