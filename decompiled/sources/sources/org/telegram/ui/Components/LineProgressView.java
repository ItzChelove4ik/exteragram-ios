package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes3.dex */
public class LineProgressView extends LinearProgressIndicator {
    private static DecelerateInterpolator decelerateInterpolator;
    private static Paint progressPaint;
    private float animatedAlphaValue;
    private float animatedProgressValue;
    private float animationProgressStart;
    private int backColor;
    private CellFlickerDrawable cellFlickerDrawable;
    private float currentProgress;
    private long currentProgressTime;
    private long lastUpdateTime;
    private int progressColor;
    private final RectF rect;
    public int type;

    public LineProgressView(Context r2) {
        this(r2, null);
    }

    public LineProgressView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.linearProgressIndicatorStyle);
    }

    public LineProgressView(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.animatedAlphaValue = 1.0f;
        this.rect = new RectF();
        if (decelerateInterpolator != null) goto L5;
        decelerateInterpolator = new DecelerateInterpolator();
        Paint r12 = new Paint(1);
        progressPaint = r12;
        r12.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
    L5:
        init();
    }

    private void init() {
        setMax(1000);
        setTrackThickness(AndroidUtilities.dp(2.0f));
        setTrackCornerRadius(AndroidUtilities.dp(1.0f));
        setTrackStopIndicatorSize(AndroidUtilities.dp(2.0f));
        setIndicatorTrackGapSize(AndroidUtilities.dp(2.0f));
        setTrackCornerRadiusFraction(0.5f);
        setIndeterminate(false);
        setProgressType(0);
    }

    private void updateLegacyAnimation() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        float r02 = this.animatedProgressValue;
        if (r02 == 1.0f) goto L13;
        float r4 = this.currentProgress;
        if (r02 == r4) goto L13;
        float r03 = this.animationProgressStart;
        float r6 = r4 - r03;
        if (r6 <= 0.0f) goto L12;
        long r7 = this.currentProgressTime + r2;
        this.currentProgressTime = r7;
        if (r7 < 300) goto L11;
        this.animatedProgressValue = r4;
        this.animationProgressStart = r4;
        this.currentProgressTime = 0;
        goto L12
    L11:
        this.animatedProgressValue = r03 + (r6 * decelerateInterpolator.getInterpolation(r7 / 300.0f));
    L12:
        invalidate();
    L13:
        float r04 = this.animatedProgressValue;
        if (r04 >= 1.0f) goto L16;
        return;
    L16:
        if (r04 != 1.0f) goto L25;
        float r05 = this.animatedAlphaValue;
        if (r05 == 0.0f) goto L26;
        float r06 = r05 - (r2 / 200.0f);
        this.animatedAlphaValue = r06;
        if (r06 > 0.0f) goto L22;
        this.animatedAlphaValue = 0.0f;
    L22:
        invalidate();
        return;
    L26:
        return;
    }

    public float getCurrentProgress() {
        return this.currentProgress;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas r8) {
        if (ExteraConfig.getNewLoadingStyle() == false) goto L6;
        super.onDraw(r8);
        return;
    L6:
        int r0 = this.backColor;
        if (r0 != 0) goto L9;
    L11:
        progressPaint.setColor(this.progressColor);
        progressPaint.setAlpha((int) (this.animatedAlphaValue * 255.0f));
        this.rect.set(0.0f, 0.0f, getWidth() * this.animatedProgressValue, getHeight());
        r8.drawRoundRect(this.rect, getHeight() / 2.0f, getHeight() / 2.0f, progressPaint);
        if (this.animatedAlphaValue > 0.0f) goto L14;
    L17:
        updateLegacyAnimation();
        return;
    L14:
        if (this.cellFlickerDrawable != null) goto L16;
        CellFlickerDrawable r02 = new CellFlickerDrawable(160, 0);
        this.cellFlickerDrawable = r02;
        r02.drawFrame = false;
        r02.animationSpeedScale = 0.8f;
        r02.repeatProgress = 1.2f;
    L16:
        this.cellFlickerDrawable.setParentWidth(getMeasuredWidth());
        this.cellFlickerDrawable.draw(r8, this.rect, getHeight() / 2.0f, null);
        invalidate();
        goto L17
    L9:
        if (this.animatedProgressValue == 1.0f) goto L11;
        progressPaint.setColor(r0);
        progressPaint.setAlpha((int) (this.animatedAlphaValue * 255.0f));
        this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
        r8.drawRoundRect(this.rect, getHeight() / 2.0f, getHeight() / 2.0f, progressPaint);
        goto L11
    }

    public void setBackColor(int r2) {
        this.backColor = r2;
        if (ExteraConfig.getNewLoadingStyle() == false) goto L6;
        setTrackColor(r2);
        return;
    }

    public void setProgress(float r2, boolean r3) {
        this.currentProgress = r2;
        if (ExteraConfig.getNewLoadingStyle() == false) goto L6;
        setProgressCompat((int) (r2 * getMax()), r3);
        return;
    L6:
        if (r3 == true) goto L8;
        this.animatedProgressValue = r2;
        this.animationProgressStart = r2;
    L10:
        if (r2 == 1.0f) goto L12;
        this.animatedAlphaValue = 1.0f;
    L12:
        this.currentProgressTime = 0;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
        return;
    L8:
        this.animationProgressStart = this.animatedProgressValue;
        goto L10
    }

    public void setProgressColor(int r2) {
        this.progressColor = r2;
        if (ExteraConfig.getNewLoadingStyle() == false) goto L6;
        setIndicatorColor(new int[]{r2});
        return;
    }

    public void setProgressType(int r3) {
        if (ExteraConfig.getNewLoadingStyle() == true) goto L7;
        this.type = 0;
        return;
    L7:
        if (this.type == r3) goto L18;
        this.type = r3;
        if (r3 != 0) goto L14;
        setWaveSpeed(0);
        setWavelength(0);
        setWaveAmplitude(0);
        return;
    L14:
        if (r3 != 1) goto L17;
        setWavelengthDeterminate(AndroidUtilities.dp(40.0f));
        setWaveAmplitude(AndroidUtilities.dp(3.0f));
        setWaveSpeed(AndroidUtilities.dp(15.0f));
        setWaveAmplitudeRampProgressMin(0.05f);
        return;
    L17:
        return;
    }
}
