package com.legacycle.ninepatchprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NinePatchProgressBar extends View {

    private int max;
    private int progress;

    private int padding = 0;

    private NinePatchDrawable bgDrawable;
    private NinePatchDrawable pbDrawable;

    private int bgPaddingTop;
    private int bgPaddingBottom;

    public NinePatchProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public NinePatchProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NinePatchProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initPaths();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NinePatchProgressBar);
        max = a.getInteger(R.styleable.NinePatchProgressBar_np_max, 100);
        progress = a.getInteger(R.styleable.NinePatchProgressBar_np_progress, 0);

        bgDrawable = (NinePatchDrawable) a.getDrawable(R.styleable.NinePatchProgressBar_np_bg_drawable);
        pbDrawable = (NinePatchDrawable) a.getDrawable(R.styleable.NinePatchProgressBar_np_pb_drawable);

        bgPaddingTop = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_top, 0);
        bgPaddingBottom = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_bottom, 0);
        a.recycle();
    }

    private void initPaths() {
        if (bgDrawable == null) {
            bgDrawable = (NinePatchDrawable) getDrawable(R.drawable.np_bg);
        }

        if (pbDrawable == null) {
            pbDrawable = (NinePatchDrawable) getDrawable(R.drawable.np_pb);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
    }

    private void drawProgress(Canvas canvas) {
        int width = getWidth();
        if (width % 2 != 0) {
            //Fix Me
            width = width - 1;
        }
        float percent = 0;
        if (max != 0) {
            percent = progress * 1.0f / max;
        }
        int progressHeight = getHeight() - padding * 2;
        if (progressHeight % 2 != 0) {
            progressHeight = progressHeight - 1;
        }
        int progressWidth = width - padding * 2 - progressHeight;
        float dx = progressWidth * percent;

        Rect npdBounds = new Rect(0, padding, (int) (padding + progressHeight / 2 + dx), padding + progressHeight);
        pbDrawable.setBounds(npdBounds);
        pbDrawable.draw(canvas);
    }

    private void drawBackground(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();

        Rect npdBounds = new Rect(0, bgPaddingTop, width, height - bgPaddingBottom);
        bgDrawable.setBounds(npdBounds);
        bgDrawable.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
//        Timber.d("progress: %s ", progress);
        this.progress = progress;
        invalidate();
    }

//    public int getBgColor() {
//        return bgColor;
//    }
//
//    public void setBgColor(int bgColor) {
//        this.bgColor = bgColor;
//        bgPaint.setColor(bgColor);
//        invalidate();
//    }
//
//    public int getProgressColor() {
//        return progressColor;
//    }
//
//    public void setProgressColor(int progressColor) {
//        this.progressColor = progressColor;
//        progressPaint.setColor(progressColor);
//        invalidate();
//    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        invalidate();
    }

    /**
     * get the percentage value of progress and max.
     *
     * @return percentage value
     */
    public int getPercentage() {
        if (max == 0) {
            return 0;
        }
        return (int) (progress * 100.0 / max);
    }

    @Nullable
    public final Drawable getDrawable(@DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getContext().getDrawable(id);
        } else {
            Resources resources = getContext().getResources();
            return resources.getDrawable(id);
        }
    }

}