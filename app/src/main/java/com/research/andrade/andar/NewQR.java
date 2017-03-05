package com.research.andrade.andar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class NewQR extends AppCompatActivity {

    private Toolbar toolbar;
    private Button gen;
    private EditText name,address,others,bday;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_qr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Generate QR Code");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Database db = new Database(this);

        gen = (Button) findViewById(R.id.btnGenQR2);
        name = (EditText) findViewById(R.id.edtName);
        address = (EditText) findViewById(R.id.edtAddress);
        others = (EditText) findViewById(R.id.edtOthers);
        bday = (EditText) findViewById(R.id.edtBday);
        
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteMyQr();


                    if(name.getText().toString().trim().length()==0)
                        name.setError("Please input your name");
                    else if(address.getText().toString().trim().length()==0)
                        address.setError("Please input your address");
                    else if(bday.getText().toString().trim().length()==0)
                        bday.setError("Please input your bday");
                    else {

                        String text2Qr = name.getText().toString() +"\n"+address.getText().toString() +"\n"+bday.getText().toString() +"\n"+others.getText().toString();
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        try {
                            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                            byte[] myQRtoByte = getBytes(bitmap);

                        boolean ins = db.insertQR(myQRtoByte);

                        if (ins) {

                            Toast.makeText(NewQR.this, "inserted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(NewQR.this, MyQR.class));
                        } else
                            Toast.makeText(NewQR.this, "not inserted", Toast.LENGTH_LONG).show();

                        } catch (WriterException e) {
                            e.printStackTrace();
                        }

                    }


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NewQR.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
