package org.telegram.messenger;

import android.net.Uri;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.MessagePreviewView;

/* loaded from: classes5.dex */
public class MessagePreviewParams {
    public CharacterStyle currentLink;
    public Messages forwardMessages;
    public boolean hasCaption;
    public boolean hasMedia;
    public boolean hasSecretMessages;
    public boolean hasSenders;
    public boolean hideCaption;
    public boolean hideForwardSendersName;
    public boolean isSecret;
    public boolean isVideo;
    public TLRPC.WebPage linkMedia;
    public Messages linkMessage;
    public boolean monoforum;
    public boolean multipleUsers;
    public boolean noforwards;
    private MessagePreviewView previewView;
    public ChatActivity.ReplyQuote quote;
    public int quoteEnd;
    public int quoteStart;
    public Messages replyMessage;
    public boolean singleLink;
    public TLRPC.WebPage webpage;
    public boolean webpagePhoto;
    public boolean webpageSmall;
    public boolean webpageTop;
    public boolean willSeeSenders;

    /* renamed from: org.telegram.messenger.MessagePreviewParams$1, reason: invalid class name */
    public class AnonymousClass1 extends MessageObject {
        final /* synthetic */ MessagePreviewParams this$0;
        final /* synthetic */ int val$msgtype;

        public AnonymousClass1(MessagePreviewParams r1, int r2, TLRPC.Message r3, boolean r4, boolean r5, int r6) {
            this.this$0 = r1;
            this.val$msgtype = r6;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.messenger.MessageObject
        public void generateLayout(TLRPC.User r2) {
            super.generateLayout(r2);
            if (this.val$msgtype != 2) goto L6;
            this.this$0.checkCurrentLink(this);
            return;
        }

        @Override // org.telegram.messenger.MessageObject
        public boolean needDrawForwarded() {
            if (this.this$0.hideForwardSendersName == false) goto L7;
            return false;
        L7:
            return super.needDrawForwarded();
        }
    }

    public class Messages {
        private long dialogId;
        public LongSparseArray<MessageObject.GroupedMessages> groupedMessagesMap;
        public boolean hasSpoilers;
        public boolean hasText;
        private final HashSet<SelectionKey> messageKeys;
        public ArrayList<MessageObject> messages;
        private Boolean out;
        public ArrayList<TLRPC.PollAnswerVoters> pollChosenAnswers;
        public ArrayList<MessageObject> previewMessages;
        private final IdentityHashMap<MessageObject, SelectionKey> previewSelectionKeys;
        private final HashSet<SelectionKey> selectedIds;
        final /* synthetic */ MessagePreviewParams this$0;
        private int type;

