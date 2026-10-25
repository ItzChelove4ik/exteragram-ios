package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Keep;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class CheckBox extends View {
    private static Paint backgroundPaint;
    private static Paint eraser;
    private static Paint eraser2;
    private static Paint paint;
    private boolean attachedToWindow;
    private Canvas bitmapCanvas;
    private ObjectAnimator checkAnimator;
    private Bitmap checkBitmap;
    private Canvas checkCanvas;
    private Drawable checkDrawable;
    private int checkOffset;
    private String checkedText;
    private int color;
    private boolean drawBackground;
    private Bitmap drawBitmap;
    private boolean hasBorder;
    private boolean isCheckAnimation;
    private boolean isChecked;
    private float progress;
    private int size;
    private TextPaint textPaint;

    /* renamed from: org.telegram.ui.Components.CheckBox$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CheckBox this$0;

        public AnonymousClass1(CheckBox r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(CheckBox.m10001$$Nest$fgetcheckAnimator(this.this$0)) == false) goto L6;
            CheckBox.m10003$$Nest$fputcheckAnimator(this.this$0, null);
        L6:
            if (CheckBox.m10002$$Nest$fgetisChecked(this.this$0) == true) goto L9;
            CheckBox.m10004$$Nest$fputcheckedText(this.this$0, null);
            return;
        }
    }

    /* renamed from: -$$Nest$fgetcheckAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ObjectAnimator m10001$$Nest$fgetcheckAnimator(CheckBox r0) {
        return r0.checkAnimator;
    }

    /* renamed from: -$$Nest$fgetisChecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10002$$Nest$fgetisChecked(CheckBox r0) {
        return r0.isChecked;
    }

    /* renamed from: -$$Nest$fputcheckAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10003$$Nest$fputcheckAnimator(CheckBox r0, ObjectAnimator r1) {
        r0.checkAnimator = r1;
    }

    /* renamed from: -$$Nest$fputcheckedText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10004$$Nest$fputcheckedText(CheckBox r0, String r1) {
        r0.checkedText = r1;
    }

    public CheckBox(Context r6, int r7) {
        super(r6);
        this.isCheckAnimation = true;
        this.size = 22;
        if (paint != null) goto L5;
        paint = new Paint(1);
        Paint r1 = new Paint(1);
        eraser = r1;
        r1.setColor(0);
        Paint r12 = eraser;
        PorterDuff.Mode r4 = PorterDuff.Mode.CLEAR;
        r12.setXfermode(new PorterDuffXfermode(r4));
        Paint r13 = new Paint(1);
        eraser2 = r13;
        r13.setColor(0);
        Paint r14 = eraser2;
        Paint.Style r2 = Paint.Style.STROKE;
        r14.setStyle(r2);
        eraser2.setXfermode(new PorterDuffXfermode(r4));
        Paint r15 = new Paint(1);
        backgroundPaint = r15;
        r15.setColor(-1);
        backgroundPaint.setStyle(r2);
    L5:
        eraser2.setStrokeWidth(AndroidUtilities.dp(28.0f));
        backgroundPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        TextPaint r16 = new TextPaint(1);
        this.textPaint = r16;
        r16.setTextSize(AndroidUtilities.dp(18.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.checkDrawable = r6.getResources().getDrawable(r7).mutate();
    }

    private void animateToCheckedState(boolean r3) {
        this.isCheckAnimation = r3;
        if (r3 == false) goto L5;
        float r32 = 1.0f;
    L6:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "progress", new float[]{r32});
        this.checkAnimator = r33;
        r33.addListener(new AnonymousClass1(this));
        this.checkAnimator.setDuration(300);
        this.checkAnimator.start();
        return;
    L5:
        r32 = 0.0f;
        goto L6
    }

    private void cancelCheckAnimator() {
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.checkAnimator = null;
        return;
    }

    @Keep
    public float getProgress() {
        return this.progress;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        if (getVisibility() == 0) goto L5;
        return;
    L5:
        if (this.drawBitmap != null) goto L7;
        return;
    L7:
        if (this.checkBitmap != null) goto L10;
        return;
    L10:
        if (this.drawBackground == false) goto L12;
    L13:
        eraser2.setStrokeWidth(AndroidUtilities.dp(this.size + 6));
        this.drawBitmap.eraseColor(0);
        float r0 = getMeasuredWidth() / 2;
        float r3 = this.progress;
        if (r3 < 0.5f) goto L16;
        float r5 = 1.0f;
    L18:
        if (r3 >= 0.5f) goto L20;
        float r7 = 0.0f;
    L22:
        if (this.isCheckAnimation == true) goto L26;
        r3 = 1.0f - r3;
    L26:
        if (r3 >= 0.2f) goto L30;
        float r8 = (AndroidUtilities.dp(2.0f) * r3) / 0.2f;
    L28:
        r0 = r0 - r8;
    L33:
        if (this.drawBackground == false) goto L35;
        paint.setColor(1140850688);
        r12.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r0 - AndroidUtilities.dp(1.0f), paint);
        r12.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r0 - AndroidUtilities.dp(1.0f), backgroundPaint);
    L35:
        paint.setColor(this.color);
        if (this.hasBorder == false) goto L38;
        r0 = r0 - AndroidUtilities.dp(2.0f);
    L38:
        this.bitmapCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r0, paint);
        this.bitmapCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r0 * (1.0f - r5), eraser);
        r12.drawBitmap(this.drawBitmap, 0.0f, 0.0f, null);
        this.checkBitmap.eraseColor(0);
        if (this.checkedText == null) goto L45;
        int r02 = (int) Math.ceil(this.textPaint.measureText(r0));
        Canvas r2 = this.checkCanvas;
        String r4 = this.checkedText;
        float r03 = (getMeasuredWidth() - r02) / 2;
        if (this.size != 40) goto L43;
        float r52 = 28.0f;
    L44:
        r2.drawText(r4, r03, AndroidUtilities.dp(r52), this.textPaint);
    L46:
        this.checkCanvas.drawCircle((getMeasuredWidth() / 2) - AndroidUtilities.dp(2.5f), (getMeasuredHeight() / 2) + AndroidUtilities.dp(4.0f), ((getMeasuredWidth() + AndroidUtilities.dp(6.0f)) / 2) * (1.0f - r7), eraser2);
        r12.drawBitmap(this.checkBitmap, 0.0f, 0.0f, null);
        return;
    L43:
        r52 = 21.0f;
        goto L44
    L45:
        int r04 = this.checkDrawable.getIntrinsicWidth();
        int r22 = this.checkDrawable.getIntrinsicHeight();
        int r42 = (getMeasuredWidth() - r04) / 2;
        int r53 = (getMeasuredHeight() - r22) / 2;
        Drawable r82 = this.checkDrawable;
        int r9 = this.checkOffset;
        r82.setBounds(r42, r53 + r9, r04 + r42, (r53 + r22) + r9);
        this.checkDrawable.draw(this.checkCanvas);
        goto L46
    L30:
        if (r3 >= 0.4f) goto L33;
        r8 = AndroidUtilities.dp(2.0f) - ((AndroidUtilities.dp(2.0f) * (r3 - 0.2f)) / 0.2f);
        goto L28
    L20:
        r7 = (r3 - 0.5f) / 0.5f;
        goto L22
    L16:
        r5 = r3 / 0.5f;
        goto L18
    L12:
        if (this.progress != 0.0f) goto L13;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.CheckBox");
        r2.setCheckable(true);
        r2.setChecked(this.isChecked);
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        this.color = r1;
        invalidate();
    }

    public void setCheckColor(int r4) {
        this.checkDrawable.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        this.textPaint.setColor(r4);
        invalidate();
    }

    public void setCheckOffset(int r1) {
        this.checkOffset = r1;
    }

    public void setChecked(int r3, boolean r4, boolean r5) {
        if (r3 < 0) goto L5;
        this.checkedText = _UrlKt.FRAGMENT_ENCODE_SET + (r3 + 1);
        invalidate();
    L5:
        if (r4 != this.isChecked) goto L7;
        return;
    L7:
        this.isChecked = r4;
        if (this.attachedToWindow == false) goto L12;
        if (r5 == false) goto L12;
        animateToCheckedState(r4);
        return;
    L12:
        cancelCheckAnimator();
        if (r4 == false) goto L15;
        float r32 = 1.0f;
    L16:
        setProgress(r32);
        return;
    L15:
        r32 = 0.0f;
        goto L16
    }

    public void setChecked(boolean r2, boolean r3) {
        setChecked(-1, r2, r3);
    }

    public void setColor(int r3, int r4) {
        this.color = r3;
        this.checkDrawable.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        this.textPaint.setColor(r4);
        invalidate();
    }

    public void setDrawBackground(boolean r1) {
        this.drawBackground = r1;
    }

    public void setHasBorder(boolean r1) {
        this.hasBorder = r1;
    }

    public void setNum(int r3) {
        if (r3 < 0) goto L5;
        this.checkedText = _UrlKt.FRAGMENT_ENCODE_SET + (r3 + 1);
    L7:
        invalidate();
        return;
    L5:
        if (this.checkAnimator != null) goto L7;
        this.checkedText = null;
        goto L7
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    public void setSize(int r2) {
        this.size = r2;
        if (r2 != 40) goto L6;
        this.textPaint.setTextSize(AndroidUtilities.dp(24.0f));
        return;
    }

    public void setStrokeWidth(int r1) {
        backgroundPaint.setStrokeWidth(r1);
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        super.setVisibility(r3);
        if (r3 == 0) goto L5;
        return;
    L5:
        if (this.drawBitmap != null) goto L12;
        int r32 = AndroidUtilities.dp(this.size);     // Catch: Throwable -> L8
        int r0 = AndroidUtilities.dp(this.size);     // Catch: Throwable -> L8
        Bitmap.Config r1 = Bitmap.Config.ARGB_4444;     // Catch: Throwable -> L8
        this.drawBitmap = Bitmap.createBitmap(r32, r0, r1);     // Catch: Throwable -> L8
        this.bitmapCanvas = new Canvas(this.drawBitmap);     // Catch: Throwable -> L8
        this.checkBitmap = Bitmap.createBitmap(AndroidUtilities.dp(this.size), AndroidUtilities.dp(this.size), r1);     // Catch: Throwable -> L8
        this.checkCanvas = new Canvas(this.checkBitmap);     // Catch: Throwable -> L8
        return;
    L13:
        return;
    }
}
