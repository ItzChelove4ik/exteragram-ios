package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class BatteryDrawable extends Drawable {
    private Paint connectorPaint;
    private Paint fillPaint;
    private float fillValue;
    private ValueAnimator fillValueAnimator;
    private Paint paintReference;
    private RectF rectTmp;
    private float scale;
    private Paint strokePaint;
    private float translateY;

    /* renamed from: org.telegram.ui.Components.BatteryDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BatteryDrawable this$0;
        final /* synthetic */ float val$value;

        public AnonymousClass1(BatteryDrawable r1, float r2) {
            this.this$0 = r1;
            this.val$value = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            BatteryDrawable.m8991$$Nest$fputfillValue(this.this$0, this.val$value);
            this.this$0.invalidateSelf();
        }
    }

    /* renamed from: $r8$lambda$i-71quQ8D8qfmeeF0Yf7pr_wSqg, reason: not valid java name */
    public static /* synthetic */ void m8990$r8$lambda$i71quQ8D8qfmeeF0Yf7pr_wSqg(BatteryDrawable r0, ValueAnimator r1) {
        r0.lambda$setFillValue$0(r1);
    }

    /* renamed from: -$$Nest$fputfillValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8991$$Nest$fputfillValue(BatteryDrawable r0, float r1) {
        r0.fillValue = r1;
    }

    public BatteryDrawable() {
        this.strokePaint = new Paint(1);
        this.connectorPaint = new Paint(1);
        this.fillPaint = new Paint(1);
        this.scale = 1.0f;
        this.translateY = 0.0f;
        this.fillValue = 1.0f;
        this.rectTmp = new RectF();
        this.strokePaint.setStyle(Paint.Style.STROKE);
    }

    public BatteryDrawable(float r2, int r3, int r4, float r5) {
        this();
        setFillValue(r2, false);
        setColor(r3, r4);
        setScale(r5);
    }

    private /* synthetic */ void lambda$setFillValue$0(ValueAnimator r1) {
        this.fillValue = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateSelf();
    }

    public void colorFromPaint(Paint r1) {
        this.paintReference = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r18) {
        if (getBounds() == null) goto L16;
        int r2 = getBounds().left;
        int r3 = getBounds().top + ((int) this.translateY);
        int r4 = getBounds().width();
        int r5 = getBounds().height();
        int r6 = getBounds().centerX();
        int r7 = getBounds().centerY() + ((int) this.translateY);
        Paint r8 = this.paintReference;
        if (r8 == null) goto L9;
        setColor(r8.getColor());
    L9:
        if (this.scale == 1.0f) goto L11;
        r18.save();
        float r82 = this.scale;
        r18.scale(r82, r82, r6, r7);
    L11:
        this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.1f));
        float r22 = r2;
        float r42 = r4;
        float r32 = r3;
        float r52 = r5;
        this.rectTmp.set((((r42 - AndroidUtilities.dpf2(16.33f)) / 2.0f) + r22) - AndroidUtilities.dpf2(1.33f), ((r52 - AndroidUtilities.dpf2(10.33f)) / 2.0f) + r32, (((AndroidUtilities.dpf2(16.33f) + r42) / 2.0f) + r22) - AndroidUtilities.dpf2(1.33f), ((AndroidUtilities.dpf2(10.33f) + r52) / 2.0f) + r32);
        r18.drawRoundRect(this.rectTmp, AndroidUtilities.dpf2(2.33f), AndroidUtilities.dpf2(2.33f), this.strokePaint);
        this.rectTmp.set((((r42 - AndroidUtilities.dpf2(13.0f)) / 2.0f) + r22) - AndroidUtilities.dpf2(1.66f), ((r52 - AndroidUtilities.dpf2(7.33f)) / 2.0f) + r32, ((r22 + ((r42 - AndroidUtilities.dpf2(13.0f)) / 2.0f)) - AndroidUtilities.dpf2(1.66f)) + Math.max(AndroidUtilities.dpf2(1.1f), this.fillValue * AndroidUtilities.dpf2(13.0f)), r32 + ((r52 + AndroidUtilities.dpf2(7.33f)) / 2.0f));
        r18.drawRoundRect(this.rectTmp, AndroidUtilities.dpf2(0.83f), AndroidUtilities.dpf2(0.83f), this.fillPaint);
        float r72 = r7;
        this.rectTmp.set((((AndroidUtilities.dpf2(17.5f) + r42) - AndroidUtilities.dpf2(4.66f)) / 2.0f) + r22, r72 - AndroidUtilities.dpf2(2.65f), r22 + (((r42 + AndroidUtilities.dpf2(17.5f)) + AndroidUtilities.dpf2(4.66f)) / 2.0f), r72 + AndroidUtilities.dpf2(2.65f));
        r18.drawArc(this.rectTmp, -90.0f, 180.0f, false, this.connectorPaint);
        if (this.scale == 1.0f) goto L15;
        r18.restore();
        return;
    L15:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(this.scale * 24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(this.scale * 24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.strokePaint.setAlpha(r2);
        this.connectorPaint.setAlpha(r2);
        this.fillPaint.setAlpha(r2);
    }

    public void setColor(int r1) {
        setColor(r1, r1);
    }

    public void setColor(int r2, int r3) {
        this.strokePaint.setColor(r2);
        this.connectorPaint.setColor(r2);
        this.fillPaint.setColor(r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.strokePaint.setColorFilter(r2);
        this.connectorPaint.setColorFilter(r2);
        this.fillPaint.setColorFilter(r2);
    }

    public void setFillValue(float r3, boolean r4) {
        float r32 = Math.max(Math.min(r3, 1.0f), 0.0f);
        ValueAnimator r0 = this.fillValueAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.fillValueAnimator = null;
    L5:
        if (r4 == true) goto L8;
        this.fillValue = r32;
        invalidateSelf();
        return;
    L8:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.fillValue, r32});
        this.fillValueAnimator = r42;
        r42.addUpdateListener(new BatteryDrawable$$ExternalSyntheticLambda0(this));
        this.fillValueAnimator.addListener(new AnonymousClass1(this, r32));
        this.fillValueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.fillValueAnimator.setDuration(200);
        this.fillValueAnimator.start();
    }

    public void setScale(float r1) {
        this.scale = r1;
        invalidateSelf();
    }

    public void setTranslationY(float r1) {
        this.translateY = r1;
    }
}
