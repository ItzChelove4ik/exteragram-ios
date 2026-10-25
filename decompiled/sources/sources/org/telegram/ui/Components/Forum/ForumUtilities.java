package org.telegram.ui.Components.Forum;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.SparseArray;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes3.dex */
public abstract class ForumUtilities {
    static SparseArray<Drawable> dialogForumDrawables;
    static Drawable dialogGeneralIcon;

    public static class GeneralTopicDrawable extends Drawable {
        int color;
        Drawable icon;
        float scale;

        public GeneralTopicDrawable(Context r1, float r2, int r3, boolean r4, boolean r5) {
            if (r4 == true) goto L5;
            Resources r12 = r1.getResources();
            if (r5 == false) goto L15;
            int r42 = R.drawable.msg_filled_general_large;
        L16:
            this.icon = r12.getDrawable(r42).mutate();
        L17:
            this.scale = r2;
            setColor(r3);
            return;
        L15:
            r42 = R.drawable.msg_filled_general;
            goto L16
        L5:
            if (ForumUtilities.dialogGeneralIcon != null) goto L11;
            Resources r13 = r1.getResources();
            if (r5 == false) goto L9;
            int r43 = R.drawable.msg_filled_general_large;
        L10:
            ForumUtilities.dialogGeneralIcon = r13.getDrawable(r43).mutate();
            goto L11
        L9:
            r43 = R.drawable.msg_filled_general;
        L11:
            this.icon = ForumUtilities.dialogGeneralIcon;
            goto L17
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r9) {
            Rect r0 = getBounds();
            float r1 = this.scale;
            Drawable r2 = this.icon;
            if (r1 != 1.0f) goto L5;
            r2.setBounds(r0);
        L6:
            this.icon.draw(r9);
            return;
        L5:
            r2.setBounds((int) (r0.centerX() - ((r0.width() / 2.0f) * this.scale)), (int) (r0.centerY() - ((r0.height() / 2.0f) * this.scale)), (int) (r0.centerX() + ((r0.width() / 2.0f) * this.scale)), (int) (r0.centerY() + ((r0.height() / 2.0f) * this.scale)));
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.icon.setAlpha(r1);
        }

        public void setColor(int r3) {
            if (this.color == r3) goto L6;
            this.color = r3;
            setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.icon.setColorFilter(r1);
        }
    }

    /* renamed from: $r8$lambda$4e-qg6ncDIbyd5NC7XptnRU1QEc, reason: not valid java name */
    public static /* synthetic */ void m10746$r8$lambda$4eqg6ncDIbyd5NC7XptnRU1QEc(TopicsFragment r1) {
        if (r1.getParentLayout() == null) goto L6;
        r1.switchToChat(true);
        return;
    }

    /* renamed from: $r8$lambda$UvVVsxZYltUo-PSD6n9UdoutH7g, reason: not valid java name */
    public static /* synthetic */ void m10747$r8$lambda$UvVVsxZYltUoPSD6n9UdoutH7g(ChatActivity r1) {
        if (r1.getParentLayout() == null) goto L6;
        TopicsFragment.prepareToSwitchAnimation(r1);
        return;
    }

    static {
        dialogForumDrawables = new SparseArray();
    }

