package org.telegram.messenger;

import android.content.SharedPreferences;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_forum;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.TypingDotsDrawable;
import org.telegram.ui.MultiLayoutTypingAnimator;

/* loaded from: classes5.dex */
public class BotForumHelper extends BaseController {
    private static volatile BotForumHelper[] Instance;
    private final DialogTopicIdKeyMap<BotDraftMessage> botTextDraftsByRandomIds;
    private final DialogTopicIdKeyMap<Object> botTextDraftsByRandomIdsBlocklist;
    private final LongSparseArray<List<MessagesStorage.IntCallback>> pendingBotTopics;
    private final SharedPreferences preferences;

    public static class BotDraftAnimationsPool {
        private final DialogTopicIdKeyMap<MultiLayoutTypingAnimator> animators;
        private final SparseIntArray ids;

        public BotDraftAnimationsPool() {
            this.animators = new DialogTopicIdKeyMap();
            this.ids = new SparseIntArray();
        }

        public void bind(int r1, int r2) {
            this.ids.put(r2, r1);
        }

        public MultiLayoutTypingAnimator getAnimator(long r10, int r12, boolean r13) {
            if (r12 <= 0) goto L4;
            r12 = this.ids.get(r12, 0);
        L4:
            if (r12 != 0) goto L7;
            return null;
        L7:
            long r5 = r12;
            MultiLayoutTypingAnimator r102 = this.animators.get(r10, 0, r5);
            if (r102 != null) goto L12;
            if (r13 == false) goto L12;
            MultiLayoutTypingAnimator r8 = new MultiLayoutTypingAnimator();
            this.animators.put(r10, 0, r5, r8);
            return r8;
        L12:
            return r102;
        }

        public void removeAnimator(long r8, int r10) {
            if (r10 <= 0) goto L4;
            r10 = this.ids.get(r10, 0);
        L4:
            if (r10 != 0) goto L6;
            return;
        L6:
            this.animators.remove(r8, 0, r10);
        }
    }

    public static class BotDraftMessage {
        private boolean canStop;
        private boolean keepOnStop;
        public final int localMessageId;
        private MessageObject messageObject;
        public final long randomId;
        private boolean removed;
        private TL_iv.RichMessage richMessage;
        private Runnable selfDestruct;
        private TLRPC.TL_textWithEntities text;
        public final int topicId;
        public final long userId;

        /* renamed from: -$$Nest$fgetcanStop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m3900$$Nest$fgetcanStop(BotDraftMessage r0) {
            return r0.canStop;
        }

        /* renamed from: -$$Nest$fgetkeepOnStop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m3901$$Nest$fgetkeepOnStop(BotDraftMessage r0) {
            return r0.keepOnStop;
        }

        /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessageObject m3902$$Nest$fgetmessageObject(BotDraftMessage r0) {
            return r0.messageObject;
        }

        /* renamed from: -$$Nest$fgetremoved, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m3903$$Nest$fgetremoved(BotDraftMessage r0) {
            return r0.removed;
        }

        /* renamed from: -$$Nest$fgetselfDestruct, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m3904$$Nest$fgetselfDestruct(BotDraftMessage r0) {
            return r0.selfDestruct;
        }

        /* renamed from: -$$Nest$fgettext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_textWithEntities m3905$$Nest$fgettext(BotDraftMessage r0) {
            return r0.text;
        }

        /* renamed from: -$$Nest$fputcanStop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3906$$Nest$fputcanStop(BotDraftMessage r0, boolean r1) {
            r0.canStop = r1;
        }

        /* renamed from: -$$Nest$fputkeepOnStop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3907$$Nest$fputkeepOnStop(BotDraftMessage r0, boolean r1) {
            r0.keepOnStop = r1;
        }

        /* renamed from: -$$Nest$fputmessageObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3908$$Nest$fputmessageObject(BotDraftMessage r0, MessageObject r1) {
            r0.messageObject = r1;
        }

        /* renamed from: -$$Nest$fputremoved, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3909$$Nest$fputremoved(BotDraftMessage r0, boolean r1) {
            r0.removed = r1;
        }

        /* renamed from: -$$Nest$fputrichMessage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3910$$Nest$fputrichMessage(BotDraftMessage r0, TL_iv.RichMessage r1) {
            r0.richMessage = r1;
        }

        /* renamed from: -$$Nest$fputselfDestruct, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3911$$Nest$fputselfDestruct(BotDraftMessage r0, Runnable r1) {
            r0.selfDestruct = r1;
        }

        /* renamed from: -$$Nest$fputtext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3912$$Nest$fputtext(BotDraftMessage r0, TLRPC.TL_textWithEntities r1) {
            r0.text = r1;
        }

        private BotDraftMessage(long r1, int r3, long r4, int r6) {
            this.userId = r1;
            this.topicId = r3;
            this.randomId = r4;
            this.localMessageId = r6;
        }

        public /* synthetic */ BotDraftMessage(long r1, int r3, long r4, int r6, BotForumHelperIA r7) {
            this(r1, r3, r4, r6);
        }
    }

