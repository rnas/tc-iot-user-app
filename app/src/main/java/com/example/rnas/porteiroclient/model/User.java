package com.example.rnas.porteiroclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rnas on 20/10/2017.
 */

public class User {

    @SerializedName("people_name")
    private String people_name;
    @SerializedName("people_photo")
    private String people_photo;
    @SerializedName("people_job")
    private String people_job;
    @SerializedName("people_in")
    private String people_in;
    @SerializedName("people_out")
    private String people_out;
    @SerializedName("people_slack")
    private String people_slack;
    @SerializedName("people_hash")
    private String people_hash;

    public String getPeople_name() {
        return people_name;
    }

    public void setPeople_name(String people_name) {
        this.people_name = people_name;
    }

    public String getPeople_photo() {
        return people_photo;
    }

    public void setPeople_photo(String people_photo) {
        this.people_photo = people_photo;
    }

    public String getPeople_job() {
        return people_job;
    }

    public void setPeople_job(String people_job) {
        this.people_job = people_job;
    }

    public String getPeople_in() {
        return people_in;
    }

    public void setPeople_in(String people_in) {
        this.people_in = people_in;
    }

    public String getPeople_out() {
        return people_out;
    }

    public void setPeople_out(String people_out) {
        this.people_out = people_out;
    }

    public String getPeople_slack() {
        return people_slack;
    }

    public void setPeople_slack(String people_slack) {
        this.people_slack = people_slack;
    }

    public String getPeople_hash() {
        return people_hash;
    }

    public void setPeople_hash(String people_hash) {
        this.people_hash = people_hash;
    }
}
