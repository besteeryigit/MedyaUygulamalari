package com.example.medyauygulamalar;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SesActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnKayit;
    Button btnDur;
    Button btnDinle;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private final String filepath= Environment.getExternalStorageDirectory().getPath() + "record.3gp";
    private static final int REQUEST_AUDIO_PERMISSION_CODE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses);
        btnKayit=(Button)findViewById(R.id.btnRecord);
        btnDur=(Button)findViewById(R.id.btnStop);
        btnDinle=(Button)findViewById(R.id.btnListen);
        btnKayit.setOnClickListener(this);
        btnDur.setOnClickListener(this);
        btnDinle.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if(v==btnKayit)
        {
            if(checkPermission())
            {
                startRecording();
            }
            else
            {
                requestPermissions();
                startRecording();
            }
        }
        else if(v==btnDur)
        {
            stopRecording();
        }
        else if(v==btnDinle)
        {
            startPlaying();
        }


    }



    private void startRecording() {
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filepath);

        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(this,"Kayıt yapılıyor...",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    private void stopRecording() {
        if(recorder != null)
        {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder=null;
            Toast.makeText(getApplicationContext(),"Kayıt Durduruldu",Toast.LENGTH_LONG).show();

        }


    }
    private void startPlaying() {
        player=new MediaPlayer();
        player.setVolume(1.0f,1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(this,"Kayıt Çalıyor...",Toast.LENGTH_SHORT).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    player.release();
                    player=null;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }






    }
    private boolean checkPermission() {
        int result= ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int result1= ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);
        boolean sonuc=( result== PackageManager.PERMISSION_GRANTED && result1 ==PackageManager.PERMISSION_GRANTED);
        return sonuc;

    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(SesActivity.this,new String[]{RECORD_AUDIO,WRITE_EXTERNAL_STORAGE},REQUEST_AUDIO_PERMISSION_CODE);



    }

    public void onRequestPermissionResult(int requestCode, String[] permissions,int[] grantResults) {
        switch (requestCode){
            case REQUEST_AUDIO_PERMISSION_CODE:
                if(grantResults.length>0)
                {
                    boolean permissionToRecord= grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(permissionToRecord && permissionToStore)
                    {
                        Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }

    }


}
