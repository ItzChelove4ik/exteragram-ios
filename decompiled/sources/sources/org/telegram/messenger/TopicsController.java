package org.telegram.messenger;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_forum;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.Forum.ForumUtilities;

/* loaded from: classes.dex */
public class TopicsController extends BaseController {
    public static final int LOAD_TYPE_HASH_CHECK = 3;
    public static final int LOAD_TYPE_LOAD_NEXT = 1;
    public static final int LOAD_TYPE_LOAD_UNKNOWN = 2;
    public static final int LOAD_TYPE_PRELOAD = 0;
    private static final int MAX_PRELOAD_COUNT = 20;
    public static final int TOPIC_FLAG_CLOSE = 8;
    public static final int TOPIC_FLAG_HIDE = 32;
    public static final int TOPIC_FLAG_ICON = 2;
    public static final int TOPIC_FLAG_PIN = 4;
    public static final int TOPIC_FLAG_TITLE = 1;
    public static final int TOPIC_FLAG_TOTAL_MESSAGES_COUNT = 16;
    private static final int[] countsTmp = null;
    LongSparseIntArray currentOpenTopicsCounter;
    LongSparseIntArray endIsReached;
    LongSparseArray<TopicsLoadOffset> offsets;
    LongSparseIntArray openedTopicsByChatId;
    LongSparseArray<ArrayList<TLRPC.TL_forumTopic>> topicsByChatId;
    LongSparseArray<TLRPC.TL_forumTopic> topicsByTopMsgId;
    LongSparseIntArray topicsIsLoading;
    LongSparseArray<LongSparseArray<TLRPC.TL_forumTopic>> topicsMapByChatId;

    /* renamed from: org.telegram.messenger.TopicsController$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements RequestDelegate {
        final /* synthetic */ TopicsController this$0;
        final /* synthetic */ long val$chatId;
        final /* synthetic */ int val$topicId;

