package com.example.medyauygulamalar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class FotografVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotograf_video);
    }

    public void fotografcek(View view) {

        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        ImageView imgView=(ImageView)findViewById(R.id.imageView);
        imgView.setImageBitmap(bitmap);
        break;
            case 1:
                VideoView videoView=(VideoView)findViewById(R.id.videoView);
                videoView.setVideoURI(data.getData());
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.start();
                break;
    }}

    public void videoCek(View view) {
        Intent i=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(i,1);
    }




}
