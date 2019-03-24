package com.example.medyauygulamalar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AramaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);
    }

    public void aramaYapp(View view) {
        EditText editText=(EditText)findViewById(R.id.txtArama);
        String phoneNumber=editText.getText().toString();
        Intent i=new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+ phoneNumber));
        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }



    }
}
