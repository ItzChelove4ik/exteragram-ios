package com.exteragram.messenger.drawer;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
final class DrawerUnreadBadge {
    private int badgeWidth;
    private int counter;
    private int defaultTextPaddingEnd;
    private final RectF rect;
    private String text;
    private int textWidth;
    private boolean visible;

    public DrawerUnreadBadge() {
        this.rect = new RectF();
        this.defaultTextPaddingEnd = Integer.MIN_VALUE;
    }

    private void applyTextPadding(TextView r3, int r4) {
        if (r3.getPaddingEnd() != r4) goto L5;
        return;
    L5:
        r3.setPaddingRelative(r3.getPaddingStart(), r3.getPaddingTop(), r4, r3.getPaddingBottom());
    }

    private void rememberDefaultTextPadding(TextView r3) {
        if (this.defaultTextPaddingEnd == Integer.MIN_VALUE) goto L5;
        return;
    L5:
        this.defaultTextPaddingEnd = r3.getPaddingEnd();
    }

    private void restoreTextPadding(TextView r3) {
        int r0 = this.defaultTextPaddingEnd;
        if (r0 != Integer.MIN_VALUE) goto L5;
        r0 = 0;
    L5:
        applyTextPadding(r3, r0);
    }

    public void bind(int r1, TextView r2) {
        this.counter = r1;
        update(r2);
    }

    public void draw(View r5, Canvas r6) {
        if (this.visible == true) goto L5;
        return;
    L5:
        float r0 = AndroidUtilities.dp(12.5f);
        int r52 = r5.getMeasuredWidth() - AndroidUtilities.dp(16.5f);
        int r1 = this.badgeWidth;
        float r53 = r52 - r1;
        this.rect.set(r53, r0, r1 + r53, AndroidUtilities.dp(23.0f) + r0);
        r6.drawRoundRect(this.rect, AndroidUtilities.dp(11.5f), AndroidUtilities.dp(11.5f), Theme.dialogs_countGrayPaint);
        String r54 = this.text;
        RectF r12 = this.rect;
        r6.drawText(r54, r12.left + ((r12.width() - this.textWidth) / 2.0f), r0 + AndroidUtilities.dp(16.0f), Theme.dialogs_countTextPaint);
    }

    public void update(TextView r3) {
        rememberDefaultTextPadding(r3);
        this.visible = false;
        this.text = null;
        this.textWidth = 0;
        this.badgeWidth = 0;
        int r0 = this.counter;
        if (r0 > 0) goto L6;
        restoreTextPadding(r3);
        return;
    L6:
        this.visible = true;
        this.text = Integer.toString(r0);
        this.textWidth = (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r0));
        int r02 = Math.max(AndroidUtilities.dp(10.0f), this.textWidth) + AndroidUtilities.dp(14.0f);
        this.badgeWidth = r02;
        applyTextPadding(r3, (this.defaultTextPaddingEnd + r02) + AndroidUtilities.dp(12.0f));
    }
}
