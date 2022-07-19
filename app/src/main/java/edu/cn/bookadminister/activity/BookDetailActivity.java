package edu.cn.bookadminister.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

import edu.cn.bookadminister.R;
import edu.cn.bookadminister.bean.BookBean;

public class BookDetailActivity extends AppCompatActivity{

    private BookBean bookBean;
    private VideoView videoView;
    private MediaPlayer mMediaPlayer;
    boolean isPlay = false;


    TextView tvContent;
    ImageView img;
    ImageView sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bookBean = (BookBean) bundle.getSerializable("item");
        tvContent = findViewById(R.id.content);
        tvContent.setText(bookBean.getContent());

        img = findViewById(R.id.image);
        if(bookBean.isVideo()){
            img.setVisibility(View.GONE);
            playVedio();
        }else{
            img.setImageResource(bookBean.getIcon());
        }
        if(bookBean.isSound()){
            sound = findViewById(R.id.sound);
            sound.setVisibility(View.VISIBLE);
            sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlay) {
                        mMediaPlayer.pause();
                    } else {
                        mMediaPlayer.start();
                    }
                    isPlay = !isPlay;
                }
            });
            playMedia();
        }
    }

    private void playMedia() {
        try {
            mMediaPlayer = MediaPlayer.create(this,R.raw.b);
            mMediaPlayer.isLooping();
            mMediaPlayer.prepareAsync();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    private void playVedio() {
        videoView = findViewById(R.id.vv);
        videoView.setVisibility(View.VISIBLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" +getApplicationContext().getPackageName() + "/"+ R.raw.a));
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.pause();
            mMediaPlayer.release();
        }
    }
}
