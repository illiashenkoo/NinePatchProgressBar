package com.legacycle.ninepatchprogressbar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.legacycle.ninepatchprogressbar.NinePatchProgressBar;
import com.legacycle.ninepatchprogressbar.NinePatchProgressBarAnimation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NinePatchProgressBar progressBar1;
    private NinePatchProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = findViewById(R.id.pb1);
        progressBar2 = findViewById(R.id.pb2);

        findViewById(R.id.progress_btn_1).setOnClickListener(this);
        findViewById(R.id.progress_btn_2).setOnClickListener(this);
        findViewById(R.id.progress_btn_3).setOnClickListener(this);
        findViewById(R.id.progress_btn_4).setOnClickListener(this);
        findViewById(R.id.progress_btn_5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.progress_btn_1:
                updateProgress(20);
                break;
            case R.id.progress_btn_2:
                updateProgress(40);
                break;
            case R.id.progress_btn_3:
                updateProgress(60);
                break;
            case R.id.progress_btn_4:
                updateProgress(80);
                break;
            case R.id.progress_btn_5:
                updateProgress(100);
                break;
        }
    }

    private void updateProgress(int progress) {
        progressBar1.setProgress(progress);

        animateProgress(progressBar2.getProgress(), progress);
    }

    private void animateProgress(int from, int to) {
        progressBar2.clearAnimation();

        NinePatchProgressBarAnimation anim = new NinePatchProgressBarAnimation(progressBar2, from, to);
        anim.setDuration(350);
        progressBar2.startAnimation(anim);
    }
}
