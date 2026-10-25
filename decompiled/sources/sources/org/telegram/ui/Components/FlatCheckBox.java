package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class FlatCheckBox extends View {
    int HEIGHT;
    int INNER_PADDING;
    int P;
    int TRANSLETE_TEXT;
    boolean attached;
    ValueAnimator checkAnimator;
    Paint checkPaint;
    public boolean checked;
    int colorActive;
    int colorInactive;
    int colorTextActive;
    public boolean enabled;
    Paint fillPaint;
    int lastW;
    Paint outLinePaint;
    float progress;
    RectF rectF;
    String text;
    TextPaint textPaint;

    public static /* synthetic */ void $r8$lambda$r5XFy6Db8hc6AFA0VP3hBhvXkog(FlatCheckBox r0, ValueAnimator r1) {
        r0.lambda$setChecked$0(r1);
    }

    public FlatCheckBox(Context r3) {
        super(r3);
        this.enabled = true;
        this.textPaint = new TextPaint(1);
        this.fillPaint = new Paint(1);
        this.outLinePaint = new Paint(1);
        this.checkPaint = new Paint(1);
        this.HEIGHT = AndroidUtilities.dp(35.0f);
        this.INNER_PADDING = AndroidUtilities.dp(22.0f);
        this.TRANSLETE_TEXT = AndroidUtilities.dp(8.0f);
        this.P = AndroidUtilities.dp(3.5f);
        this.rectF = new RectF();
        this.progress = 0.0f;
        this.lastW = 0;
        this.textPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.outLinePaint.setStrokeWidth(AndroidUtilities.dpf2(1.5f));
        Paint r32 = this.outLinePaint;
        Paint.Style r0 = Paint.Style.STROKE;
        r32.setStyle(r0);
        this.checkPaint.setStyle(r0);
        this.checkPaint.setStrokeCap(Paint.Cap.ROUND);
        this.checkPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
    }

    private /* synthetic */ void lambda$setChecked$0(ValueAnimator r1) {
        this.progress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void denied() {
        AndroidUtilities.shakeView(this);
    }

    @Override // android.view.View
    public void draw(Canvas r12) {
        super.draw(r12);
        float r2 = this.progress;
        if (r2 > 0.5f) goto L5;
        float r22 = r2 / 0.5f;
        this.fillPaint.setColor(Color.rgb(Color.red(this.colorActive) + ((int) ((Color.red(this.colorInactive) - Color.red(this.colorActive)) * r22)), Color.green(this.colorActive) + ((int) ((Color.green(this.colorInactive) - Color.green(this.colorActive)) * r22)), Color.blue(this.colorActive) + ((int) ((Color.blue(this.colorInactive) - Color.blue(this.colorActive)) * r22))));
        this.textPaint.setColor(Color.rgb(Color.red(this.colorInactive) + ((int) ((Color.red(this.colorTextActive) - Color.red(this.colorInactive)) * r22)), Color.green(this.colorInactive) + ((int) ((Color.green(this.colorTextActive) - Color.green(this.colorInactive)) * r22)), Color.blue(this.colorInactive) + ((int) ((Color.blue(this.colorTextActive) - Color.blue(this.colorInactive)) * r22))));
    L6:
        int r4 = getMeasuredHeight() >> 1;
        this.outLinePaint.setColor(this.colorInactive);
        RectF r6 = this.rectF;
        int r7 = this.HEIGHT;
        r12.drawRoundRect(r6, r7 / 2.0f, r7 / 2.0f, this.fillPaint);
        RectF r62 = this.rectF;
        int r72 = this.HEIGHT;
        r12.drawRoundRect(r62, r72 / 2.0f, r72 / 2.0f, this.outLinePaint);
        String r63 = this.text;
        if (r63 == null) goto L9;
        r12.drawText(r63, (getMeasuredWidth() >> 1) + (r22 * this.TRANSLETE_TEXT), r4 + (this.textPaint.getTextSize() * 0.35f), this.textPaint);
    L9:
        float r9 = 2.0f - (this.progress / 0.5f);
        r12.save();
        r12.scale(0.9f, 0.9f, AndroidUtilities.dpf2(7.0f), r4);
        r12.translate(AndroidUtilities.dp(12.0f), r4 - AndroidUtilities.dp(9.0f));
        if (this.progress <= 0.5f) goto L12;
        this.checkPaint.setColor(this.colorTextActive);
        float r8 = 1.0f - r9;
        r12.drawLine(AndroidUtilities.dpf2(7.0f), (int) AndroidUtilities.dpf2(13.0f), (int) (AndroidUtilities.dpf2(7.0f) - (AndroidUtilities.dp(4.0f) * r8)), (int) (AndroidUtilities.dpf2(13.0f) - (AndroidUtilities.dp(4.0f) * r8)), this.checkPaint);
        r12.drawLine((int) AndroidUtilities.dpf2(7.0f), (int) AndroidUtilities.dpf2(13.0f), (int) (AndroidUtilities.dpf2(7.0f) + (AndroidUtilities.dp(8.0f) * r8)), (int) (AndroidUtilities.dpf2(13.0f) - (AndroidUtilities.dp(8.0f) * r8)), this.checkPaint);
    L12:
        r12.restore();
        return;
    L5:
        this.textPaint.setColor(this.colorTextActive);
        this.fillPaint.setColor(this.colorInactive);
        r22 = 1.0f;
        goto L6
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        String r32 = this.text;
        if (r32 != null) goto L5;
        int r33 = 0;
    L6:
        setMeasuredDimension((r33 + (this.INNER_PADDING << 1)) + (this.P * 2), this.HEIGHT + AndroidUtilities.dp(4.0f));
        if (getMeasuredWidth() == this.lastW) goto L10;
        this.rectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.rectF.inset(this.P + (this.outLinePaint.getStrokeWidth() / 2.0f), this.P + (this.outLinePaint.getStrokeWidth() / 2.0f));
        return;
    L10:
        return;
    L5:
        r33 = (int) this.textPaint.measureText(r32);
        goto L6
    }

    public void recolor(int r2) {
        this.colorActive = Theme.getColor(Theme.key_windowBackgroundWhite);
        this.colorTextActive = -1;
        this.colorInactive = r2;
        invalidate();
    }

    public void setChecked(boolean r2) {
        setChecked(r2, true);
    }

    public void setChecked(boolean r4, boolean r5) {
        this.checked = r4;
        float r1 = 0.0f;
        if (this.attached == false) goto L14;
        if (r5 == false) goto L14;
        ValueAnimator r52 = this.checkAnimator;
        if (r52 == null) goto L9;
        r52.removeAllListeners();
        this.checkAnimator.cancel();
    L9:
        float r53 = this.progress;
        if (r4 == false) goto L12;
        r1 = 1.0f;
    L12:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r53, r1});
        this.checkAnimator = r42;
        r42.addUpdateListener(new FlatCheckBox$$ExternalSyntheticLambda0(this));
        this.checkAnimator.setDuration(300);
        this.checkAnimator.start();
        return;
    L14:
        if (r4 == false) goto L16;
        r1 = 1.0f;
    L16:
        this.progress = r1;
    }

    public void setText(String r1) {
        this.text = r1;
        requestLayout();
    }
}
