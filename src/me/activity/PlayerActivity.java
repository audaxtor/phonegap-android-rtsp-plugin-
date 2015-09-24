package me.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;
import android.widget.VideoView;

import com.phonegap.helloworld.R;

public class PlayerActivity extends Activity {
    private VideoView videoView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.player);
        Intent intent = getIntent();
        String uri_str = intent.getStringExtra("uri");
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(uri_str));
        progressDialog = ProgressDialog.show(this,"",getString(R.string.loading),true);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK&&!event.isCanceled()) {
                    if (progressDialog.isShowing()) {
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoView.start();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                progressDialog.dismiss();
                Toast.makeText(PlayerActivity.this,R.string.loading_failed,Toast.LENGTH_LONG).show();
                finish();
                return true;
            }
        });
    }
}



