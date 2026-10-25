package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;

/* loaded from: classes7.dex */
public class VideoForwardDrawable extends Drawable {
    private static final int[] playPath = null;
    private boolean animating;
    private float animationProgress;
    private Path clippingPath;
    private VideoForwardDrawableDelegate delegate;
    private float enterAnimationProgress;
    private boolean isOneShootAnimation;
    private boolean isRound;
    private long lastAnimationTime;
    private int lastClippingPath;
    private boolean leftSide;
    private Paint paint;
    private Path path1;
    private float playScaleFactor;
    private boolean showing;
    private TextPaint textPaint;
    private long time;
    private String timeStr;

    public interface VideoForwardDrawableDelegate {
        void invalidate();

        void onAnimationEnd();
    }

    static {
        playPath = new int[]{10, 7, 26, 16, 10, 25};
    }

    public VideoForwardDrawable(boolean r6) {
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path1 = new Path();
        this.playScaleFactor = 1.0f;
        this.isRound = r6;
        this.paint.setColor(-1);
        this.textPaint.setColor(-1);
        this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.path1.reset();
        int r62 = 0;
    L4:
        if (r62 >= (playPath.length / 2)) goto L10;
        Path r2 = this.path1;
        if (r62 != 0) goto L8;
        int r3 = r62 * 2;
        r2.moveTo(AndroidUtilities.dp(r0[r3]), AndroidUtilities.dp(r0[r3 + 1]));
    L9:
        r62 = r62 + 1;
        goto L4
    L8:
        int r32 = r62 * 2;
        r2.lineTo(AndroidUtilities.dp(r0[r32]), AndroidUtilities.dp(r0[r32 + 1]));
        goto L9
    L10:
        this.path1.close();
    }

    private void invalidate() {
        VideoForwardDrawableDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    L6:
        invalidateSelf();
    }

