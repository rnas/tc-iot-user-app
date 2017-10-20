package com.example.rnas.porteiroclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.rnas.porteiroclient.api.RemoteAPIClient;
import com.example.rnas.porteiroclient.api.RemoteAPIInterface;
import com.example.rnas.porteiroclient.helper.Constants;
import com.example.rnas.porteiroclient.model.Hash;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRViewActivity extends AppCompatActivity {

    RemoteAPIInterface remoteAPIInterface = RemoteAPIClient.getClient().create(RemoteAPIInterface.class);
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrview);

        imageView = (ImageView) findViewById(R.id.iv);

        Hawk.init(getApplicationContext()).build();

        if (! Hawk.contains(Constants.USER_HASH)) {
           finish();
        }

        String hash = Hawk.get(Constants.USER_HASH);

        remoteAPIInterface.getCode(hash).enqueue(new Callback<Hash>() {
            @Override
            public void onResponse(Call<Hash> call, Response<Hash> response) {
                if (response.isSuccessful()) {

                    String qr_code_url = "https://chart.googleapis.com/chart?cht=qr&chs=500x500&chl=" + response.body().getHash_key();
                    Picasso.with(getApplicationContext()).load(qr_code_url).into(imageView);
                } else {
                    Log.i("WOW", "skdl");
                }
            }

            @Override
            public void onFailure(Call<Hash> call, Throwable t) {
                Log.i("WOW", "failure");
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