    public static class BotForumTextDraftDeleteNotification {
        public final long botTopicId;
        public final long botUserId;
        public final int messageId;

        public BotForumTextDraftDeleteNotification(long r1, long r3, int r5) {
            this.botUserId = r1;
            this.botTopicId = r3;
            this.messageId = r5;
        }
    }

    public static class BotForumTextDraftUpdateNotification {
        public final long botTopicId;
        public final long botUserId;
        public final boolean isNew;
        public final MessageObject messageObject;

        public BotForumTextDraftUpdateNotification(long r1, long r3, MessageObject r5, boolean r6) {
            this.botUserId = r1;
            this.botTopicId = r3;
            this.messageObject = r5;
            this.isNew = r6;
        }
    }

    public static class BotForumTopicCreateNotification {
        public final long dialogId;
        public final int topicId;

        public BotForumTopicCreateNotification(long r1, int r3) {
            this.dialogId = r1;
            this.topicId = r3;
        }
    }

    public static class DialogTopicIdKeyMap<T> {
        private final LongSparseArray<LongSparseArray<LongSparseArray<T>>> map;

        public DialogTopicIdKeyMap() {
            this.map = new LongSparseArray();
        }

        public LongSparseArray<T> get(long r1, long r3) {
            LongSparseArray<LongSparseArray<T>> r0 = this.map.get(r1);
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.get(r3);
        }

        public T get(long r1, long r3, long r5) {
            LongSparseArray<T> r0 = get(r1, r3);
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.get(r5);
        }

        public T put(long r2, long r4, long r6, T r8) {
            LongSparseArray<LongSparseArray<T>> r0 = this.map.get(r2);
            if (r0 != null) goto L5;
            r0 = new LongSparseArray();
            this.map.put(r2, r0);
        L5:
            LongSparseArray<T> r1 = r0.get(r4);
            if (r1 != null) goto L8;
            r1 = new LongSparseArray();
            r0.put(r4, r1);
        L8:
            T r22 = r1.get(r6);
            r1.put(r6, r8);
            return r22;
        }

        public T remove(long r1, long r3, long r5) {
            LongSparseArray<LongSparseArray<T>> r0 = this.map.get(r1);
            if (r0 != null) goto L5;
            return null;
        L5:
            LongSparseArray<T> r02 = r0.get(r3);
            if (r02 != null) goto L8;
            return null;
        L8:
            T r12 = r02.get(r5);
            r02.remove(r5);
            return r12;
        }

        public LongSparseArray<T> removeAll(long r1, long r3) {
            LongSparseArray<LongSparseArray<T>> r0 = this.map.get(r1);
            if (r0 != null) goto L6;
            return null;
        L6:
            LongSparseArray<T> r12 = r0.get(r3);
            r0.remove(r3);
            return r12;
        }
    }

    public enum SteamingSendButtonState extends Enum<SteamingSendButtonState> {
        private static final /* synthetic */ SteamingSendButtonState[] $VALUES = null;
        public static final SteamingSendButtonState BLOCKING = null;
        public static final SteamingSendButtonState NO_STREAMING = null;
        public static final SteamingSendButtonState STOP = null;

        private static /* synthetic */ SteamingSendButtonState[] $values() {
            return new SteamingSendButtonState[]{NO_STREAMING, BLOCKING, STOP};
        }

        static {
            NO_STREAMING = new SteamingSendButtonState("NO_STREAMING", 0);
            BLOCKING = new SteamingSendButtonState("BLOCKING", 1);
            STOP = new SteamingSendButtonState("STOP", 2);
            $VALUES = $values();
        }

        SteamingSendButtonState(String r1, int r2) {
        }

        public static SteamingSendButtonState valueOf(String r1) {
            return (SteamingSendButtonState) Enum.valueOf(SteamingSendButtonState.class, r1);
        }

        public static SteamingSendButtonState[] values() {
            return (SteamingSendButtonState[]) $VALUES.clone();
        }
    }

    public static class TypingBotSpan extends ColoredImageSpan {
        public TypingBotSpan(TypingDotsDrawable r1, int r2) {
            super(r1, r2);
        }
    }

