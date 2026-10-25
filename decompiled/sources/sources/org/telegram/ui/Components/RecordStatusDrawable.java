package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class RecordStatusDrawable extends StatusDrawable {
    int alpha;
    Paint currentPaint;
    private boolean isChat;
    private long lastUpdateTime;
    private float progress;
    private RectF rect;
    private boolean started;

    public RecordStatusDrawable(boolean r4) {
        this.isChat = false;
        this.lastUpdateTime = 0;
        this.started = false;
        this.rect = new RectF();
        this.alpha = 255;
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
        this.progress += r2 / 800.0f;
    L6:
        float r02 = this.progress;
        if (r02 <= 1.0f) goto L9;
        this.progress = r02 - 1.0f;
        goto L6
    L9:
        invalidateLimited();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r9) {
        Paint r0 = this.currentPaint;
        if (r0 != null) goto L5;
        r0 = Theme.chat_statusRecordPaint;
    L5:
        Paint r6 = r0;
        float r1 = 2.0f;
        if (r6.getStrokeWidth() == AndroidUtilities.dp(2.0f)) goto L8;
        r6.setStrokeWidth(AndroidUtilities.dp(2.0f));
    L8:
        r9.save();
        int r02 = getIntrinsicHeight() / 2;
        if (this.isChat == false) goto L11;
        r1 = 1.0f;
    L11:
        r9.translate(0.0f, r02 + AndroidUtilities.dp(r1));
        int r03 = 0;
    L13:
        if (r03 >= 4) goto L21;
        if (r03 != 0) goto L16;
        r6.setAlpha((int) (this.alpha * this.progress));
    L20:
        float r2 = (AndroidUtilities.dp(4.0f) * r03) + (AndroidUtilities.dp(4.0f) * this.progress);
        float r3 = -r2;
        this.rect.set(r3, r3, r2, r2);
        r9.drawArc(this.rect, -15.0f, 30.0f, false, r6);
        r03 = r03 + 1;
        goto L13
    L16:
        int r12 = this.alpha;
        if (r03 != 3) goto L19;
        r6.setAlpha((int) (r12 * (1.0f - this.progress)));
        goto L20
    L19:
        r6.setAlpha(r12);
        goto L20
    L21:
        r9.restore();
        if (this.started == false) goto L29;
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
        this.alpha = r1;
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