        public Messages(MessagePreviewParams r18, Boolean r19, int r20, ArrayList<MessageObject> r21, long r22, Messages r24) {
            this.this$0 = r18;
            this.groupedMessagesMap = new LongSparseArray();
            this.previewMessages = new ArrayList();
            this.messageKeys = new HashSet();
            this.selectedIds = new HashSet();
            this.previewSelectionKeys = new IdentityHashMap();
            this.pollChosenAnswers = new ArrayList();
            this.out = r19;
            this.type = r20;
            this.dialogId = r22;
            this.messages = r21;
            int r9 = 0;
        L4:
            if (r9 >= r21.size()) goto L38;
            MessageObject r10 = r21.get(r9);
            SelectionKey r12 = new SelectionKey(r10.getDialogId(), r10.getId());
            this.messageKeys.add(r12);
            if (r20 != 0) goto L13;
            if (r24 != null) goto L9;
        L12:
            this.selectedIds.add(r12);
            goto L13
        L9:
            if (r24.messageKeys.contains(r12) == false) goto L12;
            if (r24.selectedIds.contains(r12) == true) goto L12;
        L13:
            MessageObject r13 = MessagePreviewParams.m4468$$Nest$mtoPreviewMessage(r18, r10, r19, r20);
            this.previewSelectionKeys.put(r13, r12);
            if (this.hasSpoilers == true) goto L20;
            ArrayList<TLRPC.MessageEntity> r122 = r13.messageOwner.entities;
            int r14 = r122.size();
            int r15 = 0;
        L16:
            if (r15 >= r14) goto L20;
            TLRPC.MessageEntity r16 = r122.get(r15);
            r15 = r15 + 1;
            if ((r16 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L16;
            this.hasSpoilers = true;
        L20:
            r13.messageOwner.dialog_id = r22;
            if (r13.getGroupId() == 0) goto L26;
            MessageObject.GroupedMessages r8 = this.groupedMessagesMap.get(r13.getGroupId(), null);
            if (r8 != null) goto L25;
            r8 = new MessageObject.GroupedMessages();
            this.groupedMessagesMap.put(r13.getGroupId(), r8);
        L25:
            r8.messages.add(r13);
        L26:
            this.previewMessages.add(0, r13);
            if (r10.isPoll() == false) goto L37;
            TLRPC.TL_messageMediaPoll r82 = (TLRPC.TL_messageMediaPoll) r10.messageOwner.media;
            PreviewMediaPoll r11 = new PreviewMediaPoll();
            r11.poll = r82.poll;
            r11.provider = r82.provider;
            TLRPC.TL_pollResults r123 = new TLRPC.TL_pollResults();
            r11.results = r123;
            int r142 = r82.results.total_voters;
            r123.total_voters = r142;
            r11.totalVotersCached = r142;
            r13.messageOwner.media = r11;
            if (r10.canUnvote() == false) goto L37;
            int r102 = r82.results.results.size();
            int r124 = 0;
        L31:
            if (r124 >= r102) goto L37;
            TLRPC.PollAnswerVoters r132 = r82.results.results.get(r124);
            if (r132.chosen == false) goto L35;
            TLRPC.PollAnswerVoters r143 = new TLRPC.PollAnswerVoters();
            r143.chosen = r132.chosen;
            r143.correct = r132.correct;
            r143.flags = r132.flags;
            r143.option = r132.option;
            r143.voters = r132.voters;
            this.pollChosenAnswers.add(r143);
            r11.results.results.add(r143);
        L36:
            r124 = r124 + 1;
            goto L31
        L35:
            r11.results.results.add(r132);
        L37:
            r9 = r9 + 1;
            goto L4
        L38:
            int r1 = 0;
        L39:
            int r2 = this.groupedMessagesMap.size();
            LongSparseArray<MessageObject.GroupedMessages> r3 = this.groupedMessagesMap;
            if (r1 >= r2) goto L42;
            r3.valueAt(r1).calculate();
            r1 = r1 + 1;
            goto L39
        L42:
            if (r3 == null) goto L52;
            if (r3.size() <= 0) goto L52;
            if (this.groupedMessagesMap.valueAt(0).findCaptionMessageObject() == null) goto L48;
            boolean r83 = true;
        L49:
            this.hasText = r83;
            return;
        L48:
            r83 = false;
        L52:
            if (r21.size() != 1) goto L72;
            int r23 = r21.get(0).type;
            if (r23 != 0) goto L56;
        L60:
            this.hasText = !TextUtils.isEmpty(r1.messageText);
            return;
        L56:
            if (r23 == 19) goto L60;
            this.hasText = !TextUtils.isEmpty(r1.caption);
            return;
        }

        public Messages(MessagePreviewParams r9, Boolean r10, int r11, MessageObject r12) {
            this(r9, r10, r11, MessagePreviewParams.m4469$$Nest$smsingletonArrayList(r12), r12.getDialogId(), null);
        }

        public Messages(MessagePreviewParams r9, Boolean r10, int r11, MessageObject r12, long r13) {
            this(r9, r10, r11, MessagePreviewParams.m4469$$Nest$smsingletonArrayList(r12), r13, null);
        }

        public Messages checkEdits(ArrayList<MessageObject> r14) {
            ArrayList<MessageObject> r0 = this.messages;
            if (r0 != null) goto L5;
        L30:
            return null;
        L5:
            if (r0.size() > 1) goto L30;
            if (r14 == null) goto L30;
            int r3 = 0;
            boolean r4 = false;
        L10:
            if (r3 >= this.messages.size()) goto L27;
            MessageObject r5 = this.messages.get(r3);
            if (r5 == null) goto L26;
            int r6 = 0;
        L16:
            if (r6 >= r14.size()) goto L26;
            MessageObject r7 = r14.get(r6);
            if (r7 == null) goto L25;
            if (r5.getId() != r7.getId()) goto L25;
            if (r5.getDialogId() != r7.getDialogId()) goto L25;
            this.messages.set(r3, r7);
            r4 = true;
        L25:
            r6 = r6 + 1;
        L26:
            r3 = r3 + 1;
            goto L10
        L27:
            if (r4 == false) goto L30;
            return new Messages(this.this$0, this.out, this.type, this.messages, this.dialogId, null);
        }

        public int getSelectedCount() {
            return this.selectedIds.size();
        }

        public void getSelectedMessages(ArrayList<MessageObject> r8) {
            r8.clear();
            int r0 = 0;
        L4:
            if (r0 >= this.messages.size()) goto L9;
            MessageObject r1 = this.messages.get(r0);
            if (this.selectedIds.contains(new SelectionKey(r1.getDialogId(), r1.getId())) == false) goto L8;
            r8.add(r1);
        L8:
            r0 = r0 + 1;
            goto L4
        }

        public boolean isSelected(MessageObject r4) {
            SelectionKey r0 = this.previewSelectionKeys.get(r4);
            if (r0 != null) goto L6;
            if (r4 == null) goto L6;
            r0 = new SelectionKey(r4.getDialogId(), r4.getId());
        L6:
            if (r0 != null) goto L8;
            return false;
        L8:
            if (this.selectedIds.contains(r0) == false) goto L13;
            return true;
        L13:
            return false;
        }

        public void setSelected(MessageObject r4, boolean r5) {
            SelectionKey r0 = this.previewSelectionKeys.get(r4);
            if (r0 != null) goto L6;
            if (r4 == null) goto L6;
            r0 = new SelectionKey(r4.getDialogId(), r4.getId());
        L6:
            if (r0 != null) goto L8;
            return;
        L8:
            HashSet<SelectionKey> r3 = this.selectedIds;
            if (r5 == false) goto L12;
            r3.add(r0);
            return;
        L12:
            r3.remove(r0);
        }
    }

    public static class PreviewMediaPoll extends TLRPC.TL_messageMediaPoll {
        public int totalVotersCached;

        public PreviewMediaPoll() {
        }
    }

    public static final class SelectionKey {
        final long dialogId;
        final int messageId;

        public SelectionKey(long r1, int r3) {
            this.dialogId = r1;
            this.messageId = r3;
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof SelectionKey) == true) goto L8;
            return false;
        L8:
            SelectionKey r82 = (SelectionKey) r8;
            if (this.dialogId == r82.dialogId) goto L11;
        L13:
            return false;
        L11:
            if (this.messageId != r82.messageId) goto L13;
            return true;
        }

