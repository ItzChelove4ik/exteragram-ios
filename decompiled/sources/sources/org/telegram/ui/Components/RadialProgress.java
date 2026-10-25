package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes7.dex */
public class RadialProgress {
    private static DecelerateInterpolator decelerateInterpolator;
    private boolean alphaForMiniPrevious;
    private boolean alphaForPrevious;
    private float animatedAlphaValue;
    private float animatedProgressValue;
    private float animationProgressStart;
    private Drawable checkBackgroundDrawable;
    private RectF cicleRect;
    private Drawable currentDrawable;
    private Drawable currentMiniDrawable;
    private boolean currentMiniWithRound;
    private float currentProgress;
    private long currentProgressTime;
    private boolean currentWithRound;
    private int diff;
    private boolean disableUpdate;
    private boolean drawMiniProgress;
    private boolean hideCurrentDrawable;
    private long lastUpdateTime;
    private Bitmap miniDrawBitmap;
    private Canvas miniDrawCanvas;
    private Paint miniProgressBackgroundPaint;
    private Paint miniProgressPaint;
    private float overrideAlpha;
    private Paint overridePaint;
    private View parent;
    private Drawable previousDrawable;
    private Drawable previousMiniDrawable;
    private boolean previousMiniWithRound;
    private boolean previousWithRound;
    private int progressColor;
    private Paint progressPaint;
    private RectF progressRect;
    private float radOffset;
    private float rotationSpeed;
    private float roundRectRadius;

    public RadialProgress(View r6) {
        this.lastUpdateTime = 0;
        this.radOffset = 0.0f;
        this.currentProgress = 0.0f;
        this.animationProgressStart = 0.0f;
        this.currentProgressTime = 0;
        this.animatedProgressValue = 0.0f;
        this.progressRect = new RectF();
        this.cicleRect = new RectF();
        this.animatedAlphaValue = 1.0f;
        this.progressColor = -1;
        this.diff = AndroidUtilities.dp(4.0f);
        this.alphaForPrevious = true;
        this.alphaForMiniPrevious = true;
        this.overrideAlpha = 1.0f;
        this.overridePaint = null;
        this.roundRectRadius = -1.0f;
        this.rotationSpeed = 3000.0f;
        if (decelerateInterpolator != null) goto L5;
        decelerateInterpolator = new DecelerateInterpolator();
    L5:
        Paint r0 = new Paint(1);
        this.progressPaint = r0;
        Paint.Style r2 = Paint.Style.STROKE;
        r0.setStyle(r2);
        Paint r02 = this.progressPaint;
        Paint.Cap r3 = Paint.Cap.ROUND;
        r02.setStrokeCap(r3);
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        Paint r03 = new Paint(1);
        this.miniProgressPaint = r03;
        r03.setStyle(r2);
        this.miniProgressPaint.setStrokeCap(r3);
        this.miniProgressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.miniProgressBackgroundPaint = new Paint(1);
        this.parent = r6;
    }

    private void drawArc(Canvas r7, RectF r8, float r9, float r10, boolean r11, Paint r12) {
        float r6 = this.roundRectRadius;
        if (r6 < 0.0f) goto L6;
        float r102 = r10 + r9;
        StoriesUtilities.drawRoundRectSegment(r7, r8, r6, Math.min(r9, r102), Math.max(r9, r102), r12);
        return;
    L6:
        r7.drawArc(r8, r9, r10, r11, r12);
    }

    private void invalidateParent() {
        int r0 = AndroidUtilities.dp(2.0f);
        View r1 = this.parent;
        RectF r5 = this.progressRect;
        int r2 = ((int) r5.left) - r0;
        int r3 = ((int) r5.top) - r0;
        int r02 = r0 * 2;
        r1.invalidate(r2, r3, ((int) r5.right) + r02, ((int) r5.bottom) + r02);
    }

