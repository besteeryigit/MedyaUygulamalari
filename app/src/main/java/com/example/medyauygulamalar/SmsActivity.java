package com.example.medyauygulamalar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
    }

    public void smsGonderr(View view) {
        EditText phoneNumber=(EditText)findViewById(R.id.txtTel);
        String telefonNumarasi=phoneNumber.getText().toString();
        EditText mesaj=(EditText)findViewById(R.id.txtMesaj);
        String icerik=mesaj.getText().toString();
        Uri uri=Uri.parse("smsto:"+ telefonNumarasi);
        Intent i=new Intent(Intent.ACTION_SENDTO,uri);
       i.putExtra("sms_body",icerik);
        startActivity(i);
    }
}