    /* renamed from: $r8$lambda$2DtSuXBVrDlclz2omVJ1u-392_4, reason: not valid java name */
    public static /* synthetic */ void m3897$r8$lambda$2DtSuXBVrDlclz2omVJ1u392_4(BotForumHelper r0, TLObject r1, long[] r2, long r3, Runnable r5, int r6) {
        r0.lambda$beforeSendingFinalRequest$4(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$CUr1I72kng9ZLBLRHOP8WOL6_aM(TLRPC.Bool r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$dDYpsdbSCGNgX5PY-oeLWu5vfBg, reason: not valid java name */
    public static /* synthetic */ void m3898$r8$lambda$dDYpsdbSCGNgX5PYoeLWu5vfBg(BotForumHelper r0, long r1, String r3, TLRPC.Updates r4, TLRPC.TL_error r5) {
        r0.lambda$performSendBotTopicCreate$5(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$pvUjcLA2sjU6VNljJYUj2nOehgA(BotForumHelper r0, long r1, int r3, long r4) {
        r0.lambda$onBotForumDraftUpdate$0(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$rn08tZHToD9-vn-5TL7CPjQqHyw, reason: not valid java name */
    public static /* synthetic */ void m3899$r8$lambda$rn08tZHToD9vn5TL7CPjQqHyw(BotForumHelper r0, long[] r1, long r2, int r4, Runnable r5) {
        r0.lambda$beforeSendingFinalRequest$3(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$sMcfFK8JaVVS2McpO9ymKVPEwoU(BotForumHelper r0, long r1, int r3, long r4) {
        r0.lambda$onBotForumDraftUpdate$1(r1, r3, r4);
    }

    static {
        Instance = new BotForumHelper[16];
    }

    private BotForumHelper(int r4) {
        super(r4);
        this.botTextDraftsByRandomIds = new DialogTopicIdKeyMap();
        this.botTextDraftsByRandomIdsBlocklist = new DialogTopicIdKeyMap();
        this.pendingBotTopics = new LongSparseArray();
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("bot_drafts" + r4, 0);
    }

    public static CharSequence applyTypingAnimationSpan(CharSequence r4) {
        if ((r4 instanceof Spannable) == false) goto L10;
        TypingBotSpan[] r0 = (TypingBotSpan[]) ((Spannable) r4).getSpans(0, r4.length(), TypingBotSpan.class);
        if (r0 == null) goto L10;
        if (r0.length <= 0) goto L10;
        return r4;
    L10:
        if ((r4 instanceof SpannableStringBuilder) == false) goto L12;
        SpannableStringBuilder r42 = (SpannableStringBuilder) r4;
    L13:
        TypingDotsDrawable r02 = new TypingDotsDrawable(true);
        r02.setColor(-1);
        r02.start();
        TypingBotSpan r2 = new TypingBotSpan(r02, 1);
        r2.setColorKey(Theme.key_chat_messageTextIn);
        r2.setTopOffset(-AndroidUtilities.dp(10.0f));
        r42.append(" _");
        r42.setSpan(r2, r42.length() - 1, r42.length(), 33);
        return r42;
    L12:
        r42 = new SpannableStringBuilder(r4);
        goto L13
    }

    private MessageObject createDraftMessage(long r3, int r5, long r6, int r8, TLRPC.TL_textWithEntities r9) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.dialog_id = r3;
        r0.peer_id = getMessagesController().getPeer(r3);
        r0.from_id = getMessagesController().getPeer(r3);
        r0.local_id = r8;
        r0.id = r8;
        r0.random_id = r6;
        r0.message = r9.text;
        r0.entities = r9.entities;
        r0.flags |= 128;
        r0.date = getConnectionsManager().getCurrentTime();
        TLRPC.TL_messageReplyHeader r32 = new TLRPC.TL_messageReplyHeader();
        r0.reply_to = r32;
        r0.flags |= 16;
        r32.forum_topic = true;
        r32.reply_to_top_id = r5;
        r32.flags |= 2;
        r0.media = new TLRPC.TL_messageMediaEmpty();
        r0.flags |= 512;
        MessageObject r33 = new MessageObject(this.currentAccount, r0, false, true);
        r33.isBotPendingDraft = true;
        r33.resetLayout();
        return r33;
    }

    private MessageObject createDraftMessage(long r3, int r5, long r6, int r8, TL_iv.RichMessage r9) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.dialog_id = r3;
        r0.peer_id = getMessagesController().getPeer(r3);
        r0.from_id = getMessagesController().getPeer(r3);
        r0.local_id = r8;
        r0.id = r8;
        r0.random_id = r6;
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.flags |= 8192;
        r0.rich_message = r9;
        r0.date = getConnectionsManager().getCurrentTime();
        TLRPC.TL_messageReplyHeader r32 = new TLRPC.TL_messageReplyHeader();
        r0.reply_to = r32;
        r0.flags |= 16;
        r32.forum_topic = true;
        r32.reply_to_top_id = r5;
        r32.flags |= 2;
        r0.media = new TLRPC.TL_messageMediaEmpty();
        r0.flags |= 512;
        MessageObject r33 = new MessageObject(this.currentAccount, r0, false, true);
        r33.isBotPendingDraft = true;
        r33.resetLayout();
        return r33;
    }

    public static BotForumHelper getInstance(int r3) {
        BotForumHelper r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(BotForumHelper.class);
        BotForumHelper r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BotForumHelper[] r03 = Instance;     // Catch: Throwable -> L9
        BotForumHelper r2 = new BotForumHelper(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(BotForumHelper.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static boolean isBotForum(int r2, long r3) {
        if (r3 > 0) goto L5;
        MessagesController.getInstance(r2).getChat(Long.valueOf(-r3));
        return false;
    L5:
        return UserObject.isBotForum(MessagesController.getInstance(r2).getUser(Long.valueOf(r3)));
    }

    private /* synthetic */ void lambda$beforeSendingFinalRequest$3(long[] r9, long r10, int r12, Runnable r13) {
        int r0 = r9.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        long r5 = r9[r1];
        getMessagesStorage().updateMessageTopicId(r10, r5, r12);
        r1 = r1 + 1;
        goto L3
    L5:
        AndroidUtilities.runOnUIThread(r13);
    }

    private /* synthetic */ void lambda$beforeSendingFinalRequest$4(TLObject r8, long[] r9, long r10, Runnable r12, int r13) {
        if ((r8 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L5;
        TLRPC.TL_messages_forwardMessages r82 = (TLRPC.TL_messages_forwardMessages) r8;
        r82.top_msg_id = r13;
        r82.flags |= 512;
    L6:
        getMessagesStorage().getStorageQueue().postRunnable(new BotForumHelper$$ExternalSyntheticLambda2(this, r9, r10, r13, r12));
        return;
    L5:
        TLRPC.TL_inputReplyToMessage r0 = new TLRPC.TL_inputReplyToMessage();
        r0.reply_to_msg_id = r13;
        TlUtils.setInputReplyToFromSendMessageRequest(r8, r0);
        goto L6
    }

    private /* synthetic */ void lambda$onBotForumDraftUpdate$0(long r1, int r3, long r4) {
        onBotForumDraftTimeout(r1, r3, r4);
    }

    private /* synthetic */ void lambda$onBotForumDraftUpdate$1(long r1, int r3, long r4) {
        onBotForumDraftTimeout(r1, r3, r4);
    }

    private /* synthetic */ void lambda$performSendBotTopicCreate$5(long r9, String r11, TLRPC.Updates r12, TLRPC.TL_error r13) {
        if (r12 != null) goto L6;
        performSendBotTopicCreateComplete(r9, -1);
        return;
    L6:
        getMessagesController().processUpdates(r12, false);
        ArrayList<TLRPC.Update> r122 = r12.updates;
        int r0 = r122.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L11;
        TLRPC.Update r3 = r122.get(r2);
        r2 = r2 + 1;
        TLRPC.Update r32 = r3;
        if ((r32 instanceof TL_update.TL_updateMessageID) == false) goto L7;
        TL_update.TL_updateMessageID r33 = (TL_update.TL_updateMessageID) r32;
    L12:
        if (r33 != null) goto L15;
        performSendBotTopicCreateComplete(r9, -1);
        return;
    L15:
        TLRPC.TL_forumTopic r123 = new TLRPC.TL_forumTopic();
        TLRPC.TL_messageService r132 = new TLRPC.TL_messageService();
        TLRPC.TL_messageActionTopicCreate r02 = new TLRPC.TL_messageActionTopicCreate();
        r02.title = r11;
        r132.action = r02;
        r132.peer_id = getMessagesController().getPeer(r9);
        r132.dialog_id = r9;
        r132.id = r33.id;
        r132.date = (int) (System.currentTimeMillis() / 1000);
        int r03 = r33.id;
        r123.id = r03;
        r123.my = true;
        r123.flags |= 2;
        r123.topicStartMessage = r132;
        r123.title = r11;
        r123.top_message = r03;
        r123.topMessage = r132;
        r123.from_id = getMessagesController().getPeer(getUserConfig().clientUserId);
        r123.notify_settings = new TLRPC.TL_peerNotifySettings();
        r123.icon_color = 0;
        r123.title_missing = true;
        getMessagesController().getTopicsController().onTopicCreated(r9, r123, true);
        performSendBotTopicCreateComplete(r9, r33.id);
        getNotificationCenter().postNotificationName(NotificationCenter.botForumTopicDidCreate, new Object[]{new BotForumTopicCreateNotification(r9, r33.id)});
        return;
    L11:
        r33 = null;
        goto L12
    }

    private void onBotForumDraftTimeout(long r8, int r10, long r11) {
        long r3 = r10;
        BotDraftMessage r82 = this.botTextDraftsByRandomIds.remove(r8, r3, r11);
        if (r82 != null) goto L5;
        return;
    L5:
        getNotificationCenter().postNotificationName(NotificationCenter.botForumDraftDelete, new Object[]{new BotForumTextDraftDeleteNotification(r8, r3, r82.localMessageId)});
    }

    private void onBotForumDraftUpdate(long r15, int r17, long r18, TLRPC.TL_textWithEntities r20, boolean r21, boolean r22) {
        FileLog.d("[BotForum] onDraftNewDraft " + r15 + " " + r17 + " " + r18);
        long r3 = (long) r17;
        if (this.botTextDraftsByRandomIdsBlocklist.get(r15, r3, r18) == null) goto L6;
        FileLog.d("[BotForum] onDraftNewDraft ignore " + r15 + " " + r17 + " " + r18);
        return;
    L6:
        LongSparseArray<BotDraftMessage> r8 = this.botTextDraftsByRandomIds.get(r15, r3);
        boolean r9 = false;
        if (r8 != null) goto L9;
    L14:
        long[] r0 = null;
    L13:
        long[] r10 = r0;
        BotDraftMessage r02 = this.botTextDraftsByRandomIds.get(r15, r3, r18);
        if (r02 != null) goto L19;
        BotDraftMessage r03 = new BotDraftMessage(r15, r17, r18, getUserConfig().getNewMessageId(), null);
        this.botTextDraftsByRandomIds.put(r15, r3, r18, r03);
        BotDraftMessage r13 = r03;
    L20:
        BotDraftMessage.m3907$$Nest$fputkeepOnStop(r13, r22);
        BotDraftMessage.m3906$$Nest$fputcanStop(r13, r21);
        if (r10 == null) goto L33;
        int r6 = r10.length;
        int r7 = 0;
    L23:
        if (r7 >= r6) goto L33;
        long r4 = r10[r7];
        if (r4 == r18) goto L31;
        BotDraftMessage r04 = r8.get(r4);
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r04) == null) goto L30;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r04));
    L30:
        onBotForumDraftTimeout(r15, r17, r4);
    L31:
        r7 = r7 + 1;
    L33:
        if (BotDraftMessage.m3902$$Nest$fgetmessageObject(r13) != null) goto L36;
        r9 = true;
    L36:
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13) == null) goto L38;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13));
    L38:
        BotDraftMessage.m3911$$Nest$fputselfDestruct(r13, new BotForumHelper$$ExternalSyntheticLambda4(this, r15, r17, r18));
        BotDraftMessage.m3912$$Nest$fputtext(r13, r20);
        BotDraftMessage.m3908$$Nest$fputmessageObject(r13, createDraftMessage(r15, r17, r18, r13.localMessageId, r20));
        AndroidUtilities.runOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13), getAppGlobalConfig().messageTypingDraftTtl.get(TimeUnit.MILLISECONDS));
        getNotificationCenter().postNotificationName(NotificationCenter.botForumDraftUpdate, new Object[]{new BotForumTextDraftUpdateNotification(r15, r3, BotDraftMessage.m3902$$Nest$fgetmessageObject(r13), r9)});
        return;
    L19:
        r13 = r02;
        goto L20
    L9:
        if (r8.size() <= 0) goto L14;
        r0 = new long[r8.size()];
        int r102 = r8.size();
        int r11 = 0;
    L11:
        if (r11 >= r102) goto L13;
        r0[r11] = r8.keyAt(r11);
        r11 = r11 + 1;
        goto L11
    }

    private void onBotForumDraftUpdate(long r15, int r17, long r18, TL_iv.RichMessage r20, boolean r21, boolean r22) {
        FileLog.d("[BotForum] onDraftNewDraft (rich_message) " + r15 + " " + r17 + " " + r18);
        long r3 = (long) r17;
        if (this.botTextDraftsByRandomIdsBlocklist.get(r15, r3, r18) == null) goto L6;
        FileLog.d("[BotForum] onDraftNewDraft (rich_message) ignore " + r15 + " " + r17 + " " + r18);
        return;
    L6:
        LongSparseArray<BotDraftMessage> r8 = this.botTextDraftsByRandomIds.get(r15, r3);
        boolean r9 = false;
        if (r8 != null) goto L9;
    L14:
        long[] r0 = null;
    L13:
        long[] r10 = r0;
        BotDraftMessage r02 = this.botTextDraftsByRandomIds.get(r15, r3, r18);
        if (r02 != null) goto L19;
        BotDraftMessage r03 = new BotDraftMessage(r15, r17, r18, getUserConfig().getNewMessageId(), null);
        this.botTextDraftsByRandomIds.put(r15, r3, r18, r03);
        BotDraftMessage r13 = r03;
    L20:
        BotDraftMessage.m3907$$Nest$fputkeepOnStop(r13, r22);
        BotDraftMessage.m3906$$Nest$fputcanStop(r13, r21);
        if (r10 == null) goto L33;
        int r6 = r10.length;
        int r7 = 0;
    L23:
        if (r7 >= r6) goto L33;
        long r4 = r10[r7];
        if (r4 == r18) goto L31;
        BotDraftMessage r04 = r8.get(r4);
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r04) == null) goto L30;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r04));
    L30:
        onBotForumDraftTimeout(r15, r17, r4);
    L31:
        r7 = r7 + 1;
    L33:
        if (BotDraftMessage.m3902$$Nest$fgetmessageObject(r13) != null) goto L36;
        r9 = true;
    L36:
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13) == null) goto L38;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13));
    L38:
        BotDraftMessage.m3911$$Nest$fputselfDestruct(r13, new BotForumHelper$$ExternalSyntheticLambda1(this, r15, r17, r18));
        BotDraftMessage.m3910$$Nest$fputrichMessage(r13, r20);
        BotDraftMessage.m3908$$Nest$fputmessageObject(r13, createDraftMessage(r15, r17, r18, r13.localMessageId, r20));
        AndroidUtilities.runOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r13), getAppGlobalConfig().messageTypingDraftTtl.get(TimeUnit.MILLISECONDS));
        getNotificationCenter().postNotificationName(NotificationCenter.botForumDraftUpdate, new Object[]{new BotForumTextDraftUpdateNotification(r15, r3, BotDraftMessage.m3902$$Nest$fgetmessageObject(r13), r9)});
        return;
    L19:
        r13 = r02;
        goto L20
    L9:
        if (r8.size() <= 0) goto L14;
        r0 = new long[r8.size()];
        int r102 = r8.size();
        int r11 = 0;
    L11:
        if (r11 >= r102) goto L13;
        r0[r11] = r8.keyAt(r11);
        r11 = r11 + 1;
        goto L11
    }

    private void performSendBotTopicCreate(TLRPC.InputPeer r5, String r6, long r7, MessagesStorage.IntCallback r9) {
        long r0 = DialogObject.getPeerDialogId(r5);
        List<MessagesStorage.IntCallback> r2 = this.pendingBotTopics.get(r0);
        if (r2 == null) goto L6;
        r2.add(r9);
        return;
    L6:
        ArrayList r22 = new ArrayList(1);
        r22.add(r9);
        this.pendingBotTopics.put(r0, r22);
        TL_forum.TL_messages_createForumTopic r92 = new TL_forum.TL_messages_createForumTopic();
        if (TextUtils.isEmpty(r6) == false) goto L9;
        String r23 = "#New Chat";
    L10:
        r92.title = r23;
        r92.title_missing = true;
        r92.peer = r5;
        r92.random_id = r7;
        getConnectionsManager().sendRequestTyped(r92, new AiTonesController$$ExternalSyntheticLambda0(), new BotForumHelper$$ExternalSyntheticLambda3(this, r0, r6));
        return;
    L9:
        r23 = r6;
        goto L10
    }

    private void performSendBotTopicCreateComplete(long r2, int r4) {
        List<MessagesStorage.IntCallback> r0 = this.pendingBotTopics.get(r2);
        if (r0 == null) goto L8;
        this.pendingBotTopics.remove(r2);
        Iterator<MessagesStorage.IntCallback> r1 = r0.iterator();
    L6:
        if (r1.hasNext() == false) goto L10;
        r1.next().run(r4);
        goto L6
    L10:
        return;
    }

    public boolean beforeSendingFinalRequest(TLObject r13, List<MessageObject> r14, Runnable r15) {
        if (r14 != null) goto L5;
    L39:
        return true;
    L5:
        if (r14.isEmpty() == true) goto L39;
        TLRPC.InputPeer r3 = TlUtils.getInputPeerFromSendMessageRequest(r13);
        long r8 = DialogObject.getPeerDialogId(r3);
        if (r3 == null) goto L39;
        if (r8 <= 0) goto L39;
        if (UserObject.isBotForumWithEditableTopics(getMessagesController().getUser(Long.valueOf(r8))) == true) goto L15;
        return true;
    L15:
        long[] r7 = new long[r14.size()];
        int r4 = 0;
    L17:
        if (r4 >= r14.size()) goto L19;
        r7[r4] = r14.get(r4).getId();
        r4 = r4 + 1;
        goto L17
    L19:
        long r42 = TlUtils.getOrCalculateRandomIdFromSendMessageRequest(r13);
        if ((TlUtils.getInputReplyToFromSendMessageRequest(r13) instanceof TLRPC.TL_inputReplyToMessage) == false) goto L23;
        return true;
    L23:
        if ((r13 instanceof TLRPC.TL_messages_forwardMessages) == true) goto L25;
    L27:
        String r142 = TlUtils.getMessageFromSendMessageRequest(r13);
        if (r42 == 0) goto L30;
        long r0 = ~r42;
    L32:
        if (TextUtils.isEmpty(r142) == false) goto L34;
        r142 = LocaleController.getString(R.string.TopicsTitleMedia);
    L37:
        performSendBotTopicCreate(r3, r142, r0, new BotForumHelper$$ExternalSyntheticLambda0(this, r13, r7, r8, r15));
        return false;
    L34:
        if (r142.length() <= 16) goto L37;
        r142 = r142.substring(0, 16).concat("...");
        goto L37
    L30:
        r0 = getSendMessagesHelper().getNextRandomId();
        goto L32
    L25:
        if (((TLRPC.TL_messages_forwardMessages) r13).top_msg_id == 0) goto L27;
        return true;
    }

    public boolean beforeSendingFinalRequest(TLObject r1, MessageObject r2, Runnable r3) {
        return beforeSendingFinalRequest(r1, Collections.singletonList(r2), r3);
    }

    public SteamingSendButtonState getStreamingSendButtonState(long r3, int r5) {
        LongSparseArray<BotDraftMessage> r2 = this.botTextDraftsByRandomIds.get(r3, r5);
        if (r2 == null) goto L25;
        if (r2.size() <= 0) goto L25;
        int r32 = r2.size();
        BotDraftMessage r4 = null;
        int r52 = 0;
    L7:
        if (r52 >= r32) goto L12;
        r4 = r2.valueAt(r52);
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r4) == false) goto L12;
        r52 = r52 + 1;
    L12:
        if (r4 == null) goto L23;
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r4) == true) goto L23;
        if (BotDraftMessage.m3900$$Nest$fgetcanStop(r4) == false) goto L21;
        return SteamingSendButtonState.STOP;
    L21:
        return SteamingSendButtonState.BLOCKING;
    L23:
        return SteamingSendButtonState.NO_STREAMING;
    L25:
        return SteamingSendButtonState.NO_STREAMING;
    }

    public boolean hasBotForumDrafts(long r3, int r5) {
        LongSparseArray<BotDraftMessage> r2 = this.botTextDraftsByRandomIds.get(r3, r5);
        if (r2 != null) goto L5;
    L13:
        return false;
    L5:
        if (r2.size() <= 0) goto L13;
        int r4 = r2.size();
        int r52 = 0;
    L7:
        if (r52 >= r4) goto L13;
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r2.valueAt(r52)) == false) goto L10;
        r52 = r52 + 1;
        goto L7
    L10:
        return true;
    }

    public boolean isStreamingTopic(long r2, long r4) {
        return this.preferences.getBoolean(r2 + "_" + r4, false);
    }

    public MessageObject onBotForumDraftCheckNewMessages(long r8, int r10, int r11, String r12) {
        removeAllMarkedAsRemovedMessages(r8, r10);
        long r3 = r10;
        LongSparseArray<BotDraftMessage> r112 = this.botTextDraftsByRandomIds.get(r8, r3);
        if (r112 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
        BotDraftMessage r2 = null;
    L7:
        if (r1 >= r112.size()) goto L18;
        BotDraftMessage r5 = r112.valueAt(r1);
        if (r2 != null) goto L11;
        r2 = r5;
    L11:
        if (r12 == null) goto L17;
        if (BotDraftMessage.m3905$$Nest$fgettext(r5) == null) goto L17;
        if (r12.startsWith(BotDraftMessage.m3905$$Nest$fgettext(r5).text) == false) goto L17;
        BotDraftMessage r113 = r5;
    L19:
        if (r113 != null) goto L21;
        return null;
    L21:
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r113) == null) goto L23;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r113));
    L23:
        this.botTextDraftsByRandomIds.remove(r8, r3, r113.randomId);
        FileLog.d("[BotForum] onDraftNewMessage " + r8 + " " + r10);
        return BotDraftMessage.m3902$$Nest$fgetmessageObject(r113);
    L17:
        r1 = r1 + 1;
        goto L7
    L18:
        r113 = r2;
        goto L19
    }

    public void onBotForumDraftUpdate(long r10, int r12, TLRPC.TL_sendMessageRichMessageDraftAction r13) {
        onBotForumDraftUpdate(r10, r12, r13.random_id, r13.rich_message, r13.can_stop, r13.keep_on_stop);
    }

    public void onBotForumDraftUpdate(long r10, int r12, TLRPC.TL_sendMessageTextDraftAction r13) {
        onBotForumDraftUpdate(r10, r12, r13.random_id, r13.text, r13.can_stop, r13.keep_on_stop);
    }

    public void removeAllMarkedAsRemovedMessages(long r12, int r14) {
        long r4 = r14;
        LongSparseArray<BotDraftMessage> r142 = this.botTextDraftsByRandomIds.get(r12, r4);
        if (r142 == null) goto L12;
        int r0 = r142.size();
        int r8 = 0;
    L6:
        if (r8 >= r0) goto L16;
        BotDraftMessage r7 = r142.valueAt(r8);
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r7) == false) goto L10;
        long r2 = r12;
        getNotificationCenter().postNotificationName(NotificationCenter.botForumDraftDelete, new Object[]{new BotForumTextDraftDeleteNotification(r2, r4, r7.localMessageId)});
        this.botTextDraftsByRandomIds.remove(r2, r4, r7.randomId);
        r8 = r8 - 1;
        r0 = r0 - 1;
    L11:
        r8 = r8 + 1;
        r12 = r2;
        goto L6
    L10:
        r2 = r12;
        goto L11
    L16:
        return;
    }

    public void saveIsStreamingTopic(long r2, long r4, boolean r6) {
        this.preferences.edit().putBoolean(r2 + "_" + r4, r6).apply();
    }

    public void stopStreaming(long r18, long r20) {
        LongSparseArray<BotDraftMessage> r1 = this.botTextDraftsByRandomIds.get(r18, r20);
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.size() <= 0) goto L31;
        int r6 = r1.size();
        BotDraftMessage r7 = null;
        int r8 = 0;
        long r11 = 0;
    L7:
        if (r8 >= r6) goto L10;
        r11 = r1.keyAt(r8);
        r7 = r1.valueAt(r8);
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r7) == false) goto L10;
        r8 = r8 + 1;
    L10:
        long r15 = r11;
        BotDraftMessage r112 = r7;
        if (r112 != null) goto L14;
        return;
    L14:
        if (BotDraftMessage.m3903$$Nest$fgetremoved(r112) == false) goto L17;
        return;
    L17:
        if (BotDraftMessage.m3904$$Nest$fgetselfDestruct(r112) == null) goto L19;
        AndroidUtilities.cancelRunOnUIThread(BotDraftMessage.m3904$$Nest$fgetselfDestruct(r112));
    L19:
        this.botTextDraftsByRandomIdsBlocklist.put(r18, r20, r15, new Object());
        if (BotDraftMessage.m3901$$Nest$fgetkeepOnStop(r112) == false) goto L22;
        BotDraftMessage.m3909$$Nest$fputremoved(r112, true);
        long r2 = r18;
        long r4 = r20;
        long r12 = r15;
    L23:
        TLRPC.TL_sendMessageStopDraftAction r13 = new TLRPC.TL_sendMessageStopDraftAction();
        r13.random_id = r12;
        TLRPC.TL_messages_setTyping r62 = new TLRPC.TL_messages_setTyping();
        r62.peer = getMessagesController().getInputPeer(r2);
        r62.action = r13;
        if (r4 == 0) goto L26;
        r62.flags |= 1;
        r62.top_msg_id = (int) r4;
    L26:
        getConnectionsManager().sendRequestTyped(r62, new BotForumHelper$$ExternalSyntheticLambda5());
        return;
    L22:
        r2 = r18;
        r4 = r20;
        this.botTextDraftsByRandomIds.remove(r2, r4, r15);
        r12 = r15;
        getNotificationCenter().postNotificationName(NotificationCenter.botForumDraftDelete, new Object[]{new BotForumTextDraftDeleteNotification(r2, r4, r112.localMessageId)});
        goto L23
    }
}