    private void updateAnimation(boolean r15) {
        if (this.disableUpdate == true) goto L73;
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        Drawable r02 = this.checkBackgroundDrawable;
        if (r02 != null) goto L8;
    L13:
        boolean r03 = false;
        if (r15 == false) goto L48;
        if (this.animatedProgressValue == 1.0f) goto L24;
        this.radOffset += (360 * r2) / this.rotationSpeed;
        float r152 = this.currentProgress;
        float r8 = this.animationProgressStart;
        float r9 = r152 - r8;
        if (r9 <= 0.0f) goto L23;
        long r10 = this.currentProgressTime + r2;
        this.currentProgressTime = r10;
        if (r10 < 300) goto L22;
        this.animatedProgressValue = r152;
        this.animationProgressStart = r152;
        this.currentProgressTime = 0;
        goto L23
    L22:
        this.animatedProgressValue = r8 + (r9 * decelerateInterpolator.getInterpolation(r10 / 300.0f));
    L23:
        invalidateParent();
    L24:
        boolean r153 = this.drawMiniProgress;
        float r82 = this.animatedProgressValue;
        if (r153 == false) goto L39;
        if (r82 >= 1.0f) goto L29;
        return;
    L29:
        if (this.previousMiniDrawable == null) goto L68;
        float r154 = this.animatedAlphaValue - (r2 / 200.0f);
        this.animatedAlphaValue = r154;
        if (r154 > 0.0f) goto L36;
        this.animatedAlphaValue = 0.0f;
        this.previousMiniDrawable = null;
        if (this.currentMiniDrawable == null) goto L35;
        r03 = true;
    L35:
        this.drawMiniProgress = r03;
    L36:
        invalidateParent();
        return;
    L68:
        return;
    L39:
        if (r82 >= 1.0f) goto L41;
        return;
    L41:
        if (this.previousDrawable == null) goto L70;
        float r155 = this.animatedAlphaValue - (r2 / 200.0f);
        this.animatedAlphaValue = r155;
        if (r155 > 0.0f) goto L45;
        this.animatedAlphaValue = 0.0f;
        this.previousDrawable = null;
    L45:
        invalidateParent();
        return;
    L70:
        return;
    L48:
        if (this.drawMiniProgress == false) goto L60;
        if (this.previousMiniDrawable == null) goto L71;
        float r156 = this.animatedAlphaValue - (r2 / 200.0f);
        this.animatedAlphaValue = r156;
        if (r156 > 0.0f) goto L57;
        this.animatedAlphaValue = 0.0f;
        this.previousMiniDrawable = null;
        if (this.currentMiniDrawable == null) goto L56;
        r03 = true;
    L56:
        this.drawMiniProgress = r03;
    L57:
        invalidateParent();
        return;
    L71:
        return;
    L60:
        if (this.previousDrawable == null) goto L72;
        float r157 = this.animatedAlphaValue - (r2 / 200.0f);
        this.animatedAlphaValue = r157;
        if (r157 > 0.0f) goto L64;
        this.animatedAlphaValue = 0.0f;
        this.previousDrawable = null;
    L64:
        invalidateParent();
        return;
    L72:
        return;
    L8:
        if (this.currentDrawable != r02) goto L10;
        throw null;
    L10:
        if (this.previousDrawable != r02) goto L13;
        throw null;
    }

    public void copyParams(RadialProgress r3) {
        this.currentProgress = r3.currentProgress;
        this.animatedProgressValue = r3.animatedProgressValue;
        this.radOffset = r3.radOffset;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidateParent();
    }

