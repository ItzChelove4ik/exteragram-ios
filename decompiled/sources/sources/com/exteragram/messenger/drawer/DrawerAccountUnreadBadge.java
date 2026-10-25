package com.exteragram.messenger.drawer;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
final class DrawerAccountUnreadBadge {
    private int account;
    private int badgeWidth;
    private int countWidth;
    private final RectF rect;
    private String text;
    private int textWidth;
    private boolean visible;

    public DrawerAccountUnreadBadge() {
        this.rect = new RectF();
        this.account = -1;
    }

    public void bind(int r1, SimpleTextView r2) {
        this.account = r1;
        update(r2);
    }

    public void draw(View r6, Canvas r7) {
        if (this.visible == true) goto L5;
        return;
    L5:
        float r0 = AndroidUtilities.dp(23.0f);
        float r1 = (r6.getMeasuredHeight() - r0) / 2.0f;
        int r62 = r6.getMeasuredWidth() - AndroidUtilities.dp(12.5f);
        int r3 = this.badgeWidth;
        float r63 = r62 - r3;
        this.rect.set(r63, r1, r3 + r63, r0 + r1);
        r7.drawRoundRect(this.rect, AndroidUtilities.dp(11.5f), AndroidUtilities.dp(11.5f), Theme.dialogs_countPaint);
        float r64 = this.rect.centerY() - ((Theme.dialogs_countTextPaint.descent() + Theme.dialogs_countTextPaint.ascent()) / 2.0f);
        String r02 = this.text;
        RectF r12 = this.rect;
        r7.drawText(r02, r12.left + ((r12.width() - this.textWidth) / 2.0f), r64, Theme.dialogs_countTextPaint);
    }

    public void update(SimpleTextView r4) {
        this.visible = false;
        this.text = null;
        this.textWidth = 0;
        this.countWidth = 0;
        this.badgeWidth = 0;
        if (this.account >= 0) goto L5;
    L15:
        r4.setRightPadding(0);
        return;
    L5:
        if (UserConfig.getActivatedAccountsCount() <= 1) goto L15;
        if (NotificationsController.getInstance(this.account).showBadgeNumber == false) goto L15;
        int r1 = MessagesStorage.getInstance(this.account).getMainUnreadCount();
        if (r1 > 0) goto L13;
        r4.setRightPadding(0);
        return;
    L13:
        this.visible = true;
        this.text = Integer.toString(r1);
        this.textWidth = (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r0));
        int r0 = Math.max(AndroidUtilities.dp(10.0f), this.textWidth);
        this.countWidth = r0;
        int r02 = r0 + AndroidUtilities.dp(14.0f);
        this.badgeWidth = r02;
        r4.setRightPadding(r02 + AndroidUtilities.dp(12.0f));
    }
}