        public int hashCode() {
            return (Long.hashCode(this.dialogId) * 31) + this.messageId;
        }
    }

    /* renamed from: -$$Nest$mtoPreviewMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m4468$$Nest$mtoPreviewMessage(MessagePreviewParams r0, MessageObject r1, Boolean r2, int r3) {
        return r0.toPreviewMessage(r1, r2, r3);
    }

    /* renamed from: -$$Nest$smsingletonArrayList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m4469$$Nest$smsingletonArrayList(MessageObject r0) {
        return singletonArrayList(r0);
    }

    public MessagePreviewParams(boolean r1, boolean r2, boolean r3) {
        this.isSecret = r1;
        if (r1 == true) goto L7;
        if (r2 == true) goto L7;
        boolean r12 = false;
    L8:
        this.noforwards = r12;
        this.monoforum = r3;
        return;
    L7:
        r12 = true;
        goto L8
    }

    public static boolean areUrlsEqual(String r4, String r5) {
        if (r4 == null) goto L28;
        if (r5 == null) goto L28;
        Uri r42 = Uri.parse(r4);
        Uri r52 = Uri.parse(r5);
        if (r42 == r52) goto L27;
        if (r42 == null) goto L26;
        if (r52 == null) goto L26;
        if (r42.getHost() == null) goto L26;
        if (r42.getHost().equalsIgnoreCase(r52.getHost()) == false) goto L26;
        if (r42.getPort() != r52.getPort()) goto L26;
        if (normalizePath(r42.getPath()).equals(normalizePath(r52.getPath())) == false) goto L26;
        if (r42.getQuery() != null) goto L24;
        if (r52.getQuery() != null) goto L26;
    L24:
        if (r42.getQuery().equals(r52.getQuery()) == true) goto L27;
    L26:
        return false;
    L27:
        return true;
    L28:
        if (r4 != null) goto L30;
        return true;
    L30:
        return false;
    }

    private static String normalizePath(String r2) {
        if (r2 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r2.endsWith("/") == false) goto L9;
        return r2;
    L9:
        return r2.concat("/");
    }

    private static ArrayList<MessageObject> singletonArrayList(MessageObject r1) {
        ArrayList<MessageObject> r0 = new ArrayList();
        r0.add(r1);
        return r0;
    }

    private MessageObject toPreviewMessage(MessageObject r17, Boolean r18, int r19) {
        TLRPC.TL_message r3 = new TLRPC.TL_message();
        if (r19 == 1) goto L5;
        r3.date = ConnectionsManager.getInstance(r17.currentAccount).getCurrentTime();
    L6:
        TLRPC.Message r2 = r17.messageOwner;
        r3.id = r2.id;
        r3.grouped_id = r2.grouped_id;
        r3.peer_id = r2.peer_id;
        r3.from_id = r2.from_id;
        r3.message = r2.message;
        r3.rich_message = r2.rich_message;
        r3.media = r2.media;
        r3.action = r2.action;
        boolean r8 = false;
        r3.edit_date = 0;
        ArrayList<TLRPC.MessageEntity> r22 = r2.entities;
        if (r22 == null) goto L9;
        r3.entities.addAll(r22);
    L9:
        if (r18 != null) goto L11;
        boolean r23 = r17.messageOwner.out;
    L12:
        r3.out = r23;
        if (r23 == false) goto L15;
        TLRPC.TL_peerUser r24 = new TLRPC.TL_peerUser();
        r3.from_id = r24;
        r24.user_id = UserConfig.getInstance(r17.currentAccount).getClientUserId();
    L15:
        r3.unread = false;
        TLRPC.Message r25 = r17.messageOwner;
        r3.via_bot_id = r25.via_bot_id;
        r3.reply_markup = r25.reply_markup;
        r3.post = r25.post;
        r3.legacy = r25.legacy;
        r3.restriction_reason = r25.restriction_reason;
        TLRPC.Message r4 = r25.replyMessage;
        r3.replyMessage = r4;
        if (r4 != null) goto L20;
        MessageObject r42 = r17.replyMessageObject;
        if (r42 == null) goto L20;
        r3.replyMessage = r42.messageOwner;
    L20:
        r3.reply_to = r25.reply_to;
        r3.invert_media = r25.invert_media;
        if (r19 != 0) goto L52;
        long r43 = UserConfig.getInstance(r17.currentAccount).getClientUserId();
        if (this.isSecret == true) goto L40;
        TLRPC.Message r26 = r17.messageOwner;
        TLRPC.MessageFwdHeader r11 = r26.fwd_from;
        if (r11 != null) goto L27;
        long r112 = r26.from_id.user_id;
        if (r112 != 0) goto L33;
    L36:
        r11 = new TLRPC.TL_messageFwdHeader();
        r11.from_id = r17.messageOwner.from_id;
        if (r17.isDice() == true) goto L39;
        this.hasSenders = true;
    L41:
        if (r11 == null) goto L44;
        r3.fwd_from = r11;
        r3.flags |= 4;
    L44:
        if (r17.isWelcomeAnchored() == false) goto L52;
        r3.id = r17.getEphemeralId();
        TLRPC.MessageFwdHeader r27 = r3.fwd_from;
        if (r27 == null) goto L52;
        if (r27.from_id == null) goto L52;
        r27.from_id = (TLRPC.Peer) TLObject.deepCopy(r17.messageOwner.peer_id, new MessagePreviewParams$$ExternalSyntheticLambda0());
        long r44 = DialogObject.getPeerDialogId(r17.messageOwner.from_id);
        if (r44 <= 0) goto L52;
        r3.via_bot_id = r44;
        goto L52
    L39:
        this.willSeeSenders = true;
        goto L41
    L33:
        if (r26.dialog_id != r43) goto L36;
        if (r112 == r43) goto L40;
    L27:
        if (r17.isDice() == true) goto L29;
        this.hasSenders = true;
        goto L41
    L29:
        this.willSeeSenders = true;
    L40:
        r11 = null;
    L52:
        AnonymousClass1 r28 = new AnonymousClass1(this, r17.currentAccount, r3, true, false, r19);
        if (r19 != 0) goto L55;
        r8 = true;
    L55:
        r28.previewForward = r8;
        r28.preview = true;
        return r28;
    L11:
        r23 = r18.booleanValue();
        goto L12
    L5:
        r3.date = r17.messageOwner.date;
        goto L6
    }

    public void attach(MessagePreviewView r1) {
        this.previewView = r1;
    }

    public void checkCurrentLink(MessageObject r4) {
        this.currentLink = null;
        if (r4 == null) goto L18;
        CharSequence r42 = r4.messageText;
        if ((r42 instanceof Spanned) == false) goto L21;
        TLRPC.WebPage r0 = this.webpage;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.url == null) goto L23;
        Spanned r43 = (Spanned) r42;
        int r2 = 0;
        URLSpan[] r44 = (URLSpan[]) r43.getSpans(0, r43.length(), URLSpan.class);
    L12:
        if (r2 >= r44.length) goto L24;
        if (areUrlsEqual(r44[r2].getURL(), this.webpage.url) == true) goto L15;
        r2 = r2 + 1;
        goto L12
    L15:
        this.currentLink = r44[r2];
        return;
    L24:
        return;
    L23:
        return;
    L21:
        return;
    }

    public void checkEdits(ArrayList<MessageObject> r4) {
        Messages r0 = this.forwardMessages;
        boolean r1 = true;
        if (r0 == null) goto L7;
        Messages r02 = r0.checkEdits(r4);
        if (r02 == null) goto L7;
        this.forwardMessages = r02;
        boolean r03 = true;
    L8:
        Messages r2 = this.replyMessage;
        if (r2 == null) goto L13;
        Messages r22 = r2.checkEdits(r4);
        if (r22 == null) goto L13;
        this.replyMessage = r22;
        r03 = true;
    L13:
        Messages r23 = this.linkMessage;
        if (r23 == null) goto L18;
        Messages r42 = r23.checkEdits(r4);
        if (r42 == null) goto L18;
        this.linkMessage = r42;
    L19:
        if (r1 == false) goto L24;
        MessagePreviewView r3 = this.previewView;
        if (r3 == null) goto L25;
        r3.updateAll();
        return;
    L25:
        return;
    L24:
        return;
    L18:
        r1 = r03;
    L7:
        r03 = false;
        goto L8
    }

    public int getForwardedMessagesCount() {
        Messages r0 = this.forwardMessages;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.getSelectedCount();
    }

    public boolean hasLink(CharSequence r4, String r5) {
        if (r5 != null) goto L20;
    L19:
        return false;
    L20:
        SpannableString r42 = SpannableString.valueOf(r4);     // Catch: Exception -> L17
        AndroidUtilities.addLinksSafe(r42, 1, false, true);     // Catch: Exception -> L8
    L10:
        URLSpan[] r43 = (URLSpan[]) r42.getSpans(0, r42.length(), URLSpan.class);     // Catch: Exception -> L17
        int r1 = 0;
    L11:
        if (r1 >= r43.length) goto L19;
        if (areUrlsEqual(r43[r1].getURL(), r5) == true) goto L15;
        r1 = r1 + 1;
        goto L11
    L15:
        return true;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    }

    public boolean isEmpty() {
        Messages r0 = this.forwardMessages;
        if (r0 == null) goto L8;
        ArrayList<MessageObject> r02 = r0.messages;
        if (r02 == null) goto L8;
        if (r02.isEmpty() == true) goto L8;
        return false;
    L8:
        Messages r03 = this.replyMessage;
        if (r03 == null) goto L14;
        ArrayList<MessageObject> r04 = r03.messages;
        if (r04 == null) goto L14;
        if (r04.isEmpty() == true) goto L14;
        return false;
    L14:
        Messages r1 = this.linkMessage;
        if (r1 == null) goto L23;
        ArrayList<MessageObject> r12 = r1.messages;
        if (r12 != null) goto L19;
        return true;
    L19:
        if (r12.isEmpty() == true) goto L28;
        return false;
    L28:
        return true;
    L23:
        return true;
    }

    public void updateForward(ArrayList<MessageObject> r13, long r14) {
        int r8 = 0;
        this.hasCaption = false;
        this.hasSenders = false;
        this.isSecret = DialogObject.isEncryptedDialog(r14);
        this.multipleUsers = false;
        if (r13 == null) goto L46;
        ArrayList r10 = new ArrayList();
        int r0 = 0;
    L6:
        if (r0 >= r13.size()) goto L20;
        MessageObject r2 = r13.get(r0);
        if (TextUtils.isEmpty(r2.caption) == true) goto L11;
        this.hasCaption = true;
    L11:
        if (this.isSecret == true) goto L19;
        TLRPC.MessageFwdHeader r22 = r2.messageOwner.fwd_from;
        if (r22 == null) goto L19;
        if (r22.from_id != null) goto L19;
        if (r10.contains(r22.from_name) == true) goto L19;
        r10.add(r22.from_name);
    L19:
        r0 = r0 + 1;
        goto L6
    L20:
        Messages r02 = new Messages(this, Boolean.TRUE, 0, r13, r14, this.forwardMessages);
        this.forwardMessages = r02;
        if (r02.messages.isEmpty() == false) goto L23;
        this.forwardMessages = null;
    L23:
        ArrayList r03 = new ArrayList();
    L25:
        if (r8 >= r13.size()) goto L43;
        MessageObject r23 = r13.get(r8);
        if (r23.isFromUser() == false) goto L29;
        long r24 = r23.messageOwner.from_id.user_id;
    L39:
        if (r03.contains(Long.valueOf(r24)) == true) goto L41;
        r03.add(Long.valueOf(r24));
    L41:
        r8 = r8 + 1;
        goto L25
    L29:
        TLRPC.Chat r3 = MessagesController.getInstance(r23.currentAccount).getChat(Long.valueOf(r23.messageOwner.peer_id.channel_id));
        if (ChatObject.isChannel(r3) == true) goto L32;
    L37:
        long r25 = r23.messageOwner.peer_id.channel_id;
    L36:
        r24 = -r25;
        goto L39
    L32:
        if (r3.megagroup == false) goto L37;
        if (r23.isForwardedChannelPost() == false) goto L37;
        r25 = r23.messageOwner.fwd_from.from_id.channel_id;
        goto L36
    L43:
        if ((r03.size() + r10.size()) <= 1) goto L57;
        this.multipleUsers = true;
        return;
    L57:
        return;
    L46:
        this.forwardMessages = null;
    }

    public void updateLink(int r9, TLRPC.WebPage r10, CharSequence r11, MessageObject r12, ChatActivity.ReplyQuote r13, MessageObject r14) {
        boolean r0 = false;
        this.hasMedia = false;
        this.isVideo = false;
        this.singleLink = true;
        if (this.webpage == r10) goto L5;
        boolean r2 = true;
    L6:
        this.webpage = r10;
        if (TextUtils.isEmpty(r11) == true) goto L9;
    L11:
        if (r11 != null) goto L13;
        r11 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        Messages r3 = this.linkMessage;
        if (r3 == null) goto L18;
        if (r2 == true) goto L18;
        boolean r22 = false;
    L19:
        if (r3 != null) goto L28;
        if (r14 == null) goto L28;
        TLRPC.Message r32 = r14.messageOwner;
        if (r32 == null) goto L28;
        this.webpageTop = r32.invert_media;
        TLRPC.MessageMedia r33 = r32.media;
        if (r33 == null) goto L28;
        if (r33.force_small_media == false) goto L28;
        this.webpageSmall = true;
    L28:
        TLRPC.TL_message r34 = new TLRPC.TL_message();
        CharSequence[] r112 = {new SpannableStringBuilder(AndroidUtilities.getTrimmedString(r11))};
        TLRPC.TL_peerUser r5 = new TLRPC.TL_peerUser();
        r34.peer_id = r5;
        r5.user_id = UserConfig.getInstance(r9).getClientUserId();
        TLRPC.TL_peerUser r52 = new TLRPC.TL_peerUser();
        r34.from_id = r52;
        r52.user_id = UserConfig.getInstance(r9).getClientUserId();
        r34.entities = MediaDataController.getInstance(r9).getEntities(r112, true);
        r34.message = r112[0].toString();
        r34.invert_media = this.webpageTop;
        if (r10 == null) goto L35;
        r34.flags |= 512;
        TLRPC.TL_messageMediaWebPage r113 = new TLRPC.TL_messageMediaWebPage();
        r34.media = r113;
        r113.webpage = r10;
        boolean r53 = this.webpageSmall;
        r113.force_large_media = !r53;
        r113.force_small_media = r53;
        if (r10.photo == null) goto L33;
        boolean r114 = true;
    L34:
        this.hasMedia = r114;
        this.isVideo = MessageObject.isVideoDocument(r10.document);
    L36:
        r34.out = true;
        r34.unread = false;
        if (r12 == null) goto L43;
        r34.replyMessage = r12.messageOwner;
        TLRPC.TL_messageReplyHeader r102 = new TLRPC.TL_messageReplyHeader();
        r34.reply_to = r102;
        if (r13 == null) goto L43;
        r102.quote_text = r13.getText();
        TLRPC.MessageReplyHeader r103 = r34.reply_to;
        r103.flags |= 64;
        r103.quote_entities = r13.getFilteredEntities();
        TLRPC.MessageReplyHeader r104 = r34.reply_to;
        if (r104.quote_entities == null) goto L43;
        r104.flags |= 128;
    L43:
        Messages r105 = new Messages(this, Boolean.TRUE, 2, new MessageObject(r9, r34, true, false));
        this.linkMessage = r105;
        if (r105.messages.isEmpty() == false) goto L46;
        this.linkMessage = null;
    L92:
        MessagePreviewView r8 = this.previewView;
        if (r8 == null) goto L96;
        r8.updateLink();
        return;
    L96:
        return;
    L46:
        MessageObject r92 = this.linkMessage.messages.get(0);
        CharSequence r106 = r92.messageText;
        if ((r106 instanceof Spanned) == true) goto L49;
    L58:
        CharSequence r107 = r92.caption;
        if ((r107 instanceof Spanned) == true) goto L61;
    L70:
        this.hasMedia = r92.hasLinkMediaToMakeSmall();
        if (r22 == false) goto L85;
        if (r14 == null) goto L85;
        TLRPC.Message r108 = r14.messageOwner;
        if (r108 == null) goto L85;
        TLRPC.MessageMedia r109 = r108.media;
        if (r109 == null) goto L85;
        if (r109.force_small_media == false) goto L80;
    L83:
        r0 = true;
    L84:
        this.webpageSmall = r0;
    L87:
        TLRPC.Message r93 = r92.messageOwner;
        if (r93 == null) goto L92;
        TLRPC.MessageMedia r94 = r93.media;
        if (r94 == null) goto L92;
        boolean r1010 = this.webpageSmall;
        r94.force_large_media = !r1010;
        r94.force_small_media = r1010;
        goto L92
    L80:
        if (r92.isLinkMediaSmall() == false) goto L84;
        if (r14.messageOwner.media.force_large_media == true) goto L84;
    L85:
        if (r22 == false) goto L87;
        this.webpageSmall = r92.isLinkMediaSmall();
        goto L87
    L61:
        if (TextUtils.isEmpty(r107) == true) goto L70;
        URLSpan[] r1011 = (URLSpan[]) ((Spanned) r92.messageText).getSpans(0, r92.caption.length(), URLSpan.class);
        if (r1011 != null) goto L65;
    L68:
        boolean r1012 = true;
    L69:
        this.singleLink = r1012;
        goto L70
    L65:
        if (r1011.length <= 1) goto L68;
        r1012 = false;
        goto L69
    L49:
        if (TextUtils.isEmpty(r106) == true) goto L58;
        CharSequence r1013 = r92.messageText;
        URLSpan[] r1014 = (URLSpan[]) ((Spanned) r1013).getSpans(0, r1013.length(), URLSpan.class);
        if (r1014 != null) goto L53;
    L56:
        boolean r1015 = true;
    L57:
        this.singleLink = r1015;
        goto L70
    L53:
        if (r1014.length <= 1) goto L56;
        r1015 = false;
        goto L57
    L33:
        r114 = false;
        goto L34
    L35:
        this.hasMedia = false;
    L18:
        r22 = true;
        goto L19
    L9:
        if (this.webpage != null) goto L11;
        this.linkMessage = null;
        goto L92
    L5:
        r2 = false;
        goto L6
    }

    public void updateLinkInvertMedia(boolean r1) {
        this.webpageTop = r1;
    }

    public void updateReply(MessageObject r11, MessageObject.GroupedMessages r12, long r13, ChatActivity.ReplyQuote r15) {
        if (this.isSecret == true) goto L21;
        if (r11 == null) goto L21;
        int r3 = r11.type;
        if (r3 == 10) goto L21;
        if (r3 == 11) goto L21;
        if (r3 == 22) goto L21;
        if (r3 == 21) goto L21;
        if (r3 == 18) goto L21;
        if (r3 == 25) goto L21;
        if (r3 == 16) goto L21;
        MessageObject r0 = r11;
        ChatActivity.ReplyQuote r9 = r15;
    L22:
        if (r0 != null) goto L24;
    L30:
        boolean r32 = false;
    L31:
        this.hasSecretMessages = r32;
        if (r0 != null) goto L37;
        if (r9 != null) goto L37;
        this.replyMessage = null;
        this.quote = null;
        return;
    L37:
        if (r12 == null) goto L39;
        this.replyMessage = new Messages(this, null, 1, r12.messages, r13, null);
    L45:
        if (this.replyMessage.messages.isEmpty() == true) goto L50;
        this.quote = r9;
        if (r9 == null) goto L52;
        this.quoteStart = r9.start;
        this.quoteEnd = r9.end;
        return;
    L52:
        return;
    L50:
        this.replyMessage = null;
        return;
    L39:
        MessageObject r2 = r0;
        if (r2 != null) goto L43;
        r2 = r9.message;
    L43:
        this.replyMessage = new Messages(this, null, 1, r2, r13);
        goto L45
    L24:
        if (r0.isVoiceOnce() == false) goto L26;
    L29:
        r32 = true;
        goto L31
    L26:
        if (r0.isRoundOnce() == true) goto L29;
        if (r0.type != 30) goto L30;
    L21:
        r0 = null;
        r9 = null;
        goto L22
    }
}