    public static void applyTopic(ChatActivity r16, MessagesStorage.TopicKey r17) {
        if (r17.topicId == 0) goto L27;
        TLRPC.TL_forumTopic r15 = r16.getMessagesController().getTopicsController().findTopic(-r17.dialogId, r17.topicId);
        if (r15 != null) goto L9;
        return;
    L9:
        if (r17.dialogId > 0) goto L11;
        TLRPC.Chat r11 = r16.getMessagesController().getChat(Long.valueOf(-r17.dialogId));
        if (r11 != null) goto L19;
        return;
    L19:
        if (ChatObject.isMonoForum(r11) == true) goto L21;
        ArrayList<MessageObject> r10 = new ArrayList();
        r10.add(new MessageObject(r16.getCurrentAccount(), r15.topicStartMessage, false, false));
        r16.setThreadMessages(r10, r11, r15.id, r15.read_inbox_max_id, r15.read_outbox_max_id, r15);
    L25:
        r16.getMessagesController().setForumLastTopicId(-r17.dialogId, r17.topicId);
        return;
    L21:
        if (ChatObject.canManageMonoForum(UserConfig.selectedAccount, r11) == false) goto L25;
        r16.setMonoForumThreadMessages(r15.read_inbox_max_id, r15.read_outbox_max_id, r15);
        goto L25
    L11:
        if (UserObject.isBotForum(r16.getMessagesController().getUser(Long.valueOf(r17.dialogId))) == false) goto L29;
        ArrayList<MessageObject> r102 = new ArrayList();
        r102.add(new MessageObject(r16.getCurrentAccount(), r15.topicStartMessage, false, false));
        r16.setThreadMessages(r102, null, r15.id, r15.read_inbox_max_id, r15.read_outbox_max_id, r15);
        r16.getMessagesController().setForumLastTopicId(-r17.dialogId, r17.topicId);
        return;
    L29:
        return;
    }

    public static void applyTopicToMessage(MessageObject r6) {
        if (r6.getDialogId() > 0) goto L13;
        TLRPC.TL_forumTopic r0 = MessagesController.getInstance(r6.currentAccount).getTopicsController().findTopic(-r6.getDialogId(), MessageObject.getTopicId(r6.currentAccount, r6.messageOwner, true));
        if (r0 == null) goto L11;
        Drawable r62 = r6.topicIconDrawable[0];
        if ((r62 instanceof ForumBubbleDrawable) == false) goto L12;
        ((ForumBubbleDrawable) r62).setColor(r0.icon_color);
        return;
    L12:
        return;
    L11:
        return;
    }