    public void draw(Canvas r20) {
        if (this.drawMiniProgress == true) goto L5;
    L65:
        Drawable r2 = this.previousDrawable;
        if (r2 == null) goto L73;
        if (this.alphaForPrevious == false) goto L70;
        r2.setAlpha((int) ((this.animatedAlphaValue * 255.0f) * this.overrideAlpha));
    L71:
        Drawable r22 = this.previousDrawable;
        RectF r3 = this.progressRect;
        r22.setBounds((int) r3.left, (int) r3.top, (int) r3.right, (int) r3.bottom);
        this.previousDrawable.draw(r20);
        goto L73
    L70:
        r2.setAlpha((int) (this.overrideAlpha * 255.0f));
    L73:
        if (this.hideCurrentDrawable == true) goto L82;
        Drawable r23 = this.currentDrawable;
        if (r23 == null) goto L82;
        if (this.previousDrawable == null) goto L79;
        r23.setAlpha((int) (((1.0f - this.animatedAlphaValue) * 255.0f) * this.overrideAlpha));
    L80:
        Drawable r24 = this.currentDrawable;
        RectF r32 = this.progressRect;
        r24.setBounds((int) r32.left, (int) r32.top, (int) r32.right, (int) r32.bottom);
        this.currentDrawable.draw(r20);
        goto L82
    L79:
        r23.setAlpha((int) (this.overrideAlpha * 255.0f));
    L82:
        if (this.currentWithRound == false) goto L84;
    L88:
        Paint r25 = this.overridePaint;
        if (r25 == null) goto L91;
    L90:
        Paint r6 = r25;
        RectF r26 = this.cicleRect;
        RectF r33 = this.progressRect;
        float r5 = r33.left;
        int r8 = this.diff;
        r26.set(r5 + r8, r33.top + r8, r33.right - r8, r33.bottom - r8);
        drawArc(r20, this.cicleRect, this.radOffset - 90.0f, Math.max(4.0f, this.animatedProgressValue * 360.0f), false, r6);
        updateAnimation(true);
        return;
    L91:
        this.progressPaint.setColor(this.progressColor);
        boolean r27 = this.previousWithRound;
        Paint r34 = this.progressPaint;
        if (r27 == false) goto L94;
        r34.setAlpha((int) ((this.animatedAlphaValue * 255.0f) * this.overrideAlpha));
    L95:
        r25 = this.progressPaint;
        goto L90
    L94:
        r34.setAlpha((int) (this.overrideAlpha * 255.0f));
        goto L95
    L84:
        if (this.previousWithRound == true) goto L88;
        updateAnimation(false);
        return;
    L5:
        if (this.currentDrawable == null) goto L65;
        if (this.miniDrawCanvas == null) goto L9;
        this.miniDrawBitmap.eraseColor(0);
    L9:
        this.currentDrawable.setAlpha((int) (this.overrideAlpha * 255.0f));
        Canvas r28 = this.miniDrawCanvas;
        Drawable r10 = this.currentDrawable;
        if (r28 == null) goto L12;
        r10.setBounds(0, 0, (int) this.progressRect.width(), (int) this.progressRect.height());
        this.currentDrawable.draw(this.miniDrawCanvas);
    L13:
        float r29 = Math.abs(this.progressRect.width() - AndroidUtilities.dp(44.0f));
        float r102 = AndroidUtilities.density;
        RectF r103 = this.progressRect;
        if (r29 >= r102) goto L16;
        float r210 = r103.centerX() + AndroidUtilities.dp(16.0f);
        float r12 = this.progressRect.centerY() + AndroidUtilities.dp(16.0f);
        int r104 = 20;
        int r13 = 0;
    L17:
        int r14 = r104 / 2;
        if (this.previousMiniDrawable != null) goto L20;
    L22:
        float r16 = 360.0f;
        float r15 = 1.0f;
    L23:
        Canvas r35 = this.miniDrawCanvas;
        if (r35 == null) goto L26;
        float r105 = (r104 + 18) + r13;
        float r17 = -90.0f;
        float r18 = 1.0f;
        r35.drawCircle(AndroidUtilities.dp(r105), AndroidUtilities.dp(r105), AndroidUtilities.dp(r14 + 1) * r15, Theme.checkboxSquare_eraserPaint);
    L34:
        if (this.miniDrawCanvas == null) goto L36;
        Bitmap r36 = this.miniDrawBitmap;
        RectF r52 = this.progressRect;
        r20.drawBitmap(r36, (int) r52.left, (int) r52.top, null);
    L36:
        Drawable r37 = this.previousMiniDrawable;
        if (r37 == null) goto L44;
        if (this.alphaForMiniPrevious == false) goto L41;
        r37.setAlpha((int) ((this.animatedAlphaValue * 255.0f) * this.overrideAlpha));
    L42:
        float r53 = r14;
        this.previousMiniDrawable.setBounds((int) (r210 - (AndroidUtilities.dp(r53) * r15)), (int) (r12 - (AndroidUtilities.dp(r53) * r15)), (int) ((AndroidUtilities.dp(r53) * r15) + r210), (int) ((AndroidUtilities.dp(r53) * r15) + r12));
        this.previousMiniDrawable.draw(r20);
        goto L44
    L41:
        r37.setAlpha((int) (this.overrideAlpha * 255.0f));
    L44:
        if (this.hideCurrentDrawable == true) goto L53;
        Drawable r38 = this.currentMiniDrawable;
        if (r38 == null) goto L53;
        if (this.previousMiniDrawable == null) goto L50;
        r38.setAlpha((int) (((r18 - this.animatedAlphaValue) * 255.0f) * this.overrideAlpha));
    L51:
        float r54 = r14;
        this.currentMiniDrawable.setBounds((int) (r210 - AndroidUtilities.dp(r54)), (int) (r12 - AndroidUtilities.dp(r54)), (int) (AndroidUtilities.dp(r54) + r210), (int) (AndroidUtilities.dp(r54) + r12));
        this.currentMiniDrawable.draw(r20);
        goto L53
    L50:
        r38.setAlpha((int) (this.overrideAlpha * 255.0f));
    L53:
        if (this.currentMiniWithRound == false) goto L55;
    L59:
        this.miniProgressPaint.setColor(this.progressColor);
        boolean r39 = this.previousMiniWithRound;
        Paint r55 = this.miniProgressPaint;
        if (r39 == false) goto L62;
        r55.setAlpha((int) ((this.animatedAlphaValue * 255.0f) * this.overrideAlpha));
    L63:
        float r56 = r14 - 2;
        this.cicleRect.set(r210 - (AndroidUtilities.dp(r56) * r15), r12 - (AndroidUtilities.dp(r56) * r15), r210 + (AndroidUtilities.dp(r56) * r15), r12 + (AndroidUtilities.dp(r56) * r15));
        r20.drawArc(this.cicleRect, this.radOffset + r17, Math.max(4.0f, this.animatedProgressValue * r16), false, this.miniProgressPaint);
        updateAnimation(true);
        return;
    L62:
        r55.setAlpha((int) (this.overrideAlpha * 255.0f));
        goto L63
    L55:
        if (this.previousMiniWithRound == true) goto L59;
        updateAnimation(false);
        return;
    L26:
        r17 = -90.0f;
        r18 = 1.0f;
        this.miniProgressBackgroundPaint.setColor(this.progressColor);
        if (this.previousMiniDrawable != null) goto L29;
    L31:
        this.miniProgressBackgroundPaint.setAlpha(255);
    L32:
        r20.drawCircle(r210, r12, AndroidUtilities.dp(12.0f), this.miniProgressBackgroundPaint);
        goto L34
    L29:
        if (this.currentMiniDrawable != null) goto L31;
        this.miniProgressBackgroundPaint.setAlpha((int) ((this.animatedAlphaValue * 255.0f) * this.overrideAlpha));
        goto L32
    L20:
        if (this.alphaForMiniPrevious == false) goto L22;
        r16 = 360.0f;
        r15 = this.animatedAlphaValue * this.overrideAlpha;
        goto L23
    L16:
        r210 = r103.centerX() + AndroidUtilities.dp(18.0f);
        r12 = this.progressRect.centerY() + AndroidUtilities.dp(18.0f);
        r104 = 22;
        r13 = 2;
        goto L17
    L12:
        RectF r211 = this.progressRect;
        r10.setBounds((int) r211.left, (int) r211.top, (int) r211.right, (int) r211.bottom);
        this.currentDrawable.draw(r20);
        goto L13
    }

