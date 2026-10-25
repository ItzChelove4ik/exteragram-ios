package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class RoundStatusDrawable extends StatusDrawable {
    private Paint currentPaint;
    private boolean isChat;
    private long lastUpdateTime;
    private float progress;
    private int progressDirection;
    private boolean started;

    public RoundStatusDrawable(boolean r4) {
        this.isChat = false;
        this.lastUpdateTime = 0;
        this.started = false;
        this.progressDirection = 1;
        if (r4 == false) goto L6;
        this.currentPaint = new Paint(1);
        return;
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L5;
        r2 = 50;
    L5:
        float r02 = this.progress;
        int r1 = this.progressDirection;
        float r03 = r02 + ((r1 * r2) / 400.0f);
        this.progress = r03;
        if (r1 > 0) goto L8;
    L10:
        if (r1 < 0) goto L12;
    L14:
        invalidateLimited();
        return;
    L12:
        if (r03 > 0.0f) goto L14;
        this.progressDirection = 1;
        this.progress = 0.0f;
        goto L14
    L8:
        if (r03 < 1.0f) goto L10;
        this.progressDirection = -1;
        this.progress = 1.0f;
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        Paint r0 = this.currentPaint;
        if (r0 != null) goto L5;
        r0 = Theme.chat_statusPaint;
    L5:
        r0.setAlpha(((int) (this.progress * 200.0f)) + 55);
        float r1 = AndroidUtilities.dp(6.0f);
        if (this.isChat == false) goto L8;
        float r2 = 8.0f;
    L9:
        r5.drawCircle(r1, AndroidUtilities.dp(r2), AndroidUtilities.dp(4.0f), r0);
        if (this.started == false) goto L13;
        update();
        return;
    L13:
        return;
    L8:
        r2 = 9.0f;
        goto L9
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(10.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(12.0f);
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
