package com.felkertech.n.cumulustv.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.felkertech.channelsurfer.players.TvInputPlayer;
import com.felkertech.channelsurfer.players.WebInputPlayer;
import com.felkertech.n.cumulustv.R;
import com.felkertech.n.recievers.NotificacionReceiver;
import com.google.android.exoplayer.ExoPlaybackException;

import java.net.URL;

/**
 * Created by Nick on 7/12/2015.
 */
public class CumulusTvPlayer extends AppCompatActivity implements NotificacionReceiver.OnNotificationListener {
    private String urlStream;
    private VideoView myVideoView;
    private URL url;
    private String TAG = "cumulus:CumulusTvPlayer";
    public static final String KEY_VIDEO_URL = "VIDEO_URL";
    private TvInputPlayer exoPlayer;
    NotificacionReceiver receiver;

    BaseCardView card;
    TextView msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Doing it the native way */


        Intent parameters = getIntent();
        if(parameters == null) {
            setContentView(R.layout.fullvideo);//***************
            myVideoView = (VideoView) this.findViewById(R.id.myVideoView);
            MediaController mc = new MediaController(this);
            myVideoView.setMediaController(mc);
            String ABCNews = "http://abclive.abcnews.com/i/abc_live4@136330/index_1200_av-b.m3u8";
            String Brazil = "http://stream331.overseebrasil.com.br/live_previd_155/_definst_/live_previd_155/playlist.m3u8";
            String NASA = "http://www.nasa.gov/multimedia/nasatv/NTV-Public-IPS.m3u8";
            urlStream = ABCNews;
            Log.d(TAG, "About to open " + urlStream.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "On UI Thread");
                    myVideoView.setVideoURI(Uri.parse(urlStream));
                    Log.d(TAG, "Now play");
                    myVideoView.start();
                }
            });
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getSupportActionBar().hide();
            final String url = parameters.getStringExtra(KEY_VIDEO_URL);
            if(!url.isEmpty()) {
                setContentView(R.layout.full_surfaceview);
                SurfaceView sv = (SurfaceView) findViewById(R.id.surface);
                exoPlayer = new TvInputPlayer();
                exoPlayer.setSurface(sv.getHolder().getSurface());
                exoPlayer.setVolume(1);
                exoPlayer.addCallback(new TvInputPlayer.Callback() {
                    @Override
                    public void onPrepared() {

                    }

                    @Override
                    public void onPlayerStateChanged(boolean playWhenReady, int state) {

                    }

                    @Override
                    public void onPlayWhenReadyCommitted() {

                    }

                    @Override
                    public void onPlayerError(ExoPlaybackException e) {
                        Log.e(TAG, "Callback2");
                        Log.e(TAG, e.getMessage()+"");
                        if(e.getMessage().contains("Extractor")) {
                            Log.d(TAG, "Cannot play the stream, try loading it as a website");
                            Toast.makeText(CumulusTvPlayer.this, "This is not a video stream, interpreting as a website", Toast.LENGTH_SHORT).show();
                            WebInputPlayer wv = new WebInputPlayer(CumulusTvPlayer.this, new WebInputPlayer.WebViewListener() {
                                @Override
                                public void onPageFinished() {
                                    //Don't do anything
                                }
                            });
                            wv.load(url);
                            setContentView(wv);
                        }
                    }

                    @Override
                    public void onDrawnToSurface(Surface surface) {

                    }

                    @Override
                    public void onText(String text) {

                    }
                });
                try {
                    exoPlayer.prepare(getApplicationContext(), Uri.parse(url), TvInputPlayer.SOURCE_TYPE_HLS);
                } catch(Exception e) {

                }
                exoPlayer.setPlayWhenReady(true);
            }
        }

        Toast.makeText(this, "Entro a TVPlayer",Toast.LENGTH_SHORT).show();
        receiver = new NotificacionReceiver(this);

        IntentFilter filter = new IntentFilter(NotificacionReceiver.ACTION);
        registerReceiver(receiver, filter);

        card = (BaseCardView) findViewById(R.id.card);
        msg = (TextView) findViewById(R.id.msg);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        exoPlayer.stop();
        exoPlayer.release();
    }

    @Override
    public void onNotification(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        this.msg.setText(msg);
        card.setVisibility(View.VISIBLE);

        ObjectAnimator animatorMostrar = ObjectAnimator.ofFloat(card, "alpha",0.0f,1.0f);
        animatorMostrar.setDuration(500);

        ObjectAnimator animatorOcultar = ObjectAnimator.ofFloat(card, "alpha",1.0f,0.0f);
        animatorOcultar.setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.play(animatorMostrar).before(animatorOcultar);
        set.play(animatorOcultar).after(2000);
        set.start();
    }
}
