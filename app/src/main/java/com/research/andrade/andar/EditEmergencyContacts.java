package com.research.andrade.andar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditEmergencyContacts extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText dispNameEdt, contNumEdt;
    private Button saveBtn, delBtn, cancBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergency_contacts);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edit Emergency Contacts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dispNameEdt = (EditText) findViewById(R.id.edtDispName);
        contNumEdt = (EditText) findViewById(R.id.edtContNum);
        saveBtn = (Button) findViewById(R.id.btnSaveCont);
        delBtn = (Button) findViewById(R.id.btnDelCont);
        cancBtn = (Button) findViewById(R.id.btnCancelCont);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String[] dataSplit = data.split("\n\t");
        String data1 = dataSplit[0];
        String data2 = dataSplit[1];

        dispNameEdt.setText(data1);
        contNumEdt.setText(data2);

        cancBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditEmergencyContacts.this.finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            EditEmergencyContacts.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
