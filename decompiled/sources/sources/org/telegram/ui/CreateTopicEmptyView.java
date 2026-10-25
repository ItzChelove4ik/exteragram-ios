package org.telegram.ui;

import android.content.Context;
import android.graphics.Paint;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class CreateTopicEmptyView extends LinearLayout {
    BackupImageView backupImageView;
    private final Theme.ResourcesProvider resourcesProvider;

    public CreateTopicEmptyView(Context r10, FrameLayout r11, Theme.ResourcesProvider r12) {
        super(r10);
        this.resourcesProvider = r12;
        setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(18.0f), this, r11, getThemedPaint("paintChatActionBackground")));
        setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
        setOrientation(1);
        this.backupImageView = new BackupImageView(r10);
        TextView r122 = new TextView(r10);
        r122.setTypeface(AndroidUtilities.bold());
        r122.setTextSize(1, 15.0f);
        int r0 = Theme.key_chat_serviceText;
        r122.setTextColor(getThemedColor(r0));
        r122.setGravity(1);
        r122.setMaxWidth(AndroidUtilities.dp(210.0f));
        r122.setText(LocaleController.getString(R.string.AlmostDone));
        TextView r1 = new TextView(r10);
        r1.setTextSize(1, 13.0f);
        r1.setTextColor(getThemedColor(r0));
        r1.setGravity(1);
        r1.setMaxWidth(AndroidUtilities.dp(160.0f));
        r1.setText(LocaleController.getString(R.string.TopicEmptyViewDescription));
        addView(this.backupImageView, LayoutHelper.createLinear(58, 58, 1, 0, 8, 0, 8));
        addView(r122, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 2, 0));
        addView(r1, LayoutHelper.createLinear(-2, -2, 1));
        setSticker();
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    private void setSticker() {
        TLRPC.Document r0 = MediaDataController.getInstance(UserConfig.selectedAccount).getEmojiAnimatedSticker("🥳");
        if (r0 == null) goto L9;
        SvgHelper.SvgDrawable r8 = DocumentObject.getSvgThumb(r0.thumbs, Theme.key_emptyListPlaceholder, 0.2f);
        if (r8 == null) goto L7;
        r8.overrideWidthAndHeight(512, 512);
    L7:
        ImageLocation r5 = ImageLocation.getForDocument(r0);
        this.backupImageView.setImage(r5, null, "tgs", r8, null);
        return;
    }
}
