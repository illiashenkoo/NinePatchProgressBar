package com.legacycle.ninepatchprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
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
    private int bgPaddingLeft;
    private int bgPaddingRight;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
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
        this.progress = progress;
        invalidate();
    }


    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        invalidate();
    }

    public int getPercentage() {
        if (max == 0) {
            return 0;
        }
        return (int) (progress * 100.0 / max);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initPaths();
    }

    private void initPaths() {
        if (bgDrawable == null) {
            bgDrawable = (NinePatchDrawable) getDrawable(R.drawable.np_bg);
        }

        if (pbDrawable == null) {
            pbDrawable = (NinePatchDrawable) getDrawable(R.drawable.np_pb);
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NinePatchProgressBar);

        max = a.getInteger(R.styleable.NinePatchProgressBar_np_max, 100);
        progress = a.getInteger(R.styleable.NinePatchProgressBar_np_progress, 0);

        bgDrawable = (NinePatchDrawable) a.getDrawable(R.styleable.NinePatchProgressBar_np_bg_drawable);
        pbDrawable = (NinePatchDrawable) a.getDrawable(R.styleable.NinePatchProgressBar_np_pb_drawable);

        bgPaddingTop = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_top, 0);
        bgPaddingBottom = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_bottom, 0);
        bgPaddingLeft = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_left, 0);
        bgPaddingRight = a.getDimensionPixelSize(R.styleable.NinePatchProgressBar_np_bg_padding_right, 0);

        a.recycle();
    }

    private void drawProgress(Canvas canvas) {
        int width = getWidth();
        if (width % 2 != 0) {
            width = width - 1;
        }

        float percent = getPercent();

        int progressHeight = getHeight() - padding * 2;
        if (progressHeight % 2 != 0) {
            progressHeight = progressHeight - 1;
        }

        int progressWidth = width - padding * 2;
        float dx = progressWidth * percent;

        int right = (int) (padding + dx);
        int bottom = padding + progressHeight;

        Rect npdBounds = new Rect(0, padding, right, bottom);
        pbDrawable.setBounds(npdBounds);
        pbDrawable.draw(canvas);
    }

    private void drawBackground(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();

        int right = width - bgPaddingRight;
        int bottom = height - bgPaddingBottom;

        Rect npdBounds = new Rect(bgPaddingLeft, bgPaddingTop, right, bottom);

        bgDrawable.setBounds(npdBounds);
        bgDrawable.draw(canvas);
    }

    private Drawable getDrawable(int id) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getContext().getDrawable(id);
        } else {
            Resources resources = getContext().getResources();
            return resources.getDrawable(id);
        }
    }

    private float getPercent() {
        return max == 0 ? 0F : progress * 1.0F / max;
    }

}