    public void addTime(long r3) {
        long r0 = this.time + r3;
        this.time = r0;
        this.timeStr = LocaleController.formatPluralString("Seconds", (int) (r0 / 1000), new Object[0]);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r13) {
        Rect r0 = getBounds();
        int r1 = r0.left + ((r0.width() - getIntrinsicWidth()) / 2);
        int r2 = r0.top + ((r0.height() - getIntrinsicHeight()) / 2);
        if (this.leftSide == false) goto L5;
        int r12 = r1 - ((r0.width() / 4) - AndroidUtilities.dp(16.0f));
    L6:
        r13.save();
        if (this.isRound == true) goto L9;
        r13.clipRect(r0.left, r0.top, r0.right, r0.bottom);
    L17:
        if (this.isOneShootAnimation == true) goto L19;
        this.paint.setAlpha((int) (this.enterAnimationProgress * 80.0f));
        this.textPaint.setAlpha((int) (this.enterAnimationProgress * 255.0f));
    L23:
        int r3 = Math.max(r0.width(), r0.height()) / 4;
        int r8 = 1;
        if (this.leftSide == false) goto L26;
        int r5 = -1;
    L27:
        r13.drawCircle((r3 * r5) + r12, AndroidUtilities.dp(16.0f) + r2, Math.max(r0.width(), r0.height()) / 2, this.paint);
        r13.restore();
        String r02 = this.timeStr;
        if (r02 == null) goto L33;
        int r32 = getIntrinsicWidth();
        if (this.leftSide == false) goto L32;
        r8 = -1;
    L32:
        r13.drawText(r02, (r32 * r8) + r12, (getIntrinsicHeight() + r2) + AndroidUtilities.dp(15.0f), this.textPaint);
    L33:
        r13.save();
        float r03 = this.playScaleFactor;
        float r14 = r12;
        float r33 = r2;
        r13.scale(r03, r03, r14, (getIntrinsicHeight() / 2.0f) + r33);
        if (this.leftSide == false) goto L36;
        r13.rotate(180.0f, r14, r2 + (getIntrinsicHeight() / 2));
    L36:
        r13.translate(r14, r33);
        float r04 = this.animationProgress;
        if (r04 <= 0.6f) goto L39;
    L46:
        r13.translate(AndroidUtilities.dp(18.0f), 0.0f);
        float r15 = this.animationProgress;
        if (r15 >= 0.2f) goto L49;
    L58:
        r13.translate(AndroidUtilities.dp(18.0f), 0.0f);
        float r05 = this.animationProgress;
        if (r05 >= 0.4f) goto L61;
    L70:
        r13.restore();
        if (this.animating == false) goto L110;
        long r06 = System.currentTimeMillis();
        long r22 = r06 - this.lastAnimationTime;
        if (r22 <= 17) goto L75;
        r22 = 17;
    L75:
        this.lastAnimationTime = r06;
        float r132 = this.animationProgress;
        if (r132 >= 1.0f) goto L92;
        float r133 = r132 + (r22 / 800.0f);
        this.animationProgress = r133;
        if (this.isOneShootAnimation == true) goto L86;
        if (r133 >= 1.0f) goto L82;
    L90:
        invalidate();
        goto L92
    L82:
        if (this.showing == false) goto L84;
        this.animationProgress = 0.0f;
        goto L90
    L84:
        this.animationProgress = 1.0f;
        goto L90
    L86:
        if (r133 < 1.0f) goto L90;
        this.animationProgress = 0.0f;
        this.animating = false;
        this.time = 0;
        this.timeStr = null;
        VideoForwardDrawableDelegate r134 = this.delegate;
        if (r134 == null) goto L90;
        r134.onAnimationEnd();
    L92:
        if (this.isOneShootAnimation == true) goto L111;
        boolean r135 = this.showing;
        if (r135 == false) goto L98;
        float r16 = this.enterAnimationProgress;
        if (r16 == 1.0f) goto L98;
        this.enterAnimationProgress = r16 + 0.10666667f;
        invalidate();
    L102:
        float r136 = this.enterAnimationProgress;
        if (r136 >= 0.0f) goto L107;
        this.enterAnimationProgress = 0.0f;
        return;
    L107:
        if (r136 <= 1.0f) goto L112;
        this.enterAnimationProgress = 1.0f;
        return;
    L112:
        return;
    L98:
        if (r135 == true) goto L102;
        float r137 = this.enterAnimationProgress;
        if (r137 == 0.0f) goto L102;
        this.enterAnimationProgress = r137 - 0.10666667f;
        invalidate();
        goto L102
    L111:
        return;
    L110:
        return;
    L61:
        if (r05 > 1.0f) goto L70;
        float r07 = r05 - 0.4f;
        if (r07 >= 0.4f) goto L65;
        int r08 = Math.min(255, (int) ((r07 * 255.0f) / 0.2f));
    L67:
        if (this.isOneShootAnimation == true) goto L69;
        r08 = (int) (r08 * this.enterAnimationProgress);
    L69:
        this.paint.setAlpha(r08);
        r13.drawPath(this.path1, this.paint);
        goto L70
    L65:
        r08 = (int) ((1.0f - ((r07 - 0.4f) / 0.2f)) * 255.0f);
        goto L67
    L49:
        if (r15 > 0.8f) goto L58;
        float r17 = r15 - 0.2f;
        if (r17 >= 0.4f) goto L53;
        int r18 = Math.min(255, (int) ((r17 * 255.0f) / 0.2f));
    L55:
        if (this.isOneShootAnimation == true) goto L57;
        r18 = (int) (r18 * this.enterAnimationProgress);
    L57:
        this.paint.setAlpha(r18);
        r13.drawPath(this.path1, this.paint);
        goto L58
    L53:
        r18 = (int) ((1.0f - ((r17 - 0.4f) / 0.2f)) * 255.0f);
        goto L55
    L39:
        if (r04 >= 0.4f) goto L41;
        int r09 = Math.min(255, (int) ((r04 * 255.0f) / 0.2f));
    L43:
        if (this.isOneShootAnimation == true) goto L45;
        r09 = (int) (r09 * this.enterAnimationProgress);
    L45:
        this.paint.setAlpha(r09);
        r13.drawPath(this.path1, this.paint);
        goto L46
    L41:
        r09 = (int) ((1.0f - ((r04 - 0.4f) / 0.2f)) * 255.0f);
        goto L43
    L26:
        r5 = 1;
        goto L27
    L19:
        float r34 = this.animationProgress;
        Paint r10 = this.paint;
        if (r34 > 0.7f) goto L22;
        r10.setAlpha((int) (Math.min(1.0f, r34 / 0.3f) * 80.0f));
        this.textPaint.setAlpha((int) (Math.min(1.0f, this.animationProgress / 0.3f) * 255.0f));
        goto L23
    L22:
        r10.setAlpha((int) ((1.0f - ((r34 - 0.7f) / 0.3f)) * 80.0f));
        this.textPaint.setAlpha((int) ((1.0f - ((this.animationProgress - 0.7f) / 0.3f)) * 255.0f));
        goto L23
    L9:
        if (this.clippingPath != null) goto L11;
        this.clippingPath = new Path();
    L11:
        int r35 = ((r0.left + (r0.top << 8)) + (r0.bottom << 16)) + (r0.right << 24);
        if (this.lastClippingPath == r35) goto L14;
        this.clippingPath.reset();
        RectF r52 = AndroidUtilities.rectTmp;
        r52.set(r0);
        this.clippingPath.addOval(r52, Path.Direction.CCW);
        this.lastClippingPath = r35;
    L14:
        r13.clipPath(this.clippingPath);
        goto L17
    L5:
        r12 = r1 + ((r0.width() / 4) + AndroidUtilities.dp(16.0f));
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return AndroidUtilities.dp(32.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public boolean isAnimating() {
        return this.animating;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        this.textPaint.setAlpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setDelegate(VideoForwardDrawableDelegate r1) {
        this.delegate = r1;
    }

    public void setLeftSide(boolean r4) {
        boolean r0 = this.leftSide;
        if (r0 == r4) goto L5;
    L9:
        if (r0 == r4) goto L11;
        this.time = 0;
        this.timeStr = null;
    L11:
        this.leftSide = r4;
        startAnimation();
        return;
    L5:
        if (this.animationProgress < 1.0f) goto L9;
        if (this.isOneShootAnimation == false) goto L9;
    }

    public void setOneShootAnimation(boolean r3) {
        if (this.isOneShootAnimation == r3) goto L6;
        this.isOneShootAnimation = r3;
        this.timeStr = null;
        this.time = 0;
        this.animationProgress = 0.0f;
        return;
    }

    public void setPlayScaleFactor(float r1) {
        this.playScaleFactor = r1;
        invalidate();
    }

    public void setShowing(boolean r1) {
        this.showing = r1;
        invalidate();
    }

    public void setTime(long r4) {
        this.time = r4;
        if (r4 < 1000) goto L6;
        this.timeStr = LocaleController.formatPluralString("Seconds", (int) (r4 / 1000), new Object[0]);
        return;
    L6:
        this.timeStr = null;
    }

    public void startAnimation() {
        this.animating = true;
        this.animationProgress = 0.0f;
        invalidateSelf();
    }
}
