package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatCell extends BaseCell {
    private TLRPC.Chat chat;
    private ChatDeleteListener chatDeleteListener;
    private final ImageView deleteImageView;
    private boolean removable;

    public interface ChatDeleteListener {
        void onChatDeleted(TLRPC.Chat r1);
    }

    /* renamed from: $r8$lambda$l0F0Rue9bD8JXKw-8CwiPPCEpUk, reason: not valid java name */
    public static /* synthetic */ void m12158$r8$lambda$l0F0Rue9bD8JXKw8CwiPPCEpUk(ChatCell r0, TLRPC.Chat r1, View r2) {
        r0.lambda$setChat$0(r1, r2);
    }

    public ChatCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11, r12);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        ImageView r122 = new ImageView(r11);
        this.deleteImageView = r122;
        r122.setFocusable(false);
        r122.setScaleType(ImageView.ScaleType.CENTER);
        r122.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector)));
        r122.setImageResource(R.drawable.poll_remove);
        r122.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        r122.setContentDescription(LocaleController.getString(R.string.Delete));
        boolean r0 = LocaleController.isRTL;
        if (r0 == false) goto L5;
        int r1 = 3;
    L6:
        int r4 = r1 | 17;
        float r9 = 0.0f;
        if (r0 == false) goto L9;
        float r5 = 3.0f;
    L10:
        if (r0 == false) goto L12;
        float r7 = 0.0f;
    L13:
        addView(r122, LayoutHelper.createFrame(48, 50.0f, r4, r5, 0.0f, r7, 0.0f));
        SimpleTextView r10 = this.titleTextView;
        if (LocaleController.isRTL == false) goto L16;
        float r123 = 24.0f;
    L17:
        int r124 = AndroidUtilities.dp(r123);
        if (LocaleController.isRTL == true) goto L21;
        r9 = 24.0f;
    L21:
        r10.setPadding(r124, 0, AndroidUtilities.dp(r9), 0);
        return;
    L16:
        r123 = 0.0f;
        goto L17
    L12:
        r7 = 3.0f;
        goto L13
    L9:
        r5 = 0.0f;
        goto L10
    L5:
        r1 = 5;
        goto L6
    }

    private /* synthetic */ void lambda$setChat$0(TLRPC.Chat r1, View r2) {
        ChatDeleteListener r0 = this.chatDeleteListener;
        if (r0 == null) goto L6;
        r0.onChatDeleted(r1);
        return;
    }

    public TLRPC.Chat getChat() {
        return this.chat;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return false;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        this.deleteImageView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
    }

    public void setChat(TLRPC.Chat r4, int r5, boolean r6, int r7) {
        this.removable = r6;
        this.chat = r4;
        this.avatarDrawable.setInfo(r4);
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        this.imageView.setForUserOrChat(r4, this.avatarDrawable);
        CharSequence r0 = Emoji.replaceEmoji(r4.title, this.titleTextView.getPaint().getFontMetricsInt(), false);
        this.titleTextView.setText(r0);
        boolean r02 = ChatObject.isChannelAndNotMegaGroup(r4);
        if (r6 == false) goto L14;
        if (r7 < 1) goto L9;
        if (r02 == false) goto L7;
        String r52 = "Subscribers";
    L8:
        String r53 = LocaleController.formatPluralString(r52, r7, new Object[0]);
    L13:
        setSubtitle(r53);
    L18:
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        setDivider(true);
        ImageView r54 = this.deleteImageView;
        if (r6 == false) goto L21;
        r54.setVisibility(0);
    L22:
        this.deleteImageView.setOnClickListener(new ChatCell$$ExternalSyntheticLambda0(this, r4));
        return;
    L21:
        r54.setVisibility(4);
        goto L22
    L7:
        r52 = "Members";
        goto L8
    L9:
        if (r02 == false) goto L11;
        int r55 = R.string.DiscussChannel;
    L12:
        r53 = LocaleController.getString(r55);
        goto L13
    L11:
        r55 = R.string.AccDescrGroup;
        goto L12
    L14:
        if (r02 == false) goto L16;
        String r72 = "BoostingChannelWillReceiveBoost";
    L17:
        setSubtitle(LocaleController.formatPluralString(r72, r5, new Object[0]));
        goto L18
    L16:
        r72 = "BoostingGroupWillReceiveBoost";
        goto L17
    }

    public void setChatDeleteListener(ChatDeleteListener r1) {
        this.chatDeleteListener = r1;
    }

    public void setCounter(int r4, int r5) {
        boolean r0 = ChatObject.isChannelAndNotMegaGroup(this.chat);
        if (this.removable == true) goto L5;
        if (r0 == false) goto L18;
        String r52 = "BoostingChannelWillReceiveBoost";
    L19:
        setSubtitle(LocaleController.formatPluralString(r52, r4, new Object[0]));
        return;
    L18:
        r52 = "BoostingGroupWillReceiveBoost";
        goto L19
    L5:
        if (r5 < 1) goto L10;
        if (r0 == false) goto L8;
        String r42 = "Subscribers";
    L9:
        String r43 = LocaleController.formatPluralString(r42, r5, new Object[0]);
    L14:
        setSubtitle(r43);
        return;
    L8:
        r42 = "Members";
        goto L9
    L10:
        if (r0 == false) goto L12;
        int r44 = R.string.DiscussChannel;
    L13:
        r43 = LocaleController.getString(r44);
        goto L14
    L12:
        r44 = R.string.AccDescrGroup;
        goto L13
    }
}
