package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.Forum.ForumBubbleDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LetterDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class ShareTopicCell extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final int currentAccount;
    private long currentDialog;
    private long currentTopic;
    private final BackupImageView imageView;
    private final TextView nameTextView;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.Cells.ShareTopicCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AvatarDrawable {
        final /* synthetic */ ShareTopicCell this$0;

        public AnonymousClass1(ShareTopicCell r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            super.invalidateSelf();
            ShareTopicCell.m7010$$Nest$fgetimageView(this.this$0).invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m7010$$Nest$fgetimageView(ShareTopicCell r0) {
        return r0.imageView;
    }

    public ShareTopicCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.currentAccount = UserConfig.selectedAccount;
        this.resourcesProvider = r11;
        setWillNotDraw(false);
        BackupImageView r0 = new BackupImageView(r10);
        this.imageView = r0;
        r0.setRoundRadius(AndroidUtilities.dp(28.0f));
        addView(r0, LayoutHelper.createFrame(56, 56.0f, 49, 0.0f, 7.0f, 0.0f, 0.0f));
        TextView r02 = new TextView(r10);
        this.nameTextView = r02;
        r02.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r02.setTextSize(1, 12.0f);
        r02.setMaxLines(2);
        r02.setGravity(49);
        r02.setLines(2);
        r02.setEllipsize(TextUtils.TruncateAt.END);
        addView(r02, LayoutHelper.createFrame(-1, -2.0f, 51, 6.0f, 66.0f, 6.0f, 0.0f));
        this.avatarDrawable = new AnonymousClass1(this, r11);
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f)));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public long getCurrentDialog() {
        return this.currentDialog;
    }

    public long getCurrentTopic() {
        return this.currentTopic;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(103.0f), TLObject.FLAG_30));
    }

    public void setAsNewBotForumTopic(boolean r5) {
        TextView r0 = this.nameTextView;
        if (r5 == false) goto L5;
        int r52 = R.string.ShareSendToNewTopic;
    L6:
        r0.setText(LocaleController.getString(r52));
        this.imageView.setAnimatedEmojiDrawable(null);
        ForumBubbleDrawable r53 = new ForumBubbleDrawable(ForumBubbleDrawable.serverSupportedColor[0]);
        LetterDrawable r1 = new LetterDrawable(null, 1);
        r1.setTitle(_UrlKt.FRAGMENT_ENCODE_SET);
        r1.scale = 1.8f;
        CombinedDrawable r02 = new CombinedDrawable(r53, r1, 0, 0);
        r02.setFullsize(true);
        this.imageView.setImageDrawable(r02);
        return;
    L5:
        r52 = R.string.ShareSendToOffTopic;
        goto L6
    }

    public void setTopic(TLRPC.Dialog r10, TLRPC.TL_forumTopic r11, boolean r12, CharSequence r13) {
        if (r10 != null) goto L4;
        return;
    L4:
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r10.id));
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r13 == null) goto L8;
        this.nameTextView.setText(r13);
    L17:
        if (ChatObject.isMonoForum(r0) == false) goto L36;
        this.imageView.setAnimatedEmojiDrawable(null);
        this.imageView.setImageDrawable(null);
        long r1 = DialogObject.getPeerDialogId(r11.from_id);
        boolean r4 = DialogObject.isUserDialog(r1);
        int r6 = this.currentAccount;
        if (r4 == false) goto L28;
        TLRPC.User r14 = MessagesController.getInstance(r6).getUser(Long.valueOf(r1));
        this.nameTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.avatarDrawable.setInfo(this.currentAccount, r14);
        if (r13 == null) goto L23;
        this.nameTextView.setText(r13);
    L27:
        this.imageView.setForUserOrChat(r14, this.avatarDrawable);
        this.imageView.setRoundRadius(AndroidUtilities.dp(28.0f));
    L43:
        BackupImageView r132 = this.imageView;
        if (r0 != null) goto L46;
    L49:
        int r122 = AndroidUtilities.dp(28.0f);
    L50:
        r132.setRoundRadius(r122);
        this.currentDialog = r10.id;
        this.currentTopic = r11.id;
        return;
    L46:
        if (r0.forum == false) goto L49;
        if (r12 == true) goto L49;
        r122 = AndroidUtilities.dp(16.0f);
        goto L50
    L23:
        TextView r133 = this.nameTextView;
        if (r14 == null) goto L26;
        r133.setText(ContactsController.formatName(r14.first_name, r14.last_name));
        goto L27
    L26:
        r133.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L27
    L28:
        TLRPC.Chat r15 = MessagesController.getInstance(r6).getChat(Long.valueOf(r1));
        if (r13 == null) goto L31;
        this.nameTextView.setText(r13);
    L35:
        this.avatarDrawable.setInfo(this.currentAccount, r15);
        this.imageView.setForUserOrChat(r0, this.avatarDrawable);
        goto L43
    L31:
        TextView r134 = this.nameTextView;
        if (r15 == null) goto L34;
        r134.setText(r15.title);
        goto L35
    L34:
        r134.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L35
    L36:
        long r7 = r11.icon_emoji_id;
        BackupImageView r16 = this.imageView;
        if (r7 == 0) goto L39;
        r16.setImageDrawable(null);
        this.imageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(13, UserConfig.selectedAccount, r11.icon_emoji_id));
        goto L43
    L39:
        r16.setAnimatedEmojiDrawable(null);
        ForumBubbleDrawable r135 = new ForumBubbleDrawable(r11.icon_color);
        LetterDrawable r17 = new LetterDrawable(null, 1);
        String r42 = r11.title.trim().toUpperCase();
        if (r42.length() < 1) goto L42;
        r3 = r42.substring(0, 1);
    L42:
        r17.setTitle(r3);
        r17.scale = 1.8f;
        CombinedDrawable r32 = new CombinedDrawable(r135, r17, 0, 0);
        r32.setFullsize(true);
        this.imageView.setImageDrawable(r32);
        goto L43
    L8:
        if (r10.id <= 0) goto L10;
        this.nameTextView.setText(r11.title);
        goto L17
    L10:
        if (r0 == null) goto L15;
        boolean r43 = r0.monoforum;
        TextView r5 = this.nameTextView;
        if (r43 == false) goto L14;
        r5.setText(MessagesController.getInstance(this.currentAccount).getPeerName(DialogObject.getPeerDialogId(r11.from_id)));
        goto L17
    L14:
        r5.setText(r11.title);
        goto L17
    L15:
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L17
    }
}