    public float getAnimatedProgress() {
        return this.animatedProgressValue;
    }

    public void setBackground(Drawable r3, boolean r4, boolean r5) {
        this.lastUpdateTime = System.currentTimeMillis();
        if (r5 == false) goto L7;
        Drawable r0 = this.currentDrawable;
        if (r0 == r3) goto L7;
        this.previousDrawable = r0;
        this.previousWithRound = this.currentWithRound;
        this.animatedAlphaValue = 1.0f;
        setProgress(1.0f, r5);
    L8:
        this.currentWithRound = r4;
        this.currentDrawable = r3;
        if (r5 == true) goto L12;
        this.parent.invalidate();
        return;
    L12:
        invalidateParent();
        return;
    L7:
        this.previousDrawable = null;
        this.previousWithRound = false;
        goto L8
    }

    public void setDiff(int r1) {
        this.diff = r1;
    }

    public void setPaint(Paint r1) {
        this.overridePaint = r1;
    }

    public void setProgress(float r5, boolean r6) {
        if (this.drawMiniProgress == false) goto L16;
        if (r5 != 1.0f) goto L7;
    L22:
        if (r6 == true) goto L25;
        this.animatedProgressValue = r5;
        this.animationProgressStart = r5;
    L28:
        this.currentProgress = r5;
        this.currentProgressTime = 0;
        invalidateParent();
        return;
    L25:
        if (this.animatedProgressValue <= r5) goto L27;
        this.animatedProgressValue = r5;
    L27:
        this.animationProgressStart = this.animatedProgressValue;
        goto L28
    L7:
        if (this.animatedAlphaValue == 0.0f) goto L22;
        if (this.previousMiniDrawable == null) goto L22;
        this.animatedAlphaValue = 0.0f;
        this.previousMiniDrawable = null;
        if (this.currentMiniDrawable == null) goto L13;
        boolean r0 = true;
    L14:
        this.drawMiniProgress = r0;
        goto L22
    L13:
        r0 = false;
        goto L14
    L16:
        if (r5 == 1.0f) goto L22;
        if (this.animatedAlphaValue == 0.0f) goto L22;
        if (this.previousDrawable == null) goto L22;
        this.animatedAlphaValue = 0.0f;
        this.previousDrawable = null;
        goto L22
    }

    public void setProgressColor(int r1) {
        this.progressColor = r1;
    }

    public void setProgressRect(int r1, int r2, int r3, int r4) {
        this.progressRect.set(r1, r2, r3, r4);
    }

    public void setRotationTime(float r1) {
        this.rotationSpeed = r1;
    }

    public void setRoundRectRadius(float r1) {
        this.roundRectRadius = r1;
    }

    public void setStrokeWidth(int r1) {
        this.progressPaint.setStrokeWidth(r1);
    }
}
