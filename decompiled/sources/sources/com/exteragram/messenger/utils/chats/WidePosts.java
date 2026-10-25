package com.exteragram.messenger.utils.chats;

import android.graphics.Point;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.feed.FeedMessageUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public abstract class WidePosts {
    private static final Map<MessageObject, CommentsPostContext> COMMENTS_POST_CONTEXTS = null;
    private static final Map<MessageObject.GroupedMessagePosition, GroupPositionState> GROUP_POSITION_STATES = null;

    public static final class CommentsPostAuthorLoader {
        private final TLRPC.Chat channel;
        private ChatActivity chatActivity;
        private final int currentAccount;
        private final ArrayList<MessageObject> messages;
        private MessageObject originalMessage;

        public static /* synthetic */ void $r8$lambda$BFXLOij4A7dOxbcNVSTT3ma_XC4(CommentsPostAuthorLoader r0, BooleanSupplier r1, TLObject r2, int r3) {
            r0.lambda$load$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gjjzueT_z3itgCxvhwJtxlXATpg(CommentsPostAuthorLoader r0, BooleanSupplier r1, TLObject r2, int r3) {
            r0.lambda$load$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$hvJG59DvSHxFWdF_H3X8Icv7E4c(CommentsPostAuthorLoader r0, BooleanSupplier r1, int r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$load$2(r1, r2, r3, r4);
        }

        public CommentsPostAuthorLoader(int r2, TLRPC.Chat r3) {
            this.messages = new ArrayList();
            this.currentAccount = r2;
            this.channel = r3;
        }

        private void apply() {
            if (this.messages.isEmpty() == true) goto L17;
            int r0 = this.messages.size();
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L9;
            MessageObject r2 = this.messages.get(r1);
            WidePosts.registerCommentsPostContext(r2, this.channel, this.originalMessage);
            r2.forceUpdate = true;
            r1 = r1 + 1;
            goto L6
        L9:
            if (this.originalMessage == null) goto L15;
            ChatActivity r02 = this.chatActivity;
            if (r02 == null) goto L16;
            r02.updateWidePostsCommentsAuthor(this.messages);
            return;
        L16:
            return;
        L15:
            return;
        }

        private /* synthetic */ void lambda$load$0(BooleanSupplier r4, TLObject r5, int r6) {
            if (r4 == null) goto L7;
            if (r4.getAsBoolean() == false) goto L7;
            return;
        L7:
            if ((r5 instanceof TLRPC.messages_Messages) == false) goto L16;
            TLRPC.messages_Messages r52 = (TLRPC.messages_Messages) r5;
            int r1 = 0;
            MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
            int r42 = r52.messages.size();
        L9:
            if (r1 >= r42) goto L16;
            TLRPC.Message r0 = r52.messages.get(r1);
            if (r0 == null) goto L15;
            if (r0.id != r6) goto L15;
            this.originalMessage = new MessageObject(this.currentAccount, r0, true, true);
        L15:
            r1 = r1 + 1;
        L16:
            apply();
        }

        private /* synthetic */ void lambda$load$1(BooleanSupplier r3, TLObject r4, int r5) {
            NotificationCenter.getInstance(this.currentAccount).doOnIdle(new WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda2(this, r3, r4, r5));
        }

        private /* synthetic */ void lambda$load$2(BooleanSupplier r1, int r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda1(this, r1, r3, r2));
        }

        public MessageObject getOriginalMessage() {
            return this.originalMessage;
        }

        public int load(int r4, BooleanSupplier r5) {
            TLRPC.TL_channels_getMessages r0 = new TLRPC.TL_channels_getMessages();
            r0.channel = MessagesController.getInputChannel(this.channel);
            r0.id.add(Integer.valueOf(r4));
            return ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda0(this, r5, r4));
        }

        public void setMessages(ArrayList<MessageObject> r2, ChatActivity r3) {
            this.messages.clear();
            if (r2 == null) goto L5;
            this.messages.addAll(r2);
        L5:
            this.chatActivity = r3;
            apply();
        }
    }

    public static final class CommentsPostContext {
        final long channelId;
        String postAuthor;

        public CommentsPostContext(long r1, String r3) {
            this.channelId = r1;
            this.postAuthor = r3;
        }

        public void updatePostAuthor(String r1) {
            if (r1 == null) goto L5;
            this.postAuthor = r1;
            return;
        }
    }

    public static final class GroupPositionState {
        private int appliedLeftSpanOffset;
        private int appliedSpanSize;
        private int appliedWidth;
        final int leftSpanOffset;
        private int scaledForWidth;
        final int spanSize;
        final int width;

        /* renamed from: -$$Nest$fgetscaledForWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1788$$Nest$fgetscaledForWidth(GroupPositionState r0) {
            return r0.scaledForWidth;
        }

        /* renamed from: -$$Nest$fputscaledForWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1789$$Nest$fputscaledForWidth(GroupPositionState r0, int r1) {
            r0.scaledForWidth = r1;
        }

        public GroupPositionState(MessageObject.GroupedMessagePosition r2) {
            int r0 = r2.pw;
            this.appliedWidth = r0;
            this.width = r0;
            int r02 = r2.spanSize;
            this.appliedSpanSize = r02;
            this.spanSize = r02;
            int r22 = r2.leftSpanOffset;
            this.appliedLeftSpanOffset = r22;
            this.leftSpanOffset = r22;
        }

        public boolean apply(MessageObject.GroupedMessagePosition r4) {
            int r0 = this.appliedWidth;
            int r1 = r4.pw;
            if (r0 == r1) goto L5;
        L10:
            boolean r02 = true;
        L11:
            this.appliedWidth = r1;
            this.appliedSpanSize = r4.spanSize;
            this.appliedLeftSpanOffset = r4.leftSpanOffset;
            return r02;
        L5:
            if (this.appliedSpanSize != r4.spanSize) goto L10;
            if (this.appliedLeftSpanOffset != r4.leftSpanOffset) goto L10;
            r02 = false;
            goto L11
        }

        public void restore(MessageObject.GroupedMessagePosition r2) {
            r2.pw = this.width;
            r2.spanSize = this.spanSize;
            r2.leftSpanOffset = this.leftSpanOffset;
        }
    }

    static {
        GROUP_POSITION_STATES = new WeakHashMap();
        COMMENTS_POST_CONTEXTS = new WeakHashMap();
    }

    public static void copyCommentsPostContext(MessageObject r1, MessageObject r2) {
        if (r1 == null) goto L9;
        if (r2 == null) goto L11;
        Map<MessageObject, CommentsPostContext> r0 = COMMENTS_POST_CONTEXTS;
        CommentsPostContext r12 = r0.get(r1);
        if (r12 == null) goto L10;
        r0.put(r2, r12);
        return;
    L10:
        return;
    L11:
        return;
    }

    private static int getBaseGroupVisualWidth(MessageObject.GroupedMessages r10) {
        ArrayList<MessageObject.GroupedMessagePosition> r0 = r10.posArray;
        int r1 = r0.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L7;
        MessageObject.GroupedMessagePosition r5 = r0.get(r4);
        r4 = r4 + 1;
        MessageObject.GroupedMessagePosition r52 = r5;
        if (r52 == null) goto L3;
        r3 = Math.max(r3, r52.maxY);
        goto L3
    L7:
        int r02 = 0;
        int r12 = 0;
    L8:
        if (r02 > r3) goto L21;
        ArrayList<MessageObject.GroupedMessagePosition> r42 = r10.posArray;
        int r53 = r42.size();
        int r6 = 0;
        int r7 = 0;
    L10:
        if (r7 >= r53) goto L19;
        MessageObject.GroupedMessagePosition r8 = r42.get(r7);
        r7 = r7 + 1;
        MessageObject.GroupedMessagePosition r82 = r8;
        if (r82 == null) goto L10;
        if (r82.minY > r02) goto L10;
        if (r82.maxY < r02) goto L10;
        r6 = r6 + r82.pw;
        goto L10
    L19:
        r12 = Math.max(r12, r6);
        r02 = r02 + 1;
        goto L8
    L21:
        return Math.min(1000, r12);
    }

    private static TLRPC.Chat getBroadcastChannel(MessageObject r4) {
        long r0 = getChannelId(r4);
        if (r0 != 0) goto L5;
        return null;
    L5:
        return MessagesController.getInstance(r4.currentAccount).getChat(Long.valueOf(r0));
    }

    public static int getBubbleRight(int r2) {
        return Math.max(AndroidUtilities.dp(120.0f), r2 - AndroidUtilities.dp(9.0f));
    }

    private static long getChannelId(MessageObject r5) {
        if (r5 != null) goto L5;
    L21:
        return 0;
    L5:
        if (r5.messageOwner == null) goto L21;
        long r2 = getCommentsChannelId(r5);
        if (r2 == 0) goto L10;
        return r2;
    L10:
        TLRPC.Peer r22 = r5.messageOwner.peer_id;
        if (r22 == null) goto L13;
        long r23 = r22.channel_id;
    L15:
        if (r23 != 0) goto L20;
        long r24 = r5.getDialogId();
        if (r24 < 0) goto L19;
        return 0;
    L19:
        return -r24;
    L20:
        return r23;
    L13:
        r23 = 0;
        goto L15
    }

    private static long getCommentsChannelId(MessageObject r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        if (r3.messageOwner == null) goto L13;
        CommentsPostContext r32 = getCommentsPostContext(r3);
        if (r32 != null) goto L10;
        return 0;
    L10:
        return r32.channelId;
    L13:
        return 0;
    }

    public static String getCommentsPostAuthor(MessageObject r0) {
        CommentsPostContext r02 = getCommentsPostContext(r0);
        if (r02 != null) goto L5;
        return null;
    L5:
        return r02.postAuthor;
    }

    private static CommentsPostContext getCommentsPostContext(MessageObject r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return COMMENTS_POST_CONTEXTS.get(r1);
    }

    public static int getDefaultGroupWidth() {
        Point r0 = AndroidUtilities.displaySize;
        int r1 = r0.x;
        if (r1 <= r0.y) goto L5;
        boolean r02 = true;
    L7:
        if (AndroidUtilities.isInMultiwindow == false) goto L9;
        return r1;
    L9:
        if (AndroidUtilities.isTablet() == true) goto L11;
        return r1;
    L11:
        if (AndroidUtilities.isSmallTablet() == false) goto L14;
        if (r02 == true) goto L14;
        return r1;
    L14:
        return r1 - Math.max((r1 * 35) / 100, AndroidUtilities.dp(320.0f));
    L5:
        r02 = false;
        goto L7
    }

    public static long getEmbeddedProfileDialogId(MessageObject r5) {
        if (shouldEmbedFeedChannelProfileAvatar(r5) == false) goto L10;
        TLRPC.Chat r52 = FeedMessageUtils.resolveDisplayChannel(r5);
        if (r52 != null) goto L7;
        return 0;
    L7:
        return -r52.id;
    L10:
        if (shouldEmbedCommentsChannelProfileAvatar(r5) == false) goto L17;
        long r3 = getCommentsChannelId(r5);
        if (r3 != 0) goto L14;
        return 0;
    L14:
        return -r3;
    L17:
        if (shouldEmbedAuthorProfileAvatar(r5) == false) goto L22;
        TLRPC.Peer r53 = r5.messageOwner.from_id;
        if (r53 == null) goto L22;
        return DialogObject.getPeerDialogId(r53);
    L22:
        return 0;
    }

    public static String getFeedPostAuthor(MessageObject r1) {
        if (FeedMessageUtils.hasProfileSignature(r1) == true) goto L5;
        return null;
    L5:
        return r1.messageOwner.post_author;
    }

    public static long getFeedSignatureProfileDialogId(MessageObject r7) {
        if (FeedMessageUtils.hasProfileSignature(r7) == false) goto L10;
        TLRPC.Peer r0 = r7.messageOwner.from_id;
        if (r0 == null) goto L10;
        long r3 = DialogObject.getPeerDialogId(r0);
        if (r3 == DialogObject.getPeerDialogId(r7.messageOwner.peer_id)) goto L10;
        return r3;
    L10:
        return 0;
    }

    public static int getImageHeight(int r1, int r2, int r3, int r4) {
        return Math.max(AndroidUtilities.dp(120.0f), Math.min(Math.round(r4 * (r2 / r1)), Math.max(r3, Math.min(AndroidUtilities.getPhotoSize(), r4 + AndroidUtilities.dp(100.0f)))));
    }

    private static MessageObject getPrimaryGroupMessage(MessageObject.GroupedMessages r8) {
        if (r8 != null) goto L6;
        return null;
    L6:
        if (r8.reversed == false) goto L8;
        int r1 = 10;
    L9:
        ArrayList<MessageObject> r2 = r8.messages;
        if (r2 == null) goto L18;
        int r4 = r2.size();
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L18;
        MessageObject r6 = r2.get(r5);
        r5 = r5 + 1;
        MessageObject r62 = r6;
        MessageObject.GroupedMessagePosition r7 = r8.getPosition(r62);
        if (r7 == null) goto L12;
        if ((r7.flags & r1) != r1) goto L12;
        return r62;
    L18:
        ArrayList<MessageObject> r12 = r8.messages;
        if (r12 != null) goto L21;
    L24:
        return null;
    L21:
        if (r12.isEmpty() == true) goto L24;
        return r8.messages.get(0);
    L8:
        r1 = 5;
        goto L9
    }

    public static int getScaledGroupWidth(MessageObject.GroupedMessages r2) {
        if (r2 == null) goto L13;
        ArrayList<MessageObject.GroupedMessagePosition> r1 = r2.posArray;
        if (r1 == null) goto L13;
        if (r1.isEmpty() == true) goto L13;
        GroupPositionState r22 = GROUP_POSITION_STATES.get(r2.posArray.get(0));
        if (r22 == null) goto L13;
        return GroupPositionState.m1788$$Nest$fgetscaledForWidth(r22);
    L13:
        return 0;
    }

    public static boolean hasSameEmbeddedProfileHeader(MessageObject r2, MessageObject r3) {
        if (shouldEmbedFeedChannelProfileAvatar(r2) == shouldEmbedFeedChannelProfileAvatar(r3)) goto L5;
        return false;
    L5:
        if (getEmbeddedProfileDialogId(r2) != getEmbeddedProfileDialogId(r3)) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isChannelPostOrSponsored(MessageObject r8) {
        if (r8 != null) goto L5;
    L36:
        return false;
    L5:
        if (r8.messageOwner == null) goto L36;
        if (r8.preview == true) goto L36;
        if (r8.type == 27) goto L36;
        if (r8.isSponsored() == false) goto L15;
        return true;
    L15:
        long r3 = getCommentsChannelId(r8);
        if (r3 == 0) goto L18;
    L20:
        if (r3 != 0) goto L24;
        r3 = getChannelId(r8);
    L24:
        if (r3 != 0) goto L26;
        return false;
    L26:
        TLRPC.Chat r32 = MessagesController.getInstance(r8.currentAccount).getChat(Long.valueOf(r3));
        if (r32 == null) goto L31;
        return ChatObject.isChannelAndNotMegaGroup(r32);
    L31:
        if (r8.messageOwner.post == true) goto L35;
        if (r3 != 0) goto L35;
        return false;
    L35:
        return true;
    L18:
        if (r8.messageOwner.peer_id != null) goto L20;
        return false;
    }

    public static boolean isCommentsChannelPost(MessageObject r4) {
        if (getCommentsChannelId(r4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isEmbeddedProfileAvatarMessage(MessageObject r5) {
        if (r5 != null) goto L5;
    L32:
        return false;
    L5:
        if (r5.messageOwner == null) goto L32;
        if (shouldExpand(r5) == true) goto L14;
        if (isEnabledFor(r5) == false) goto L32;
        if (isEmbeddedProfileAvatarMessageWithoutBackground(r5) == false) goto L32;
    L14:
        if (r5.isOutOwner() == true) goto L32;
        if (r5.isSponsored() == true) goto L32;
        if (r5.isExpiredStory() == true) goto L32;
        if (r5.needDrawAvatar() == false) goto L32;
        TLRPC.MessageFwdHeader r1 = r5.messageOwner.fwd_from;
        if (r1 == null) goto L29;
        String r12 = r1.psa_type;
        if (r12 == null) goto L29;
        if (r12.isEmpty() == false) goto L32;
    L29:
        if (getChannelId(r5) == 0) goto L32;
        return true;
    }

    public static boolean isEmbeddedProfileAvatarMessageWithoutBackground(MessageObject r1) {
        if (r1 == null) goto L13;
        int r12 = r1.type;
        if (r12 != 5) goto L6;
        return true;
    L6:
        if (r12 != 13) goto L8;
        return true;
    L8:
        if (r12 != 15) goto L10;
        return true;
    L10:
        if (r12 != 19) goto L18;
        return true;
    L18:
        return false;
    L13:
        return false;
    }

    public static boolean isEnabledFor(MessageObject r3) {
        boolean r0 = false;
        if (r3 != null) goto L5;
    L14:
        return false;
    L5:
        if (ExteraConfig.getWidePostsInFeed() == true) goto L10;
        if (ExteraConfig.getWidePostsInChannels() == false) goto L14;
    L10:
        if (r3.searchType != 4) goto L13;
        r0 = true;
    L13:
        return isEnabledFor(r0, isChannelPostOrSponsored(r3));
    }

    public static boolean isEnabledFor(boolean r0, boolean r1) {
        if (r1 == false) goto L11;
        if (r0 == false) goto L8;
        if (ExteraConfig.getWidePostsInFeed() == false) goto L14;
        return true;
    L14:
        return false;
    L8:
        if (ExteraConfig.getWidePostsInChannels() == false) goto L15;
        return true;
    L15:
        return false;
    L11:
        return false;
    }

    public static boolean isExpandableMedia(MessageObject r2) {
        int r22 = r2.type;
        if (r22 != 1) goto L5;
    L13:
        return true;
    L5:
        if (r22 == 20) goto L13;
        if (r22 == 3) goto L13;
        if (r22 == 8) goto L13;
        return false;
    }

    private static boolean isGroupedLayout(MessageObject.GroupedMessages r2) {
        if (r2 == null) goto L12;
        ArrayList<MessageObject.GroupedMessagePosition> r0 = r2.posArray;
        if (r0 != null) goto L6;
    L7:
        ArrayList<MessageObject> r22 = r2.messages;
        if (r22 != null) goto L10;
        return false;
    L10:
        if (r22.size() <= 1) goto L15;
    L11:
        return true;
    L15:
        return false;
    L6:
        if (r0.size() > 1) goto L11;
    L12:
        return false;
    }

    private static boolean isPeerResolved(int r3, long r4) {
        if (r4 > 0) goto L5;
        if (r4 < 0) goto L10;
        return true;
    L10:
        if (MessagesController.getInstance(r3).getChat(Long.valueOf(-r4)) == null) goto L12;
        return true;
    L12:
        return false;
    L5:
        if (MessagesController.getInstance(r3).getUser(Long.valueOf(r4)) == null) goto L7;
        return true;
    L7:
        return false;
    }

    public static boolean isProfileResolved(MessageObject r4) {
        if (r4 != null) goto L5;
    L14:
        return true;
    L5:
        if (shouldEmbedProfileAvatar(r4) == false) goto L14;
        if (isPeerResolved(r4.currentAccount, getEmbeddedProfileDialogId(r4)) == true) goto L10;
        return false;
    L10:
        if (isPeerResolved(r4.currentAccount, getFeedSignatureProfileDialogId(r4)) == false) goto L15;
        return true;
    L15:
        return false;
    }

    public static boolean isTextMessage(MessageObject r1) {
        int r12 = r1.type;
        if (r12 != 0) goto L5;
        return true;
    L5:
        if (r12 != 36) goto L7;
        return true;
    L7:
        if (r12 == 24) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isWideGroupedMedia(MessageObject.GroupedMessages r1) {
        if (isGroupedLayout(r1) == true) goto L5;
        return false;
    L5:
        if (r1.isDocuments == true) goto L10;
        return true;
    L10:
        return false;
    }

    private static void normalizeGroupRows(MessageObject.GroupedMessages r12, int r13, boolean r14) {
        ArrayList<MessageObject.GroupedMessagePosition> r0 = r12.posArray;
        int r1 = r0.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L7;
        MessageObject.GroupedMessagePosition r5 = r0.get(r4);
        r4 = r4 + 1;
        MessageObject.GroupedMessagePosition r52 = r5;
        if (r52 == null) goto L3;
        r3 = Math.max(r3, r52.maxY);
        goto L3
    L7:
        int r02 = 0;
    L8:
        if (r02 > r3) goto L47;
        ArrayList<MessageObject.GroupedMessagePosition> r15 = r12.posArray;
        int r42 = r15.size();
        MessageObject.GroupedMessagePosition r53 = null;
        int r6 = 0;
        int r7 = 0;
    L10:
        boolean r8 = true;
        if (r7 >= r42) goto L35;
        MessageObject.GroupedMessagePosition r9 = r15.get(r7);
        r7 = r7 + 1;
        MessageObject.GroupedMessagePosition r92 = r9;
        if (r92 == null) goto L10;
        if (r92.minY > r02) goto L10;
        if (r92.maxY < r02) goto L10;
        r6 = r6 + r92.pw;
        if ((r92.flags & 2) == 0) goto L22;
        boolean r10 = true;
    L23:
        if (r53 != null) goto L25;
    L27:
        r8 = false;
    L28:
        if (r53 == null) goto L34;
        if (r10 == false) goto L31;
        if (r8 == false) goto L34;
    L31:
        if (r10 != r8) goto L10;
        if (r92.maxX <= r53.maxX) goto L10;
    L34:
        r53 = r92;
        goto L10
    L25:
        if ((r53.flags & 2) == 0) goto L27;
    L22:
        r10 = false;
        goto L23
    L35:
        if (r53 == null) goto L46;
        r53.pw = Math.max(1, (r53.pw + r13) - r6);
        GroupPositionState r16 = GROUP_POSITION_STATES.get(r53);
        if (r14 == true) goto L40;
        if (r16 == null) goto L46;
        if (r16.leftSpanOffset <= 0) goto L46;
    L45:
        r53.leftSpanOffset = Math.max(0, r13 - r53.pw);
        goto L46
    L40:
        if (r53.leftSpanOffset > 0) goto L45;
    L46:
        r02 = r02 + 1;
        goto L8
    }

    public static void registerCommentsPostContext(MessageObject r13, TLRPC.Chat r14, MessageObject r15) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r13.messageOwner != null) goto L6;
        return;
    L6:
        if (ChatObject.isChannelAndNotMegaGroup(r14) == false) goto L62;
        TLRPC.Message r0 = r13.messageOwner;
        TLRPC.MessageFwdHeader r1 = r0.fwd_from;
        TLRPC.Peer r2 = r0.from_id;
        if (r2 == null) goto L11;
        long r5 = r2.channel_id;
    L12:
        TLRPC.Peer r22 = r0.peer_id;
        if (r22 == null) goto L15;
        long r7 = r22.channel_id;
    L16:
        if (r1 == null) goto L20;
        TLRPC.Peer r23 = r1.saved_from_peer;
        if (r23 == null) goto L20;
        long r9 = r23.channel_id;
    L21:
        long r11 = r14.id;
        boolean r52 = false;
        if (r5 == r11) goto L24;
    L28:
        boolean r24 = false;
    L30:
        if (r0.reply_to != null) goto L41;
        if (r1 == null) goto L41;
        if (r0.isThreadMessage == false) goto L35;
    L40:
        r52 = true;
        goto L41
    L35:
        if (r9 == r11) goto L40;
        if (r1.channel_post != 0) goto L40;
        if (r0.replies != null) goto L40;
    L41:
        if (r24 == false) goto L61;
        if (r52 == false) goto L63;
        if (r15 != null) goto L46;
    L50:
        String r152 = null;
    L51:
        Map<MessageObject, CommentsPostContext> r02 = COMMENTS_POST_CONTEXTS;
        CommentsPostContext r12 = r02.get(r13);
        if (r12 != null) goto L54;
    L57:
        r02.put(r13, new CommentsPostContext(r14.id, r152));
        return;
    L54:
        if (r12.channelId != r14.id) goto L57;
        r12.updatePostAuthor(r152);
        return;
    L46:
        if (r15.messageOwner == null) goto L50;
        if (r15.getDialogId() != (-r14.id)) goto L50;
        r152 = r15.messageOwner.post_author;
        goto L51
    L63:
        return;
    L61:
        return;
    L24:
        if (r7 == 0) goto L28;
        if (r7 == r11) goto L28;
        r24 = true;
    L20:
        r9 = 0;
        goto L21
    L15:
        r7 = 0;
        goto L16
    L11:
        r5 = 0;
        goto L12
    }

    private static void removeGroupAvatarOffset(MessageObject.GroupedMessagePosition r3) {
        if (r3.edge == false) goto L9;
        r3.pw = Math.max(1, r3.pw - 108);
        int r0 = r3.spanSize;
        if (r0 == 1000) goto L18;
        r3.spanSize = Math.max(1, r0 - 108);
        return;
    L18:
        return;
    L9:
        if ((r3.flags & 2) == 0) goto L19;
        int r02 = r3.spanSize;
        if (r02 == 1000) goto L14;
        r3.spanSize = r02 + 108;
        return;
    L14:
        int r03 = r3.leftSpanOffset;
        if (r03 == 0) goto L20;
        r3.leftSpanOffset = Math.max(0, r03 - 108);
        return;
    L20:
        return;
    }

    private static void restoreGroupedLayout(MessageObject.GroupedMessages r6) {
        if (r6 == null) goto L26;
        ArrayList<MessageObject.GroupedMessagePosition> r0 = r6.posArray;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isEmpty() == true) goto L29;
        ArrayList<MessageObject.GroupedMessagePosition> r02 = r6.posArray;
        int r1 = r02.size();
        boolean r2 = false;
        int r3 = 0;
    L9:
        if (r3 >= r1) goto L16;
        MessageObject.GroupedMessagePosition r4 = r02.get(r3);
        r3 = r3 + 1;
        MessageObject.GroupedMessagePosition r42 = r4;
        if (r42 == null) goto L9;
        GroupPositionState r5 = GROUP_POSITION_STATES.remove(r42);
        if (r5 == null) goto L9;
        r5.restore(r42);
        r2 = true;
        goto L9
    L16:
        if (r2 == false) goto L28;
        r6.cachedWidthForCaption = -1;
        return;
    L28:
        return;
    L29:
        return;
    }

    public static boolean shouldDrawBackground(MessageObject r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.shouldDrawWithoutBackground() == true) goto L6;
        return true;
    L6:
        if (r1.isRoundVideo() == true) goto L8;
        return false;
    L8:
        if (r1.isVoiceTranscriptionOpen() == false) goto L15;
        return true;
    L15:
        return false;
    }

    public static boolean shouldDrawFeedAuthorSignature(MessageObject r0) {
        return FeedMessageUtils.hasProfileSignature(r0);
    }

    public static boolean shouldEmbedAuthorProfileAvatar(MessageObject r3) {
        if (isEmbeddedProfileAvatarMessage(r3) == true) goto L5;
    L17:
        return false;
    L5:
        if (r3.searchType == 4) goto L17;
        TLRPC.Chat r0 = getBroadcastChannel(r3);
        if (r0 == null) goto L17;
        if (r0.signature_profiles == true) goto L14;
        if (r3.currentEvent == null) goto L17;
    L14:
        if (ChatObject.isChannelAndNotMegaGroup(r0) == false) goto L17;
        return true;
    }

    private static boolean shouldEmbedCommentsChannelProfileAvatar(MessageObject r7) {
        boolean r0 = isEmbeddedProfileAvatarMessage(r7);
        long r1 = getCommentsChannelId(r7);
        CommentsPostContext r3 = getCommentsPostContext(r7);
        if (r7 != null) goto L5;
    L7:
        TLRPC.Chat r72 = null;
    L8:
        if (r0 == true) goto L10;
        return false;
    L10:
        if (r1 == 0) goto L19;
        if (r3 == null) goto L13;
        return true;
    L13:
        if (ChatObject.isChannelAndNotMegaGroup(r72) == false) goto L20;
        return true;
    L20:
        return false;
    L19:
        return false;
    L5:
        if (r1 == 0) goto L7;
        r72 = MessagesController.getInstance(r7.currentAccount).getChat(Long.valueOf(r1));
        goto L8
    }

    private static boolean shouldEmbedFeedChannelProfileAvatar(MessageObject r1) {
        if (isEmbeddedProfileAvatarMessage(r1) == true) goto L5;
        return false;
    L5:
        if (FeedMessageUtils.resolveDisplayChannel(r1) == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean shouldEmbedProfileAvatar(MessageObject r1) {
        if (shouldEmbedFeedChannelProfileAvatar(r1) == false) goto L5;
        return true;
    L5:
        if (shouldEmbedCommentsChannelProfileAvatar(r1) == false) goto L7;
        return true;
    L7:
        if (shouldEmbedAuthorProfileAvatar(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean shouldExpand(MessageObject r1) {
        if (isEnabledFor(r1) == true) goto L5;
        return false;
    L5:
        if (r1.shouldDrawWithoutBackground() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean shouldHideAuxiliaryActions(MessageObject r1) {
        if (isEnabledFor(r1) == true) goto L5;
        return false;
    L5:
        if (r1.isSponsored() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean shouldSuppressExternalAvatar(MessageObject r2) {
        if (isEnabledFor(r2) == true) goto L5;
        return false;
    L5:
        if (r2.searchType == 4) goto L7;
        return false;
    L7:
        if (r2.isSponsored() == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static void updateGroupedLayout(MessageObject.GroupedMessages r12, int r13) {
        if (isWideGroupedMedia(r12) == false) goto L75;
        if (r13 <= 0) goto L75;
        MessageObject r0 = getPrimaryGroupMessage(r12);
        if (shouldExpand(r0) == true) goto L10;
        restoreGroupedLayout(r12);
        return;
    L10:
        boolean r3 = false;
        if (shouldEmbedProfileAvatar(r0) == false) goto L13;
    L16:
        boolean r02 = true;
    L17:
        ArrayList<MessageObject.GroupedMessagePosition> r1 = r12.posArray;
        int r4 = r1.size();
        int r5 = 0;
    L18:
        if (r5 >= r4) goto L28;
        MessageObject.GroupedMessagePosition r6 = r1.get(r5);
        r5 = r5 + 1;
        MessageObject.GroupedMessagePosition r62 = r6;
        if (r62 == null) goto L18;
        Map<MessageObject.GroupedMessagePosition, GroupPositionState> r7 = GROUP_POSITION_STATES;
        GroupPositionState r8 = r7.get(r62);
        if (r8 != null) goto L25;
        r8 = new GroupPositionState(r62);
        r7.put(r62, r8);
    L25:
        r8.restore(r62);
        if (r02 == false) goto L18;
        removeGroupAvatarOffset(r62);
        goto L18
    L28:
        int r14 = getBaseGroupVisualWidth(r12);
        if (r14 > 0) goto L32;
        restoreGroupedLayout(r12);
        return;
    L32:
        int r42 = Utilities.clamp(Math.round((Math.max(AndroidUtilities.dp(120.0f), r13 - AndroidUtilities.dp(9.0f)) * 1000) / r13), 1000, 1);
        float r63 = r42 / r14;
        ArrayList<MessageObject.GroupedMessagePosition> r15 = r12.posArray;
        int r72 = r15.size();
        int r82 = 0;
    L33:
        if (r82 >= r72) goto L49;
        MessageObject.GroupedMessagePosition r9 = r15.get(r82);
        r82 = r82 + 1;
        MessageObject.GroupedMessagePosition r92 = r9;
        if (r92 == null) goto L33;
        if (GROUP_POSITION_STATES.get(r92) == null) goto L33;
        int r10 = r92.pw;
        if (r92.leftSpanOffset == 0) goto L43;
        boolean r11 = true;
    L44:
        int r102 = Math.max(1, Math.round(r10 * r63));
        r92.pw = r102;
        if (r11 == true) goto L47;
        int r103 = 0;
    L48:
        r92.leftSpanOffset = r103;
        goto L33
    L47:
        r103 = Math.max(0, r42 - r102);
        goto L48
    L43:
        r11 = false;
        goto L44
    L49:
        normalizeGroupRows(r12, r42, r02);
        int r16 = 1000 - r42;
        ArrayList<MessageObject.GroupedMessagePosition> r2 = r12.posArray;
        int r43 = r2.size();
        int r64 = 0;
    L50:
        if (r64 >= r43) goto L72;
        MessageObject.GroupedMessagePosition r73 = r2.get(r64);
        r64 = r64 + 1;
        MessageObject.GroupedMessagePosition r74 = r73;
        if (r74 == null) goto L50;
        GroupPositionState r83 = GROUP_POSITION_STATES.get(r74);
        if (r83 == null) goto L50;
        if (r02 == false) goto L64;
        if (r74.spanSize == 1000) goto L61;
    L68:
        int r93 = r74.pw;
        r74.spanSize = r93;
        if ((r74.flags & 2) == 0) goto L71;
        r74.spanSize = r93 + r16;
    L71:
        GroupPositionState.m1789$$Nest$fputscaledForWidth(r83, r13);
        r3 = r3 | r83.apply(r74);
        goto L50
    L61:
        if (r74.leftSpanOffset <= 0) goto L68;
    L67:
        r74.spanSize = 1000;
        goto L71
    L64:
        if (r83.spanSize != 1000) goto L68;
        if (r83.leftSpanOffset <= 0) goto L68;
    L72:
        if (r3 == false) goto L98;
        r12.cachedWidthForCaption = -1;
        return;
    L98:
        return;
    L13:
        if (shouldSuppressExternalAvatar(r0) == true) goto L16;
        r02 = false;
    L75:
        restoreGroupedLayout(r12);
    }
}
