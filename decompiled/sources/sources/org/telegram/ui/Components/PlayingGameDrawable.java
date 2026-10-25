package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PlayingGameDrawable extends StatusDrawable {
    private int currentAccount;
    private boolean isChat;
    private final boolean isDialogScreen;
    private long lastUpdateTime;
    private Paint paint;
    private float progress;
    private RectF rect;
    Theme.ResourcesProvider resourcesProvider;
    private boolean started;

    public static /* synthetic */ void $r8$lambda$L6EKsBtp4d2xtOjYxvz8Nn7Am68(PlayingGameDrawable r0) {
        r0.checkUpdate();
    }

    public PlayingGameDrawable(boolean r4, Theme.ResourcesProvider r5) {
        this.isChat = false;
        this.paint = new Paint(1);
        this.currentAccount = UserConfig.selectedAccount;
        this.lastUpdateTime = 0;
        this.started = false;
        this.rect = new RectF();
        this.isDialogScreen = r4;
        this.resourcesProvider = r5;
    }

    private void checkUpdate() {
        if (this.started == true) goto L5;
        return;
    L5:
        if (NotificationCenter.getInstance(this.currentAccount).isAnimationInProgress() == true) goto L8;
        update();
        return;
    L8:
        AndroidUtilities.runOnUIThread(new PlayingGameDrawable$$ExternalSyntheticLambda0(this), 100);
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L6;
        r2 = 50;
    L6:
        if (this.progress < 1.0f) goto L8;
        this.progress = 0.0f;
    L8:
        float r02 = this.progress + (r2 / 300.0f);
        this.progress = r02;
        if (r02 <= 1.0f) goto L11;
        this.progress = 1.0f;
    L11:
        invalidateLimited();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r17) {
        int r7 = AndroidUtilities.dp(10.0f);
        int r2 = getBounds().top + ((getIntrinsicHeight() - r7) / 2);
        if (this.isChat == false) goto L5;
    L4:
        int r10 = r2;
        Paint r22 = this.paint;
        if (this.isDialogScreen == false) goto L9;
        int r3 = Theme.key_chats_actionMessage;
    L10:
        r22.setColor(Theme.getColor(r3, this.resourcesProvider));
        this.rect.set(0.0f, r10, r7, r10 + r7);
        float r23 = this.progress;
        if (r23 >= 0.5f) goto L14;
        float r24 = (1.0f - (r23 / 0.5f)) * 35.0f;
    L13:
        int r25 = (int) r24;
        int r4 = 0;
    L17:
        if (r4 >= 3) goto L28;
        float r11 = (AndroidUtilities.dp(5.0f) * r4) + AndroidUtilities.dp(9.2f);
        float r5 = AndroidUtilities.dp(5.0f);
        float r12 = this.progress;
        float r112 = r11 - (r5 * r12);
        if (r4 != 2) goto L21;
        this.paint.setAlpha(Math.min(255, (int) ((r12 * 255.0f) / 0.5f)));
    L27:
        r17.drawCircle(r112, (r7 / 2) + r10, AndroidUtilities.dp(1.2f), this.paint);
        r4 = r4 + 1;
        goto L17
    L21:
        if (r4 != 0) goto L26;
        Paint r14 = this.paint;
        if (r12 <= 0.5f) goto L25;
        r14.setAlpha((int) ((1.0f - ((r12 - 0.5f) / 0.5f)) * 255.0f));
        goto L27
    L25:
        r14.setAlpha(255);
        goto L27
    L26:
        this.paint.setAlpha(255);
        goto L27
    L28:
        this.paint.setAlpha(255);
        r17.drawArc(this.rect, r25, 360 - (r25 * 2), true, this.paint);
        Paint r26 = this.paint;
        if (this.isDialogScreen == false) goto L31;
        int r32 = Theme.key_windowBackgroundWhite;
    L32:
        r26.setColor(Theme.getColor(r32));
        r17.drawCircle(AndroidUtilities.dp(4.0f), (r10 + (r7 / 2)) - AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f), this.paint);
        checkUpdate();
        return;
    L31:
        r32 = Theme.key_actionBarDefault;
        goto L32
    L14:
        r24 = ((r23 - 0.5f) * 35.0f) / 0.5f;
        goto L13
    L9:
        r3 = Theme.key_chat_status;
        goto L10
    L5:
        r2 = r2 + AndroidUtilities.dp(1.0f);
        goto L4
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
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // org.telegram.ui.Components.StatusDrawable
    public void setColor(int r1) {
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
        this.progress = 0.0f;
        this.started = false;
    }
}
