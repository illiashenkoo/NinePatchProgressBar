package com.legacycle.ninepatchprogressbar;


import android.view.animation.Animation;
import android.view.animation.Transformation;

public class NinePatchProgressBarAnimation extends Animation {

    private NinePatchProgressBar progressBar;
    private float from;
    private float to;

    public NinePatchProgressBarAnimation(NinePatchProgressBar progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
    }

}