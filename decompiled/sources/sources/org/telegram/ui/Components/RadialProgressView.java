package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Keep;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.loadingindicator.LoadingIndicator;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class RadialProgressView extends View {
    private AccelerateInterpolator accelerateInterpolator;
    private float animatedProgress;
    private RectF cicleRect;
    private float currentCircleLength;
    private float currentProgress;
    private float currentProgressTime;
    private int currentStyle;
    private DecelerateInterpolator decelerateInterpolator;
    private float drawingCircleLenght;
    private long lastUpdateTime;
    private CircularProgressIndicator m3CircularProgressIndicator;
    private Drawable m3Drawable;
    private LoadingIndicator m3IndicatorView;
    private boolean noProgress;
    private float progressAnimationStart;
    private int progressColor;
    private Paint progressPaint;
    private int progressTime;
    private float radOffset;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean risingCircleLength;
    private int size;
    private boolean toCircle;
    private float toCircleProgress;
    private boolean useSelfAlpha;

    public RadialProgressView(Context r2) {
        this(r2, null);
    }

    public RadialProgressView(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.currentStyle = 0;
        this.cicleRect = new RectF();
        this.noProgress = true;
        this.resourcesProvider = r2;
        this.size = AndroidUtilities.dp(40.0f);
        this.progressColor = getThemedColor(Theme.key_progressCircle);
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.accelerateInterpolator = new AccelerateInterpolator();
        Paint r22 = new Paint(1);
        this.progressPaint = r22;
        r22.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.progressPaint.setColor(this.progressColor);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void setM3Visible(boolean r1, boolean r2) {
        Drawable r0 = this.m3Drawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r2 == false) goto L8;
        if (r1 == false) goto L8;
        boolean r22 = true;
    L9:
        r0.setVisible(r1, r22);
        return;
    L8:
        r22 = false;
        goto L9
    }

    private void updateAnimation() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        if (r2 <= 17) goto L5;
        r2 = 17;
    L5:
        this.lastUpdateTime = r0;
        updateAnimation(r2);
    }

    private void updateAnimation(long r9) {
        this.radOffset = (this.radOffset + ((360 * r9) / 2000.0f)) - (((int) (r0 / 360.0f)) * 360);
        boolean r0 = this.toCircle;
        if (r0 == false) goto L9;
        float r4 = this.toCircleProgress;
        if (r4 == 1.0f) goto L9;
        float r42 = r4 + 0.07272727f;
        this.toCircleProgress = r42;
        if (r42 <= 1.0f) goto L16;
        this.toCircleProgress = 1.0f;
    L16:
        if (this.noProgress == true) goto L18;
        float r02 = this.currentProgress;
        float r2 = this.progressAnimationStart;
        float r5 = r02 - r2;
        if (r5 <= 0.0f) goto L46;
        int r92 = (int) (this.progressTime + r9);
        this.progressTime = r92;
        if (r92 < 200.0f) goto L45;
        this.progressAnimationStart = r02;
        this.animatedProgress = r02;
        this.progressTime = 0;
        goto L46
    L45:
        this.animatedProgress = r2 + (r5 * AndroidUtilities.decelerateInterpolator.getInterpolation(r92 / 200.0f));
    L46:
        this.currentCircleLength = Math.max(4.0f, this.animatedProgress * 360.0f);
    L47:
        invalidate();
        return;
    L18:
        if (this.toCircleProgress != 0.0f) goto L32;
        float r03 = this.currentProgressTime + r9;
        this.currentProgressTime = r03;
        if (r03 < 500.0f) goto L23;
        this.currentProgressTime = 500.0f;
    L23:
        if (this.risingCircleLength == false) goto L25;
        this.currentCircleLength = (this.accelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f) * 266.0f) + 4.0f;
    L27:
        if (this.currentProgressTime != 500.0f) goto L47;
        boolean r93 = this.risingCircleLength;
        if (r93 == false) goto L31;
        this.radOffset += 270.0f;
        this.currentCircleLength = -266.0f;
    L31:
        this.risingCircleLength = !r93;
        this.currentProgressTime = 0.0f;
        goto L47
    L25:
        this.currentCircleLength = 4.0f - ((1.0f - this.decelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f)) * 270.0f);
        goto L27
    L32:
        boolean r94 = this.risingCircleLength;
        float r10 = this.currentCircleLength;
        if (r94 == false) goto L37;
        float r95 = ((this.accelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f) * 266.0f) + 4.0f) + (this.toCircleProgress * 360.0f);
        this.currentCircleLength = r95;
        if ((r10 - r95) <= 0.0f) goto L47;
        this.radOffset += r10 - r95;
        goto L47
    L37:
        float r43 = (4.0f - ((1.0f - this.decelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f)) * 270.0f)) - (this.toCircleProgress * 364.0f);
        this.currentCircleLength = r43;
        if ((r10 - r43) <= 0.0f) goto L47;
        this.radOffset += r10 - r43;
    L9:
        if (r0 == true) goto L16;
        float r04 = this.toCircleProgress;
        if (r04 == 0.0f) goto L16;
        float r05 = r04 - 0.04f;
        this.toCircleProgress = r05;
        if (r05 >= 0.0f) goto L16;
        this.toCircleProgress = 0.0f;
        goto L16
    }

    public void draw(Canvas r12, float r13, float r14) {
        if (this.currentStyle == 0) goto L8;
        Drawable r0 = this.m3Drawable;
        if (r0 == null) goto L8;
        int r2 = this.size;
        int r132 = (int) (r13 - (r2 / 2.0f));
        int r142 = (int) (r14 - (r2 / 2.0f));
        r0.setBounds(r132, r142, r132 + r2, r2 + r142);
        this.m3Drawable.draw(r12);
        return;
    L8:
        RectF r02 = this.cicleRect;
        int r22 = this.size;
        r02.set(r13 - (r22 / 2.0f), r14 - (r22 / 2.0f), r13 + (r22 / 2.0f), r14 + (r22 / 2.0f));
        RectF r6 = this.cicleRect;
        float r7 = this.radOffset;
        float r8 = this.currentCircleLength;
        this.drawingCircleLenght = r8;
        r12.drawArc(r6, r7, r8, false, this.progressPaint);
        updateAnimation();
    }

    public boolean isCircle() {
        if (Math.abs(this.drawingCircleLenght) < 360.0f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isMaterial3ProgressStyle() {
        int r1 = this.currentStyle;
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 == 3) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        setM3Visible(false, false);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        if (this.currentStyle != 0) goto L5;
    L8:
        this.cicleRect.set((getMeasuredWidth() - this.size) / 2, (getMeasuredHeight() - this.size) / 2, r0 + r2, r1 + r2);
        RectF r7 = this.cicleRect;
        float r8 = this.radOffset;
        float r9 = this.currentCircleLength;
        this.drawingCircleLenght = r9;
        r13.drawArc(r7, r8, r9, false, this.progressPaint);
        updateAnimation();
        return;
    L5:
        if (this.m3Drawable == null) goto L8;
        int r0 = (getMeasuredWidth() - this.size) / 2;
        int r1 = getMeasuredHeight();
        int r2 = this.size;
        int r12 = (r1 - r2) / 2;
        this.m3Drawable.setBounds(r0, r12, r0 + r2, r2 + r12);
        this.m3Drawable.draw(r13);
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean r2) {
        super.onVisibilityAggregated(r2);
        setM3Visible(r2, false);
    }

    @Override // android.view.View
    @Keep
    public void setAlpha(float r3) {
        super.setAlpha(r3);
        if (this.useSelfAlpha == false) goto L11;
        Drawable r0 = getBackground();
        int r32 = (int) (r3 * 255.0f);
        if (r0 == null) goto L7;
        r0.setAlpha(r32);
    L7:
        this.progressPaint.setAlpha(r32);
        Drawable r2 = this.m3Drawable;
        if (r2 == null) goto L12;
        r2.setAlpha(r32);
        return;
    L12:
        return;
    }

    public void setNoProgress(boolean r1) {
        this.noProgress = r1;
    }

    public void setProgress(float r2) {
        this.currentProgress = r2;
        if (this.animatedProgress <= r2) goto L5;
        this.animatedProgress = r2;
    L5:
        this.progressAnimationStart = this.animatedProgress;
        this.progressTime = 0;
    }

    public void setProgressColor(int r2) {
        this.progressColor = r2;
        this.progressPaint.setColor(r2);
        LoadingIndicator r22 = this.m3IndicatorView;
        if (r22 == null) goto L5;
        r22.setIndicatorColor(new int[]{this.progressColor});
    L5:
        CircularProgressIndicator r23 = this.m3CircularProgressIndicator;
        if (r23 == null) goto L9;
        r23.setIndicatorColor(new int[]{this.progressColor});
        return;
    }

    public void setSize(int r2) {
        this.size = r2;
        CircularProgressIndicator r0 = this.m3CircularProgressIndicator;
        if (r0 == null) goto L5;
        r0.setIndicatorSize(r2);
    L5:
        invalidate();
    }

    @Keep
    public void setSpecValues(int r2, int r3, int r4, int r5, int r6, int r7) {
        if (isMaterial3ProgressStyle() == true) goto L5;
        return;
    L5:
        this.m3CircularProgressIndicator.setIndicatorSize(r2);
        this.m3CircularProgressIndicator.setTrackThickness(r3);
        this.m3CircularProgressIndicator.setTrackCornerRadius(r4);
        this.m3CircularProgressIndicator.setIndicatorTrackGapSize(r5);
        this.m3CircularProgressIndicator.setIndicatorColor(new int[]{r6});
        this.m3CircularProgressIndicator.setTrackColor(r7);
    }

    public void setStrokeWidth(float r3) {
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(r3));
        CircularProgressIndicator r2 = this.m3CircularProgressIndicator;
        if (r2 == null) goto L6;
        r2.setTrackThickness(AndroidUtilities.dp(r3));
        return;
    }

    public void setStyle(int r8) {
        if (ExteraConfig.getNewLoadingStyle() == true) goto L8;
        if (r8 == 0) goto L8;
        if (r8 == 1) goto L8;
        r8 = 0;
    L8:
        if (this.currentStyle == r8) goto L37;
        Drawable r0 = this.m3Drawable;
        this.currentStyle = r8;
        if (r8 != 1) goto L16;
        if (this.m3IndicatorView != null) goto L14;
        this.m3IndicatorView = new LoadingIndicator(getContext());
    L14:
        this.m3IndicatorView.setIndicatorColor(new int[]{this.progressColor});
        this.m3Drawable = this.m3IndicatorView.getDrawable();
    L28:
        if (r0 != null) goto L30;
    L32:
        Drawable r82 = this.m3Drawable;
        if (r82 == null) goto L35;
        r82.setCallback(this);
        setM3Visible(isShown(), true);
    L35:
        invalidate();
        return;
    L30:
        if (r0 == this.m3Drawable) goto L32;
        r0.setVisible(false, false);
        r0.setCallback(null);
        goto L32
    L16:
        if (r8 == 2) goto L21;
        if (r8 == 3) goto L21;
        this.m3Drawable = null;
    L21:
        if (this.m3CircularProgressIndicator != null) goto L23;
        CircularProgressIndicator r83 = new CircularProgressIndicator(getContext());
        this.m3CircularProgressIndicator = r83;
        r83.setIndeterminate(true);
    L23:
        this.m3CircularProgressIndicator.setIndicatorColor(new int[]{this.progressColor});
        this.m3CircularProgressIndicator.setIndicatorSize(this.size);
        this.m3CircularProgressIndicator.setTrackThickness((int) this.progressPaint.getStrokeWidth());
        this.m3CircularProgressIndicator.setTrackCornerRadius(AndroidUtilities.dp(2.0f));
        this.m3CircularProgressIndicator.setIndicatorTrackGapSize(AndroidUtilities.dp(2.0f));
        if (this.currentStyle != 3) goto L26;
        boolean r84 = true;
    L27:
        setWavy(r84);
        this.m3Drawable = this.m3CircularProgressIndicator.getIndeterminateDrawable();
        goto L28
    L26:
        r84 = false;
        goto L27
    }

    public void setTrackColor(int r1) {
        CircularProgressIndicator r0 = this.m3CircularProgressIndicator;
        if (r0 == null) goto L6;
        r0.setTrackColor(r1);
        return;
    }

    public void setUseSelfAlpha(boolean r1) {
        this.useSelfAlpha = r1;
    }

    @Keep
    public void setWavy(boolean r4) {
        CircularProgressIndicator r0 = this.m3CircularProgressIndicator;
        if (r4 == false) goto L6;
        r0.setIndicatorInset(0);
        setWavyValues(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(1.6f), AndroidUtilities.dp(5.0f), 0.05f);
        return;
    L6:
        r0.setIndicatorInset(0);
        setWavyValues(0, 0, 0, 1.0f);
    }

    public void setWavyValues(int r3, int r4, int r5, float r6) {
        if (this.currentStyle == 3) goto L5;
        return;
    L5:
        this.m3CircularProgressIndicator.setWavelengthIndeterminate(r3);
        this.m3CircularProgressIndicator.setWaveAmplitude(r4);
        this.m3CircularProgressIndicator.setWaveSpeed(r5);
        this.m3CircularProgressIndicator.setWaveAmplitudeRampProgressMin(r6);
    }

    public void sync(RadialProgressView r3) {
        this.lastUpdateTime = r3.lastUpdateTime;
        this.radOffset = r3.radOffset;
        this.toCircle = r3.toCircle;
        this.toCircleProgress = r3.toCircleProgress;
        this.noProgress = r3.noProgress;
        this.currentCircleLength = r3.currentCircleLength;
        this.drawingCircleLenght = r3.drawingCircleLenght;
        this.currentProgressTime = r3.currentProgressTime;
        this.currentProgress = r3.currentProgress;
        this.progressTime = r3.progressTime;
        this.animatedProgress = r3.animatedProgress;
        this.risingCircleLength = r3.risingCircleLength;
        this.progressAnimationStart = r3.progressAnimationStart;
        updateAnimation(85);
    }

    public void toCircle(boolean r1, boolean r2) {
        this.toCircle = r1;
        if (r2 == true) goto L9;
        if (r1 == false) goto L6;
        float r12 = 1.0f;
    L7:
        this.toCircleProgress = r12;
        return;
    L6:
        r12 = 0.0f;
        goto L7
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.m3Drawable) goto L7;
        return true;
    L7:
        return super.verifyDrawable(r2);
    }
}
