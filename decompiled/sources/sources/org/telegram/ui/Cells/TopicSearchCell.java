package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TopicSearchCell extends FrameLayout {
    BackupImageView backupImageView;
    public boolean drawDivider;
    TextView textView;
    TLRPC.TL_forumTopic topic;

    public TopicSearchCell(Context r9) {
        super(r9);
        this.backupImageView = new BackupImageView(r9);
        TextView r0 = new TextView(r9);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        boolean r92 = LocaleController.isRTL;
        BackupImageView r02 = this.backupImageView;
        if (r92 == false) goto L6;
        addView(r02, LayoutHelper.createFrame(30, 30.0f, 21, 12.0f, 0.0f, 12.0f, 0.0f));
        addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 21, 12.0f, 0.0f, 56.0f, 0.0f));
        return;
    L6:
        addView(r02, LayoutHelper.createFrame(30, 30.0f, 16, 12.0f, 0.0f, 12.0f, 0.0f));
        addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 16, 56.0f, 0.0f, 12.0f, 0.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        if (this.drawDivider == false) goto L10;
        int r0 = AndroidUtilities.dp(56.0f);
        if (LocaleController.isRTL == false) goto L8;
        r15.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - r0, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L8:
        r15.drawLine(r0, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    public TLRPC.TL_forumTopic getTopic() {
        return this.topic;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
    }

    public void setTopic(TLRPC.TL_forumTopic r5) {
        this.topic = r5;
        boolean r0 = TextUtils.isEmpty(r5.searchQuery);
        TextView r1 = this.textView;
        if (r0 == false) goto L5;
        r1.setText(AndroidUtilities.removeDiacritics(r5.title));
    L6:
        ForumUtilities.setTopicIcon(this.backupImageView, r5);
        BackupImageView r52 = this.backupImageView;
        if (r52 != null) goto L9;
        return;
    L9:
        if (r52.getImageReceiver() != null) goto L11;
        return;
    L11:
        if ((this.backupImageView.getImageReceiver().getDrawable() instanceof ForumUtilities.GeneralTopicDrawable) == false) goto L16;
        ((ForumUtilities.GeneralTopicDrawable) this.backupImageView.getImageReceiver().getDrawable()).setColor(Theme.getColor(Theme.key_chats_archiveBackground));
        return;
    L16:
        return;
    L5:
        r1.setText(AndroidUtilities.highlightText(AndroidUtilities.removeDiacritics(r5.title), r5.searchQuery, null));
        goto L6
    }
}