    public static CharSequence createActionTextWithTopic(TLRPC.TL_forumTopic r7, MessageObject r8) {
        if (r7 != null) goto L5;
        return null;
    L5:
        TLRPC.MessageAction r1 = r8.messageOwner.action;
        if ((r1 instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L10;
        return AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.TopicWasCreatedAction), getTopicSpannedName(r7, null, false));
    L10:
        if ((r1 instanceof TLRPC.TL_messageActionTopicEdit) == false) goto L52;
        TLRPC.TL_messageActionTopicEdit r12 = (TLRPC.TL_messageActionTopicEdit) r1;
        long r5 = r8.getFromChatId();
        boolean r2 = DialogObject.isUserDialog(r5);
        int r82 = r8.currentAccount;
        if (r2 == false) goto L14;
        TLRPC.User r83 = MessagesController.getInstance(r82).getUser(Long.valueOf(r5));
        TLRPC.Chat r22 = null;
    L15:
        if (r83 == null) goto L17;
        String r84 = ContactsController.formatName(r83.first_name, r83.last_name);
    L20:
        int r23 = r12.flags;
        if ((r23 & 8) == 0) goto L30;
        if (r12.hidden == false) goto L26;
        int r72 = R.string.TopicHidden2;
    L28:
        return AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(r72), r84);
    L26:
        r72 = R.string.TopicShown2;
        goto L28
    L30:
        if ((r23 & 4) == 0) goto L39;
        if (r12.closed == false) goto L35;
        int r13 = R.string.TopicWasClosedAction;
    L37:
        return AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceCharSequence("%2$s", LocaleController.getString(r13), getTopicSpannedName(r7, null, false)), r84);
    L35:
        r13 = R.string.TopicWasReopenedAction;
        goto L37
    L39:
        if ((r23 & 1) == 0) goto L45;
        if ((r23 & 2) == 0) goto L45;
        TLRPC.TL_forumTopic r73 = new TLRPC.TL_forumTopic();
        r73.icon_emoji_id = r12.icon_emoji_id;
        r73.title = r12.title;
        return AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceCharSequence("%2$s", LocaleController.getString(R.string.TopicWasRenamedToAction2), getTopicSpannedName(r73, null, false)), r84);
    L45:
        if ((r23 & 1) == 0) goto L49;
        return AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceCharSequence("%2$s", LocaleController.getString(R.string.TopicWasRenamedToAction), r12.title), r84);
    L49:
        if ((r23 & 2) == 0) goto L52;
        TLRPC.TL_forumTopic r74 = new TLRPC.TL_forumTopic();
        r74.icon_emoji_id = r12.icon_emoji_id;
        r74.title = _UrlKt.FRAGMENT_ENCODE_SET;
        return AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceCharSequence("%2$s", LocaleController.getString(R.string.TopicWasIconChangedToAction), getTopicSpannedName(r74, null, false)), r84);
    L17:
        if (r22 == null) goto L19;
        r84 = r22.title;
        goto L20
    L19:
        r84 = null;
        goto L20
    L14:
        r22 = MessagesController.getInstance(r82).getChat(Long.valueOf(-r5));
        r83 = null;
    L52:
        return null;
    }

    public static GeneralTopicDrawable createGeneralTopicDrawable(Context r1, float r2, int r3, boolean r4) {
        return createGeneralTopicDrawable(r1, r2, r3, r4, false);
    }

    public static GeneralTopicDrawable createGeneralTopicDrawable(Context r6, float r7, int r8, boolean r9, boolean r10) {
        if (r6 != null) goto L6;
        return null;
    L6:
        return new GeneralTopicDrawable(r6, r7, r8, r9, r10);
    }

    public static Drawable createTopicDrawable(String r3, int r4, boolean r5) {
        if (r5 == false) goto L6;
        Drawable r52 = dialogForumDrawables.get(r4);
        if (r52 != null) goto L7;
        r52 = new ForumBubbleDrawable(r4);
        dialogForumDrawables.put(r4, r52);
    L7:
        LetterDrawable r42 = new LetterDrawable(null, 1);
        String r32 = r3.trim();
        if (r32.length() < 1) goto L10;
        String r33 = r32.substring(0, 1).toUpperCase();
    L11:
        r42.setTitle(r33);
        CombinedDrawable r34 = new CombinedDrawable(r52, r42, 0, 0);
        r34.setFullsize(true);
        return r34;
    L10:
        r33 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    L6:
        r52 = new ForumBubbleDrawable(r4);
        goto L7
    }

    public static Drawable createTopicDrawable(TLRPC.TL_forumTopic r1, boolean r2) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return createTopicDrawable(r1.title, r1.icon_color, r2);
    }

    public static void filterMessagesByTopic(long r4, ArrayList<MessageObject> r6) {
        if (r6 == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= r6.size()) goto L15;
        if (r4 == MessageObject.getTopicId(r6.get(r0).currentAccount, r6.get(r0).messageOwner, true)) goto L10;
        r6.remove(r0);
        r0 = r0 - 1;
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    public static ChatActivity getChatActivityForTopic(BaseFragment r10, long r11, TLRPC.TL_forumTopic r13, int r14, Bundle r15) {
        if (r10 == null) goto L27;
        if (r13 == null) goto L27;
        TLRPC.Chat r5 = r10.getMessagesController().getChat(Long.valueOf(r11));
        if (r15 != null) goto L9;
        r15 = new Bundle();
    L9:
        r15.putLong("chat_id", r11);
        if (r14 == 0) goto L13;
        r15.putInt("message_id", r14);
    L15:
        r15.putInt("unread_count", r13.unread_count);
        r15.putBoolean("historyPreloaded", false);
        ChatActivity r3 = new ChatActivity(r15);
        TLRPC.Message r152 = r13.topicStartMessage;
        if (r152 != null) goto L20;
        TLRPC.TL_forumTopic r112 = r10.getMessagesController().getTopicsController().findTopic(r11, r13.id);
        if (r112 == null) goto L20;
        r152 = r112.topicStartMessage;
        TLRPC.TL_forumTopic r9 = r112;
    L21:
        if (r152 != null) goto L23;
        return null;
    L23:
        ArrayList<MessageObject> r4 = new ArrayList();
        r4.add(new MessageObject(r10.getCurrentAccount(), r152, false, false));
        r3.setThreadMessages(r4, r5, r9.id, r9.read_inbox_max_id, r9.read_outbox_max_id, r9);
        if (r14 == 0) goto L26;
        r3.highlightMessageId = r14;
    L26:
        return r3;
    L20:
        r9 = r13;
        goto L21
    L13:
        if (r13.read_inbox_max_id != 0) goto L15;
        r15.putInt("message_id", r13.id);
    L27:
        return null;
    }

    public static String getMonoForumTitle(int r1, long r2, boolean r4) {
        return getMonoForumTitle(r1, MessagesController.getInstance(r1).getChat(Long.valueOf(-r2)), r4);
    }

    public static String getMonoForumTitle(int r1, TLRPC.Chat r2) {
        return getMonoForumTitle(r1, r2, false);
    }

    public static String getMonoForumTitle(int r4, TLRPC.Chat r5, boolean r6) {
        if (ChatObject.isMonoForum(r5) == false) goto L11;
        TLRPC.Chat r42 = MessagesController.getInstance(r4).getChat(Long.valueOf(r5.linked_monoforum_id));
        if (r42 == null) goto L19;
        String r43 = r42.title;
        if (r6 == false) goto L10;
        return r43;
    L10:
        return LocaleController.formatString(R.string.MonoforumTitle, new Object[]{r43});
    L19:
        if (r5 != null) goto L21;
        return null;
    L21:
        return r5.title;
    L11:
        if (r5 == null) goto L19;
        if (r5.linked_monoforum_id == 0) goto L19;
        String r44 = r5.title;
        if (r6 == false) goto L18;
        return r44;
    L18:
        return LocaleController.formatString(R.string.MonoforumTitle, new Object[]{r44});
    }

    public static CharSequence getTopicSpannedName(TLRPC.ForumTopic r1, Paint r2, boolean r3) {
        return getTopicSpannedName(r1, r2, null, r3);
    }

    public static CharSequence getTopicSpannedName(TLRPC.ForumTopic r10, Paint r11, Drawable[] r12, boolean r13) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if ((r10 instanceof TLRPC.TL_forumTopic) == false) goto L44;
        TLRPC.TL_forumTopic r102 = (TLRPC.TL_forumTopic) r10;
        if (r102.id != 1) goto L23;
        Context r1 = ApplicationLoader.applicationContext;     // Catch: Exception -> L46
        if (r11 != null) goto L9;
        int r6 = Theme.getColor(Theme.key_chat_inMenu);     // Catch: Exception -> L46
    L10:
        GeneralTopicDrawable r132 = createGeneralTopicDrawable(r1, 1.0f, r6, r13);     // Catch: Exception -> L46
        if (r11 != null) goto L13;
        int r62 = AndroidUtilities.dp(14.0f);     // Catch: Exception -> L46
    L14:
        if (r11 != null) goto L16;
        int r112 = AndroidUtilities.dp(14.0f);     // Catch: Exception -> L46
    L17:
        r132.setBounds(0, 0, r62, r112);     // Catch: Exception -> L46
        r0.append(" ");     // Catch: Exception -> L46
        if (r12 == null) goto L20;
        r12[0] = r132;     // Catch: Exception -> L46
    L20:
        r0.setSpan(new ImageSpan(r132, 2), 0, 1, 33);     // Catch: Exception -> L46
        goto L41
    L16:
        r112 = (int) r11.getTextSize();     // Catch: Exception -> L46
        goto L17
    L13:
        r62 = (int) r11.getTextSize();     // Catch: Exception -> L46
        goto L14
    L9:
        r6 = r11.getColor();     // Catch: Exception -> L46
    L41:
        if (TextUtils.isEmpty(r102.title) == true) goto L43;
        r0.append(" ");
        r0.append(r102.title);
    L43:
        return r0;
    L23:
        if (r102.icon_emoji_id == 0) goto L29;
        r0.append(" ");
        long r63 = r102.icon_emoji_id;
        if (r11 != null) goto L27;
        Paint.FontMetricsInt r113 = null;
    L28:
        AnimatedEmojiSpan r122 = new AnimatedEmojiSpan(r63, 0.95f, r113);
        r0.setSpan(r122, 0, 1, 33);
        r122.top = true;
        r122.cacheType = 13;
        goto L41
    L27:
        r113 = r11.getFontMetricsInt();
        goto L28
    L29:
        r0.append(" ");
        Drawable r133 = createTopicDrawable(r102, r13);
        if (r12 == null) goto L32;
        r12[0] = ((CombinedDrawable) r133).getBackgroundDrawable();
    L32:
        r133.setBounds(0, 0, (int) (r133.getIntrinsicWidth() * 0.65f), (int) (r133.getIntrinsicHeight() * 0.65f));
        if ((r133 instanceof CombinedDrawable) == false) goto L37;
        CombinedDrawable r123 = (CombinedDrawable) r133;
        if ((r123.getIcon() instanceof LetterDrawable) == false) goto L37;
        ((LetterDrawable) r123.getIcon()).scale = 0.7f;
    L37:
        if (r11 == null) goto L39;
        ColoredImageSpan r124 = new ColoredImageSpan(r133);
        r124.setSize((int) (Math.abs(r11.getFontMetrics().descent) + Math.abs(r11.getFontMetrics().ascent)));
        r0.setSpan(r124, 0, 1, 33);
        goto L41
    L39:
        r0.setSpan(new ImageSpan(r133), 0, 1, 33);
        goto L41
    L44:
        return "DELETED";
    }

    public static boolean isTopicCreateMessage(MessageObject r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if ((r0.messageOwner.action instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static int monoForumTopicIdToTopicId(long r0) {
        return Long.hashCode(r0);
    }

    public static ArrayList<TLRPC.TL_forumTopic> monoForumTopicToTopic(ArrayList<TLRPC.savedDialog> r5) {
        ArrayList<TLRPC.TL_forumTopic> r0 = new ArrayList(r5.size());
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        TLRPC.savedDialog r3 = r5.get(r2);
        r2 = r2 + 1;
        TLRPC.savedDialog r32 = r3;
        if ((r32 instanceof TLRPC.TL_monoForumDialog) == false) goto L3;
        r0.add(monoForumTopicToTopic((TLRPC.TL_monoForumDialog) r32));
        goto L3
    L7:
        return r0;
    }

    public static TLRPC.TL_forumTopic monoForumTopicToTopic(TLRPC.TL_monoForumDialog r4) {
        long r0 = DialogObject.getPeerDialogId(r4.peer);
        TLRPC.TL_forumTopic r2 = new TLRPC.TL_forumTopic();
        r2.id = monoForumTopicIdToTopicId(r0);
        r2.title = Long.toString(r0);
        r2.top_message = r4.top_message;
        r2.read_inbox_max_id = r4.read_inbox_max_id;
        r2.read_outbox_max_id = r4.read_outbox_max_id;
        r2.unread_reactions_count = r4.unread_reactions_count;
        r2.unread_count = r4.unread_count;
        r2.draft = r4.draft;
        r2.notify_settings = new TLRPC.TL_peerNotifySettings();
        r2.from_id = r4.peer;
        r2.nopaid_messages_exception = r4.nopaid_messages_exception;
        return r2;
    }

    public static void openTopic(BaseFragment r6, long r7, TLRPC.TL_forumTopic r9, int r10) {
        ChatActivity r62 = getChatActivityForTopic(r6, r7, r9, r10, new Bundle());
        if (r62 == null) goto L6;
        r6.presentFragment(r62);
        return;
    }

    public static void setMonoForumAvatar(int r3, TLRPC.Chat r4, AvatarDrawable r5, ImageReceiver r6) {
        if (ChatObject.isMonoForum(r4) == false) goto L5;
        TLRPC.Chat r0 = MessagesController.getInstance(r3).getChat(Long.valueOf(r4.linked_monoforum_id));
    L6:
        if (r0 == null) goto L8;
        r4 = r0;
    L8:
        r5.setInfo(r3, r4);
        r6.setForUserOrChat(r0, r5);
        return;
    L5:
        r0 = null;
        goto L6
    }

    public static void setMonoForumAvatar(int r3, TLRPC.Chat r4, AvatarDrawable r5, BackupImageView r6) {
        if (ChatObject.isMonoForum(r4) == false) goto L5;
        TLRPC.Chat r0 = MessagesController.getInstance(r3).getChat(Long.valueOf(r4.linked_monoforum_id));
    L6:
        if (r0 == null) goto L8;
        r4 = r0;
    L8:
        r5.setInfo(r3, r4);
        r6.setForUserOrChat(r0, r5);
        return;
    L5:
        r0 = null;
        goto L6
    }

    public static void setTopicIcon(BackupImageView r2, TLRPC.TL_forumTopic r3) {
        setTopicIcon(r2, r3, false, false, null);
    }

    public static void setTopicIcon(BackupImageView r6, TLRPC.TL_forumTopic r7, boolean r8, boolean r9, Theme.ResourcesProvider r10) {
        if (r7 == null) goto L27;
        if (r6 != null) goto L6;
        return;
    L6:
        if (r7.id != 1) goto L10;
        r6.setAnimatedEmojiDrawable(null);
        r6.setImageDrawable(createGeneralTopicDrawable(r6.getContext(), 0.75f, Theme.getColor(Theme.key_actionBarDefaultIcon, r10), false, r9));
        return;
    L10:
        if (r7.icon_emoji_id == 0) goto L25;
        r6.setImageDrawable(null);
        AnimatedEmojiDrawable r0 = r6.animatedEmojiDrawable;
        if (r0 == null) goto L16;
        if (r7.icon_emoji_id != r0.getDocumentId()) goto L16;
        return;
    L16:
        if (r9 == false) goto L18;
        int r92 = 11;
    L19:
        AnimatedEmojiDrawable r02 = new AnimatedEmojiDrawable(r92, UserConfig.selectedAccount, r7.icon_emoji_id);
        if (r8 == false) goto L22;
        ColorFilter r72 = new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultTitle), PorterDuff.Mode.SRC_IN);
    L23:
        r02.setColorFilter(r72);
        r6.setAnimatedEmojiDrawable(r02);
        return;
    L22:
        r72 = Theme.getAnimatedEmojiColorFilter(r10);
        goto L23
    L18:
        r92 = 10;
        goto L19
    L25:
        r6.setAnimatedEmojiDrawable(null);
        r6.setImageDrawable(createTopicDrawable(r7, false));
        return;
    }

    public static void switchAllFragmentsInStackToForum(long r5, INavigationLayout r7) {
        BaseFragment r72 = r7.getLastFragment();
        if ((r72 instanceof ChatActivity) == false) goto L15;
        ChatActivity r0 = (ChatActivity) r72;
        if ((-r0.getDialogId()) != r5) goto L15;
        if (r0.getMessagesController().getChat(Long.valueOf(r5)).forum == false) goto L15;
        if (r0.getParentLayout() == null) goto L15;
        if (r0.getParentLayout().checkTransitionAnimation() == false) goto L13;
        AndroidUtilities.runOnUIThread(new ForumUtilities$$ExternalSyntheticLambda0(r0), 500);
        goto L15
    L13:
        TopicsFragment.prepareToSwitchAnimation(r0);
    L15:
        if ((r72 instanceof TopicsFragment) == false) goto L28;
        TopicsFragment r73 = (TopicsFragment) r72;
        if ((-r73.getDialogId()) == r5) goto L19;
        return;
    L19:
        if (r73.getMessagesController().getChat(Long.valueOf(r5)).forum == false) goto L21;
        return;
    L21:
        if (r73.getParentLayout() != null) goto L23;
    L26:
        r73.switchToChat(true);
        return;
    L23:
        if (r73.getParentLayout().checkTransitionAnimation() == false) goto L26;
        AndroidUtilities.runOnUIThread(new ForumUtilities$$ExternalSyntheticLambda1(r73), 500);
        return;
    }
}
