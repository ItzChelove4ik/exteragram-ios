package org.telegram.ui.community.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class CommunityLinkView2 extends FrameLayout implements Theme.Colorable {
    private final ImageView arrowView;
    public final BackupImageView avatarView;
    public final Theme.ResourcesProvider resourcesProvider;
    public final TextView subtitleView;
    public final TextView titleView;

    public CommunityLinkView2(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.resourcesProvider = r12;
        BackupImageView r122 = new BackupImageView(r11);
        this.avatarView = r122;
        r122.setRoundRadius(ExteraConfig.getAvatarCorners(26.0f, false, AvatarCornerType.COMMUNITY));
        addView(r122, LayoutHelper.createFrame(26, 26.0f, 19, 16.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r123 = new LinearLayout(r11);
        r123.setOrientation(1);
        r123.setGravity(16);
        TextView r1 = new TextView(r11);
        this.titleView = r1;
        r1.setTextSize(1, 16.0f);
        r1.setSingleLine(true);
        TextUtils.TruncateAt r2 = TextUtils.TruncateAt.END;
        r1.setEllipsize(r2);
        r123.addView(r1, LayoutHelper.createLinear(-1, -2));
        TextView r13 = new TextView(r11);
        this.subtitleView = r13;
        r13.setTextSize(1, 13.0f);
        r13.setSingleLine(true);
        r13.setEllipsize(r2);
        r123.addView(r13, LayoutHelper.createLinear(-1, -2, 0.0f, 2.0f, 0.0f, 0.0f));
        addView(r123, LayoutHelper.createFrame(-1, -2.0f, 19, 58.0f, 0.0f, 48.0f, 1.0f));
        ImageView r124 = new ImageView(r11);
        this.arrowView = r124;
        r124.setImageResource(R.drawable.msg_inputarrow);
        r124.setScaleType(ImageView.ScaleType.CENTER);
        addView(r124, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 11.0f, 0.0f));
        updateColors();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        DrawableUtils.drawCommunityCardDrawable(r8, Theme.dialogs_communityCardsDrawable, this.avatarView.getLeft() + (this.avatarView.getWidth() / 2.0f), this.avatarView.getTop() + (this.avatarView.getHeight() / 2.0f), this.avatarView.getHeight());
        super.dispatchDraw(r8);
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint("paintDivider");
    L6:
        if (r02 != null) goto L8;
        r02 = Theme.dividerPaint;
    L8:
        r8.drawLine(AndroidUtilities.dp(58.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, r02);
        return;
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    public void setChat(int r3, TLRPC.Chat r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        TLRPC.ChatFull r32 = MessagesController.getInstance(r3).getChatFull(r4.id);
        setTitle(DialogObject.getShortName(r4));
        if (r32 == null) goto L7;
        int r33 = r32.linked_peers.size();
    L8:
        setSubtitle(LocaleController.formatPluralString("CommunityWithChats", r33, new Object[0]));
        this.avatarView.setForUserOrChat(r4, new AvatarDrawable(r4));
        return;
    L7:
        r33 = 0;
        goto L8
    }

    public void setSubtitle(CharSequence r1) {
        this.subtitleView.setText(r1);
    }

    public void setTitle(CharSequence r1) {
        this.titleView.setText(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        ImageView r0 = this.arrowView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText2;
        r0.setColorFilter(Theme.getColor(r1, this.resourcesProvider));
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        this.subtitleView.setTextColor(Theme.getColor(r1, this.resourcesProvider));
    }
}
