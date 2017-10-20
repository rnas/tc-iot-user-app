package com.example.rnas.porteiroclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rnas on 20/10/2017.
 */

public class Hash {

    @SerializedName("people_id")
    private String people_id;
    @SerializedName("hash_in")
    private String hash_in;
    @SerializedName("hash_out")
    private String hash_out;
    @SerializedName("hash_key")
    private String hash_key;

    public String getPeople_id() {
        return people_id;
    }

    public void setPeople_id(String people_id) {
        this.people_id = people_id;
    }

    public String getHash_in() {
        return hash_in;
    }

    public void setHash_in(String hash_in) {
        this.hash_in = hash_in;
    }

    public String getHash_out() {
        return hash_out;
    }

    public void setHash_out(String hash_out) {
        this.hash_out = hash_out;
    }

    public String getHash_key() {
        return hash_key;
    }

    public void setHash_key(String hash_key) {
        this.hash_key = hash_key;
    }
}
