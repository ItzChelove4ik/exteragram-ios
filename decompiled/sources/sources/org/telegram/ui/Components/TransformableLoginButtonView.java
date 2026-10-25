package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class TransformableLoginButtonView extends View {
    private final Paint backgroundPaint;
    private String buttonText;
    private float buttonWidth;
    private boolean drawBackground;
    private final Paint outlinePaint;
    private float progress;
    private final RectF rect;
    private Drawable rippleDrawable;
    private TextPaint textPaint;
    private int transformType;

    public TransformableLoginButtonView(Context r3) {
        super(r3);
        Paint r32 = new Paint(1);
        this.backgroundPaint = r32;
        Paint r1 = new Paint(1);
        this.outlinePaint = r1;
        this.drawBackground = true;
        this.transformType = 0;
        this.rect = new RectF();
        r32.setColor(Theme.getColor(Theme.key_chats_actionBackground));
        r1.setStrokeWidth(AndroidUtilities.dp(2.0f));
        r1.setStyle(Paint.Style.STROKE);
        r1.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float r1, float r2) {
        super.drawableHotspotChanged(r1, r2);
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.setHotspot(r1, r2);
        return;
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.setState(getDrawableState());
        invalidate();
        return;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r19) {
        Canvas r1 = r19;
        if (this.drawBackground == true) goto L5;
    L12:
        int r2 = this.transformType;
        if (r2 == 0) goto L17;
        if (r2 != 1) goto L25;
        float r22 = AndroidUtilities.dp(21.0f);
        float r4 = getWidth() - AndroidUtilities.dp(21.0f);
        float r3 = getHeight() / 2.0f;
        r1.save();
        r1.translate((-AndroidUtilities.dp(2.0f)) * this.progress, 0.0f);
        r1.rotate(this.progress * 90.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        r1.drawLine(r22 + ((r4 - r22) * this.progress), r3, r4, r3, this.outlinePaint);
        int r12 = AndroidUtilities.dp((this.progress * (-1.0f)) + 9.0f);
        int r8 = AndroidUtilities.dp((this.progress * 7.0f) + 9.0f);
        double r11 = r4;
        double r42 = r12;
        double r13 = r3;
        r19.drawLine(r4, r3, (float) (r11 - (Math.cos(0.7853981633974483d) * r42)), (float) ((r42 * Math.sin(0.7853981633974483d)) + r13), this.outlinePaint);
        double r43 = r8;
        r1 = r19;
        r1.drawLine(r4, r3, (float) (r11 - (Math.cos(0.7853981633974483d) * r43)), (float) (r13 - (r43 * Math.sin(0.7853981633974483d))), this.outlinePaint);
        r1.restore();
    L25:
        Drawable r23 = this.rippleDrawable;
        if (r23 == null) goto L29;
        r23.setBounds(0, 0, getWidth(), getHeight());
        this.rippleDrawable.setHotspotBounds(0, 0, getWidth(), getHeight());
        this.rippleDrawable.draw(r1);
        return;
    L29:
        return;
    L17:
        TextPaint r24 = this.textPaint;
        if (r24 != null) goto L20;
    L22:
        float r122 = (Math.max(0.4f, this.progress) - 0.4f) / 0.6f;
        if (r122 == 0.0f) goto L25;
        float r25 = AndroidUtilities.dp(21.0f) + ((getWidth() - (AndroidUtilities.dp(21.0f) * 2)) * r122);
        float r32 = getHeight() / 2.0f;
        r1.drawLine(AndroidUtilities.dp(21.0f), r32, r25, r32, this.outlinePaint);
        double r132 = AndroidUtilities.dp(9.0f) * r122;
        float r44 = (float) (r25 - (Math.cos(0.7853981633974483d) * r132));
        float r82 = (float) (Math.sin(0.7853981633974483d) * r132);
        r1 = r19;
        r1.drawLine(r25, r32, r44, r32 - r82, this.outlinePaint);
        r1.drawLine(r25, r32, r44, r32 + r82, this.outlinePaint);
        goto L25
    L20:
        if (this.buttonText == null) goto L22;
        int r26 = r24.getAlpha();
        this.textPaint.setAlpha((int) (r26 * (1.0f - (Math.min(0.6f, this.progress) / 0.6f))));
        r1.drawText(this.buttonText, (getWidth() - this.buttonWidth) / 2.0f, ((getHeight() / 2.0f) + (this.textPaint.getTextSize() / 2.0f)) - AndroidUtilities.dp(1.75f), this.textPaint);
        this.textPaint.setAlpha(r26);
        goto L22
    L5:
        if (this.transformType != 0) goto L7;
        boolean r27 = true;
    L8:
        if (r27 == false) goto L10;
        float r28 = this.progress;
    L11:
        float r29 = AndroidUtilities.dp((r28 * 26.0f) + 6.0f);
        this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
        r1.drawRoundRect(this.rect, r29, r29, this.backgroundPaint);
        goto L12
    L10:
        r28 = 1.0f;
        goto L11
    L7:
        r27 = false;
        goto L8
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.backgroundPaint.setColor(r2);
        invalidate();
    }

    public void setButtonText(TextPaint r3, String r4) {
        this.textPaint = r3;
        this.buttonText = r4;
        this.outlinePaint.setColor(r3.getColor());
        this.buttonWidth = r3.measureText(r4);
    }

    public void setColor(int r2) {
        this.outlinePaint.setColor(r2);
        invalidate();
    }

    public void setDrawBackground(boolean r1) {
        this.drawBackground = r1;
    }

    public void setProgress(float r1) {
        this.progress = r1;
        invalidate();
    }

    public void setRippleDrawable(Drawable r1) {
        this.rippleDrawable = r1;
        invalidate();
    }

    public void setTransformType(int r1) {
        this.transformType = r1;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == true) goto L10;
        Drawable r1 = this.rippleDrawable;
        if (r1 == null) goto L8;
        if (r2 == r1) goto L13;
        return false;
    L13:
        return true;
    L8:
        return false;
    L10:
        return true;
    }
}
