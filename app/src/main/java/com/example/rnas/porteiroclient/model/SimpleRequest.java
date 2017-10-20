package com.example.rnas.porteiroclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rnas on 20/10/2017.
 */

public class SimpleRequest {

    @SerializedName("success")
    public boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
