package org.telegram.ui.community.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class CommunityBanGroupConfirmCell extends FrameLayout {
    public final BackupImageView avatarView;
    public final TextView subtitleView;
    public final TextView titleView;

    /* renamed from: org.telegram.ui.community.cells.CommunityBanGroupConfirmCell$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        private final Paint pBg;
        private final Paint pRed;
        private final Paint pWhite;
        private final RectF rectF;
        final /* synthetic */ CommunityBanGroupConfirmCell this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(CommunityBanGroupConfirmCell r3, Context r4, Theme.ResourcesProvider r5) {
            this.this$0 = r3;
            this.val$resourcesProvider = r5;
            super(r4);
            Paint r32 = new Paint(1);
            this.pBg = r32;
            Paint r0 = new Paint(1);
            this.pWhite = r0;
            Paint r1 = new Paint(1);
            this.pRed = r1;
            this.rectF = new RectF();
            r0.setColor(-1);
            r32.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, r5));
            r1.setColor(Theme.getColor(Theme.key_color_red, r5));
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            super.onDraw(r6);
            this.rectF.set(0.0f, 0.0f, getWidth(), getHeight());
            RectF r0 = this.rectF;
            r6.drawRoundRect(r0, r0.width() / 2.0f, this.rectF.height() / 2.0f, this.pBg);
            this.rectF.inset(AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f));
            RectF r02 = this.rectF;
            r6.drawRoundRect(r02, r02.width() / 2.0f, this.rectF.height() / 2.0f, this.pRed);
            this.rectF.inset(AndroidUtilities.dpf2(4.67f), AndroidUtilities.dpf2(9.066f));
            RectF r03 = this.rectF;
            r6.drawRoundRect(r03, r03.height() / 2.0f, this.rectF.height() / 2.0f, this.pWhite);
        }
    }

    public CommunityBanGroupConfirmCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, r3, true);
    }

    public CommunityBanGroupConfirmCell(Context r10, Theme.ResourcesProvider r11, boolean r12) {
        super(r10);
        BackupImageView r0 = new BackupImageView(r10);
        this.avatarView = r0;
        r0.setRoundRadius(AndroidUtilities.dp(23.0f));
        addView(r0, LayoutHelper.createFrame(46, 46.0f, 19, 21.0f, 6.0f, 0.0f, 6.0f));
        if (r12 == false) goto L5;
        addView(new AnonymousClass1(this, r10, r11), LayoutHelper.createFrame(22.66f, 22.66f, 83, 48.66f, 0.0f, 0.0f, 3.6599998f));
    L5:
        TextView r122 = new TextView(r10);
        this.titleView = r122;
        r122.setTypeface(AndroidUtilities.bold());
        r122.setTextSize(1, 16.0f);
        r122.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r11));
        r122.setSingleLine(true);
        TextUtils.TruncateAt r02 = TextUtils.TruncateAt.END;
        r122.setEllipsize(r02);
        addView(r122, LayoutHelper.createFrame(-1, -2.0f, 51, 80.0f, 7.0f, 0.0f, 0.0f));
        TextView r123 = new TextView(r10);
        this.subtitleView = r123;
        r123.setTextSize(1, 14.0f);
        r123.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r11));
        r123.setSingleLine(true);
        r123.setEllipsize(r02);
        addView(r123, LayoutHelper.createFrame(-1, -2.0f, 51, 80.0f, 30.33f, 0.0f, 0.0f));
    }
}
