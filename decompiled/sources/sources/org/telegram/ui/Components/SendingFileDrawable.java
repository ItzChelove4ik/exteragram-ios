package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SendingFileDrawable extends StatusDrawable {
    Paint currentPaint;
    private boolean isChat;
    private long lastUpdateTime;
    private float progress;
    private boolean started;

    public SendingFileDrawable(boolean r4) {
        this.isChat = false;
        this.lastUpdateTime = 0;
        this.started = false;
        if (r4 == false) goto L6;
        Paint r42 = new Paint(1);
        this.currentPaint = r42;
        r42.setStyle(Paint.Style.STROKE);
        this.currentPaint.setStrokeCap(Paint.Cap.ROUND);
        this.currentPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        return;
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L5;
        r2 = 50;
    L5:
        this.progress += r2 / 500.0f;
    L6:
        float r02 = this.progress;
        if (r02 <= 1.0f) goto L9;
        this.progress = r02 - 1.0f;
        goto L6
    L9:
        invalidateLimited();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        Paint r0 = this.currentPaint;
        if (r0 != null) goto L5;
        r0 = Theme.chat_statusRecordPaint;
    L5:
        Paint r6 = r0;
        int r02 = 0;
    L7:
        if (r02 >= 3) goto L32;
        if (r02 != 0) goto L12;
        r6.setAlpha((int) (this.progress * 255.0f));
    L15:
        float r2 = (AndroidUtilities.dp(5.0f) * r02) + (AndroidUtilities.dp(5.0f) * this.progress);
        if (this.isChat == false) goto L18;
        float r1 = 3.0f;
    L19:
        float r3 = AndroidUtilities.dp(r1);
        float r4 = r2 + AndroidUtilities.dp(4.0f);
        float r8 = 8.0f;
        if (this.isChat == false) goto L22;
        float r12 = 7.0f;
    L23:
        float r5 = AndroidUtilities.dp(r12);
        Canvas r13 = r11;
        r13.drawLine(r2, r3, r4, r5, r6);
        if (this.isChat == false) goto L26;
        float r112 = 11.0f;
    L27:
        float r32 = AndroidUtilities.dp(r112);
        float r42 = r2 + AndroidUtilities.dp(4.0f);
        if (this.isChat == false) goto L30;
        r8 = 7.0f;
    L30:
        r13.drawLine(r2, r32, r42, AndroidUtilities.dp(r8), r6);
        r02 = r02 + 1;
        r11 = r13;
        goto L7
    L26:
        r112 = 12.0f;
        goto L27
    L22:
        r12 = 8.0f;
        goto L23
    L18:
        r1 = 4.0f;
        goto L19
    L12:
        if (r02 != 2) goto L14;
        r6.setAlpha((int) ((1.0f - this.progress) * 255.0f));
        goto L15
    L14:
        r6.setAlpha(255);
        goto L15
    L32:
        if (this.started == false) goto L38;
        update();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(14.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(18.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void setColor(int r1) {
        Paint r0 = this.currentPaint;
        if (r0 == null) goto L6;
        r0.setColor(r1);
        return;
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
