package com.example.rnas.porteiroclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rnas.porteiroclient.helper.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.hawk.Hawk;
import com.vistrav.ask.Ask;

public class MainActivity extends AppCompatActivity {

    private Button btAdvance;
    public static final int REQ_ID = 1233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Ask.on(this)
                .id(REQ_ID) // in case you are invoking multiple time Ask from same activity or fragment
                .forPermissions(android.Manifest.permission.CAMERA
                        , android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("Permissões necessárias para o aplicativo",
                        "É necessário uso da câmera para sincronizar o APP.") //optional
                .go();


        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("globals");
        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.i("WOW", msg);




        Hawk.init(getApplicationContext()).build();

        if (Hawk.contains(Constants.USER_HASH)) {
            Intent i = new Intent(getApplicationContext(), LoggedActivity.class);
            // todo send data
            startActivity(i);
        }

        btAdvance = (Button) findViewById(R.id.btadv);
        btAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CaptureActivity.class);
                startActivity(i);
            }
        });
    }
}
