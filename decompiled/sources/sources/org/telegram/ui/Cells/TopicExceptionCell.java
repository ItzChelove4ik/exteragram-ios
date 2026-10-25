package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TopicExceptionCell extends FrameLayout {
    BackupImageView backupImageView;
    public boolean drawDivider;
    TextView subtitle;
    TextView title;

    public TopicExceptionCell(Context r11) {
        super(r11);
        BackupImageView r0 = new BackupImageView(r11);
        this.backupImageView = r0;
        addView(r0, LayoutHelper.createFrame(30, 30.0f, 16, 20.0f, 0.0f, 0.0f, 0.0f));
        TextView r02 = new TextView(r11);
        this.title = r02;
        r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.title.setTextSize(1, 16.0f);
        this.title.setTypeface(AndroidUtilities.bold());
        this.title.setMaxLines(1);
        addView(this.title, LayoutHelper.createFrame(-1, -2.0f, 0, 72.0f, 8.0f, 12.0f, 0.0f));
        TextView r03 = new TextView(r11);
        this.subtitle = r03;
        r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        this.subtitle.setTextSize(1, 14.0f);
        addView(this.subtitle, LayoutHelper.createFrame(-1, -2.0f, 0, 72.0f, 32.0f, 12.0f, 0.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.drawDivider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(72.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    public void setTopic(long r4, TLRPC.TL_forumTopic r6) {
        ForumUtilities.setTopicIcon(this.backupImageView, r6);
        BackupImageView r0 = this.backupImageView;
        if (r0 != null) goto L5;
    L9:
        this.title.setText(r6.title);
        this.subtitle.setText(MessagesController.getInstance(UserConfig.selectedAccount).getMutedString(r4, r6.id));
        return;
    L5:
        if (r0.getImageReceiver() == null) goto L9;
        if ((this.backupImageView.getImageReceiver().getDrawable() instanceof ForumUtilities.GeneralTopicDrawable) == false) goto L9;
        ((ForumUtilities.GeneralTopicDrawable) this.backupImageView.getImageReceiver().getDrawable()).setColor(Theme.getColor(Theme.key_chats_archiveBackground));
        goto L9
    }
}
