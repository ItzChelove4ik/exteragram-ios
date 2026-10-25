package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Consumer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public abstract class PipettePickerView extends View {
    private float appearProgress;
    private Bitmap bitmap;
    private Consumer<Integer> colorListener;
    private Paint colorPaint;
    private RectF dstRect;
    private boolean isDisappeared;
    private Paint linePaint;
    private int mColor;
    private Paint outlinePaint;
    private Path path;
    private float positionX;
    private float positionY;
    private Rect srcRect;

    /* renamed from: org.telegram.ui.Components.Paint.Views.PipettePickerView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ PipettePickerView this$0;
        final /* synthetic */ boolean val$saveColor;

        public AnonymousClass1(PipettePickerView r1, boolean r2) {
            this.this$0 = r1;
            this.val$saveColor = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.onStopPipette();
            if (this.val$saveColor == false) goto L6;
            PipettePickerView.m11578$$Nest$fgetcolorListener(this.this$0).accept(Integer.valueOf(PipettePickerView.m11579$$Nest$fgetmColor(this.this$0)));
        L6:
            if (this.this$0.getParent() == null) goto L9;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    /* renamed from: $r8$lambda$8EgvvQrJtbUL6QIIj4-RxBtY7hM, reason: not valid java name */
    public static /* synthetic */ void m11576$r8$lambda$8EgvvQrJtbUL6QIIj4RxBtY7hM(PipettePickerView r0, ValueAnimator r1) {
        r0.lambda$animateDisappear$1(r1);
    }

    /* renamed from: $r8$lambda$qUy6bOm4T7du3r0qInZNMriv-RM, reason: not valid java name */
    public static /* synthetic */ void m11577$r8$lambda$qUy6bOm4T7du3r0qInZNMrivRM(PipettePickerView r0, ValueAnimator r1) {
        r0.lambda$animateShow$0(r1);
    }

    /* renamed from: -$$Nest$fgetcolorListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Consumer m11578$$Nest$fgetcolorListener(PipettePickerView r0) {
        return r0.colorListener;
    }

    /* renamed from: -$$Nest$fgetmColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11579$$Nest$fgetmColor(PipettePickerView r0) {
        return r0.mColor;
    }

    public PipettePickerView(Context r2, Bitmap r3) {
        super(r2);
        this.outlinePaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.colorPaint = new Paint(1);
        this.positionX = 0.5f;
        this.positionY = 0.5f;
        this.path = new Path();
        this.srcRect = new Rect();
        this.dstRect = new RectF();
        this.bitmap = r3;
        Paint r22 = this.outlinePaint;
        Paint.Style r32 = Paint.Style.STROKE;
        r22.setStyle(r32);
        this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
        this.outlinePaint.setColor(-1);
        this.linePaint.setStyle(r32);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.linePaint.setColor(-1711276033);
        this.colorPaint.setStyle(r32);
        this.colorPaint.setStrokeWidth(AndroidUtilities.dp(12.0f));
    }

    private /* synthetic */ void lambda$animateDisappear$1(ValueAnimator r1) {
        this.appearProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$animateShow$0(ValueAnimator r1) {
        this.appearProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void updatePosition(MotionEvent r3) {
        this.positionX = r3.getX() / getWidth();
        this.positionY = r3.getY() / getHeight();
        invalidate();
    }

    public void animateDisappear(boolean r4) {
        if (this.isDisappeared == false) goto L5;
        return;
    L5:
        this.isDisappeared = true;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(150);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.addUpdateListener(new PipettePickerView$$ExternalSyntheticLambda0(this));
        r0.addListener(new AnonymousClass1(this, r4));
        r0.start();
    }

    public void animateShow() {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.addUpdateListener(new PipettePickerView$$ExternalSyntheticLambda1(this));
        r0.start();
        onStartPipette();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onStopPipette();
        this.bitmap.recycle();
        this.bitmap = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        super.onDraw(r12);
        float r0 = Math.min(getWidth(), getHeight()) * 0.2f;
        float r1 = this.positionX * getWidth();
        float r2 = this.positionY * getHeight();
        int r3 = Math.round(this.positionX * this.bitmap.getWidth());
        int r4 = Math.round(this.positionY * this.bitmap.getHeight());
        int r5 = this.bitmap.getPixel(Utilities.clamp(r3, r5.getWidth() - 1, 0), Utilities.clamp(r4, this.bitmap.getHeight() - 1, 0));
        this.mColor = r5;
        this.colorPaint.setColor(r5);
        float r52 = this.appearProgress;
        if (r52 != 0.0f) goto L5;
    L7:
        r12.save();
    L8:
        float r53 = (this.appearProgress * 0.5f) + 0.5f;
        r12.scale(r53, r53, r1, r2);
        this.path.rewind();
        Path r54 = this.path;
        Path.Direction r6 = Path.Direction.CW;
        r54.addCircle(r1, r2, r0, r6);
        r12.clipPath(this.path);
        int r55 = Math.round(3.5f);
        this.srcRect.set(r3 - r55, r4 - r55, r3 + r55, r4 + r55);
        this.dstRect.set(r1 - r0, r2 - r0, r1 + r0, r2 + r0);
        r12.drawBitmap(this.bitmap, this.srcRect, this.dstRect, null);
        float r02 = r0 - (this.colorPaint.getStrokeWidth() / 2.0f);
        r12.drawCircle(r1, r2, r02, this.colorPaint);
        float r03 = (r02 - (this.colorPaint.getStrokeWidth() / 2.0f)) - (this.outlinePaint.getStrokeWidth() / 2.0f);
        r12.drawCircle(r1, r2, r03, this.outlinePaint);
        float r04 = r03 - (this.outlinePaint.getStrokeWidth() / 2.0f);
        this.path.rewind();
        this.path.addCircle(r1, r2, r04, r6);
        r12.clipPath(this.path);
        float r32 = (r04 * 2.0f) / 8.0f;
        this.path.rewind();
        float r56 = -3.5f;
        float r62 = -3.5f;
    L10:
        if (r62 >= 4.5f) goto L12;
        float r9 = (r62 * r32) + r1;
        this.path.moveTo(r9, r2 - r04);
        this.path.lineTo(r9, r2 + r04);
        r62 = r62 + 1.0f;
    L12:
        Path r92 = this.path;
        if (r56 >= 4.5f) goto L15;
        float r10 = (r56 * r32) + r2;
        r92.moveTo(r1 - r04, r10);
        this.path.lineTo(r1 + r04, r10);
        r56 = r56 + 1.0f;
        goto L12
    L15:
        r12.drawPath(r92, this.linePaint);
        float r33 = r32 / 2.0f;
        this.dstRect.set(r1 - r33, r2 - r33, r1 + r33, r2 + r33);
        r12.drawRoundRect(this.dstRect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), this.outlinePaint);
        r12.restore();
        return;
    L5:
        if (r52 == 1.0f) goto L7;
        RectF r57 = AndroidUtilities.rectTmp;
        r57.set(r1 - r0, r2 - r0, r1 + r0, r2 + r0);
        r12.saveLayerAlpha(r57, (int) (this.appearProgress * 255.0f), 31);
        goto L8
    }

    @Override // android.view.View
    public void onSizeChanged(int r2, int r3, int r4, int r5) {
        super.onSizeChanged(r2, r3, r4, r5);
        if (r2 == 0) goto L11;
        if (r3 == 0) goto L12;
        if (r4 == 0) goto L13;
        if (r5 != 0) goto L8;
        return;
    L8:
        if (isLaidOut() == false) goto L15;
        this.positionX = (r4 * this.positionX) / r2;
        this.positionY = (r5 * this.positionY) / r3;
        return;
    L15:
        return;
    L13:
        return;
    L12:
        return;
    }

    public abstract void onStartPipette();

    public abstract void onStopPipette();

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r4) {
        int r0 = r4.getActionMasked();
        if (r0 == 0) goto L13;
        if (r0 != 1) goto L6;
        animateDisappear(true);
    L14:
        return true;
    L6:
        if (r0 != 2) goto L8;
        updatePosition(r4);
        goto L14
    L8:
        if (r0 != 3) goto L14;
        animateDisappear(false);
        goto L14
    L13:
        updatePosition(r4);
        getParent().requestDisallowInterceptTouchEvent(true);
        goto L14
    }

    public void setColorListener(Consumer<Integer> r1) {
        this.colorListener = r1;
    }
}
