package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class TypingDotsDrawable extends StatusDrawable {
    private int currentAccount;
    private Paint currentPaint;
    private DecelerateInterpolator decelerateInterpolator;
    private float[] elapsedTimes;
    private boolean ignoreAnimationLocks;
    private boolean isChat;
    private long lastUpdateTime;
    private float[] scales;
    private float[] startTimes;
    private boolean started;

    public static /* synthetic */ void $r8$lambda$DhAqYqzFQSQmSZ7kBcUQzPeSD9M(TypingDotsDrawable r0) {
        r0.checkUpdate();
    }

    public TypingDotsDrawable(boolean r4) {
        this.currentAccount = UserConfig.selectedAccount;
        this.isChat = false;
        this.scales = new float[3];
        this.startTimes = new float[]{0.0f, 150.0f, 300.0f};
        this.elapsedTimes = new float[]{0.0f, 0.0f, 0.0f};
        this.lastUpdateTime = 0;
        this.started = false;
        this.decelerateInterpolator = new DecelerateInterpolator();
        if (r4 == false) goto L6;
        this.currentPaint = new Paint(1);
        return;
    }

    private void checkUpdate() {
        if (this.started == true) goto L5;
        return;
    L5:
        if (NotificationCenter.getInstance(this.currentAccount).isAnimationInProgress() == true) goto L7;
    L11:
        update();
        return;
    L7:
        if (this.ignoreAnimationLocks == true) goto L11;
        AndroidUtilities.runOnUIThread(new TypingDotsDrawable$$ExternalSyntheticLambda0(this), 100);
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L5;
        r2 = 50;
    L5:
        int r02 = 0;
    L7:
        if (r02 >= 3) goto L22;
        float[] r1 = this.elapsedTimes;
        float r4 = r1[r02] + r2;
        r1[r02] = r4;
        float[] r5 = this.startTimes;
        float r42 = r4 - r5[r02];
        if (r42 > 0.0f) goto L11;
        this.scales[r02] = 1.33f;
    L21:
        r02 = r02 + 1;
        goto L7
    L11:
        if (r42 > 320.0f) goto L14;
        this.scales[r02] = this.decelerateInterpolator.getInterpolation(r42 / 320.0f) + 1.33f;
        goto L21
    L14:
        if (r42 > 640.0f) goto L17;
        this.scales[r02] = (1.0f - this.decelerateInterpolator.getInterpolation((r42 - 320.0f) / 320.0f)) + 1.33f;
        goto L21
    L17:
        if (r42 < 800.0f) goto L19;
        r1[r02] = 0.0f;
        r5[r02] = 0.0f;
        this.scales[r02] = 1.33f;
        goto L21
    L19:
        this.scales[r02] = 1.33f;
        goto L21
    L22:
        invalidateLimited();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        int r0 = getBounds().left;
        if (this.isChat == false) goto L6;
        int r1 = AndroidUtilities.dp(8.5f);
        int r2 = getBounds().top;
    L5:
        int r12 = r1 + r2;
        Paint r22 = this.currentPaint;
        if (r22 != null) goto L10;
        r22 = Theme.chat_statusPaint;
        r22.setAlpha(255);
    L10:
        float r13 = r12;
        r7.drawCircle(AndroidUtilities.dp(3.0f) + r0, r13, this.scales[0] * AndroidUtilities.density, r22);
        r7.drawCircle(AndroidUtilities.dp(9.0f) + r0, r13, this.scales[1] * AndroidUtilities.density, r22);
        r7.drawCircle(r0 + AndroidUtilities.dp(15.0f), r13, this.scales[2] * AndroidUtilities.density, r22);
        checkUpdate();
        return;
    L6:
        r1 = AndroidUtilities.dp(9.3f);
        r2 = getBounds().top;
        goto L5
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(18.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(18.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public boolean isStarted() {
        return this.started;
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
        Paint r0 = this.currentPaint;
        if (r0 == null) goto L6;
        r0.setColorFilter(r1);
        return;
    }

    public void setIgnoreAnimationLocks() {
        this.ignoreAnimationLocks = true;
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
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L6;
        this.elapsedTimes[r1] = 0.0f;
        this.scales[r1] = 1.33f;
        r1 = r1 + 1;
        goto L4
    L6:
        float[] r12 = this.startTimes;
        r12[0] = 0.0f;
        r12[1] = 150.0f;
        r12[2] = 300.0f;
        this.started = false;
    }
}
