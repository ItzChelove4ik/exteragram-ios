package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ChoosingStickerStatusDrawable extends StatusDrawable {
    int color;
    Paint fillPaint;
    boolean increment;
    private boolean isChat;
    private long lastUpdateTime;
    float progress;
    private boolean started;
    Paint strokePaint;

    public ChoosingStickerStatusDrawable(boolean r4) {
        this.isChat = false;
        this.lastUpdateTime = 0;
        this.started = false;
        this.increment = true;
        if (r4 == false) goto L6;
        this.strokePaint = new Paint(1);
        this.fillPaint = new Paint(1);
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.2f));
        return;
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L5;
        r2 = 50;
    L5:
        float r02 = this.progress + (r2 / 500.0f);
        this.progress = r02;
        if (r02 < 2.0f) goto L8;
        this.progress = 0.0f;
        this.increment = !this.increment;
    L8:
        invalidateLimited();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r18) {
        float r2 = Math.min(this.progress, 1.0f);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_IN;
        if (r2 >= 0.3f) goto L5;
        float r7 = r2 / 0.3f;
    L6:
        float r42 = r4.getInterpolation(r7);
        CubicBezierInterpolator r72 = CubicBezierInterpolator.EASE_OUT;
        if (r2 >= 0.3f) goto L9;
        float r22 = 0.0f;
    L10:
        float r23 = r72.getInterpolation(r22);
        if (this.increment == false) goto L13;
        float r5 = (AndroidUtilities.dp(2.1f) * r42) + ((AndroidUtilities.dp(7.0f) - AndroidUtilities.dp(2.1f)) * (1.0f - r42));
        float r6 = AndroidUtilities.dpf2(1.5f) * (1.0f - r72.getInterpolation(this.progress / 2.0f));
    L14:
        float r3 = 11.0f;
        float r73 = AndroidUtilities.dp(11.0f) / 2.0f;
        float r10 = AndroidUtilities.dpf2(2.0f);
        float r13 = (AndroidUtilities.dpf2(0.5f) * r42) - (AndroidUtilities.dpf2(0.5f) * r23);
        Paint r24 = this.strokePaint;
        if (r24 != null) goto L18;
        r24 = Theme.chat_statusRecordPaint;
    L18:
        Paint r43 = this.fillPaint;
        if (r43 != null) goto L23;
        r43 = Theme.chat_statusPaint;
    L23:
        if (r24.getStrokeWidth() == AndroidUtilities.dp(0.8f)) goto L25;
        r24.setStrokeWidth(AndroidUtilities.dp(0.8f));
    L25:
        int r12 = 0;
    L27:
        if (r12 >= 2) goto L30;
        r18.save();
        float r16 = r3;
        r18.translate(((((r24.getStrokeWidth() / 2.0f) + r6) + (AndroidUtilities.dp(9.0f) * r12)) + getBounds().left) + AndroidUtilities.dpf2(0.2f), ((r24.getStrokeWidth() / 2.0f) + AndroidUtilities.dpf2(2.0f)) + getBounds().top);
        RectF r32 = AndroidUtilities.rectTmp;
        r32.set(0.0f, r13, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(r16) - r13);
        r18.drawOval(r32, r24);
        r18.drawCircle(r5, r73, r10, r43);
        r18.restore();
        r12 = r12 + 1;
        r3 = r16;
        goto L27
    L30:
        if (this.started == false) goto L34;
        update();
        return;
    L34:
        return;
    L13:
        r5 = (AndroidUtilities.dp(2.1f) * (1.0f - r42)) + ((AndroidUtilities.dp(7.0f) - AndroidUtilities.dp(2.1f)) * r42);
        r6 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.progress / 2.0f) * AndroidUtilities.dpf2(1.5f);
        goto L14
    L9:
        r22 = (r2 - 0.3f) / 0.7f;
        goto L10
    L5:
        r7 = 1.0f;
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(18.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(20.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void setColor(int r2) {
        if (this.color == r2) goto L5;
        this.fillPaint.setColor(r2);
        this.strokePaint.setColor(r2);
    L5:
        this.color = r2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void setIsChat(boolean r1) {
        this.isChat = r1;
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void start() {
        this.lastUpdateTime = System.currentTimeMillis();
        this.started = true;
        invalidateSelf();
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void stop() {
        this.started = false;
    }
}
