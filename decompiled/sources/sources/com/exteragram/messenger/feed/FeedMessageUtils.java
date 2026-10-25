package com.exteragram.messenger.feed;

import java.util.ArrayList;
import java.util.Calendar;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public abstract class FeedMessageUtils {
    public static /* synthetic */ void $r8$lambda$IXZLP3pS1__oSfuf53vB5B1rwvc(TLObject r1, ChatActivity r2) {
        if ((r1 instanceof TLRPC.TL_exportedMessageLink) == false) goto L10;
        String r12 = ((TLRPC.TL_exportedMessageLink) r1).link;
        if (AndroidUtilities.addToClipboard(r12) == true) goto L7;
        return;
    L7:
        if (BulletinFactory.canShowBulletin(r2) == false) goto L12;
        BulletinFactory.of(r2).createCopyLinkBulletin(r12.contains("/c/")).show();
        return;
    L12:
        return;
    }

    public static /* synthetic */ void $r8$lambda$XflDMlSYrgi89Wgpq48L5M4D8XE(ChatActivity r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new FeedMessageUtils$$ExternalSyntheticLambda1(r1, r0));
    }

    public static void copyFeedPostLink(ChatActivity r3, MessageObject r4) {
        if (r3 == null) goto L10;
        if (r4 == null) goto L11;
        TLRPC.Chat r0 = r3.getMessagesController().getChat(Long.valueOf(-r4.getDialogId()));
        if (ChatObject.isChannel(r0) == false) goto L12;
        TLRPC.TL_channels_exportMessageLink r1 = new TLRPC.TL_channels_exportMessageLink();
        r1.id = r4.getRealId();
        r1.channel = MessagesController.getInputChannel(r0);
        r3.getConnectionsManager().sendRequest(r1, new FeedMessageUtils$$ExternalSyntheticLambda0(r3));
        return;
    L12:
        return;
    L11:
        return;
    }

    private static TLRPC.TL_message copyMessage(TLRPC.Message r3) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.id = r3.id;
        r0.from_id = r3.from_id;
        r0.from_boosts_applied = r3.from_boosts_applied;
        r0.peer_id = r3.peer_id;
        r0.saved_peer_id = r3.saved_peer_id;
        r0.date = r3.date;
        r0.expire_date = r3.expire_date;
        r0.action = r3.action;
        r0.message = r3.message;
        r0.media = r3.media;
        r0.flags = r3.flags;
        r0.flags2 = r3.flags2;
        r0.mentioned = r3.mentioned;
        r0.media_unread = r3.media_unread;
        r0.out = r3.out;
        r0.unread = r3.unread;
        r0.entities = r3.entities;
        r0.via_bot_name = r3.via_bot_name;
        r0.reply_markup = r3.reply_markup;
        r0.views = r3.views;
        r0.forwards = r3.forwards;
        r0.replies = r3.replies;
        r0.edit_date = r3.edit_date;
        r0.silent = r3.silent;
        r0.post = r3.post;
        r0.from_scheduled = r3.from_scheduled;
        r0.legacy = r3.legacy;
        r0.edit_hide = r3.edit_hide;
        r0.pinned = r3.pinned;
        r0.fwd_from = r3.fwd_from;
        r0.via_bot_id = r3.via_bot_id;
        r0.via_business_bot_id = r3.via_business_bot_id;
        r0.reply_to = r3.reply_to;
        r0.post_author = r3.post_author;
        r0.grouped_id = r3.grouped_id;
        r0.reactions = r3.reactions;
        r0.restriction_reason = r3.restriction_reason;
        r0.ttl_period = r3.ttl_period;
        r0.quick_reply_shortcut_id = r3.quick_reply_shortcut_id;
        r0.effect = r3.effect;
        r0.noforwards = r3.noforwards;
        r0.invert_media = r3.invert_media;
        r0.offline = r3.offline;
        r0.factcheck = r3.factcheck;
        r0.send_state = r3.send_state;
        r0.fwd_msg_id = r3.fwd_msg_id;
        r0.params = r3.params;
        r0.random_id = r3.random_id;
        r0.local_id = r3.local_id;
        r0.attachPath = r3.attachPath;
        r0.dialog_id = r3.dialog_id;
        r0.ttl = r3.ttl;
        r0.destroyTime = r3.destroyTime;
        r0.destroyTimeMillis = r3.destroyTimeMillis;
        r0.layer = r3.layer;
        r0.seq_in = r3.seq_in;
        r0.seq_out = r3.seq_out;
        r0.with_my_score = r3.with_my_score;
        r0.replyMessage = r3.replyMessage;
        r0.reqId = r3.reqId;
        r0.realId = r3.realId;
        r0.stickerVerified = r3.stickerVerified;
        r0.isThreadMessage = r3.isThreadMessage;
        r0.voiceTranscription = r3.voiceTranscription;
        r0.voiceTranscriptionOpen = r3.voiceTranscriptionOpen;
        r0.voiceTranscriptionRated = r3.voiceTranscriptionRated;
        r0.voiceTranscriptionFinal = r3.voiceTranscriptionFinal;
        r0.voiceTranscriptionForce = r3.voiceTranscriptionForce;
        r0.voiceTranscriptionId = r3.voiceTranscriptionId;
        r0.premiumEffectWasPlayed = r3.premiumEffectWasPlayed;
        r0.originalLanguage = r3.originalLanguage;
        r0.translatedToLanguage = r3.translatedToLanguage;
        r0.translatedText = r3.translatedText;
        r0.replyStory = r3.replyStory;
        r0.quick_reply_shortcut = r3.quick_reply_shortcut;
        return r0;
    }

    public static void copyTranslationState(MessageObject r1, MessageObject r2) {
        if (r1 == null) goto L12;
        if (r2 == null) goto L13;
        if (r1 == r2) goto L14;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L15;
        TLRPC.Message r22 = r2.messageOwner;
        if (r22 == null) goto L16;
        r22.translatedText = r12.translatedText;
        r22.translatedToLanguage = r12.translatedToLanguage;
        r22.translatedVoiceTranscription = r12.translatedVoiceTranscription;
        r22.translatedPoll = r12.translatedPoll;
        r22.summaryText = r12.summaryText;
        r22.summarizedOpen = r12.summarizedOpen;
        r22.translatedSummaryText = r12.translatedSummaryText;
        r22.translatedSummaryLanguage = r12.translatedSummaryLanguage;
        return;
    L16:
        return;
    L15:
        return;
    L14:
        return;
    L13:
        return;
    }

    public static MessageObject createDateHeader(int r7, MessageObject r8, int r9) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.message = LocaleController.formatDateChat(r8.messageOwner.date);
        r0.id = 0;
        Calendar r2 = Calendar.getInstance();
        r2.setTimeInMillis(r8.messageOwner.date * 1000);
        r2.set(11, 0);
        r2.set(12, 0);
        r2.set(13, 0);
        r2.set(14, 0);
        r0.date = (int) (r2.getTimeInMillis() / 1000);
        MessageObject r82 = new MessageObject(r7, r0, false, false);
        r82.type = 10;
        r82.contentType = 1;
        r82.isDateObject = true;
        r82.stableId = r9;
        return r82;
    }

    public static MessageObject createReplacement(int r20, long r21, MessageObject r23) {
        if (r23 != null) goto L5;
        return null;
    L5:
        FeedController r2 = FeedController.getInstance(r20);
        MessageObject r3 = r2.getMessage(r21, r23.getRealId());
        if (r3 != null) goto L8;
        return null;
    L8:
        TLRPC.TL_message r6 = copyMessage(r23.messageOwner);
        r6.id = r3.getId();
        r6.realId = r3.getRealId();
        r6.dialog_id = r3.getDialogId();
        MessageObject r4 = new MessageObject(r20, r6, r3.replyMessageObject, null, null, null, null, true, true, 0, false, false, false, 4);
        r4.isPrimaryGroupMessage = r3.isPrimaryGroupMessage;
        r4.localGroupId = r3.localGroupId;
        r4.copyStableParams(r3);
        r2.replaceMessage(r3, r4);
        return r4;
    }

    public static ArrayList<MessageObject> createReplacements(int r3, long r4, ArrayList<MessageObject> r6) {
        ArrayList<MessageObject> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L9;
        MessageObject r2 = createReplacement(r3, r4, r6.get(r1));
        if (r2 == null) goto L8;
        r0.add(r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    public static MessageObject createUnreadDivider(int r3, int r4) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.id = 0;
        MessageObject r2 = new MessageObject(r3, r0, false, false);
        r2.type = 6;
        r2.contentType = 2;
        r2.stableId = r4;
        return r2;
    }

    public static void filterAllowedOptions(ArrayList<CharSequence> r2, ArrayList<Integer> r3, ArrayList<Integer> r4) {
        int r0 = r3.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (isAllowedFeedOption(r3.get(r0).intValue()) == true) goto L7;
        r4.remove(r0);
        r2.remove(r0);
        r3.remove(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    }

    public static MessageObject getForwardingMessageObject(int r17, boolean r18, MessageObject r19) {
        if (r18 == false) goto L10;
        if (r19 == null) goto L10;
        if (r19.getId() == r19.getRealId()) goto L10;
        TLRPC.TL_message r4 = copyMessage(r19.messageOwner);
        r4.id = r19.getRealId();
        r4.realId = 0;
        r4.dialog_id = r19.getDialogId();
        MessageObject r2 = new MessageObject(r17, r4, r19.replyMessageObject, null, null, null, null, false, true, 0, false, false, false);
        r2.isPrimaryGroupMessage = r19.isPrimaryGroupMessage;
        r2.localGroupId = r19.localGroupId;
        r2.copyStableParams(r19);
        return r2;
    L10:
        return r19;
    }

    public static TLRPC.InputPeer getInputPeerForMessageRequest(MessagesController r0, long r1, boolean r3, MessageObject r4) {
        if (r3 == false) goto L6;
        if (r4 == null) goto L6;
        r1 = r4.getDialogId();
    L6:
        return r0.getInputPeer(r1);
    }

    public static int getPlaybackScrollMessageId(boolean r2, long r3, MessageObject r5) {
        if (r5 != null) goto L4;
    L10:
        if (r5 != null) goto L12;
        return 0;
    L12:
        return r5.getId();
    L4:
        if (r5.searchType != 4) goto L10;
        if (r2 == true) goto L10;
        if (r5.getDialogId() != r3) goto L10;
        return r5.getRealId();
    }

    public static boolean hasProfileSignature(MessageObject r0) {
        TLRPC.Chat r02 = resolveDisplayChannel(r0);
        if (r02 != null) goto L5;
        return false;
    L5:
        if (r02.signature_profiles == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isAllowedDoubleTapAction(int r2) {
        if (r2 != 1) goto L5;
    L16:
        return true;
    L5:
        if (r2 == 2) goto L16;
        if (r2 == 3) goto L16;
        if (r2 == 4) goto L16;
        if (r2 == 6) goto L16;
        if (r2 == 9) goto L16;
        return false;
    }

    public static boolean isAllowedFeedOption(int r1) {
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 != 3) goto L7;
        return true;
    L7:
        if (r1 != 4) goto L9;
        return true;
    L9:
        if (r1 != 6) goto L11;
        return true;
    L11:
        if (r1 != 7) goto L13;
        return true;
    L13:
        if (r1 != 8) goto L15;
        return true;
    L15:
        if (r1 != 10) goto L17;
        return true;
    L17:
        if (r1 != 16) goto L19;
        return true;
    L19:
        if (r1 != 22) goto L21;
        return true;
    L21:
        if (r1 != 25) goto L23;
        return true;
    L23:
        if (r1 != 36) goto L25;
        return true;
    L25:
        if (r1 != 115) goto L27;
        return true;
    L27:
        if (r1 != 200) goto L29;
        return true;
    L29:
        if (r1 != 203) goto L31;
        return true;
    L31:
        if (r1 != 206) goto L33;
        return true;
    L33:
        if (r1 != 28) goto L35;
        return true;
    L35:
        if (r1 == 29) goto L55;
        return false;
    L55:
        return true;
    }

    public static boolean isAllowedSwipeAction(int r1) {
        if (r1 != 1) goto L5;
        return false;
    L5:
        if (isAllowedDoubleTapAction(r1) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isPostRow(MessageObject r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.isDateObject == false) goto L6;
        return false;
    L6:
        if (r2.type != 6) goto L8;
        return false;
    L8:
        if (r2.isSponsored() == true) goto L15;
        return true;
    L15:
        return false;
    }

    public static boolean matchesPlaybackNotification(int r7, MessageObject r8, int r9) {
        if (r8 != null) goto L6;
        return false;
    L6:
        if (r8.getId() != r9) goto L8;
        return true;
    L8:
        FeedController r72 = FeedController.peekInstance(r7);
        if (r72 != null) goto L11;
        return false;
    L11:
        long r3 = r72.resolveRealDialogId(r9);
        if (r3 != 0) goto L14;
    L18:
        return false;
    L14:
        if (r3 != r8.getDialogId()) goto L18;
        if (r72.resolveRealMessageId(r3, r9) != r8.getFeedRealId()) goto L18;
        return true;
    }

    public static TLRPC.Chat resolveDisplayChannel(MessageObject r5) {
        if (r5 != null) goto L5;
    L26:
        return null;
    L5:
        if (r5.messageOwner == null) goto L26;
        if (r5.searchType != 4) goto L26;
        if (r5.preview == true) goto L26;
        if (r5.isSponsored() == true) goto L26;
        if (r5.type == 27) goto L26;
        TLRPC.Message r1 = r5.messageOwner;
        if (r1.post == false) goto L26;
        if (r1.action != null) goto L26;
        TLRPC.Peer r12 = r1.peer_id;
        if (r12 == null) goto L26;
        if (r12.channel_id == 0) goto L26;
        TLRPC.Chat r52 = MessagesController.getInstance(r5.currentAccount).getChat(Long.valueOf(r5.messageOwner.peer_id.channel_id));
        if (ChatObject.isChannelAndNotMegaGroup(r52) == false) goto L26;
        return r52;
    }

    public static boolean shouldMergePosts(MessageObject r4, TLRPC.Chat r5, MessageObject r6, TLRPC.Chat r7) {
        if (r5 == null) goto L20;
        if (r7 != null) goto L5;
        return false;
    L5:
        if (r5.id == r7.id) goto L7;
        return false;
    L7:
        if (r4.contentType == r6.contentType) goto L9;
        return false;
    L9:
        if ((r4.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L11;
        return false;
    L11:
        if (r4.isOutOwner() == r6.isOutOwner()) goto L13;
        return false;
    L13:
        if (Math.abs(r4.messageOwner.date - r6.messageOwner.date) <= 300) goto L15;
        return false;
    L15:
        if (r6.messageOwner.paid_message_stars <= 0) goto L17;
        return false;
    L17:
        if (DialogObject.getPeerDialogId(r4.messageOwner.guestchat_via_from) != DialogObject.getPeerDialogId(r6.messageOwner.guestchat_via_from)) goto L29;
        return true;
    L29:
        return false;
    L20:
        return false;
    }
}
