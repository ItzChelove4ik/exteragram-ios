package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;

/* loaded from: classes3.dex */
public class ProxyDrawable extends Drawable {
    private final RectF circleRect;
    private boolean connected;
    private float connectedAnimationProgress;
    private final Drawable emptyDrawable;
    private final Drawable fullDrawable;
    private boolean isEnabled;
    private long lastUpdateTime;
    private final Paint outerPaint;
    private int radOffset;

    public ProxyDrawable(Context r4) {
        Paint r0 = new Paint(1);
        this.outerPaint = r0;
        this.circleRect = new RectF();
        this.radOffset = 0;
        this.emptyDrawable = r4.getResources().getDrawable(R.drawable.outline_shield_plain_24).mutate();
        this.fullDrawable = r4.getResources().getDrawable(R.drawable.outline_shield_check).mutate();
        r0.setColor(-1);
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeWidth(AndroidUtilities.dp(1.66f));
        r0.setStrokeCap(Paint.Cap.ROUND);
        this.lastUpdateTime = SystemClock.elapsedRealtime();
    }

    private void setBounds(Drawable r5) {
        Rect r4 = getBounds();
        r5.setBounds(r4.centerX() - (r5.getIntrinsicWidth() / 2), r4.centerY() - (r5.getIntrinsicHeight() / 2), r4.centerX() + (r5.getIntrinsicWidth() / 2), r4.centerY() + (r5.getIntrinsicHeight() / 2));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        long r1 = SystemClock.elapsedRealtime();
        long r6 = r1 - this.lastUpdateTime;
        this.lastUpdateTime = r1;
        if (this.isEnabled == true) goto L6;
        setBounds(this.emptyDrawable);
        this.emptyDrawable.draw(r12);
    L11:
        if (this.isEnabled == true) goto L13;
    L17:
        boolean r0 = this.connected;
        if (r0 == false) goto L26;
        float r3 = this.connectedAnimationProgress;
        if (r3 == 1.0f) goto L26;
        float r32 = r3 + (r6 / 300.0f);
        this.connectedAnimationProgress = r32;
        if (r32 <= 1.0f) goto L24;
        this.connectedAnimationProgress = 1.0f;
    L24:
        invalidateSelf();
        return;
    L26:
        if (r0 == true) goto L34;
        float r02 = this.connectedAnimationProgress;
        if (r02 == 0.0f) goto L35;
        float r03 = r02 - (r6 / 300.0f);
        this.connectedAnimationProgress = r03;
        if (r03 >= 0.0f) goto L32;
        this.connectedAnimationProgress = 0.0f;
    L32:
        invalidateSelf();
        return;
    L35:
        return;
    L34:
        return;
    L13:
        if (this.connected == false) goto L15;
    L16:
        this.fullDrawable.setAlpha((int) (this.connectedAnimationProgress * 255.0f));
        setBounds(this.fullDrawable);
        this.fullDrawable.draw(r12);
        goto L17
    L15:
        if (this.connectedAnimationProgress == 0.0f) goto L17;
    L6:
        if (this.connected == true) goto L8;
    L9:
        setBounds(this.emptyDrawable);
        this.emptyDrawable.draw(r12);
        this.outerPaint.setAlpha((int) ((1.0f - this.connectedAnimationProgress) * 255.0f));
        this.radOffset += (int) ((360 * r6) / 1000.0f);
        int r13 = getBounds().width();
        int r2 = getBounds().height();
        int r33 = AndroidUtilities.dp(4.0f);
        this.circleRect.set((r13 / 2) - r33, (r2 / 2) - r33, (r1 + r33) + r33, (r2 + r33) + r33);
        r12.drawArc(this.circleRect, this.radOffset - 90, 90.0f, false, this.outerPaint);
        invalidateSelf();
        goto L11
    L8:
        if (this.connectedAnimationProgress == 1.0f) goto L11;
        goto L9
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.emptyDrawable.setColorFilter(r2);
        this.fullDrawable.setColorFilter(r2);
        this.outerPaint.setColorFilter(r2);
    }

    public void setConnected(boolean r1, boolean r2, boolean r3) {
        this.isEnabled = r1;
        this.connected = r2;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        if (r3 == false) goto L5;
    L9:
        invalidateSelf();
        return;
    L5:
        if (this.connected == false) goto L7;
        float r12 = 1.0f;
    L8:
        this.connectedAnimationProgress = r12;
        goto L9
    L7:
        r12 = 0.0f;
        goto L8
    }
}
