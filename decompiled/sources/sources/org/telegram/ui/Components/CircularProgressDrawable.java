package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.loadingindicator.LoadingIndicator;
import com.google.android.material.loadingindicator.LoadingIndicatorDrawable;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import com.google.android.material.progressindicator.IndeterminateDrawable;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class CircularProgressDrawable extends Drawable implements Drawable.Callback {
    public static final FastOutSlowInInterpolator interpolator = null;
    private float angleOffset;
    private final RectF bounds;
    private IndeterminateDrawable<CircularProgressIndicatorSpec> circularIndicatorDrawable;
    private CircularProgressIndicatorSpec circularIndicatorSpec;
    private int currentColor;
    private int currentStyle;
    private long lastDrawTime;
    private Drawable m3Drawable;
    private LoadingIndicator m3IndicatorView;
    private final Paint paint;
    private float[] segment;
    public float size;
    private long start;
    public float thickness;
    private int trackColor;
    private boolean updatingM3Visibility;

    static {
        interpolator = new FastOutSlowInInterpolator();
    }

    public CircularProgressDrawable() {
        this(-1);
    }

    public CircularProgressDrawable(float r3, float r4, int r5) {
        this.currentStyle = 0;
        this.size = AndroidUtilities.dp(18.0f);
        this.thickness = AndroidUtilities.dp(2.25f);
        this.start = -1;
        this.segment = new float[2];
        Paint r0 = new Paint();
        this.paint = r0;
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeCap(Paint.Cap.ROUND);
        r0.setStrokeJoin(Paint.Join.ROUND);
        this.bounds = new RectF();
        this.size = r3;
        this.thickness = r4;
        this.currentColor = r5;
        setStyle(1, AndroidUtilities.getActivity());
        setColor(r5);
    }

    public CircularProgressDrawable(float r4, float r5, int r6, int r7) {
        this.currentStyle = 0;
        this.size = AndroidUtilities.dp(18.0f);
        this.thickness = AndroidUtilities.dp(2.25f);
        this.start = -1;
        this.segment = new float[2];
        Paint r1 = new Paint();
        this.paint = r1;
        r1.setStyle(Paint.Style.STROKE);
        r1.setStrokeCap(Paint.Cap.ROUND);
        r1.setStrokeJoin(Paint.Join.ROUND);
        this.bounds = new RectF();
        this.size = r4;
        this.thickness = r5;
        this.trackColor = r6;
        this.currentColor = r7;
        setStyle(2, AndroidUtilities.getActivity());
        setColor(r7);
        setTrackColor(r6);
    }

    public CircularProgressDrawable(int r3) {
        this.currentStyle = 0;
        this.size = AndroidUtilities.dp(18.0f);
        this.thickness = AndroidUtilities.dp(2.25f);
        this.start = -1;
        this.segment = new float[2];
        Paint r0 = new Paint();
        this.paint = r0;
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeCap(Paint.Cap.ROUND);
        r0.setStrokeJoin(Paint.Join.ROUND);
        this.bounds = new RectF();
        this.currentColor = r3;
        setStyle(1, AndroidUtilities.getActivity());
        setColor(r3);
    }

    private void ensureManualDrawVisible() {
        if (getCallback() == null) goto L5;
        return;
    L5:
        if (isVisible() == true) goto L9;
        setVisible(true, false);
        return;
    }

    private void forceUpdateM3Visibility() {
        boolean r0 = isVisible();
        this.updatingM3Visibility = true;
        Drawable r1 = this.m3Drawable;
        if (r1 == null) goto L5;
        r1.setVisible(r0, false);
    L5:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r12 = this.circularIndicatorDrawable;
        if (r12 == null) goto L8;
        r12.setVisible(r0, false);
    L8:
        this.updatingM3Visibility = false;
    }

    public static void getSegments(float r9, float[] r10) {
        float r0 = (1520.0f * r9) / 5400.0f;
        r10[0] = Math.max(0.0f, r0 - 20.0f);
        r10[1] = r0;
        int r02 = 0;
    L4:
        if (r02 >= 4) goto L6;
        float r3 = r10[1];
        FastOutSlowInInterpolator r4 = interpolator;
        r10[1] = r3 + (r4.getInterpolation((r9 - (r02 * 1350)) / 667.0f) * 250.0f);
        r10[0] = r10[0] + (r4.getInterpolation((r9 - (r5 + 667)) / 667.0f) * 250.0f);
        r02 = r02 + 1;
        goto L4
    }

    private void stopM3Drawables() {
        Drawable r0 = this.m3Drawable;
        if (r0 != null) goto L5;
    L7:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r02 = this.circularIndicatorDrawable;
        if (r02 != null) goto L10;
        return;
    L10:
        if (r02.isVisible() == false) goto L14;
        this.circularIndicatorDrawable.setVisible(false, false);
        return;
    L14:
        return;
    L5:
        if (r0.isVisible() == false) goto L7;
        this.m3Drawable.setVisible(false, false);
        goto L7
    }

    private void updateCircularBounds(Rect r5) {
        if (this.circularIndicatorDrawable == null) goto L6;
        int r0 = r5.width();
        int r1 = r5.height();
        int r2 = (int) this.size;
        int r3 = r5.left + ((r0 - r2) / 2);
        int r52 = r5.top + ((r1 - r2) / 2);
        this.circularIndicatorDrawable.setBounds(r3, r52, r3 + r2, r2 + r52);
        return;
    }

    private void updateM3Bounds(Rect r5) {
        if (this.m3Drawable == null) goto L6;
        int r0 = r5.width();
        int r1 = r5.height();
        int r2 = (int) this.size;
        int r3 = r5.left + ((r0 - r2) / 2);
        int r52 = r5.top + ((r1 - r2) / 2);
        this.m3Drawable.setBounds(r3, r52, r3 + r2, r2 + r52);
        return;
    }

    private void updateM3Visibility(boolean r6) {
        boolean r0 = isVisible();
        boolean r1 = true;
        this.updatingM3Visibility = true;
        Drawable r2 = this.m3Drawable;
        if (r2 != null) goto L5;
    L13:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r22 = this.circularIndicatorDrawable;
        if (r22 != null) goto L16;
    L24:
        this.updatingM3Visibility = false;
        return;
    L16:
        if (r22.isVisible() != r0) goto L18;
        if (r6 == false) goto L24;
    L18:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r23 = this.circularIndicatorDrawable;
        if (r6 == false) goto L22;
        if (r0 == false) goto L22;
    L23:
        r23.setVisible(r0, r1);
    L22:
        r1 = false;
        goto L23
    L5:
        if (r2.isVisible() != r0) goto L7;
        if (r6 == false) goto L13;
    L7:
        Drawable r24 = this.m3Drawable;
        if (r6 == false) goto L11;
        if (r0 == false) goto L11;
        boolean r4 = true;
    L12:
        r24.setVisible(r0, r4);
    L11:
        r4 = false;
        goto L12
    }

    private void updateSegment() {
        getSegments((SystemClock.elapsedRealtime() - this.start) % 5400, this.segment);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        int r0 = this.currentStyle;
        if (r0 != 1) goto L9;
        if (this.m3Drawable == null) goto L9;
        this.lastDrawTime = SystemClock.elapsedRealtime();
        ensureManualDrawVisible();
        updateM3Visibility(false);
        this.m3Drawable.draw(r12);
        return;
    L9:
        if (r0 == 2) goto L13;
        if (r0 == 3) goto L13;
    L17:
        if (this.start >= 0) goto L19;
        this.start = SystemClock.elapsedRealtime();
    L19:
        updateSegment();
        RectF r6 = this.bounds;
        float r02 = this.angleOffset;
        float[] r3 = this.segment;
        float r1 = r3[0];
        r12.drawArc(r6, r02 + r1, r3[1] - r1, false, this.paint);
        invalidateSelf();
        return;
    L13:
        if (this.circularIndicatorDrawable == null) goto L17;
        this.lastDrawTime = SystemClock.elapsedRealtime();
        ensureManualDrawVisible();
        updateM3Visibility(false);
        this.circularIndicatorDrawable.draw(r12);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) (this.size + this.thickness);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) (this.size + this.thickness);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        if (this.updatingM3Visibility == false) goto L5;
        return;
    L5:
        if (getCallback() == null) goto L7;
        return;
    L7:
        if ((SystemClock.elapsedRealtime() - this.lastDrawTime) <= 1000) goto L12;
        stopM3Drawables();
        return;
    }

    public void reset() {
        this.start = -1;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        scheduleSelf(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        Drawable r0 = this.m3Drawable;
        if (r0 == null) goto L5;
        r0.setAlpha(r2);
    L5:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r1 = this.circularIndicatorDrawable;
        if (r1 == null) goto L9;
        r1.setAlpha(r2);
        return;
    }

    public void setAngleOffset(float r1) {
        this.angleOffset = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r8, int r9, int r10, int r11) {
        super.setBounds(r8, r9, r10, r11);
        int r102 = r10 - r8;
        int r112 = r11 - r9;
        RectF r0 = this.bounds;
        float r82 = r8;
        float r103 = r102;
        float r1 = this.thickness;
        float r4 = this.size;
        float r92 = r9;
        float r113 = r112;
        r0.set((((r103 - (r1 / 2.0f)) - r4) / 2.0f) + r82, (((r113 - (r1 / 2.0f)) - r4) / 2.0f) + r92, r82 + (((r103 + (r1 / 2.0f)) + r4) / 2.0f), r92 + (((r113 + (r1 / 2.0f)) + r4) / 2.0f));
        this.paint.setStrokeWidth(this.thickness);
        updateM3Bounds(getBounds());
        updateCircularBounds(getBounds());
    }

    public void setColor(int r3) {
        this.currentColor = r3;
        this.paint.setColor(r3);
        LoadingIndicator r0 = this.m3IndicatorView;
        if (r0 == null) goto L6;
        r0.setIndicatorColor(new int[]{r3});
    L6:
        if (this.circularIndicatorDrawable == null) goto L9;
        int[] r32 = {r3};
        this.circularIndicatorSpec.indicatorColors = r32;
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        Drawable r0 = this.m3Drawable;
        if (r0 == null) goto L5;
        r0.setColorFilter(r2);
    L5:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r1 = this.circularIndicatorDrawable;
        if (r1 == null) goto L9;
        r1.setColorFilter(r2);
        return;
    }

    public void setStyle(int r8, Context r9) {
        if (ExteraConfig.getNewLoadingStyle() == true) goto L8;
        if (r8 == 0) goto L8;
        if (r8 == 1) goto L8;
        r8 = 0;
    L8:
        if (this.currentStyle != r8) goto L18;
        if (r8 == 1) goto L11;
    L12:
        if (r8 == 2) goto L15;
        if (r8 == 3) goto L15;
        return;
    L15:
        if (this.circularIndicatorDrawable == null) goto L18;
        return;
    L11:
        if (this.m3Drawable != null) goto L12;
    L18:
        Drawable r0 = this.m3Drawable;
        IndeterminateDrawable<CircularProgressIndicatorSpec> r5 = this.circularIndicatorDrawable;
        this.currentStyle = r8;
        if (r8 != 1) goto L27;
        if (r9 == null) goto L27;
        this.circularIndicatorDrawable = null;
        this.circularIndicatorSpec = null;
        if (this.m3IndicatorView != null) goto L24;
        this.m3IndicatorView = new LoadingIndicator(r9);
    L24:
        this.m3IndicatorView.setIndicatorSize((int) this.size);
        this.m3IndicatorView.setIndicatorColor(new int[]{this.currentColor});
        LoadingIndicatorDrawable r82 = this.m3IndicatorView.getDrawable();
        this.m3Drawable = r82;
        r82.setCallback(this);
        forceUpdateM3Visibility();
        if (getBounds().isEmpty() == true) goto L37;
        updateM3Bounds(getBounds());
    L37:
        if (r0 != null) goto L39;
    L41:
        if (r5 != null) goto L43;
    L45:
        invalidateSelf();
        return;
    L43:
        if (r5 == this.circularIndicatorDrawable) goto L45;
        r5.setVisible(false, false);
        r5.setCallback(null);
        goto L45
    L39:
        if (r0 == this.m3Drawable) goto L41;
        r0.setVisible(false, false);
        r0.setCallback(null);
    L27:
        if (r8 == 2) goto L29;
        if (r8 == 3) goto L29;
    L36:
        this.m3Drawable = null;
        this.m3IndicatorView = null;
        this.circularIndicatorDrawable = null;
        this.circularIndicatorSpec = null;
    L29:
        if (r9 == null) goto L36;
        this.m3Drawable = null;
        this.m3IndicatorView = null;
        CircularProgressIndicatorSpec r83 = new CircularProgressIndicatorSpec(r9, null);
        this.circularIndicatorSpec = r83;
        r83.indicatorSize = (int) this.size;
        r83.trackThickness = (int) this.thickness;
        r83.indicatorColors = new int[]{this.currentColor};
        r83.trackColor = this.trackColor;
        r83.indicatorTrackGapSize = AndroidUtilities.dp(2.0f);
        if (this.currentStyle != 3) goto L33;
        this.circularIndicatorSpec.wavelengthIndeterminate = AndroidUtilities.dp(7.0f);
        CircularProgressIndicatorSpec r84 = this.circularIndicatorSpec;
        r84.indicatorInset = 0;
        r84.waveAmplitude = AndroidUtilities.dp(0.75f);
        this.circularIndicatorSpec.waveSpeed = AndroidUtilities.dp(6.0f);
    L33:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r85 = IndeterminateDrawable.createCircularDrawable(r9, this.circularIndicatorSpec);
        this.circularIndicatorDrawable = r85;
        r85.setCallback(this);
        forceUpdateM3Visibility();
        if (getBounds().isEmpty() == true) goto L37;
        updateCircularBounds(getBounds());
        goto L37
    }

    public void setTrackColor(int r2) {
        this.trackColor = r2;
        if (this.circularIndicatorDrawable == null) goto L6;
        this.circularIndicatorSpec.trackColor = r2;
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r1, boolean r2) {
        boolean r12 = super.setVisible(r1, r2);
        updateM3Visibility(r2);
        return r12;
    }

    public void setWavyValues(float r2, float r3, float r4) {
        CircularProgressIndicatorSpec r0 = this.circularIndicatorSpec;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.waveAmplitude = AndroidUtilities.dp(r2);
        CircularProgressIndicatorSpec r22 = this.circularIndicatorSpec;
        r22.wavelengthIndeterminate = AndroidUtilities.dp(r3);
        CircularProgressIndicatorSpec r1 = this.circularIndicatorSpec;
        r1.waveSpeed = AndroidUtilities.dp(r4);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        unscheduleSelf(r2);
    }
}
