package com.example.rnas.porteiroclient;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.example.rnas.porteiroclient.api.RemoteAPIClient;
import com.example.rnas.porteiroclient.api.RemoteAPIInterface;
import com.example.rnas.porteiroclient.helper.Constants;
import com.example.rnas.porteiroclient.model.User;
import com.orhanobut.hawk.Hawk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaptureActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView qrCodeReaderView;
    private RemoteAPIInterface remoteAPIInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        remoteAPIInterface = RemoteAPIClient.getClient().create(RemoteAPIInterface.class);

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // todo: camera authorization
        initQRReader();

    }

    public void initQRReader() {

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(5000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        Log.i("WOW", "QR CODE " + text);

        qrCodeReaderView.stopCamera();

        remoteAPIInterface.syncUser(text).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // todo check if success

                if (response.isSuccessful()) {

                    Hawk.init(getApplicationContext()).build();

                    Hawk.put(Constants.USER_NAME, response.body().getPeople_name());
                    Hawk.put(Constants.USER_PHOTO, response.body().getPeople_photo());
                    Hawk.put(Constants.USER_JOB, response.body().getPeople_job());
                    Hawk.put(Constants.USER_IN, response.body().getPeople_in());
                    Hawk.put(Constants.USER_OUT, response.body().getPeople_out());
                    Hawk.put(Constants.USER_HASH, response.body().getPeople_hash());
                    Hawk.put(Constants.USER_SLACK, response.body().getPeople_slack());

                    Intent i = new Intent(getApplicationContext(), LoggedActivity.class);
                    // todo send data
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }

}
