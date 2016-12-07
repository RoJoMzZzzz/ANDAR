package com.research.andrade.andar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MessageContact extends AppCompatActivity {

    private Toolbar toolbar;
    TextView longi, lati;
    private Button fetch, immediately, possible, ok;
    private TrackGPS gps;
    double longitude, latitude;
    private String message, latis, longis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_contact);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Message Contacts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        longi = (TextView) findViewById(R.id.txtLong);
        lati = (TextView) findViewById(R.id.txtLat);
        fetch = (Button) findViewById(R.id.btnFetch);
        immediately = (Button) findViewById(R.id.btnImmediately);
        possible = (Button) findViewById(R.id.btnPossible);
        ok = (Button) findViewById(R.id.btnOk);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchClick();
            }
        });

        immediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = immediately.getText().toString();
                sendSMS();
            }
        });

        possible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = possible.getText().toString();
                sendSMS();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = ok.getText().toString();
                sendSMS();
            }
        });


    }

    private void fetchClick(){
        gps = new TrackGPS(MessageContact.this);
        if(gps.canGetLocation()){
            longitude = gps.getLongitude();
            latitude = gps .getLatitude();
            lati.setText("Latitude: "+latitude);
            longi.setText("Longitude: "+longitude);
        }
        else
            gps.showSettingsAlert();

    }

    private void sendSMS(){
        String fMess = message+" \nMy Coordinates are Latitude : "+latitude+" and Longitude : "+longitude;
//        Snackbar.make(findViewById(android.R.id.content), ""+fMess, Snackbar.LENGTH_LONG).show();
        Toast.makeText(this, fMess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            MessageContact.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
