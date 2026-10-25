package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CheckBoxSquare extends View {
    private boolean attachedToWindow;
    private ObjectAnimator checkAnimator;
    private Bitmap drawBitmap;
    private Canvas drawCanvas;
    private boolean isAlert;
    private boolean isChecked;
    private boolean isDisabled;
    private int key1;
    private int key2;
    private int key3;
    private float progress;
    private RectF rectF;
    private final Theme.ResourcesProvider resourcesProvider;

    public CheckBoxSquare(Context r2, boolean r3) {
        this(r2, r3, null);
    }

    public CheckBoxSquare(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        super(r2);
        this.resourcesProvider = r4;
        if (Theme.checkboxSquare_backgroundPaint != null) goto L5;
        Theme.createCommonResources(r2);
    L5:
        boolean r22 = this.isAlert;
        if (r22 == false) goto L8;
        int r42 = Theme.key_dialogCheckboxSquareUnchecked;
    L9:
        this.key1 = r42;
        if (r22 == false) goto L12;
        int r43 = Theme.key_dialogCheckboxSquareBackground;
    L13:
        this.key2 = r43;
        if (r22 == false) goto L16;
        int r23 = Theme.key_dialogCheckboxSquareCheck;
    L17:
        this.key3 = r23;
        this.rectF = new RectF();
        this.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), Bitmap.Config.ARGB_4444);
        this.drawCanvas = new Canvas(this.drawBitmap);
        this.isAlert = r3;
        return;
    L16:
        r23 = Theme.key_checkboxSquareCheck;
        goto L17
    L12:
        r43 = Theme.key_checkboxSquareBackground;
        goto L13
    L8:
        r42 = Theme.key_checkboxSquareUnchecked;
        goto L9
    }

    private void animateToCheckedState(boolean r3) {
        if (r3 == false) goto L4;
        float r32 = 1.0f;
    L5:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "progress", new float[]{r32});
        this.checkAnimator = r33;
        r33.setDuration(300);
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

    @Keep
    public float getProgress() {
        return this.progress;
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
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
    public void onDraw(Canvas r15) {
        if (getVisibility() == 0) goto L5;
        return;
    L5:
        int r0 = getThemedColor(this.key1);
        int r1 = getThemedColor(this.key2);
        float r2 = this.progress;
        if (r2 > 0.5f) goto L8;
        float r22 = r2 / 0.5f;
        Theme.checkboxSquare_backgroundPaint.setColor(Color.rgb(Color.red(r0) + ((int) ((Color.red(r1) - Color.red(r0)) * r22)), Color.green(r0) + ((int) ((Color.green(r1) - Color.green(r0)) * r22)), Color.blue(r0) + ((int) ((Color.blue(r1) - Color.blue(r0)) * r22))));
        float r02 = r22;
    L10:
        if (this.isDisabled == false) goto L16;
        Paint r12 = Theme.checkboxSquare_backgroundPaint;
        if (this.isAlert == false) goto L14;
        int r4 = Theme.key_dialogCheckboxSquareDisabled;
    L15:
        r12.setColor(getThemedColor(r4));
        goto L16
    L14:
        r4 = Theme.key_checkboxSquareDisabled;
    L16:
        float r13 = AndroidUtilities.dp(1.0f) * r02;
        this.rectF.set(r13, r13, AndroidUtilities.dp(18.0f) - r13, AndroidUtilities.dp(18.0f) - r13);
        this.drawBitmap.eraseColor(0);
        this.drawCanvas.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.checkboxSquare_backgroundPaint);
        if (r22 == 1.0f) goto L20;
        float r14 = Math.min(AndroidUtilities.dp(7.0f), (AndroidUtilities.dp(7.0f) * r22) + r13);
        this.rectF.set(AndroidUtilities.dp(1.33f) + r14, AndroidUtilities.dp(1.33f) + r14, AndroidUtilities.dp(16.66f) - r14, AndroidUtilities.dp(16.66f) - r14);
        this.drawCanvas.drawRoundRect(this.rectF, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), Theme.checkboxSquare_eraserPaint);
    L20:
        if (this.progress <= 0.5f) goto L22;
        Theme.checkboxSquare_checkPaint.setColor(getThemedColor(this.key3));
        float r5 = 1.0f - r02;
        this.drawCanvas.drawLine(AndroidUtilities.dp(7.0f), (int) AndroidUtilities.dpf2(13.0f), (int) (AndroidUtilities.dp(7.0f) - (AndroidUtilities.dp(3.0f) * r5)), (int) (AndroidUtilities.dpf2(13.0f) - (AndroidUtilities.dp(3.0f) * r5)), Theme.checkboxSquare_checkPaint);
        this.drawCanvas.drawLine((int) AndroidUtilities.dpf2(7.0f), (int) AndroidUtilities.dpf2(13.0f), (int) (AndroidUtilities.dpf2(7.0f) + (AndroidUtilities.dp(7.0f) * r5)), (int) (AndroidUtilities.dpf2(13.0f) - (AndroidUtilities.dp(7.0f) * r5)), Theme.checkboxSquare_checkPaint);
    L22:
        r15.drawBitmap(this.drawBitmap, 0.0f, 0.0f, null);
        return;
    L8:
        Theme.checkboxSquare_backgroundPaint.setColor(r1);
        r02 = 2.0f - (r2 / 0.5f);
        r22 = 1.0f;
        goto L10
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
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

    public void setColors(int r1, int r2, int r3) {
        this.key1 = r1;
        this.key2 = r2;
        this.key3 = r3;
        invalidate();
    }

    public void setDisabled(boolean r1) {
        this.isDisabled = r1;
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
}
