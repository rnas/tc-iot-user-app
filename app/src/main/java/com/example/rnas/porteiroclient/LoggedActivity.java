package com.example.rnas.porteiroclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnas.porteiroclient.api.RemoteAPIClient;
import com.example.rnas.porteiroclient.api.RemoteAPIInterface;
import com.example.rnas.porteiroclient.helper.Constants;
import com.example.rnas.porteiroclient.model.SimpleRequest;
import com.example.rnas.porteiroclient.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.hawk.Hawk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    RemoteAPIInterface remoteAPIInterface = RemoteAPIClient.getClient().create(RemoteAPIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle("Porteiro");

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("globals");
        String token = FirebaseInstanceId.getInstance().getToken();

        Hawk.init(getApplicationContext()).build();
        if (Hawk.contains(Constants.USER_HASH)) {
            String hash = Hawk.get(Constants.USER_HASH);


            remoteAPIInterface.syncToken(token, hash).enqueue(new Callback<SimpleRequest>() {
                @Override
                public void onResponse(Call<SimpleRequest> call, Response<SimpleRequest> response) {

                }

                @Override
                public void onFailure(Call<SimpleRequest> call, Throwable t) {

                }
            });
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);


        TextView tvUser = (TextView) header.findViewById(R.id.side_name);
        TextView tvCargo = (TextView) header.findViewById(R.id.side_cargo);

        tvUser.setText(Hawk.get(Constants.USER_NAME) + "");
        tvCargo.setText(Hawk.get(Constants.USER_JOB) + "");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logged, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_exit) {

            Hawk.init(getApplicationContext()).build();
            Hawk.deleteAll();
            Hawk.destroy();
            // todo exit
            finish();

        } else if (id == R.id.nav_qr) {
            Intent i = new Intent(this, QRViewActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
