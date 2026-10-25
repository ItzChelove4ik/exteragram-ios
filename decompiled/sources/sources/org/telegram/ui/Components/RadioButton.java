package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class RadioButton extends View {
    private static Paint checkedPaint;
    private static Paint eraser;
    private static Paint paint;
    private boolean attachedToWindow;
    private ObjectAnimator checkAnimator;
    private int checkedColor;
    private int color;
    private Drawable icon;
    private int iconColor;
    private boolean isChecked;
    private float progress;
    private int size;

    public RadioButton(Context r2) {
        super(r2);
        this.size = AndroidUtilities.dp(16.0f);
        if (paint != null) goto L6;
        Paint r1 = new Paint(1);
        paint = r1;
        r1.setStrokeWidth(AndroidUtilities.dp(2.0f));
        paint.setStyle(Paint.Style.STROKE);
        checkedPaint = new Paint(1);
        Paint r12 = new Paint(1);
        eraser = r12;
        r12.setColor(0);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        return;
    }

    private void animateToCheckedState(boolean r3) {
        if (r3 == false) goto L4;
        float r32 = 1.0f;
    L5:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "progress", new float[]{r32});
        this.checkAnimator = r33;
        r33.setDuration(200);
        this.checkAnimator.start();
        return;
    L4:
        r32 = 0.0f;
        goto L5
    }

    private void cancelCheckAnimator() {
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    public int getColor() {
        return this.color;
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
    public void onDraw(Canvas r14) {
        float r0 = this.progress;
        int r3 = this.color;
        if (r0 > 0.5f) goto L5;
        paint.setColor(r3);
        checkedPaint.setColor(this.color);
        float r02 = this.progress / 0.5f;
    L6:
        r14.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        float r142 = (this.size / 2) - ((r02 + 1.0f) * AndroidUtilities.density);
        r14.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r142, paint);
        if (this.icon == null) goto L9;
    L12:
        r14.restore();
        if (this.icon == null) goto L19;
        int r143 = ColorUtils.blendARGB(this.color, this.checkedColor, Utilities.clamp(this.progress, 1.0f, 0.0f));
        if (this.iconColor == r143) goto L17;
        Drawable r03 = this.icon;
        this.iconColor = r143;
        r03.setColorFilter(new PorterDuffColorFilter(r143, PorterDuff.Mode.SRC_IN));
    L17:
        this.icon.setBounds((int) ((getWidth() / 2.0f) - (this.icon.getIntrinsicWidth() / 2.0f)), (int) ((getHeight() / 2.0f) - (this.icon.getIntrinsicHeight() / 2.0f)), (int) ((getWidth() / 2.0f) + (this.icon.getIntrinsicWidth() / 2.0f)), (int) ((getHeight() / 2.0f) + (this.icon.getIntrinsicHeight() / 2.0f)));
        this.icon.draw(r14);
        return;
    L19:
        return;
    L9:
        if (this.progress > 0.5f) goto L11;
        r14.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r142 - AndroidUtilities.dp(1.0f), checkedPaint);
        r14.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (r142 - AndroidUtilities.dp(1.0f)) * (1.0f - r02), eraser);
        goto L12
    L11:
        r14.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (this.size / 4) + (((r142 - AndroidUtilities.dp(1.0f)) - (this.size / 4)) * r02), checkedPaint);
        goto L12
    L5:
        r02 = 2.0f - (r0 / 0.5f);
        float r6 = 1.0f - r02;
        int r2 = Color.rgb(Color.red(r3) + ((int) ((Color.red(this.checkedColor) - r2) * r6)), Color.green(this.color) + ((int) ((Color.green(this.checkedColor) - r7) * r6)), Color.blue(this.color) + ((int) ((Color.blue(this.checkedColor) - r9) * r6)));
        paint.setColor(r2);
        checkedPaint.setColor(r2);
        goto L6
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        this.color = r1;
        invalidate();
    }

    public void setChecked(boolean r2, boolean r3) {
        if (r2 != this.isChecked) goto L5;
        return;
    L5:
        this.isChecked = r2;
        if (this.attachedToWindow == false) goto L10;
        if (r3 == false) goto L10;
        animateToCheckedState(r2);
        return;
    L10:
        cancelCheckAnimator();
        if (r2 == false) goto L13;
        float r22 = 1.0f;
    L14:
        setProgress(r22);
        return;
    L13:
        r22 = 0.0f;
        goto L14
    }

    public void setCheckedColor(int r1) {
        this.checkedColor = r1;
        invalidate();
    }

    public void setColor(int r1, int r2) {
        this.color = r1;
        this.checkedColor = r2;
        invalidate();
    }

    public void setIcon(Drawable r2) {
        this.iconColor = 0;
        this.icon = r2;
        invalidate();
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
        if (this.size != r2) goto L5;
        return;
    L5:
        this.size = r2;
    }
}