        public AnonymousClass1(TopicsController r1, long r2, int r4) {
            this.this$0 = r1;
            this.val$chatId = r2;
            this.val$topicId = r4;
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r5, TLRPC.TL_error r6) {
            if (r6 != null) goto L7;
            TLRPC.TL_messages_affectedHistory r52 = (TLRPC.TL_messages_affectedHistory) r5;
            this.this$0.getMessagesController().processNewChannelDifferenceParams(r52.pts, r52.pts_count, this.val$chatId);
            int r53 = r52.offset;
            if (r53 <= 0) goto L8;
            TopicsController.m5018$$Nest$mdeleteTopic(this.this$0, this.val$chatId, this.val$topicId, r53);
            return;
        L8:
            return;
        }
    }

    /* renamed from: org.telegram.messenger.TopicsController$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements RequestDelegate {
        final /* synthetic */ TopicsController this$0;

        public AnonymousClass2(TopicsController r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r1, TLRPC.TL_error r2) {
        }
    }

    public static class TopicUpdate {
        boolean checkForDelete;
        long dialogId;
        ArrayList<MessageObject> groupedMessages;
        boolean onlyCounters;
        boolean reloadTopic;
        TLRPC.Message topMessage;
        int topMessageId;
        long topicId;
        public int totalMessagesCount;
        int unreadCount;
        int unreadMentions;

        public TopicUpdate() {
            this.totalMessagesCount = -1;
        }
    }

    /* loaded from: classes5.dex */
    public class TopicsLoadOffset {
        int lastMessageDate;
        int lastMessageId;
        long lastTopicId;
        final /* synthetic */ TopicsController this$0;

        private TopicsLoadOffset(TopicsController r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TopicsLoadOffset(TopicsController r1, TopicsControllerIA r2) {
            this(r1);
        }
    }

    /* renamed from: $r8$lambda$4-f3Jcyp_RLptkemU7s1sxpN-Ro, reason: not valid java name */
    public static /* synthetic */ void m5008$r8$lambda$4f3Jcyp_RLptkemU7s1sxpNRo(TopicsController r0, TLRPC.TL_messages_savedDialogsSlice r1, long r2, LongSparseArray r4, int r5) {
        r0.lambda$loadTopics$3(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$53lPsQHNEw0cIHI5V_MORCIF4Iw(TopicsController r0, TLRPC.TL_messages_savedDialogs r1, long r2, ArrayList r4, LongSparseArray r5, HashSet r6, Runnable r7) {
        r0.lambda$reloadTopics$13(r1, r2, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$6QJ8tsbiN9dWm6tlSDlBbwTG9mA(TopicsController r0, long r1, ArrayList r3, boolean r4, int r5) {
        r0.lambda$loadTopics$0(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7952D4AI4L55oh3MeWT4_A9nV5c(TopicsController r0, long r1) {
        r0.lambda$processTopics$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$7ig3rg9w2y3_NMFTYn2DSg2j4tY(TopicsController r0, TLRPC.TL_forumTopic r1, long r2, long r4, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$getTopicRepliesCount$30(r1, r2, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$F3YAkG8B5ht3XtsxS6cK41iqCiE(TopicsController r0, long r1, boolean r3) {
        r0.lambda$reloadTopics$24(r1, r3);
    }

    /* renamed from: $r8$lambda$GLdqyWRNSegB64EzCQ7a-jt8qmc, reason: not valid java name */
    public static /* synthetic */ int m5009$r8$lambda$GLdqyWRNSegB64EzCQ7ajt8qmc(TLRPC.TL_forumTopic r2, TLRPC.TL_forumTopic r3) {
        boolean r0 = r2.hidden;
        if (r0 == r3.hidden) goto L6;
        if (r0 == true) goto L26;
        return 1;
    L26:
        return -1;
    L6:
        boolean r02 = r2.pinned;
        boolean r1 = r3.pinned;
        if (r02 == r1) goto L13;
        if (r02 == false) goto L27;
        return -1;
    L27:
        return 1;
    L13:
        if (r02 == false) goto L17;
        if (r1 == false) goto L17;
        return r2.pinnedOrder - r3.pinnedOrder;
    L17:
        TLRPC.Message r32 = r3.topMessage;
        int r03 = 0;
        if (r32 == null) goto L20;
        int r33 = r32.date;
    L21:
        TLRPC.Message r22 = r2.topMessage;
        if (r22 == null) goto L25;
        r03 = r22.date;
    L25:
        return r33 - r03;
    L20:
        r33 = 0;
        goto L21
    }

    /* renamed from: $r8$lambda$HnoldAJAy1Ez_uk929Qwf-KgW1o, reason: not valid java name */
    public static /* synthetic */ void m5010$r8$lambda$HnoldAJAy1Ez_uk929QwfKgW1o(TopicsController r0, List r1) {
        r0.lambda$processUpdate$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ji32cTbokDzcKfANM8e3wIIuPP4(TopicsController r0, TLObject r1, long r2, TLRPC.TL_messages_forumTopics r4, LongSparseArray r5, Runnable r6) {
        r0.lambda$reloadTopics$14(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$KLR66BBxLcJGrPz29XRPGhWsUi0(TopicsController r0, BaseFragment r1, long r2, ArrayList r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$pinTopic$20(r1, r2, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$KpmgeKm7qw52VdmZ0GZok-krFOo, reason: not valid java name */
    public static /* synthetic */ void m5011$r8$lambda$KpmgeKm7qw52VdmZ0GZokkrFOo(TopicsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$toggleViewForumAsMessages$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ltzb8S2T4IhW4acSakLiQi5LL6M(TopicsController r0, TLObject r1, TLRPC.TL_forumTopic r2, long r3, long r5) {
        r0.lambda$getTopicRepliesCount$29(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$NWiEcqX_2lf4TuTyVHl1wNKOvmg(TopicsController r0, long r1) {
        r0.lambda$loadTopics$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$ODY0gqzUSV1zJQM9DScCJmsZNgA(TopicsController r0, ArrayList r1, long r2) {
        r0.lambda$updateTopicsWithDeletedMessages$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Q543HTvFtolmgp6f8EtDV1lqa6Y(TopicsController r0, long r1, ArrayList r3, boolean r4, long r5) {
        r0.lambda$updateTopicsWithDeletedMessages$11(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$T7_cMtzy9bptiGfz7CfRqs5bA1c(TopicsController r0, boolean r1, long r2, HashSet r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$reloadTopics$16(r1, r2, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$X6-YUdIsbg17keSuQNKdw6v42ng, reason: not valid java name */
    public static /* synthetic */ void m5012$r8$lambda$X6YUdIsbg17keSuQNKdw6v42ng(TopicsController r0, TLRPC.TL_messages_savedDialogs r1, long r2, LongSparseArray r4, int r5) {
        r0.lambda$loadTopics$2(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$Z6AzyUdH-eWP4i6f5KAguU_JSw8, reason: not valid java name */
    public static /* synthetic */ void m5013$r8$lambda$Z6AzyUdHeWP4i6f5KAguU_JSw8(TopicsController r0, HashMap r1) {
        r0.lambda$updateReadOutbox$26(r1);
    }

    /* renamed from: $r8$lambda$eUjBAnO44SvYxOA-OJovbN4JMQU, reason: not valid java name */
    public static /* synthetic */ void m5014$r8$lambda$eUjBAnO44SvYxOAOJovbN4JMQU(TopicsController r0, long r1, ArrayList r3, long r4, Runnable r6) {
        r0.lambda$loadTopic$27(r1, r3, r4, r6);
    }

    public static /* synthetic */ void $r8$lambda$hn8nyyXEwSNzwH4tdhw_Mq6DDGo(TopicsController r0, TLObject r1, boolean r2, long r3, HashSet r5, Runnable r6) {
        r0.lambda$reloadTopics$15(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$iopPJFs_GKLqIxWJVcUCjQWOA4I(TopicsController r0, BaseFragment r1) {
        r0.lambda$pinTopic$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$jkG8vzUmvmvrlZ9oEbE5zMKwqUE(TopicsController r0) {
        r0.lambda$applyPinnedOrder$17();
    }

    public static /* synthetic */ void $r8$lambda$nJ7F91oxCOB_Piffj5k6_pWOkGM(TopicsController r0, long r1, int r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadTopics$7(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$niBvLAav7bJJnZd4gVjrIWeKXgQ(TopicsController r0, long r1, boolean r3, int r4, ArrayList r5) {
        r0.lambda$loadTopics$1(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$qmoH9JzZAIKmGBDSbkazlTDic3s(TopicsController r0, long r1, TLRPC.TL_messages_savedDialogsNotModified r3) {
        r0.lambda$loadTopics$4(r1, r3);
    }

    /* renamed from: $r8$lambda$sHUUK3JDTBJRb-lhOtfjolxyLUM, reason: not valid java name */
    public static /* synthetic */ void m5015$r8$lambda$sHUUK3JDTBJRblhOtfjolxyLUM(TopicsController r0) {
        r0.lambda$databaseCleared$25();
    }

    /* renamed from: $r8$lambda$uIvd1q7KIT_-leTO07RoUvUN3P8, reason: not valid java name */
    public static /* synthetic */ void m5016$r8$lambda$uIvd1q7KIT_leTO07RoUvUN3P8(TopicsController r0, long r1, ArrayList r3, boolean r4, long r5) {
        r0.lambda$updateTopicsWithDeletedMessages$12(r1, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$ueOeP_JAZ5vvnbWtHT-HlziW-yY, reason: not valid java name */
    public static /* synthetic */ void m5017$r8$lambda$ueOeP_JAZ5vvnbWtHTHlziWyY(TopicsController r0, TLObject r1, long r2, TLRPC.TL_messages_forumTopics r4, LongSparseArray r5, int r6) {
        r0.lambda$loadTopics$5(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$vuBBLZwWgRS4c5tCuvgnQTm5XYM(TopicsController r0, long r1, long r3, Runnable r5, ArrayList r6) {
        r0.lambda$loadTopic$28(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$yss0oUaKP8OpZ_rlH0Ftgg76g10(TopicsController r0, ArrayList r1) {
        r0.lambda$onTopicsDeletedServerSide$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$zbTCfWAvoYVdqESGkyEPITAwMbw(TopicsController r0, long r1, long r3, int r5) {
        r0.lambda$updateMentionsUnread$21(r1, r3, r5);
    }

    /* renamed from: -$$Nest$mdeleteTopic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5018$$Nest$mdeleteTopic(TopicsController r0, long r1, int r3, int r4) {
        r0.deleteTopic(r1, r3, r4);
    }

    static {
        countsTmp = new int[5];
    }

    public TopicsController(int r1) {
        super(r1);
        this.topicsByChatId = new LongSparseArray();
        this.topicsMapByChatId = new LongSparseArray();
        this.topicsIsLoading = new LongSparseIntArray();
        this.endIsReached = new LongSparseIntArray();
        this.topicsByTopMsgId = new LongSparseArray();
        this.currentOpenTopicsCounter = new LongSparseIntArray();
        this.openedTopicsByChatId = new LongSparseIntArray();
        this.offsets = new LongSparseArray();
    }

    public static long calculateHashSavedDialogs(ArrayList<TLRPC.TL_forumTopic> r9, int r10, int r11) {
        if (r9 != null) goto L5;
        return 0;
    L5:
        int r112 = r11 + r10;
        if (r9.size() >= r112) goto L8;
        return 0;
    L8:
        long r2 = 0;
    L9:
        if (r10 >= r112) goto L26;
        TLRPC.TL_forumTopic r4 = r9.get(r10);
        TLRPC.Message r5 = r4.topMessage;
        if (r5 == null) goto L25;
        if (r5.id != r4.top_message) goto L25;
        if ((r5.flags & 32768) == 0) goto L19;
        int r52 = r5.edit_date;
    L18:
        long r53 = r52;
        if (r4.pinned == false) goto L23;
        long r7 = 1;
    L24:
        r2 = MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(r2, r7), Math.abs(DialogObject.getPeerDialogId(r4.from_id))), r4.top_message), r53);
        r10 = r10 + 1;
        goto L9
    L23:
        r7 = 0;
        goto L24
    L19:
        r52 = r5.date;
    L25:
        return 0;
    L26:
        return r2;
    }

    private void deleteTopic(long r7, int r9, int r10) {
        TL_forum.TL_messages_deleteTopicHistory r0 = new TL_forum.TL_messages_deleteTopicHistory();
        long r2 = -r7;
        r0.peer = getMessagesController().getInputPeer(r2);
        r0.top_msg_id = r9;
        if (r10 != 0) goto L5;
        getMessagesStorage().removeTopic(r2, r9);
    L5:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new AnonymousClass1(this, r7, r9));
    }

    private /* synthetic */ void lambda$applyPinnedOrder$17() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_SELECT_DIALOG)});
    }

    private /* synthetic */ void lambda$databaseCleared$25() {
        this.topicsByChatId.clear();
        this.topicsMapByChatId.clear();
        this.endIsReached.clear();
        SharedPreferences.Editor r0 = getUserConfig().getPreferences().edit();
        Iterator<String> r3 = getUserConfig().getPreferences().getAll().keySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L17;
        String r1 = r3.next();
        if (r1.startsWith("topics_load_offset_message_id_") == false) goto L9;
        r0.remove(r1);
    L9:
        if (r1.startsWith("topics_load_offset_date_") == false) goto L12;
        r0.remove(r1);
    L12:
        if (r1.startsWith("topics_load_offset_topic_id_") == false) goto L15;
        r0.remove(r1);
    L15:
        if (r1.startsWith("topics_end_reached_") == false) goto L4;
        r0.remove(r1);
        goto L4
    L17:
        r0.apply();
    }

    private /* synthetic */ void lambda$getTopicRepliesCount$29(TLObject r3, TLRPC.TL_forumTopic r4, long r5, long r7) {
        if (r3 == null) goto L10;
        TLRPC.messages_Messages r32 = (TLRPC.messages_Messages) r3;
        r4.totalMessagesCount = r32.count;
        getMessagesStorage().updateTopicData(r5, r4, 16);
        if (r32.count == 0) goto L6;
    L8:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{Long.valueOf(-r5), Boolean.TRUE});
        return;
    L6:
        if (getMessagesController().isMonoForum(r5) == false) goto L8;
        getMessagesStorage().removeTopic(r5, r7);
        onTopicsDeletedServerSide(-r5, r7);
        goto L8
    }

    private /* synthetic */ void lambda$getTopicRepliesCount$30(TLRPC.TL_forumTopic r3, long r4, long r6, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda5(this, r8, r3, r4, r6));
    }

    private /* synthetic */ void lambda$loadTopic$27(long r10, ArrayList r12, long r13, Runnable r15) {
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        StringBuilder r0 = new StringBuilder("loaded from cache ");
        r0.append(r10);
        r0.append(" topics_count=");
        if (r12 != null) goto L7;
        int r1 = 0;
    L8:
        r0.append(r1);
        FileLog.d(r0.toString());
        goto L9
    L7:
        r1 = r12.size();
    L9:
        processTopics(r10, r12, null, true, 0, -1);
        sortTopics(r10);
        if (findTopic(r10, r13) == null) goto L13;
        r15.run();
        return;
    L13:
        ArrayList<TLRPC.TL_forumTopic> r9 = new ArrayList();
        int r11 = (int) r13;
        new TLRPC.TL_forumTopic().id = r11;
        reloadTopics(r10, r9, r15);
    }

    private /* synthetic */ void lambda$loadTopic$28(long r9, long r11, Runnable r13, ArrayList r14) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda9(this, r9, r14, r11, r13));
    }

    private /* synthetic */ void lambda$loadTopics$0(long r11, ArrayList r13, boolean r14, int r15) {
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        StringBuilder r0 = new StringBuilder("loaded from cache ");
        r0.append(r11);
        r0.append(" topics_count=");
        if (r13 != null) goto L7;
        int r2 = 0;
    L8:
        r0.append(r2);
        FileLog.d(r0.toString());
        goto L9
    L7:
        r2 = r13.size();
    L9:
        this.topicsIsLoading.put(r11, 0);
        processTopics(r11, r13, null, r14, r15, -1);
        sortTopics(r11);
    }

    private /* synthetic */ void lambda$loadTopics$1(long r8, boolean r10, int r11, ArrayList r12) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda0(this, r8, r12, r10, r11));
    }

    private /* synthetic */ void lambda$loadTopics$2(TLRPC.TL_messages_savedDialogs r29, long r30, LongSparseArray r32, int r33) {
        getMessagesStorage().putUsersAndChats(r29.users, r29.chats, true, true);
        int r10 = 0;
        getMessagesController().putUsers(r29.users, false);
        getMessagesController().putChats(r29.chats, false);
        this.topicsIsLoading.put(r30, 0);
        processTopics(r30, ForumUtilities.monoForumTopicToTopic(r29.dialogs), r32, false, r33, r29.dialogs.size());
        sortTopics(r30);
        getMessagesStorage().saveTopics(-r30, this.topicsByChatId.get(r30), true, true, getConnectionsManager().getCurrentTime());
        getMessagesStorage().putMessages(r29.messages, false, true, false, 0, false, 0, 0);
        if (r29.dialogs.isEmpty() == true) goto L13;
        if (r33 != 1) goto L13;
        ArrayList<TLRPC.savedDialog> r3 = r29.dialogs;
        TLRPC.TL_monoForumDialog r34 = (TLRPC.TL_monoForumDialog) r3.get(r3.size() - 1);
        TLRPC.Message r4 = (TLRPC.Message) r32.get(r34.top_message);
        int r5 = r34.top_message;
        if (r4 == null) goto L10;
        r10 = r4.date;
    L10:
        saveLoadOffset(r30, r5, r10, DialogObject.getPeerDialogId(r34.peer));
        return;
    L13:
        if (getTopics(r30) != null) goto L15;
    L18:
        clearLoadingOffset(r30);
        loadTopics(r30);
        return;
    L15:
        if (getTopics(r30).size() < r29.dialogs.size()) goto L18;
    }

    private /* synthetic */ void lambda$loadTopics$3(TLRPC.TL_messages_savedDialogsSlice r29, long r30, LongSparseArray r32, int r33) {
        getMessagesStorage().putUsersAndChats(r29.users, r29.chats, true, true);
        int r10 = 0;
        getMessagesController().putUsers(r29.users, false);
        getMessagesController().putChats(r29.chats, false);
        this.topicsIsLoading.put(r30, 0);
        processTopics(r30, ForumUtilities.monoForumTopicToTopic(r29.dialogs), r32, false, r33, r29.count);
        sortTopics(r30);
        getMessagesStorage().saveTopics(-r30, this.topicsByChatId.get(r30), true, true, getConnectionsManager().getCurrentTime());
        getMessagesStorage().putMessages(r29.messages, false, true, false, 0, false, 0, 0);
        if (r29.dialogs.isEmpty() == true) goto L13;
        if (r33 != 1) goto L13;
        ArrayList<TLRPC.savedDialog> r3 = r29.dialogs;
        TLRPC.TL_monoForumDialog r34 = (TLRPC.TL_monoForumDialog) r3.get(r3.size() - 1);
        TLRPC.Message r4 = (TLRPC.Message) r32.get(r34.top_message);
        int r5 = r34.top_message;
        if (r4 == null) goto L10;
        r10 = r4.date;
    L10:
        saveLoadOffset(r30, r5, r10, DialogObject.getPeerDialogId(r34.peer));
        return;
    L13:
        if (getTopics(r30) != null) goto L15;
    L18:
        clearLoadingOffset(r30);
        loadTopics(r30);
        return;
    L15:
        if (getTopics(r30).size() < r29.count) goto L18;
    }

    private /* synthetic */ void lambda$loadTopics$4(long r5, TLRPC.TL_messages_savedDialogsNotModified r7) {
        this.topicsIsLoading.put(r5, 0);
        ArrayList<TLRPC.TL_forumTopic> r0 = getTopics(r5);
        if (r0 == null) goto L5;
        long r02 = r0.size();
    L7:
        if (r02 < r7.count) goto L9;
        this.endIsReached.put(r5, 1);
        getUserConfig().getPreferences().edit().putBoolean("topics_end_reached_" + r5, true).apply();
    L9:
        getNotificationCenter().postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{Long.valueOf(r5), Boolean.FALSE});
        return;
    L5:
        r02 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$loadTopics$5(TLObject r29, long r30, TLRPC.TL_messages_forumTopics r32, LongSparseArray r33, int r34) {
        TLRPC.TL_messages_forumTopics r4 = (TLRPC.TL_messages_forumTopics) r29;
        getMessagesStorage().putUsersAndChats(r4.users, r4.chats, true, true);
        int r10 = 0;
        getMessagesController().putUsers(r4.users, false);
        getMessagesController().putChats(r4.chats, false);
        this.topicsIsLoading.put(r30, 0);
        processTopics(r30, r32.topics, r33, false, r34, r4.count);
        sortTopics(r30);
        getMessagesStorage().saveTopics(-r30, this.topicsByChatId.get(r30), true, true, getConnectionsManager().getCurrentTime());
        getMessagesStorage().putMessages(r32.messages, false, true, false, 0, false, 0, 0);
        if (r32.topics.isEmpty() == true) goto L13;
        if (r34 != 1) goto L13;
        ArrayList<TLRPC.TL_forumTopic> r3 = r32.topics;
        TLRPC.TL_forumTopic r35 = r3.get(r3.size() - 1);
        TLRPC.Message r42 = (TLRPC.Message) r33.get(r35.top_message);
        int r5 = r35.top_message;
        if (r42 == null) goto L10;
        r10 = r42.date;
    L10:
        saveLoadOffset(r30, r5, r10, r35.id);
        return;
    L13:
        if (getTopics(r30) != null) goto L15;
    L18:
        clearLoadingOffset(r30);
        loadTopics(r30);
        return;
    L15:
        if (getTopics(r30).size() < r32.count) goto L18;
    }

    private /* synthetic */ void lambda$loadTopics$6(long r3) {
        this.topicsIsLoading.put(r3, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{Long.valueOf(r3), Boolean.FALSE});
    }

    private /* synthetic */ void lambda$loadTopics$7(long r9, int r11, TLObject r12, TLRPC.TL_error r13) {
        LongSparseArray r5 = new LongSparseArray();
        int r0 = 0;
        if ((r12 instanceof TLRPC.TL_messages_savedDialogs) == false) goto L11;
        TLRPC.TL_messages_savedDialogs r2 = (TLRPC.TL_messages_savedDialogs) r12;
    L6:
        if (r0 >= r2.messages.size()) goto L8;
        r5.put(r2.messages.get(r0).id, r2.messages.get(r0));
        r0 = r0 + 1;
        goto L6
    L8:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda11(this, r2, r9, r5, r11));
        return;
    L11:
        if ((r12 instanceof TLRPC.TL_messages_savedDialogsSlice) == false) goto L19;
        TLRPC.TL_messages_savedDialogsSlice r22 = (TLRPC.TL_messages_savedDialogsSlice) r12;
    L14:
        if (r0 >= r22.messages.size()) goto L16;
        r5.put(r22.messages.get(r0).id, r22.messages.get(r0));
        r0 = r0 + 1;
        goto L14
    L16:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda12(this, r22, r9, r5, r11));
        return;
    L19:
        if ((r12 instanceof TLRPC.TL_messages_savedDialogsNotModified) == false) goto L23;
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda13(this, r9, (TLRPC.TL_messages_savedDialogsNotModified) r12));
        return;
    L23:
        if ((r12 instanceof TLRPC.TL_messages_forumTopics) == false) goto L30;
        TLRPC.TL_messages_forumTopics r52 = (TLRPC.TL_messages_forumTopics) r12;
    L26:
        if (r0 >= r52.messages.size()) goto L28;
        r5.put(r52.messages.get(r0).id, r52.messages.get(r0));
        r0 = r0 + 1;
        goto L26
    L28:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda14(this, r12, r9, r52, r5, r11));
        return;
    L30:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda15(this, r9));
    }

    private /* synthetic */ void lambda$onTopicsDeletedServerSide$23(ArrayList r13) {
        HashSet r0 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= r13.size()) goto L22;
        MessagesStorage.TopicKey r3 = (MessagesStorage.TopicKey) r13.get(r2);
        long r4 = -r3.dialogId;
        LongSparseArray<TLRPC.TL_forumTopic> r6 = this.topicsMapByChatId.get(r4);
        if (r6 == null) goto L8;
        r6.remove(r3.topicId);
    L8:
        ArrayList<TLRPC.TL_forumTopic> r62 = this.topicsByChatId.get(r4);
        if (r62 == null) goto L21;
        int r7 = 0;
    L12:
        if (r7 >= r62.size()) goto L21;
        if (getMessagesController().isMonoForum(r3.dialogId) == false) goto L16;
        long r8 = DialogObject.getPeerDialogId(r62.get(r7).from_id);
    L18:
        if (r8 == r3.topicId) goto L19;
        r7 = r7 + 1;
        goto L12
    L19:
        r62.remove(r7);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogDeleted, new Object[]{Long.valueOf(-r4), Long.valueOf(r3.topicId)});
        r0.add(Long.valueOf(r4));
        goto L21
    L16:
        r8 = r62.get(r7).id;
    L21:
        r2 = r2 + 1;
        goto L4
    L22:
        Iterator r132 = r0.iterator();
    L24:
        if (r132.hasNext() == false) goto L26;
        sortTopics(((Long) r132.next()).longValue(), true);
        goto L24
    }

    private /* synthetic */ void lambda$pinTopic$19(BaseFragment r3) {
        r3.showDialog(new AlertDialog.Builder(r3.getContext()).setTitle(LocaleController.getString(R.string.LimitReached)).setMessage(LocaleController.formatString(R.string.LimitReachedPinnedTopics, new Object[]{Integer.valueOf(MessagesController.getInstance(this.currentAccount).topicsPinnedLimit)})).setPositiveButton(LocaleController.getString(R.string.OK), null).create());
    }

    private /* synthetic */ void lambda$pinTopic$20(BaseFragment r2, long r3, ArrayList r5, TLObject r6, TLRPC.TL_error r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if ("PINNED_TOO_MUCH".equals(r7.text) == false) goto L10;
        if (r2 == null) goto L15;
        applyPinnedOrder(r3, r5);
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda21(this, r2));
        return;
    L15:
        return;
    L10:
        if ("PINNED_TOPIC_NOT_MODIFIED".equals(r7.text) == false) goto L14;
        reloadTopics(r3, false);
        return;
    }

    private /* synthetic */ void lambda$processTopics$8(long r2) {
        loadTopics(r2, false, 0);
    }

    private /* synthetic */ void lambda$processUpdate$22(List r12) {
        HashSet r0 = new HashSet();
        int r2 = 0;
        LongSparseArray r4 = null;
        int r3 = 0;
    L4:
        if (r3 >= r12.size()) goto L37;
        TopicUpdate r5 = (TopicUpdate) r12.get(r3);
        if (r5.reloadTopic == false) goto L17;
        if (r4 != null) goto L9;
        r4 = new LongSparseArray();
    L9:
        ArrayList r6 = (ArrayList) r4.get(r5.dialogId);
        if (r6 != null) goto L12;
        r6 = new ArrayList();
        r4.put(r5.dialogId, r6);
    L12:
        TLRPC.TL_forumTopic r7 = new TLRPC.TL_forumTopic();
        if (ChatObject.isMonoForum(this.currentAccount, r5.dialogId) == false) goto L15;
        TLRPC.TL_peerUser r8 = new TLRPC.TL_peerUser();
        r7.from_id = r8;
        r8.user_id = r5.topicId;
    L16:
        r6.add(r7);
    L36:
        r3 = r3 + 1;
        goto L4
    L15:
        r7.id = (int) r5.topicId;
        goto L16
    L17:
        TLRPC.TL_forumTopic r62 = findTopic(-r5.dialogId, r5.topicId);
        if (r62 != null) goto L20;
    L32:
        if (r62 == null) goto L36;
        if (r5.checkForDelete == false) goto L36;
        r62.totalMessagesCount = 0;
        getTopicRepliesCount(r5.dialogId, r5.topicId);
        goto L36
    L20:
        if (r5.onlyCounters == false) goto L27;
        int r72 = r5.unreadCount;
        if (r72 < 0) goto L24;
        r62.unread_count = r72;
    L24:
        int r73 = r5.unreadMentions;
        if (r73 < 0) goto L28;
        r62.unread_mentions_count = r73;
    L28:
        int r74 = r5.totalMessagesCount;
        if (r74 <= 0) goto L31;
        r62.totalMessagesCount = r74;
    L31:
        r0.add(Long.valueOf(-r5.dialogId));
        goto L32
    L27:
        this.topicsByTopMsgId.remove(messageHash(r62.top_message, -r5.dialogId));
        r62.topMessage = r5.topMessage;
        r62.groupedMessages = r5.groupedMessages;
        int r75 = r5.topMessageId;
        r62.top_message = r75;
        r62.unread_count = r5.unreadCount;
        r62.unread_mentions_count = r5.unreadMentions;
        this.topicsByTopMsgId.put(messageHash(r75, -r5.dialogId), r62);
        goto L28
    L37:
        Iterator r122 = r0.iterator();
    L39:
        if (r122.hasNext() == false) goto L41;
        sortTopics(((Long) r122.next()).longValue(), true);
        goto L39
    L41:
        if (r4 != null) goto L43;
        return;
    L43:
        if (r2 >= r4.size()) goto L53;
        reloadTopics(-r4.keyAt(r2), (ArrayList) r4.valueAt(r2), null);
        r2 = r2 + 1;
        goto L43
    }

    private /* synthetic */ void lambda$reloadTopics$13(TLRPC.TL_messages_savedDialogs r24, long r25, ArrayList r27, LongSparseArray r28, HashSet r29, Runnable r30) {
        int r9 = 0;
        getMessagesController().putUsers(r24.users, false);
        getMessagesController().putChats(r24.chats, false);
        processTopics(r25, r27, r28, false, 2, -1);
        long r11 = -r25;
        getMessagesStorage().saveTopics(r11, this.topicsByChatId.get(r25), true, true, getConnectionsManager().getCurrentTime());
        getMessagesStorage().putMessages(r24.messages, false, true, false, 0, false, 0, 0);
        int r3 = r27.size();
    L3:
        if (r9 >= r3) goto L5;
        Object r5 = r27.get(r9);
        r9 = r9 + 1;
        r29.remove(Long.valueOf(DialogObject.getPeerDialogId(((TLRPC.TL_forumTopic) r5).from_id)));
        goto L3
    L5:
        Iterator r32 = r29.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        long r4 = ((Long) r32.next()).longValue();
        getMessagesStorage().removeTopic(r11, r4);
        onTopicsDeletedServerSide(r25, r4);
        goto L7
    L9:
        if (r30 == null) goto L14;
        r30.run();
        return;
    }

    private /* synthetic */ void lambda$reloadTopics$14(TLObject r20, long r21, TLRPC.TL_messages_forumTopics r23, LongSparseArray r24, Runnable r25) {
        TLRPC.TL_messages_forumTopics r1 = (TLRPC.TL_messages_forumTopics) r20;
        getMessagesController().putUsers(r1.users, false);
        getMessagesController().putChats(r1.chats, false);
        processTopics(r21, r23.topics, r24, false, 2, -1);
        getMessagesStorage().saveTopics(-r21, this.topicsByChatId.get(r21), true, true, getConnectionsManager().getCurrentTime());
        getMessagesStorage().putMessages(r23.messages, false, true, false, 0, false, 0, 0);
        if (r25 == null) goto L6;
        r25.run();
        return;
    }

    private /* synthetic */ void lambda$reloadTopics$15(TLObject r11, boolean r12, long r13, HashSet r15, Runnable r16) {
        int r0 = 0;
        if (r11 == null) goto L11;
        if (r12 == false) goto L11;
        LongSparseArray r7 = new LongSparseArray();
        TLRPC.TL_messages_savedDialogs r3 = (TLRPC.TL_messages_savedDialogs) r11;
        ArrayList<TLRPC.TL_forumTopic> r6 = ForumUtilities.monoForumTopicToTopic(r3.dialogs);
    L7:
        if (r0 >= r3.messages.size()) goto L9;
        r7.put(r3.messages.get(r0).id, r3.messages.get(r0));
        r0 = r0 + 1;
        goto L7
    L9:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda22(this, r3, r13, r6, r7, r15, r16));
        return;
    L11:
        if (r11 == null) goto L20;
        LongSparseArray r8 = new LongSparseArray();
        TLRPC.TL_messages_forumTopics r72 = (TLRPC.TL_messages_forumTopics) r11;
    L14:
        if (r0 >= r72.messages.size()) goto L16;
        r8.put(r72.messages.get(r0).id, r72.messages.get(r0));
        r0 = r0 + 1;
        goto L14
    L16:
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda23(this, r11, r13, r72, r8, r16));
        return;
    }

    private /* synthetic */ void lambda$reloadTopics$16(boolean r4, long r5, HashSet r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda17(this, r9, r4, r5, r7, r8));
    }

    private /* synthetic */ void lambda$reloadTopics$24(long r4, boolean r6) {
        getUserConfig().getPreferences().edit().remove("topics_end_reached_" + r4).apply();
        this.topicsByChatId.remove(r4);
        this.topicsMapByChatId.remove(r4);
        this.endIsReached.delete(r4);
        clearLoadingOffset(r4);
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(r4));
        if (r0 != null) goto L5;
    L9:
        sortTopics(r4);
        return;
    L5:
        if (r0.forum == false) goto L7;
    L8:
        loadTopics(r4, r6, 0);
        goto L9
    L7:
        if (r0.monoforum == false) goto L9;
        goto L8
    }

    private /* synthetic */ void lambda$toggleViewForumAsMessages$18(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$updateMentionsUnread$21(long r1, long r3, int r5) {
        long r12 = -r1;
        TLRPC.TL_forumTopic r32 = findTopic(r12, r3);
        if (r32 == null) goto L6;
        r32.unread_mentions_count = r5;
        sortTopics(r12, true);
        return;
    }

    private /* synthetic */ void lambda$updateReadOutbox$26(HashMap r9) {
        HashSet r0 = new HashSet();
        Iterator r1 = r9.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L12;
        MessagesStorage.TopicKey r2 = (MessagesStorage.TopicKey) r1.next();
        int r3 = ((Integer) r9.get(r2)).intValue();
        TLRPC.TL_forumTopic r4 = findTopic(-r2.dialogId, r2.topicId);
        if (r4 == null) goto L4;
        r4.read_outbox_max_id = Math.max(r4.read_outbox_max_id, r3);
        r0.add(Long.valueOf(-r2.dialogId));
        TLRPC.Message r22 = r4.topMessage;
        if (r22 == null) goto L4;
        if (r4.read_outbox_max_id < r22.id) goto L4;
        r22.unread = false;
        goto L4
    L12:
        Iterator r92 = r0.iterator();
    L14:
        if (r92.hasNext() == false) goto L16;
        Long r02 = (Long) r92.next();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{r02, Boolean.TRUE});
        goto L14
    }

    private /* synthetic */ void lambda$updateTopicsWithDeletedMessages$10(ArrayList r9, long r10) {
        int r0 = 0;
        boolean r2 = false;
        ArrayList<TLRPC.TL_forumTopic> r3 = null;
    L4:
        if (r0 >= r9.size()) goto L22;
        TLRPC.TL_forumTopic r4 = (TLRPC.TL_forumTopic) r9.get(r0);
        LongSparseArray<TLRPC.TL_forumTopic> r5 = this.topicsMapByChatId.get(r10);
        if (r5 == null) goto L21;
        TLRPC.TL_forumTopic r52 = r5.get(r4.id);
        if (r52 == null) goto L15;
        if (r4.top_message == (-1)) goto L15;
        if (r4.topMessage == null) goto L15;
        this.topicsByTopMsgId.remove(messageHash(r52.top_message, r10));
        TLRPC.Message r22 = r4.topMessage;
        int r6 = r22.id;
        r52.top_message = r6;
        r52.topMessage = r22;
        r52.groupedMessages = r4.groupedMessages;
        this.topicsByTopMsgId.put(messageHash(r6, r10), r52);
        r2 = true;
    L15:
        if (r4.top_message != (-1)) goto L17;
    L18:
        if (r3 != null) goto L20;
        r3 = new ArrayList();
    L20:
        r3.add(r4);
        goto L21
    L17:
        if (r4.topMessage == null) goto L18;
    L21:
        r0 = r0 + 1;
        goto L4
    L22:
        if (r2 == false) goto L24;
        sortTopics(r10);
    L24:
        if (r3 == null) goto L32;
        reloadTopics(r10, r3, null);
        return;
    }

    private /* synthetic */ void lambda$updateTopicsWithDeletedMessages$11(long r10, ArrayList r12, boolean r13, long r14) {
        ArrayList<TLRPC.TL_forumTopic> r0 = null;
        int r2 = 0;
        SQLiteCursor r122 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT topic_id, top_message FROM topics WHERE did = %d AND top_message IN (%s)", new Object[]{Long.valueOf(r10), TextUtils.join(",", r12)}), new Object[0]);     // Catch: Exception -> L31
        ArrayList<TLRPC.TL_forumTopic> r1 = null;
    L37:
    L9:
        e = e;
        r0 = r1;
    L32:
        e.printStackTrace();
        r1 = r0;
    L33:
        getMessagesStorage().loadGroupedMessagesForTopics(r10, r1);
        if (r1 == null) goto L49;
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda30(this, r1, r14));
        return;
    L49:
        return;
    L6:
        if (r122.next() == false) goto L16;
        if (r1 != null) goto L11;
        r1 = new ArrayList();     // Catch: Exception -> L9
    L11:
        TLRPC.TL_forumTopic r3 = new TLRPC.TL_forumTopic();     // Catch: Exception -> L9
        r3.id = r122.intValue(0);     // Catch: Exception -> L9
        r3.top_message = r122.intValue(1);     // Catch: Exception -> L9
        if (r13 == false) goto L14;
        r3.from_id = getMessagesController().getPeer(r3.id);     // Catch: Exception -> L9
    L15:
        r3.notify_settings = new TLRPC.TL_peerNotifySettings();     // Catch: Exception -> L9
        r1.add(r3);     // Catch: Exception -> L9
        goto L37
    L14:
        r3.from_id = getMessagesController().getPeer(getUserConfig().getClientUserId());     // Catch: Exception -> L9
        goto L15
    L16:
        r122.dispose();     // Catch: Exception -> L9
        if (r1 == null) goto L33;
        int r123 = 0;
    L20:
        if (r123 >= r1.size()) goto L27;
        TLRPC.TL_forumTopic r132 = r1.get(r123);     // Catch: Exception -> L9
        SQLiteCursor r32 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT mid, data FROM messages_topics WHERE uid = %d AND topic_id = %d ORDER BY mid DESC LIMIT 1", new Object[]{Long.valueOf(r10), Integer.valueOf(r132.id)}), new Object[0]);     // Catch: Exception -> L9
        if (r32.next() == false) goto L26;
        NativeByteBuffer r5 = r32.byteBufferValue(1);     // Catch: Exception -> L9
        if (r5 == null) goto L26;
        TLRPC.Message r6 = TLRPC.Message.TLdeserialize(r5, r5.readInt32(false), false);     // Catch: Exception -> L9
        r6.readAttachPath(r5, getUserConfig().clientUserId);     // Catch: Exception -> L9
        r5.reuse();     // Catch: Exception -> L9
        this.topicsByTopMsgId.remove(messageHash(r132.top_message, r14));     // Catch: Exception -> L9
        int r52 = r6.id;     // Catch: Exception -> L9
        r132.top_message = r52;     // Catch: Exception -> L9
        r132.topMessage = r6;     // Catch: Exception -> L9
        r132.groupedMessages = null;     // Catch: Exception -> L9
        this.topicsByTopMsgId.put(messageHash(r52, r14), r132);     // Catch: Exception -> L9
    L26:
        r32.dispose();     // Catch: Exception -> L9
        r123 = r123 + 1;     // Catch: Exception -> L9
    L27:
        if (r2 >= r1.size()) goto L33;
        getMessagesStorage().getDatabase().executeFast(String.format(Locale.US, "UPDATE topics SET top_message = %d WHERE did = %d AND topic_id = %d", new Object[]{Integer.valueOf(r1.get(r2).top_message), Long.valueOf(r10), Integer.valueOf(r1.get(r2).id)})).stepThis().dispose();     // Catch: Exception -> L9
        r2 = r2 + 1;
    L31:
        e = e;
        goto L32
    }

    private /* synthetic */ void lambda$updateTopicsWithDeletedMessages$12(long r10, ArrayList r12, boolean r13, long r14) {
        getMessagesStorage().getStorageQueue().postRunnable(new TopicsController$$ExternalSyntheticLambda10(this, r10, r12, r13, r14));
    }

    private long messageHash(int r2, long r3) {
        return r3 + (r2 << 12);
    }

    private void sortTopics(long r2) {
        sortTopics(r2, true);
    }

    public void applyPinnedOrder(long r2, ArrayList<Integer> r4) {
        applyPinnedOrder(r2, r4, true);
    }

    public void applyPinnedOrder(long r10, ArrayList<Integer> r12, boolean r13) {
        if (r12 == null) goto L35;
        ArrayList<TLRPC.TL_forumTopic> r0 = getTopics(r10);
        boolean r1 = true;
        if (r0 == null) goto L24;
        int r3 = 0;
        boolean r4 = false;
    L8:
        if (r3 >= r0.size()) goto L23;
        TLRPC.TL_forumTopic r5 = r0.get(r3);
        if (r5 == null) goto L22;
        int r6 = r12.indexOf(Integer.valueOf(r5.id));
        if (r6 < 0) goto L15;
        boolean r7 = true;
    L17:
        if (r5.pinned != r7) goto L21;
        if (r7 == false) goto L22;
        if (r5.pinnedOrder == r6) goto L22;
    L21:
        r5.pinned = r7;
        r5.pinnedOrder = r6;
        getMessagesStorage().updateTopicData(r10, r5, 4);
        r4 = true;
        goto L22
    L15:
        r7 = false;
    L22:
        r3 = r3 + 1;
        goto L8
    L23:
        r1 = r4;
    L24:
        if (r13 == false) goto L33;
        if (r1 == false) goto L34;
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda1(this));
        return;
    L34:
        return;
    L33:
        return;
    }

    public void clearLoadingOffset(long r1) {
        this.offsets.remove(r1);
    }

    public void databaseCleared() {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda20(this));
    }

    public void deleteTopics(long r10, ArrayList<Integer> r12) {
        ArrayList<TLRPC.TL_forumTopic> r0 = this.topicsByChatId.get(r10);
        LongSparseArray<TLRPC.TL_forumTopic> r1 = this.topicsMapByChatId.get(r10);
        if (r1 == null) goto L13;
        if (r0 == null) goto L13;
        int r3 = 0;
    L7:
        if (r3 >= r12.size()) goto L12;
        long r4 = r12.get(r3).intValue();
        TLRPC.TL_forumTopic r6 = r1.get(r4);
        r1.remove(r4);
        if (r6 == null) goto L11;
        this.topicsByTopMsgId.remove(messageHash(r6.top_message, r10));
        r0.remove(r6);
    L11:
        r3 = r3 + 1;
        goto L7
    L12:
        sortTopics(r10);
    L13:
        int r02 = 0;
    L15:
        if (r02 >= r12.size()) goto L17;
        deleteTopic(r10, r12.get(r02).intValue(), 0);
        r02 = r02 + 1;
        goto L15
    }

    public boolean endIsReached(long r2) {
        if (this.endIsReached.get(r2, 0) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public TLRPC.TL_forumTopic findTopic(long r3, long r5) {
        LongSparseArray<TLRPC.TL_forumTopic> r0 = this.topicsMapByChatId.get(r3);
        if (r0 == null) goto L11;
        TLRPC.TL_forumTopic r1 = r0.get(r5);
        if (r1 == null) goto L7;
    L10:
        return r1;
    L7:
        if (getMessagesController().isMonoForum(-r3) == false) goto L10;
        return r0.get(ForumUtilities.monoForumTopicIdToTopicId(r5));
    L11:
        return null;
    }

    public ArrayList<Integer> getCurrentPinnedOrder(long r3) {
        ArrayList<TLRPC.TL_forumTopic> r2 = getTopics(r3);
        ArrayList<Integer> r32 = new ArrayList();
        if (r2 == null) goto L14;
        int r4 = 0;
    L6:
        if (r4 >= r2.size()) goto L14;
        TLRPC.TL_forumTopic r0 = r2.get(r4);
        if (r0 == null) goto L13;
        if (r0.pinned == false) goto L13;
        r32.add(Integer.valueOf(r0.id));
    L13:
        r4 = r4 + 1;
    L14:
        return r32;
    }

    public int[] getForumUnreadCount(long r11) {
        ArrayList<TLRPC.TL_forumTopic> r0 = this.topicsByChatId.get(r11);
        Arrays.fill(countsTmp, 0);
        if (r0 == null) goto L24;
        int r1 = 0;
    L6:
        if (r1 >= r0.size()) goto L24;
        TLRPC.TL_forumTopic r3 = r0.get(r1);
        int[] r4 = countsTmp;
        int r5 = r4[0];
        int r7 = 1;
        if (r3.unread_count <= 0) goto L10;
        int r6 = 1;
    L11:
        r4[0] = r5 + r6;
        int r52 = r4[1];
        if (r3.unread_mentions_count <= 0) goto L14;
        int r62 = 1;
    L15:
        r4[1] = r52 + r62;
        int r63 = r4[2];
        if (r3.unread_reactions_count > 0) goto L19;
        r7 = 0;
    L19:
        r4[2] = r63 + r7;
        if (getMessagesController().isDialogMuted(-r11, r3.id) == true) goto L22;
        r4[3] = r4[3] + r3.unread_count;
    L22:
        r4[4] = r4[4] + r3.unread_poll_votes_count;
        r1 = r1 + 1;
        goto L6
    L14:
        r62 = 0;
        goto L15
    L10:
        r6 = 0;
    L24:
        return countsTmp;
    }

    public TopicsLoadOffset getLoadOffset(long r2) {
        TopicsLoadOffset r22 = this.offsets.get(r2);
        if (r22 == null) goto L6;
        return r22;
    L6:
        return new TopicsLoadOffset(this, null);
    }

    public CharSequence getTopicIconName(TLRPC.Chat r2, MessageObject r3, TextPaint r4) {
        return getTopicIconName(r2, r3, r4, null);
    }

    public CharSequence getTopicIconName(TLRPC.Chat r4, MessageObject r5, TextPaint r6, Drawable[] r7) {
        TLRPC.MessageReplyHeader r52 = r5.messageOwner.reply_to;
        if (r52 != null) goto L5;
        return null;
    L5:
        int r1 = r52.reply_to_top_id;
        if (r1 != 0) goto L8;
        r1 = r52.reply_to_msg_id;
    L8:
        if (r1 == 0) goto L13;
        TLRPC.TL_forumTopic r3 = findTopic(r4.id, r1);
        if (r3 == null) goto L13;
        return ForumUtilities.getTopicSpannedName(r3, r6, r7, false);
    L13:
        return null;
    }

    public CharSequence getTopicIconNameSafe(TLRPC.Chat r3, long r4, TextPaint r6, Drawable[] r7) {
        if (r4 == 0) goto L8;
        TLRPC.TL_forumTopic r2 = findTopic(r3.id, r4);
        if (r2 != null) goto L7;
        return null;
    L7:
        return ForumUtilities.getTopicSpannedName(r2, r6, r7, false);
    L8:
        return null;
    }

    public String getTopicName(TLRPC.Chat r3, MessageObject r4) {
        TLRPC.MessageReplyHeader r42 = r4.messageOwner.reply_to;
        if (r42 != null) goto L6;
        return null;
    L6:
        int r0 = r42.reply_to_top_id;
        if (r0 != 0) goto L9;
        r0 = r42.reply_to_msg_id;
    L9:
        if (r0 == 0) goto L14;
        TLRPC.TL_forumTopic r2 = findTopic(r3.id, r0);
        if (r2 != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return r2.title;
    L14:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void getTopicRepliesCount(long r10, long r12) {
        TLRPC.TL_forumTopic r4 = findTopic(-r10, r12);
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.totalMessagesCount == 0) goto L7;
        return;
    L7:
        if (getMessagesController().isMonoForum(r10) == false) goto L9;
        TLRPC.TL_messages_getSavedHistory r0 = new TLRPC.TL_messages_getSavedHistory();
        r0.peer = getMessagesController().getInputPeer(r12);
        r0.parent_peer = getMessagesController().getInputPeer(r10);
        r0.limit = 1;
        TLRPC.TL_messages_getReplies r02 = r0;
    L10:
        getConnectionsManager().sendRequest(r02, new TopicsController$$ExternalSyntheticLambda8(this, r4, r10, r12));
        return;
    L9:
        TLRPC.TL_messages_getReplies r03 = new TLRPC.TL_messages_getReplies();
        r03.peer = getMessagesController().getInputPeer(r10);
        r03.msg_id = (int) r12;
        r03.limit = 1;
        r02 = r03;
        goto L10
    }

    public ArrayList<TLRPC.TL_forumTopic> getTopics(long r1) {
        return this.topicsByChatId.get(r1);
    }

    public int getTopicsCount(long r3) {
        if (endIsReached(r3) == false) goto L8;
        ArrayList<TLRPC.TL_forumTopic> r2 = getTopics(r3);
        if (r2 == null) goto L8;
        return r2.size();
    L8:
        return 0;
    }

    public boolean isLoading(long r4) {
        if (this.topicsIsLoading.get(r4, 0) == 1) goto L5;
    L9:
        return false;
    L5:
        if (this.topicsByChatId.get(r4) != null) goto L7;
    L8:
        return true;
    L7:
        if (this.topicsByChatId.get(r4).isEmpty() == false) goto L9;
        goto L8
    }

    public void loadTopic(long r11, long r13, Runnable r15) {
        getMessagesStorage().loadTopics(-r11, new TopicsController$$ExternalSyntheticLambda3(this, r11, r13, r15));
    }

    public void loadTopics(long r3) {
        loadTopics(r3, false, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [org.telegram.tgnet.tl.TL_forum$TL_messages_getForumTopics] */
    /* JADX WARN: Type inference failed for: r0v7, types: [org.telegram.tgnet.TLObject] */
    /* JADX WARN: Type inference failed for: r0v8, types: [org.telegram.tgnet.TLRPC$TL_messages_getSavedDialogs] */
    /* JADX WARN: Type inference failed for: r4v3, types: [org.telegram.tgnet.ConnectionsManager] */
    public void loadTopics(long r19, boolean r21, int r22) {
        if (this.topicsIsLoading.get(r19, 0) == 0) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("load topics " + r19 + " fromCache=" + r21 + " loadType=" + r22);
    L8:
        this.topicsIsLoading.put(r19, 1);
        if (r21 == false) goto L12;
        getMessagesStorage().loadTopics(-r19, new TopicsController$$ExternalSyntheticLambda28(this, r19, r21, r22));
        return;
    L12:
        long r8 = -r19;
        if (getMessagesController().isMonoForum(r8) == false) goto L32;
        ?? r0 = new TLRPC.TL_messages_getSavedDialogs();
        r0.parent_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r8);
        r0.flags |= 2;
        TopicsLoadOffset r82 = getLoadOffset(r19);
        if (r22 != 0) goto L17;
    L26:
        long r16 = 0;
    L27:
        ArrayList<TLRPC.TL_forumTopic> r4 = getTopics(r19);
        r0.limit = 20;
        r0.offset_id = Integer.MAX_VALUE;
        r0.offset_date = 0;
        r0.offset_peer = new TLRPC.TL_inputPeerEmpty();
        if (r4 == null) goto L30;
        long r14 = calculateHashSavedDialogs(r4, 0, Math.min(r4.size(), 20));
    L31:
        r0.hash = r14;
    L39:
        getConnectionsManager().sendRequest(r0, new TopicsController$$ExternalSyntheticLambda29(this, r19, r22));
        return;
    L30:
        r14 = r16;
        goto L31
    L17:
        if (r22 == 3) goto L26;
        if (r22 == 1) goto L22;
        r16 = 0;
        if (r82.lastTopicId == 0) goto L27;
    L22:
        if (r22 != 1) goto L39;
        r0.limit = 100;
        r0.offset_date = r82.lastMessageDate;
        r0.offset_id = r82.lastMessageId;
        r0.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r82.lastTopicId);
        if (BuildVars.LOGS_ENABLED == false) goto L39;
        FileLog.d("offset_date=" + r82.lastMessageDate + " offset_id=" + r82.lastMessageId + " offset_topic=" + r82.lastTopicId);
        goto L39
    L32:
        r0 = new TL_forum.TL_messages_getForumTopics();
        r0.peer = getMessagesController().getInputPeer(r8);
        if (r22 != 0) goto L35;
        r0.limit = 20;
        goto L39
    L35:
        if (r22 != 1) goto L39;
        r0.limit = 100;
        TopicsLoadOffset r6 = getLoadOffset(r19);
        r0.offset_date = r6.lastMessageDate;
        r0.offset_id = r6.lastMessageId;
        r0.offset_topic = (int) r6.lastTopicId;
        if (BuildVars.LOGS_ENABLED == false) goto L39;
        FileLog.d("offset_date=" + r6.lastMessageDate + " offset_id=" + r6.lastMessageId + " offset_topic=" + r6.lastTopicId);
        goto L39
    }

    public void markAllPollVotesAsRead(long r5) {
        ArrayList<TLRPC.TL_forumTopic> r0 = getTopics(r5);
        if (r0 == null) goto L17;
        int r2 = 0;
    L6:
        if (r2 >= r0.size()) goto L12;
        TLRPC.TL_forumTopic r3 = r0.get(r2);
        if (r3 == null) goto L11;
        r3.unread_poll_votes_count = 0;
    L11:
        r2 = r2 + 1;
        goto L6
    L12:
        sortTopics(r5);
        return;
    }

    public void markAllPollVotesAsRead(long r1, long r3) {
        TLRPC.TL_forumTopic r32 = findTopic(r1, r3);
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.unread_poll_votes_count <= 0) goto L9;
        r32.unread_poll_votes_count = 0;
        sortTopics(r1);
        return;
    }

    public void markAllReactionsAsRead(long r5) {
        ArrayList<TLRPC.TL_forumTopic> r0 = getTopics(r5);
        if (r0 == null) goto L17;
        int r2 = 0;
    L6:
        if (r2 >= r0.size()) goto L12;
        TLRPC.TL_forumTopic r3 = r0.get(r2);
        if (r3 == null) goto L11;
        r3.unread_reactions_count = 0;
    L11:
        r2 = r2 + 1;
        goto L6
    L12:
        sortTopics(r5);
        return;
    }

    public void markAllReactionsAsRead(long r1, long r3) {
        TLRPC.TL_forumTopic r32 = findTopic(r1, r3);
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.unread_reactions_count <= 0) goto L9;
        r32.unread_reactions_count = 0;
        sortTopics(r1);
        return;
    }

    public void onTopicCreated(long r12, TLRPC.TL_forumTopic r14, boolean r15) {
        long r9 = -r12;
        LongSparseArray<TLRPC.TL_forumTopic> r1 = this.topicsMapByChatId.get(r9);
        if (findTopic(r9, r14.id) == null) goto L5;
        return;
    L5:
        if (r1 != null) goto L7;
        r1 = new LongSparseArray();
        this.topicsMapByChatId.put(r9, r1);
    L7:
        ArrayList<TLRPC.TL_forumTopic> r2 = this.topicsByChatId.get(r9);
        if (r2 != null) goto L10;
        r2 = new ArrayList();
        this.topicsByChatId.put(r9, r2);
    L10:
        r1.put(r14.id, r14);
        r2.add(r14);
        if (r15 == false) goto L13;
        getMessagesStorage().saveTopics(r12, Collections.singletonList(r14), false, true, getConnectionsManager().getCurrentTime());
    L13:
        sortTopics(r9, true);
    }

    public void onTopicEdited(long r3, TLRPC.TL_forumTopic r5) {
        getMessagesStorage().updateTopicData(r3, r5, 35);
        sortTopics(-r3);
    }

    public void onTopicFragmentPause(long r3) {
        int r1 = 0;
        int r0 = this.openedTopicsByChatId.get(r3, 0) - 1;
        if (r0 < 0) goto L6;
        r1 = r0;
    L6:
        this.openedTopicsByChatId.put(r3, r1);
    }

    public void onTopicFragmentResume(long r3) {
        int r0 = this.openedTopicsByChatId.get(r3, 0);
        this.openedTopicsByChatId.put(r3, r0 + 1);
        sortTopics(r3);
    }

    public void onTopicsDeletedServerSide(long r3, long r5) {
        ArrayList<MessagesStorage.TopicKey> r0 = new ArrayList(1);
        r0.add(MessagesStorage.TopicKey.of(-r3, r5));
        onTopicsDeletedServerSide(r0);
    }

    public void onTopicsDeletedServerSide(ArrayList<MessagesStorage.TopicKey> r2) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda25(this, r2));
    }

    public void pinTopic(long r7, int r9, boolean r10, BaseFragment r11) {
        TL_forum.TL_messages_updatePinnedForumTopic r0 = new TL_forum.TL_messages_updatePinnedForumTopic();
        r0.peer = getMessagesController().getInputPeer(-r7);
        r0.topic_id = r9;
        r0.pinned = r10;
        ArrayList<Integer> r112 = getCurrentPinnedOrder(r7);
        ArrayList<Integer> r2 = new ArrayList(r112);
        r2.remove(Integer.valueOf(r9));
        if (r10 == false) goto L5;
        r2.add(0, Integer.valueOf(r9));
    L5:
        applyPinnedOrder(r7, r2);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new TopicsController$$ExternalSyntheticLambda6(this, r11, r7, r112));
    }

    public void preloadTopics(long r3) {
        loadTopics(r3, true, 0);
    }

    public void processEditedMessage(TLRPC.Message r5) {
        TLRPC.TL_forumTopic r0 = this.topicsByTopMsgId.get(messageHash(r5.id, -r5.dialog_id));
        if (r0 == null) goto L6;
        r0.topMessage = r5;
        sortTopics(-r5.dialog_id, true);
        return;
    }

    public void processEditedMessages(LongSparseArray<ArrayList<MessageObject>> r10) {
        HashSet r0 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= r10.size()) goto L13;
        ArrayList<MessageObject> r3 = r10.valueAt(r2);
        int r4 = 0;
    L7:
        if (r4 >= r3.size()) goto L12;
        TLRPC.TL_forumTopic r5 = this.topicsByTopMsgId.get(messageHash(r3.get(r4).getId(), -r3.get(r4).getDialogId()));
        if (r5 == null) goto L11;
        r5.topMessage = r3.get(r4).messageOwner;
        r0.add(Long.valueOf(-r3.get(r4).getDialogId()));
    L11:
        r4 = r4 + 1;
        goto L7
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        Iterator r102 = r0.iterator();
    L15:
        if (r102.hasNext() == false) goto L17;
        sortTopics(((Long) r102.next()).longValue(), true);
        goto L15
    }

    public void processTopics(long r22, ArrayList<TLRPC.TL_forumTopic> r24, LongSparseArray<TLRPC.Message> r25, boolean r26, int r27, int r28) {
        ArrayList<TLRPC.TL_forumTopic> r3 = r24;
        LongSparseArray<TLRPC.Message> r4 = r25;
        if (r27 != 3) goto L8;
        if (getMessagesController().isMonoForum(-r22) == false) goto L8;
        getUserConfig().getPreferences().edit().remove("topics_end_reached_" + r22).apply();
        this.topicsByChatId.remove(r22);
        this.topicsMapByChatId.remove(r22);
        this.endIsReached.delete(r22);
        clearLoadingOffset(r22);
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        StringBuilder r10 = new StringBuilder("processTopics=new_topics_size=");
        if (r3 != null) goto L12;
        int r12 = 0;
    L13:
        r10.append(r12);
        r10.append(" fromCache=");
        r10.append(r26);
        r10.append(" load_type=");
        r10.append(r27);
        r10.append(" totalCount=");
        r10.append(r28);
        FileLog.d(r10.toString());
        goto L14
    L12:
        r12 = r3.size();
    L14:
        ArrayList<TLRPC.TL_forumTopic> r102 = this.topicsByChatId.get(r22);
        LongSparseArray<TLRPC.TL_forumTopic> r122 = this.topicsMapByChatId.get(r22);
        if (r102 != null) goto L17;
        r102 = new ArrayList();
        this.topicsByChatId.put(r22, r102);
    L17:
        if (r122 != null) goto L19;
        r122 = new LongSparseArray();
        this.topicsMapByChatId.put(r22, r122);
    L19:
        if (r3 == null) goto L55;
        int r15 = 0;
        ArrayList<Long> r16 = null;
        ArrayList<TLRPC.TL_forumTopic> r17 = null;
        boolean r18 = false;
    L22:
        if (r15 >= r3.size()) goto L54;
        TLRPC.TL_forumTopic r11 = r3.get(r15);
        if ((r11 instanceof TLRPC.TL_forumTopicDeleted) == false) goto L29;
        if (r16 != null) goto L27;
        ArrayList<Long> r9 = new ArrayList();
    L28:
        int r19 = r15;
        r9.add(Long.valueOf(r11.id));
        r16 = r9;
    L53:
        r15 = r19 + 1;
        r3 = r24;
        r4 = r25;
        goto L22
    L27:
        r9 = r16;
        goto L28
    L29:
        r19 = r15;
        if (r122.containsKey(r11.id) == true) goto L47;
        if (r4 == null) goto L34;
        r11.topMessage = r4.get(r11.top_message);
        r11.topicStartMessage = r4.get(r11.id);
    L34:
        if (r11.topMessage != null) goto L42;
        if (r11.isShort == true) goto L42;
        if (r17 != null) goto L39;
        ArrayList<TLRPC.TL_forumTopic> r92 = new ArrayList();
    L40:
        r92.add(r11);
        r17 = r92;
        goto L42
    L39:
        r92 = r17;
    L42:
        if (r11.topicStartMessage != null) goto L44;
        TLRPC.TL_message r93 = new TLRPC.TL_message();
        r11.topicStartMessage = r93;
        r93.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r93.id = r11.id;
        r93.peer_id = getMessagesController().getPeer(-r22);
        r11.topicStartMessage.action = new TLRPC.TL_messageActionTopicCreate();
        r11.topicStartMessage.action.title = r11.title;
    L44:
        r102.add(r11);
        r122.put(r11.id, r11);
        this.topicsByTopMsgId.put(messageHash(r11.top_message, r22), r11);
    L45:
        r18 = true;
        goto L53
    L47:
        if (r11.isShort == true) goto L53;
        TLRPC.TL_forumTopic r32 = r122.get(r11.id);
        if (r32 == null) goto L53;
        boolean r42 = r32.closed;
        boolean r94 = r11.closed;
        if (r42 == r94) goto L53;
        r32.closed = r94;
        getMessagesStorage().updateTopicData(-r22, r11, 8);
        goto L45
    L54:
        ArrayList<Long> r33 = r16;
        ArrayList<TLRPC.TL_forumTopic> r43 = r17;
    L56:
        int r95 = 0;
        int r112 = 0;
    L58:
        if (r95 >= r102.size()) goto L69;
        TLRPC.TL_forumTopic r14 = r102.get(r95);
        if (r14 == null) goto L67;
        if (r14.pinned == false) goto L67;
        int r152 = r112 + 1;
        if (r14.pinnedOrder == r112) goto L66;
        r14.pinnedOrder = r112;
        r112 = r152;
        r18 = true;
        goto L67
    L66:
        r112 = r152;
    L67:
        r95 = r95 + 1;
        goto L58
    L69:
        if (r33 == null) goto L83;
        if (r27 != 2) goto L83;
        int r113 = 0;
    L73:
        if (r113 >= r33.size()) goto L82;
        int r13 = 0;
    L76:
        if (r13 >= r102.size()) goto L81;
        if (r102.get(r13).id == r33.get(r113).longValue()) goto L79;
        r13 = r13 + 1;
        goto L76
    L79:
        r102.remove(r13);
    L81:
        r113 = r113 + 1;
        goto L73
    L82:
        getMessagesStorage().removeTopics(r22, r33);
    L83:
        if (r43 == null) goto L86;
        if (r27 == 2) goto L86;
        reloadTopics(r22, r43, null);
        boolean r34 = true;
    L100:
        if (r122.size() <= r102.size()) goto L116;
        FileLog.e("[TopicsController]: cache desynchronization");
        HashSet r44 = new HashSet(r122.size());
        int r7 = 0;
    L103:
        if (r7 >= r122.size()) goto L105;
        r44.add(Long.valueOf(r122.keyAt(r7)));
        r7 = r7 + 1;
        goto L103
    L105:
        int r72 = r102.size();
        int r8 = 0;
    L106:
        if (r8 >= r72) goto L111;
        TLRPC.TL_forumTopic r96 = r102.get(r8);
        r8 = r8 + 1;
        if (r96 == null) goto L106;
        r44.remove(Long.valueOf(r9.id));
        goto L106
    L111:
        Iterator r45 = r44.iterator();
    L113:
        if (r45.hasNext() == false) goto L115;
        r122.remove(((Long) r45.next()).longValue());
        goto L113
    L115:
        boolean r142 = r34;
    L117:
        if (r142 == false) goto L119;
        sortTopics(r22, false);
    L119:
        getNotificationCenter().postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{Long.valueOf(r22), Boolean.TRUE});
        if (r27 == 0) goto L123;
        if (r27 != 0) goto L150;
        if (r26 == false) goto L123;
        return;
    L150:
        return;
    L123:
        if (r26 == true) goto L125;
        return;
    L125:
        if (this.topicsByChatId.get(r22).isEmpty() == false) goto L153;
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda18(this, r22));
        return;
    L153:
        return;
    L116:
        r142 = r18;
    L86:
        if (r27 != 0) goto L88;
        if (r26 == true) goto L88;
        r34 = true;
    L94:
        if (r102.size() < r28) goto L100;
        if (r28 < 0) goto L100;
        if (endIsReached(r22) == true) goto L100;
        this.endIsReached.put(r22, r34 ? 1 : 0);
        getUserConfig().getPreferences().edit().putBoolean("topics_end_reached_" + r22, r34).apply();
        r18 = r34 ? 1 : 0;
    L88:
        r34 = true;
        r34 = true;
        r34 = true;
        if (r27 == 1) goto L94;
        if (r27 != 3) goto L100;
    L55:
        r33 = null;
        r43 = null;
        r18 = false;
        goto L56
    }

    public void processUpdate(List<TopicUpdate> r2) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda19(this, r2));
    }

    public void reloadTopics(long r2) {
        reloadTopics(r2, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reloadTopics(long r11, ArrayList<TLRPC.TL_forumTopic> r13, Runnable r14) {
        long r1 = -r11;
        boolean r5 = getMessagesController().isMonoForum(r1);
        HashSet r8 = new HashSet();
        int r0 = 0;
        if (r5 == false) goto L10;
        TLRPC.TL_messages_getSavedDialogsByID r3 = new TLRPC.TL_messages_getSavedDialogsByID();
    L6:
        if (r0 >= r13.size()) goto L8;
        long r6 = DialogObject.getPeerDialogId(r13.get(r0).from_id);
        r8.add(Long.valueOf(r6));
        r3.ids.add(getMessagesController().getInputPeer(r6));
        r0 = r0 + 1;
        goto L6
    L8:
        r3.parent_peer = getMessagesController().getInputPeer(r1);
        TL_forum.TL_messages_getForumTopicsByID r32 = r3;
    L9:
        TL_forum.TL_messages_getForumTopicsByID r132 = r32;
        getConnectionsManager().sendRequest(r132, new TopicsController$$ExternalSyntheticLambda7(this, r5, r11, r8, r14));
        return;
    L10:
        TL_forum.TL_messages_getForumTopicsByID r33 = new TL_forum.TL_messages_getForumTopicsByID();
    L12:
        if (r0 >= r13.size()) goto L14;
        r33.topics.add(Integer.valueOf(r13.get(r0).id));
        r0 = r0 + 1;
        goto L12
    L14:
        r33.peer = getMessagesController().getInputPeer(r1);
        r32 = r33;
        goto L9
    }

    public void reloadTopics(long r2, boolean r4) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda24(this, r2, r4));
    }

    public void reorderPinnedTopics(long r5, ArrayList<Integer> r7) {
        TL_forum.TL_messages_reorderPinnedForumTopics r0 = new TL_forum.TL_messages_reorderPinnedForumTopics();
        r0.peer = getMessagesController().getInputPeer(-r5);
        if (r7 == null) goto L5;
        r0.order.addAll(r7);
    L5:
        r0.force = true;
        applyPinnedOrder(r5, r7, false);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }

    public void saveLoadOffset(long r3, int r5, int r6, long r7) {
        TopicsLoadOffset r0 = new TopicsLoadOffset(this, null);
        r0.lastMessageId = r5;
        r0.lastMessageDate = r6;
        r0.lastTopicId = r7;
        this.offsets.put(r3, r0);
    }

    public void saveTopics(long r9) {
        if (this.topicsByChatId.get(r9) == null) goto L6;
        getMessagesStorage().saveTopics(-r9, this.topicsByChatId.get(r9), true, true, getConnectionsManager().getCurrentTime());
        return;
    }

    public void sortTopics(long r4, boolean r6) {
        ArrayList<TLRPC.TL_forumTopic> r0 = this.topicsByChatId.get(r4);
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.openedTopicsByChatId.get(r4, 0) <= 0) goto L7;
        Collections.sort(r0, new TopicsController$$ExternalSyntheticLambda16());
    L7:
        if (r6 == false) goto L11;
        getNotificationCenter().postNotificationName(NotificationCenter.topicsDidLoaded, new Object[]{Long.valueOf(r4), Boolean.TRUE});
        return;
    }

    public void toggleCloseTopic(long r5, int r7, boolean r8) {
        TL_forum.TL_messages_editForumTopic r0 = new TL_forum.TL_messages_editForumTopic();
        long r2 = -r5;
        r0.peer = getMessagesController().getInputPeer(r2);
        r0.topic_id = r7;
        r0.flags |= 4;
        r0.closed = r8;
        LongSparseArray<TLRPC.TL_forumTopic> r52 = this.topicsMapByChatId.get(r5);
        if (r52 == null) goto L7;
        TLRPC.TL_forumTopic r53 = r52.get(r7);
        if (r53 == null) goto L7;
        r53.closed = r8;
        getMessagesStorage().updateTopicData(r2, r53, 8);
    L7:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new AnonymousClass2(this));
    }

    public void toggleShowTopic(long r5, int r7, boolean r8) {
        TL_forum.TL_messages_editForumTopic r0 = new TL_forum.TL_messages_editForumTopic();
        long r2 = -r5;
        r0.peer = getMessagesController().getInputPeer(r2);
        r0.topic_id = r7;
        r0.flags = 8;
        r0.hidden = !r8;
        TLRPC.TL_forumTopic r52 = findTopic(r5, r7);
        if (r52 == null) goto L8;
        boolean r6 = r0.hidden;
        r52.hidden = r6;
        if (r6 == false) goto L7;
        r52.closed = true;
    L7:
        updateTopicInUi(r2, r52, 44);
        getMessagesStorage().updateTopicData(r2, r52, 44);
    L8:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }

    public void toggleViewForumAsMessages(long r3, boolean r5) {
        TLRPC.TL_channels_toggleViewForumAsMessages r0 = new TLRPC.TL_channels_toggleViewForumAsMessages();
        r0.channel_id = getMessagesController().getInputChannel(r3);
        r0.enabled = r5;
        getConnectionsManager().sendRequest(r0, new TopicsController$$ExternalSyntheticLambda26(this));
    }

    public void updateMaxReadId(long r1, long r3, int r5, int r6, int r7) {
        TLRPC.TL_forumTopic r32 = findTopic(r1, r3);
        if (r32 == null) goto L9;
        r32.read_inbox_max_id = r5;
        r32.unread_count = r6;
        if (r7 < 0) goto L7;
        r32.unread_mentions_count = r7;
    L7:
        sortTopics(r1);
        return;
    }

    public void updateMentionsUnread(long r8, long r10, int r12) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda2(this, r8, r10, r12));
    }

    public int updatePollVotesUnread(long r1, long r3, int r5, boolean r6) {
        long r12 = -r1;
        TLRPC.TL_forumTopic r32 = findTopic(r12, r3);
        if (r32 == null) goto L11;
        if (r6 == false) goto L8;
        int r4 = r32.unread_poll_votes_count + r5;
        r32.unread_poll_votes_count = r4;
        if (r4 >= 0) goto L9;
        r32.unread_poll_votes_count = 0;
    L9:
        int r33 = r32.unread_poll_votes_count;
        sortTopics(r12, true);
        return r33;
    L8:
        r32.unread_poll_votes_count = r5;
        goto L9
    L11:
        return -1;
    }

    public int updateReactionsUnread(long r1, long r3, int r5, boolean r6) {
        long r12 = -r1;
        TLRPC.TL_forumTopic r32 = findTopic(r12, r3);
        if (r32 == null) goto L11;
        if (r6 == false) goto L8;
        int r4 = r32.unread_reactions_count + r5;
        r32.unread_reactions_count = r4;
        if (r4 >= 0) goto L9;
        r32.unread_reactions_count = 0;
    L9:
        int r33 = r32.unread_reactions_count;
        sortTopics(r12, true);
        return r33;
    L8:
        r32.unread_reactions_count = r5;
        goto L9
    L11:
        return -1;
    }

    public void updateReadOutbox(HashMap<MessagesStorage.TopicKey, Integer> r2) {
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda27(this, r2));
    }

    public void updateTopicInUi(long r4, TLRPC.TL_forumTopic r6, int r7) {
        long r42 = -r4;
        TLRPC.TL_forumTopic r0 = findTopic(r42, r6.id);
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r7 & 1) == 0) goto L8;
        r0.title = r6.title;
    L8:
        if ((r7 & 2) == 0) goto L11;
        r0.icon_emoji_id = r6.icon_emoji_id;
    L11:
        if ((r7 & 8) == 0) goto L14;
        r0.closed = r6.closed;
    L14:
        if ((r7 & 4) == 0) goto L17;
        r0.pinned = r6.pinned;
    L17:
        if ((r7 & 32) == 0) goto L19;
        r0.hidden = r6.hidden;
    L19:
        sortTopics(r42);
    }

    public void updateTopicsWithDeletedMessages(long r10, ArrayList<Integer> r12) {
        if (r10 <= 0) goto L5;
        return;
    L5:
        long r7 = -r10;
        AndroidUtilities.runOnUIThread(new TopicsController$$ExternalSyntheticLambda4(this, r10, r12, ChatObject.isMonoForum(getMessagesController().getChat(Long.valueOf(r7))), r7));
    }
}
