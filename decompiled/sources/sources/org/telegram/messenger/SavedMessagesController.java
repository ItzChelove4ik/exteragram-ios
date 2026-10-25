package org.telegram.messenger;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class SavedMessagesController {
    public ArrayList<SavedDialog> allDialogs;
    private ArrayList<SavedDialog> cachedDialogs;
    private final LongSparseArray<ArrayList<Utilities.Callback<Boolean>>> checkMessagesCallbacks;
    private final int currentAccount;
    private int dialogsCount;
    private int dialogsCountHidden;
    public boolean dialogsEndReached;
    private boolean dialogsLoaded;
    private boolean dialogsLoading;
    private boolean loadedCache;
    private ArrayList<SavedDialog> loadedDialogs;
    private boolean loadingCache;
    private boolean loadingCacheOnly;
    private final Runnable saveCacheRunnable;
    private boolean saving;
    public boolean unsupported;

    /* loaded from: classes5.dex */
    public static class SavedDialog {
        public long dialogId;
        private int lastDate;
        private int localDate;
        public MessageObject message;
        public int messagesCount;
        public boolean messagesCountLoaded;
        public boolean pinned;
        private int pinnedOrder;
        public long readInboxMaxId;
        public long readOutboxMaxId;
        public int top_message_id;
        public long unreadCount;

        /* renamed from: -$$Nest$fgetpinnedOrder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4886$$Nest$fgetpinnedOrder(SavedDialog r0) {
            return r0.pinnedOrder;
        }

        /* renamed from: -$$Nest$fputlocalDate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4887$$Nest$fputlocalDate(SavedDialog r0, int r1) {
            r0.localDate = r1;
        }

        /* renamed from: -$$Nest$fputpinnedOrder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4888$$Nest$fputpinnedOrder(SavedDialog r0, int r1) {
            r0.pinnedOrder = r1;
        }

        public SavedDialog() {
        }

        public static SavedDialog fromMessage(int r16, TLRPC.Message r17, boolean r18) {
            SavedDialog r15 = new SavedDialog();
            r15.dialogId = MessageObject.getSavedDialogId(UserConfig.getInstance(r16).getClientUserId(), r17);
            r15.pinned = false;
            r15.top_message_id = r17.id;
            r15.message = new MessageObject(r16, r17, null, null, null, null, null, false, false, 0, false, false, r18);
            return r15;
        }

        public static SavedDialog fromTL(int r19, TLRPC.savedDialog r20, ArrayList<TLRPC.Message> r21, boolean r22) {
            SavedDialog r1 = new SavedDialog();
            r1.dialogId = DialogObject.getPeerDialogId(r20.peer);
            r1.pinned = r20.pinned;
            r1.top_message_id = r20.top_message;
            r1.unreadCount = r20.unread_count;
            r1.readInboxMaxId = r20.read_inbox_max_id;
            r1.readOutboxMaxId = r20.read_outbox_max_id;
            int r0 = 0;
        L4:
            if (r0 >= r21.size()) goto L9;
            TLRPC.Message r3 = r21.get(r0);
            if (r1.top_message_id == r3.id) goto L7;
            r0 = r0 + 1;
        L7:
            TLRPC.Message r6 = r3;
            if (r6 == null) goto L12;
            r1.message = new MessageObject(r19, r6, null, null, null, null, null, false, false, 0, false, false, r22);
        L12:
            return r1;
        L9:
            r3 = null;
            goto L7
        }

        private int getDateInternal() {
            MessageObject r0 = this.message;
            if (r0 == null) goto L14;
            TLRPC.Message r02 = r0.messageOwner;
            if (r02 == null) goto L14;
            if ((r02.flags & 32768) == 0) goto L12;
            return r02.edit_date;
        L12:
            return r02.date;
        L14:
            return this.localDate;
        }

        public int getDate() {
            int r0 = getDateInternal();
            this.lastDate = r0;
            return r0;
        }

        public boolean isHidden() {
            MessageObject r0 = this.message;
            if (r0 == null) goto L10;
            TLRPC.Message r02 = r0.messageOwner;
            if (r02 != null) goto L7;
            return false;
        L7:
            if ((r02.action instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }
    }

    /* renamed from: $r8$lambda$-sSuV4zjr2r8i-5DLaP9LiuvV1o, reason: not valid java name */
    public static /* synthetic */ void m4880$r8$lambda$sSuV4zjr2r8i5DLaP9LiuvV1o(SavedMessagesController r0) {
        r0.lambda$deleteCache$12();
    }

    public static /* synthetic */ void $r8$lambda$6JDMTifkCOOLlYO0muT2L2GW2ms(SavedMessagesController r0, TLObject r1, long r2) {
        r0.lambda$hasSavedMessages$14(r1, r2);
    }

    /* renamed from: $r8$lambda$GSvuyxOt3q15EK34jiI6-LY10MA, reason: not valid java name */
    public static /* synthetic */ int m4881$r8$lambda$GSvuyxOt3q15EK34jiI6LY10MA(SavedDialog r0, SavedDialog r1) {
        return r1.getDate() - r0.getDate();
    }

    public static /* synthetic */ void $r8$lambda$HvH1nMEVduNBB_GkY2q6FxQQOoU(SavedMessagesController r0, MessagesStorage r1, ArrayList r2, long r3) {
        r0.lambda$updateDialogsLastMessage$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$J_GWXtq53kL_mEUWGGGkmPIpp0U(SavedMessagesController r0, MessagesStorage r1, long r2, Runnable r4) {
        r0.lambda$loadCache$7(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$LYJ8lqY6AZfcyITbkBYloznY7a4(SavedMessagesController r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, LongSparseArray r5) {
        r0.lambda$updateDialogsLastMessage$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QUUbn8Zz3sQYft_7bZAsAQB2jY4(SavedMessagesController r0, TLObject r1, ArrayList r2, TLRPC.TL_error r3) {
        r0.lambda$loadDialogs$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YZcCB3xWv6JZLY6ZzyGBQzPIyLs(SavedMessagesController r0, MessagesStorage r1) {
        r0.lambda$deleteCache$13(r1);
    }

    /* renamed from: $r8$lambda$Yk08U6mSr-IlU8ZSjyku4Mms9vg, reason: not valid java name */
    public static /* synthetic */ void m4882$r8$lambda$Yk08U6mSrIlU8ZSjyku4Mms9vg(SavedMessagesController r0) {
        r0.saveCache();
    }

    public static /* synthetic */ void $r8$lambda$Zsux8bVJ6l1SQFXhHMugb9Ij3lQ(SavedMessagesController r0) {
        r0.lambda$saveCache$10();
    }

    public static /* synthetic */ void $r8$lambda$gISHbPNOLgNeuruBMMX9zY6_3Hk(SavedMessagesController r0) {
        r0.lambda$loadDialogs$1();
    }

    public static /* synthetic */ int $r8$lambda$mlsKB21tK1WClx_bTOJ4ZDE4v2U(SavedDialog r0, SavedDialog r1) {
        return SavedDialog.m4886$$Nest$fgetpinnedOrder(r0) - SavedDialog.m4886$$Nest$fgetpinnedOrder(r1);
    }

    /* renamed from: $r8$lambda$o-P46C-p71OhNcRZ2S-yxMjc3dU, reason: not valid java name */
    public static /* synthetic */ void m4883$r8$lambda$oP46Cp71OhNcRZ2SyxMjc3dU(SavedMessagesController r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, Runnable r5) {
        r0.lambda$loadCache$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$qNTEx4E69IhKNyK5I_SErwc2WYk(SavedMessagesController r0, MessagesStorage r1, ArrayList r2) {
        r0.lambda$saveCache$11(r1, r2);
    }

    /* renamed from: $r8$lambda$rE5DX-YZOxMuZ-V-3xq_XrVKEK0, reason: not valid java name */
    public static /* synthetic */ void m4884$r8$lambda$rE5DXYZOxMuZV3xq_XrVKEK0(SavedMessagesController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$hasSavedMessages$15(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$xwd-CB6KxU3Pc46Gnb89goEExbg, reason: not valid java name */
    public static /* synthetic */ int m4885$r8$lambda$xwdCB6KxU3Pc46Gnb89goEExbg(SavedDialog r0, SavedDialog r1) {
        return r1.getDate() - r0.getDate();
    }

    public static /* synthetic */ void $r8$lambda$yPNwywrhLwkGpjrIQRksXKi6uso(SavedMessagesController r0, ArrayList r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadDialogs$3(r1, r2, r3);
    }

    public SavedMessagesController(int r3) {
        this.cachedDialogs = new ArrayList();
        this.loadedDialogs = new ArrayList();
        this.allDialogs = new ArrayList();
        this.saveCacheRunnable = new SavedMessagesController$$ExternalSyntheticLambda16(this);
        this.checkMessagesCallbacks = new LongSparseArray();
        this.currentAccount = r3;
        this.unsupported = MessagesController.getMainSettings(r3).getBoolean("savedMessagesUnsupported", true);
    }

    private void deleteCache() {
        if (this.saving == false) goto L5;
        return;
    L5:
        this.saving = true;
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        r0.getStorageQueue().postRunnable(new SavedMessagesController$$ExternalSyntheticLambda11(this, r0));
    }

    private ArrayList<Long> getCurrentPinnedOrder(ArrayList<SavedDialog> r4) {
        ArrayList<Long> r3 = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L9;
        SavedDialog r1 = r4.get(r0);
        if (r1.pinned == false) goto L8;
        r3.add(Long.valueOf(r1.dialogId));
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r3;
    }

    private void invalidate() {
        if (this.dialogsLoaded == true) goto L5;
    L7:
        int r1 = 0;
    L8:
        int r2 = this.loadedDialogs.size();
        ArrayList<SavedDialog> r3 = this.loadedDialogs;
        if (r1 >= r2) goto L23;
        SavedDialog r22 = r3.get(r1);
        int r32 = 0;
    L12:
        if (r32 >= this.cachedDialogs.size()) goto L17;
        SavedDialog r4 = this.cachedDialogs.get(r32);
        if (r4.dialogId == r22.dialogId) goto L18;
        r32 = r32 + 1;
    L18:
        if (r4 != null) goto L22;
        if (r22.pinned == true) goto L22;
        this.cachedDialogs.add(r22);
    L22:
        r1 = r1 + 1;
        goto L8
    L17:
        r4 = null;
        goto L18
    L23:
        r3.clear();
        this.dialogsLoaded = false;
        this.dialogsCount = 0;
        this.dialogsEndReached = false;
        update();
        loadDialogs(false);
        return;
    L5:
        if (this.loadedDialogs.isEmpty() == false) goto L7;
    }

    private /* synthetic */ void lambda$deleteCache$12() {
        this.saving = false;
        this.loadedCache = false;
    }

    private /* synthetic */ void lambda$deleteCache$13(MessagesStorage r4) {
        SQLitePreparedStatement r42 = r4.getDatabase().executeFast("DELETE FROM saved_dialogs WHERE forumChatId = ?");     // Catch: Exception -> L5
        r42.requery();     // Catch: Exception -> L5
        r42.bindLong(1, 0);     // Catch: Exception -> L5
        r42.step();     // Catch: Exception -> L5
        r42.dispose();     // Catch: Exception -> L5
    L7:
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda5(this));
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$hasSavedMessages$14(TLObject r7, long r8) {
        int r1 = 0;
        if ((r7 instanceof TLRPC.messages_Messages) == false) goto L18;
        TLRPC.messages_Messages r72 = (TLRPC.messages_Messages) r7;
        int r0 = r72.messages.size();
        if ((r72 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L7;
        r0 = ((TLRPC.TL_messages_messagesSlice) r72).count;
    L7:
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r72.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r72.users, r72.chats, true, true);
        if (r0 <= 0) goto L10;
        boolean r2 = true;
    L11:
        if (r0 > 0) goto L13;
    L19:
        ArrayList<Utilities.Callback<Boolean>> r73 = this.checkMessagesCallbacks.get(r8);
        this.checkMessagesCallbacks.remove(r8);
        if (r73 != null) goto L22;
        return;
    L22:
        if (r1 >= r73.size()) goto L26;
        r73.get(r1).run(Boolean.valueOf(r2));
        r1 = r1 + 1;
        goto L22
    L26:
        return;
    L13:
        if (updatedDialogCount(r8, r0, true) == false) goto L15;
        update();
        goto L19
    L15:
        if (r72.messages.isEmpty() == true) goto L19;
        SavedDialog r74 = SavedDialog.fromMessage(this.currentAccount, r72.messages.get(0), true);
        r74.messagesCount = r0;
        r74.messagesCountLoaded = true;
        this.cachedDialogs.add(r74);
        update();
        goto L19
    L10:
        r2 = false;
        goto L11
    L18:
        r2 = false;
        goto L19
    }

    private /* synthetic */ void lambda$hasSavedMessages$15(long r1, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda0(this, r3, r1));
    }

    private /* synthetic */ void lambda$loadCache$6(ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, Runnable r7) {
        this.loadingCache = false;
        this.loadedCache = true;
        MessagesController.getInstance(this.currentAccount).putUsers(r3, true);
        MessagesController.getInstance(this.currentAccount).putChats(r4, true);
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).processDocuments(r5);
        this.cachedDialogs.clear();
        this.cachedDialogs.addAll(r6);
        updateAllDialogs(true);
        if (r7 != null) goto L5;
        return;
    L5:
        if (this.loadingCacheOnly == true) goto L9;
        r7.run();
        return;
    }

    private /* synthetic */ void lambda$loadCache$7(MessagesStorage r34, long r35, Runnable r37) {
        SQLiteDatabase r1 = r34.getDatabase();
        ArrayList r7 = new ArrayList();
        ArrayList<Long> r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList<TLRPC.User> r5 = new ArrayList();
        ArrayList<TLRPC.Chat> r6 = new ArrayList();
        ArrayList<TLRPC.Document> r8 = new ArrayList();
        SQLiteCursor r9 = null;
        SQLiteCursor r10 = r1.queryFinalized("SELECT did, date, last_mid, pinned, flags, folder_id, last_mid_group, count, unread_count, max_read_id, read_outbox FROM saved_dialogs WHERE forumChatId = ? ORDER BY pinned ASC, date DESC", new Object[]{0});     // Catch: Throwable -> L68 Exception -> L70
    L95:
        if (r10.next() == false) goto L52;
        SavedDialog r12 = new SavedDialog();     // Catch: Throwable -> L48 Exception -> L50
        r12.dialogId = r10.longValue(0);     // Catch: Throwable -> L48 Exception -> L50
        SavedDialog.m4887$$Nest$fputlocalDate(r12, r10.intValue(1));     // Catch: Throwable -> L48 Exception -> L50
        r12.top_message_id = r10.intValue(2);     // Catch: Throwable -> L48 Exception -> L50
        SavedDialog.m4888$$Nest$fputpinnedOrder(r12, r10.intValue(3));     // Catch: Throwable -> L48 Exception -> L50
        if ((r10.intValue(4) & 1) == 0) goto L9;
        boolean r14 = true;
    L10:
        r12.messagesCountLoaded = r14;     // Catch: Throwable -> L48 Exception -> L50
        if (SavedDialog.m4886$$Nest$fgetpinnedOrder(r12) == 999) goto L13;
        boolean r142 = true;
    L14:
        r12.pinned = r142;     // Catch: Throwable -> L48 Exception -> L50
        r12.messagesCount = r10.intValue(7);     // Catch: Throwable -> L48 Exception -> L50
        r12.unreadCount = r10.longValue(8);     // Catch: Throwable -> L48 Exception -> L50
        r12.readInboxMaxId = r10.longValue(9);     // Catch: Throwable -> L48 Exception -> L50
        r12.readOutboxMaxId = r10.longValue(10);     // Catch: Throwable -> L48 Exception -> L50
        long r143 = r12.dialogId;     // Catch: Throwable -> L48 Exception -> L50
        if (r143 < 0) goto L99;
        r2.add(Long.valueOf(r143));     // Catch: Throwable -> L48 Exception -> L50
    L26:
        SQLiteCursor r18 = r9;
        SQLiteCursor r17 = r10;
        r9 = r1.queryFinalized("SELECT data FROM messages_topics WHERE uid = ? AND mid = ? AND topic_id = ?", new Object[]{Long.valueOf(r35), Integer.valueOf(r12.top_message_id), Long.valueOf(r12.dialogId)});     // Catch: Throwable -> L44 Exception -> L46
        if (r9.next() == false) goto L41;
        NativeByteBuffer r102 = r9.byteBufferValue(0);     // Catch: Throwable -> L33 Exception -> L39
        TLRPC.Message r13 = TLRPC.Message.TLdeserialize(r102, r102.readInt32(true), true);     // Catch: Throwable -> L33 Exception -> L39
        MessagesStorage.addUsersAndChatsFromMessage(r13, r2, r3, r4);     // Catch: Throwable -> L33 Exception -> L39
        SavedMessagesController r144 = this;
        r12.message = new MessageObject(r144.currentAccount, r13, null, null, null, null, null, false, false, 0, false, false, true);     // Catch: Throwable -> L33 Exception -> L36
        r102.reuse();     // Catch: Throwable -> L33 Exception -> L36
    L42:
        r9.dispose();     // Catch: Throwable -> L33 Exception -> L36
        r7.add(r12);     // Catch: Throwable -> L33 Exception -> L36
        r10 = r17;
    L36:
        e = e;
    L37:
        r18 = r9;
    L38:
        r9 = r17;
    L87:
        FileLog.e(e);     // Catch: Throwable -> L79
        if (r9 == null) goto L75;
        r9.dispose();
    L75:
        if (r18 != null) goto L67;
    L77:
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda2(r144, r5, r6, r8, r7, r37));
        return;
    L67:
        r18.dispose();
    L79:
        th = th;
    L80:
        if (r9 == null) goto L82;
        r9.dispose();
    L82:
        if (r18 == null) goto L84;
        r18.dispose();
    L84:
        throw th;
    L41:
        r144 = this;
        goto L42
    L39:
        e = e;
        r144 = this;
    L33:
        th = th;
        r18 = r9;
    L35:
        r9 = r17;
    L46:
        e = e;
        r144 = this;
    L44:
        th = th;
        goto L35
    L99:
        r3.add(Long.valueOf(-r143));     // Catch: Throwable -> L20 Exception -> L22
    L22:
        e = e;
        r144 = this;
        r18 = r9;
        r9 = r10;
    L20:
        th = th;
        r18 = r9;
        r9 = r10;
        goto L80
    L13:
        r142 = false;
        goto L14
    L9:
        r14 = false;
        goto L10
    L52:
        r144 = this;
        r18 = r9;
        r17 = r10;
    L56:
        e = e;
        goto L38
    L54:
        if (r2.isEmpty() == true) goto L60;
        r34.getUsersInternal(r2, r5);     // Catch: Throwable -> L44 Exception -> L56
    L60:
        if (r3.isEmpty() == true) goto L63;
        r34.getChatsInternal(TextUtils.join(",", r3), r6);     // Catch: Throwable -> L44 Exception -> L56
    L63:
        if (r4.isEmpty() == true) goto L65;
        r34.getAnimatedEmoji(TextUtils.join(",", r4), r8);     // Catch: Throwable -> L44 Exception -> L56
    L65:
        r17.dispose();
        if (r18 == null) goto L77;
    L50:
        e = e;
        r144 = this;
        r18 = r9;
        r17 = r10;
    L48:
        th = th;
        r18 = r9;
        r17 = r10;
        goto L35
    L70:
        e = e;
        r144 = this;
        r18 = null;
    L68:
        th = th;
        r18 = null;
        goto L80
    }

    private /* synthetic */ void lambda$loadDialogs$1() {
        loadDialogs(false);
    }

    private /* synthetic */ void lambda$loadDialogs$2(TLObject r19, ArrayList r20, TLRPC.TL_error r21) {
        boolean r4 = this.unsupported;
        if ((r19 instanceof TLRPC.TL_messages_savedDialogs) == false) goto L27;
        this.dialogsLoaded = true;
        TLRPC.TL_messages_savedDialogs r1 = (TLRPC.TL_messages_savedDialogs) r19;
        MessagesController.getInstance(this.currentAccount).putUsers(r1.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r1.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r1.users, r1.chats, true, true);
        MessagesStorage.getInstance(this.currentAccount).putMessages(r1.messages, false, true, false, 0, false, 3, 0);
        int r2 = 0;
    L6:
        if (r2 >= r1.dialogs.size()) goto L25;
        SavedDialog r3 = SavedDialog.fromTL(this.currentAccount, r1.dialogs.get(r2), r1.messages, true);
        int r5 = 0;
    L9:
        if (r5 >= this.cachedDialogs.size()) goto L14;
        if (this.cachedDialogs.get(r5).dialogId == r3.dialogId) goto L12;
        r5 = r5 + 1;
        goto L9
    L12:
        r3.messagesCount = this.cachedDialogs.get(r5).messagesCount;
        this.cachedDialogs.get(r5).pinned = r3.pinned;
    L14:
        int r52 = 0;
    L15:
        int r8 = this.loadedDialogs.size();
        ArrayList<SavedDialog> r9 = this.loadedDialogs;
        if (r52 >= r8) goto L21;
        if (r9.get(r52).dialogId == r3.dialogId) goto L24;
        r52 = r52 + 1;
    L24:
        r2 = r2 + 1;
        goto L6
    L21:
        r9.add(r3);
        if (r3.isHidden() == false) goto L24;
        this.dialogsCountHidden++;
        goto L24
    L25:
        this.dialogsEndReached = true;
        this.dialogsCount = r1.dialogs.size();
        updateAllDialogs(true);
        saveCacheSchedule();
        this.unsupported = false;
    L79:
        if (this.unsupported == r4) goto L81;
        MessagesController.getMainSettings(this.currentAccount).edit().putBoolean("savedMessagesUnsupported", this.unsupported).apply();
    L81:
        this.dialogsLoading = false;
        return;
    L27:
        if ((r19 instanceof TLRPC.TL_messages_savedDialogsSlice) == false) goto L58;
        this.dialogsLoaded = true;
        TLRPC.TL_messages_savedDialogsSlice r12 = (TLRPC.TL_messages_savedDialogsSlice) r19;
        MessagesController.getInstance(this.currentAccount).putUsers(r12.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r12.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r12.users, r12.chats, true, true);
        MessagesStorage.getInstance(this.currentAccount).putMessages(r12.messages, false, true, false, 0, false, 3, 0);
        int r22 = 0;
    L30:
        if (r22 >= r12.dialogs.size()) goto L49;
        SavedDialog r32 = SavedDialog.fromTL(this.currentAccount, r12.dialogs.get(r22), r12.messages, true);
        int r53 = 0;
    L33:
        if (r53 >= this.cachedDialogs.size()) goto L38;
        if (this.cachedDialogs.get(r53).dialogId == r32.dialogId) goto L36;
        r53 = r53 + 1;
        goto L33
    L36:
        r32.messagesCount = this.cachedDialogs.get(r53).messagesCount;
        this.cachedDialogs.get(r53).pinned = r32.pinned;
    L38:
        int r54 = 0;
    L39:
        int r82 = this.loadedDialogs.size();
        ArrayList<SavedDialog> r92 = this.loadedDialogs;
        if (r54 >= r82) goto L45;
        if (r92.get(r54).dialogId == r32.dialogId) goto L48;
        r54 = r54 + 1;
    L48:
        r22 = r22 + 1;
        goto L30
    L45:
        r92.add(r32);
        if (r32.isHidden() == false) goto L48;
        this.dialogsCountHidden++;
        goto L48
    L49:
        this.dialogsCount = r12.count;
        if ((getPinnedCount() + this.loadedDialogs.size()) < this.dialogsCount) goto L52;
    L55:
        boolean r13 = true;
    L56:
        this.dialogsEndReached = r13;
        updateAllDialogs(true);
        saveCacheSchedule();
        this.unsupported = false;
        goto L79
    L52:
        if (r12.dialogs.size() == 0) goto L55;
        r13 = false;
        goto L56
    L58:
        if ((r19 instanceof TLRPC.TL_messages_savedDialogsNotModified) == false) goto L74;
        this.dialogsLoaded = true;
        this.loadedDialogs.addAll(r20);
        this.dialogsCount = ((TLRPC.TL_messages_savedDialogsNotModified) r19).count;
        this.dialogsCountHidden = 0;
        int r14 = 0;
    L61:
        if (r14 >= r20.size()) goto L66;
        if (((SavedDialog) r20.get(r14)).isHidden() == false) goto L65;
        this.dialogsCountHidden++;
    L65:
        r14 = r14 + 1;
        goto L61
    L66:
        boolean r15 = this.dialogsEndReached;
        if (this.loadedDialogs.size() < this.dialogsCount) goto L69;
        boolean r23 = true;
    L70:
        this.dialogsEndReached = r23;
        this.unsupported = false;
        if (r23 == false) goto L79;
        if (r15 == true) goto L79;
        updateAllDialogs(true);
        goto L79
    L69:
        r23 = false;
        goto L70
    L74:
        if (r21 == null) goto L79;
        this.dialogsLoaded = true;
        if ("SAVED_DIALOGS_UNSUPPORTED".equals(r21.text) == false) goto L79;
        this.unsupported = true;
        goto L79
    }

    private /* synthetic */ void lambda$loadDialogs$3(ArrayList r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda12(this, r3, r2, r4));
    }

    private /* synthetic */ void lambda$saveCache$10() {
        this.saving = false;
    }

    private /* synthetic */ void lambda$saveCache$11(MessagesStorage r10, ArrayList r11) {
        SQLiteDatabase r102 = r10.getDatabase();
        SQLitePreparedStatement r0 = null;
        r0 = null;
        SQLitePreparedStatement r1 = r102.executeFast("DELETE FROM saved_dialogs WHERE forumChatId = ?");     // Catch: Throwable -> L11 Exception -> L13
        r1.requery();     // Catch: Throwable -> L11 Exception -> L13
        r1.bindLong(1, 0);     // Catch: Throwable -> L11 Exception -> L13
        r1.step();     // Catch: Throwable -> L11 Exception -> L13
        r1.dispose();     // Catch: Throwable -> L11 Exception -> L13
        r0 = r102.executeFast("REPLACE INTO saved_dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");     // Catch: Throwable -> L11 Exception -> L13
        int r12 = 0;
    L5:
        if (r12 >= r11.size()) goto L15;
        SavedDialog r5 = (SavedDialog) r11.get(r12);     // Catch: Throwable -> L11 Exception -> L13
        r0.requery();     // Catch: Throwable -> L11 Exception -> L13
        r0.bindLong(1, r5.dialogId);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(2, r5.getDate());     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(3, r5.top_message_id);     // Catch: Throwable -> L11 Exception -> L13
        if (r5.pinned == false) goto L9;
        int r6 = r12;
    L10:
        r0.bindInteger(4, r6);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(5, r5.messagesCountLoaded ? 1 : 0);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(6, 0);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(7, 0);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindInteger(8, r5.messagesCount);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindLong(9, 0);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindLong(10, r5.unreadCount);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindLong(11, r5.readInboxMaxId);     // Catch: Throwable -> L11 Exception -> L13
        r0.bindLong(12, r5.readOutboxMaxId);     // Catch: Throwable -> L11 Exception -> L13
        r0.step();     // Catch: Throwable -> L11 Exception -> L13
        r12 = r12 + 1;     // Catch: Throwable -> L11 Exception -> L13
        goto L5
    L9:
        r6 = 999;
        goto L10
    L15:
        r0.dispose();     // Catch: Throwable -> L11 Exception -> L13
    L16:
        r0.dispose();
    L20:
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda7(this));
        return;
    L11:
        th = move-exception;
        if (r0 == null) goto L24;
        r0.dispose();
    L24:
        throw th;
    L13:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L11
        if (r0 == null) goto L20;
        goto L20
    }

    private /* synthetic */ void lambda$updateDialogsLastMessage$8(ArrayList r23, ArrayList r24, ArrayList r25, ArrayList r26, LongSparseArray r27) {
        MessagesController.getInstance(this.currentAccount).putUsers(r23, true);
        MessagesController.getInstance(this.currentAccount).putChats(r24, true);
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).processDocuments(r25);
        int r3 = 0;
    L4:
        if (r3 >= r26.size()) goto L6;
        removeDialog(((Long) r26.get(r3)).longValue());
        r3 = r3 + 1;
        goto L4
    L6:
        int r32 = 0;
    L8:
        if (r32 >= r27.size()) goto L24;
        long r4 = r27.keyAt(r32);
        MessageObject r7 = new MessageObject(this.currentAccount, (TLRPC.Message) r27.valueAt(r32), null, null, null, null, null, false, false, 0, false, false, true);
        int r6 = 0;
    L11:
        if (r6 >= this.loadedDialogs.size()) goto L16;
        SavedDialog r8 = this.loadedDialogs.get(r6);
        if (r8.dialogId != r4) goto L15;
        r8.top_message_id = r7.getId();
        r8.message = r7;
    L15:
        r6 = r6 + 1;
        goto L11
    L16:
        int r62 = 0;
    L18:
        if (r62 >= this.cachedDialogs.size()) goto L23;
        SavedDialog r82 = this.cachedDialogs.get(r62);
        if (r82.dialogId != r4) goto L22;
        r82.top_message_id = r7.getId();
        r82.message = r7;
    L22:
        r62 = r62 + 1;
        goto L18
    L23:
        r32 = r32 + 1;
        goto L8
    L24:
        update();
    }

    private /* synthetic */ void lambda$updateDialogsLastMessage$9(MessagesStorage r18, ArrayList r19, long r20) {
        SQLiteDatabase r1 = r18.getDatabase();
        ArrayList r7 = new ArrayList();
        LongSparseArray r8 = new LongSparseArray();
        ArrayList<Long> r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList<TLRPC.User> r5 = new ArrayList();
        ArrayList<TLRPC.Chat> r6 = new ArrayList();
        ArrayList<TLRPC.Document> r9 = new ArrayList();
        SQLiteCursor r10 = null;
        int r12 = 0;
    L34:
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r10 != null) goto L25;
    L29:
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda3(this, r5, r6, r9, r7, r8));
        return;
    L25:
        r10.dispose();
    L8:
        th = move-exception;
        if (r10 == null) goto L33;
        r10.dispose();
    L33:
        throw th;
    L4:
        if (r12 >= r19.size()) goto L15;
        SavedDialog r14 = (SavedDialog) r19.get(r12);     // Catch: Throwable -> L8 Exception -> L10
        int r16 = r12;
        r10 = r1.queryFinalized("SELECT mid, data FROM messages_topics WHERE uid = ? AND topic_id = ? ORDER BY mid DESC LIMIT 1", new Object[]{Long.valueOf(r20), Long.valueOf(r14.dialogId)});     // Catch: Throwable -> L8 Exception -> L10
        if (r10.next() == false) goto L12;
        r10.intValue(0);     // Catch: Throwable -> L8 Exception -> L10
        NativeByteBuffer r13 = r10.byteBufferValue(1);     // Catch: Throwable -> L8 Exception -> L10
        TLRPC.Message r122 = TLRPC.Message.TLdeserialize(r13, r13.readInt32(true), true);     // Catch: Throwable -> L8 Exception -> L10
        MessagesStorage.addUsersAndChatsFromMessage(r122, r2, r3, r4);     // Catch: Throwable -> L8 Exception -> L10
        r8.put(r14.dialogId, r122);     // Catch: Throwable -> L8 Exception -> L10
        r13.reuse();     // Catch: Throwable -> L8 Exception -> L10
    L13:
        r10.dispose();     // Catch: Throwable -> L8 Exception -> L10
        r12 = r16 + 1;     // Catch: Throwable -> L8 Exception -> L10
        goto L34
    L12:
        r7.add(Long.valueOf(r14.dialogId));     // Catch: Throwable -> L8 Exception -> L10
        goto L13
    L15:
        if (r2.isEmpty() == true) goto L19;
        r18.getUsersInternal(r2, r5);     // Catch: Throwable -> L8 Exception -> L10
    L19:
        if (r3.isEmpty() == true) goto L22;
        r18.getChatsInternal(TextUtils.join(",", r3), r6);     // Catch: Throwable -> L8 Exception -> L10
    L22:
        if (r4.isEmpty() == true) goto L24;
        r18.getAnimatedEmoji(TextUtils.join(",", r4), r9);     // Catch: Throwable -> L8 Exception -> L10
    L24:
        if (r10 == null) goto L29;
        goto L25
    }

    private void loadCache(Runnable r8) {
        if (this.loadingCache == false) goto L5;
        return;
    L5:
        this.loadingCache = true;
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);
        r3.getStorageQueue().postRunnable(new SavedMessagesController$$ExternalSyntheticLambda1(this, r3, r4, r8));
    }

    public static void openSavedMessages() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        Bundle r1 = new Bundle();
        r1.putLong("user_id", UserConfig.getInstance(r0.getCurrentAccount()).getClientUserId());
        r0.presentFragment(new ChatActivity(r1));
    }

    public static void openSavedMessagesReminders() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        Bundle r1 = new Bundle();
        r1.putLong("user_id", UserConfig.getInstance(r0.getCurrentAccount()).getClientUserId());
        r1.putInt("chatMode", 1);
        r0.presentFragment(new ChatActivity(r1));
    }

    private boolean processUpdateInternal(TLRPC.Update r6) {
        if ((r6 instanceof TL_update.TL_updateSavedDialogPinned) == false) goto L10;
        TL_update.TL_updateSavedDialogPinned r62 = (TL_update.TL_updateSavedDialogPinned) r6;
        TLRPC.DialogPeer r0 = r62.peer;
        if ((r0 instanceof TLRPC.TL_dialogPeer) == true) goto L7;
        return false;
    L7:
        long r2 = DialogObject.getPeerDialogId(((TLRPC.TL_dialogPeer) r0).peer);
        ArrayList<Long> r02 = new ArrayList();
        r02.add(Long.valueOf(r2));
        return updatePinned(r02, r62.pinned, false);
    L10:
        if ((r6 instanceof TL_update.TL_updatePinnedSavedDialogs) == false) goto L26;
        TL_update.TL_updatePinnedSavedDialogs r63 = (TL_update.TL_updatePinnedSavedDialogs) r6;
        ArrayList<Long> r03 = new ArrayList(r63.order.size());
        int r22 = 0;
    L13:
        if (r22 >= r63.order.size()) goto L19;
        TLRPC.DialogPeer r3 = r63.order.get(r22);
        if ((r3 instanceof TLRPC.TL_dialogPeer) == false) goto L18;
        r03.add(Long.valueOf(DialogObject.getPeerDialogId(((TLRPC.TL_dialogPeer) r3).peer)));
    L18:
        r22 = r22 + 1;
        goto L13
    L19:
        boolean r64 = updatePinnedOrder(this.loadedDialogs, r03);
        boolean r5 = updatePinnedOrder(this.cachedDialogs, r03);
        if (r64 == true) goto L24;
        if (r5 == true) goto L30;
        return false;
    L30:
        return true;
    L24:
        return true;
    L26:
        return false;
    }

    private int removeDialog(long r7) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.allDialogs.size()) goto L9;
        if (this.allDialogs.get(r1).dialogId != r7) goto L8;
        this.allDialogs.remove(r1);
        r2 = r2 + 1;
        r1 = r1 - 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
        int r3 = 0;
    L11:
        if (r12 >= this.loadedDialogs.size()) goto L17;
        if (this.loadedDialogs.get(r12).dialogId != r7) goto L15;
        this.loadedDialogs.remove(r12);
        r3 = r3 + 1;
        r12 = r12 - 1;
    L15:
        r12 = r12 + 1;
    L17:
        if (r0 >= this.cachedDialogs.size()) goto L23;
        if (this.cachedDialogs.get(r0).dialogId != r7) goto L21;
        this.cachedDialogs.remove(r0);
        r0 = r0 - 1;
    L21:
        r0 = r0 + 1;
        goto L17
    L23:
        return Math.max(r2, r3);
    }

    private boolean sameOrder(ArrayList<Long> r4, ArrayList<Long> r5) {
        if (r4.size() == r5.size()) goto L5;
        return false;
    L5:
        int r3 = 0;
    L7:
        if (r3 >= r4.size()) goto L12;
        if (Objects.equals(r4.get(r3), r5.get(r3)) == false) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    private void saveCache() {
        if (this.saving == false) goto L5;
        return;
    L5:
        this.saving = true;
        ArrayList r0 = new ArrayList(this.allDialogs);
        MessagesStorage r1 = MessagesStorage.getInstance(this.currentAccount);
        r1.getStorageQueue().postRunnable(new SavedMessagesController$$ExternalSyntheticLambda15(this, r1, r0));
    }

    private void saveCacheSchedule() {
        AndroidUtilities.cancelRunOnUIThread(this.saveCacheRunnable);
        AndroidUtilities.runOnUIThread(this.saveCacheRunnable, 450);
    }

    private void updateAllDialogs(boolean r8) {
        this.allDialogs.clear();
        HashSet r0 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= this.cachedDialogs.size()) goto L13;
        SavedDialog r3 = this.cachedDialogs.get(r2);
        if (r3.pinned == false) goto L12;
        if (r0.contains(Long.valueOf(r3.dialogId)) == true) goto L12;
        if (r3.isHidden() == true) goto L12;
        this.allDialogs.add(r3);
        r0.add(Long.valueOf(r3.dialogId));
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        int r22 = 0;
    L15:
        if (r22 >= this.loadedDialogs.size()) goto L24;
        SavedDialog r32 = this.loadedDialogs.get(r22);
        if (r32.pinned == false) goto L23;
        if (r0.contains(Long.valueOf(r32.dialogId)) == true) goto L23;
        if (r32.isHidden() == true) goto L23;
        this.allDialogs.add(r32);
        r0.add(Long.valueOf(r32.dialogId));
    L23:
        r22 = r22 + 1;
        goto L15
    L24:
        ArrayList r23 = new ArrayList();
        int r33 = 0;
    L26:
        if (r33 >= this.loadedDialogs.size()) goto L34;
        SavedDialog r4 = this.loadedDialogs.get(r33);
        if (r0.contains(Long.valueOf(r4.dialogId)) == true) goto L32;
        if (r4.isHidden() == true) goto L32;
        r23.add(r4);
        r0.add(Long.valueOf(r4.dialogId));
    L32:
        r33 = r33 + 1;
        goto L26
    L34:
        if (this.dialogsEndReached == true) goto L44;
        int r34 = 0;
    L37:
        if (r34 >= this.cachedDialogs.size()) goto L44;
        SavedDialog r42 = this.cachedDialogs.get(r34);
        if (r0.contains(Long.valueOf(r42.dialogId)) == true) goto L43;
        if (r42.isHidden() == true) goto L43;
        r23.add(r42);
        r0.add(Long.valueOf(r42.dialogId));
    L43:
        r34 = r34 + 1;
    L44:
        Collections.sort(r23, new SavedMessagesController$$ExternalSyntheticLambda6());
        this.allDialogs.addAll(r23);
        if (r8 == false) goto L70;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.savedMessagesDialogsUpdate, new Object[0]);
        if (hasDialogs() == false) goto L49;
        return;
    L49:
        if (MessagesController.getInstance(this.currentAccount).savedViewAsChats == false) goto L72;
        MessagesController.getInstance(this.currentAccount).setSavedViewAs(false);
        return;
    L72:
        return;
    }

    private void updateDialogsLastMessage(ArrayList<SavedDialog> r8) {
        long r5 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);
        r3.getStorageQueue().postRunnable(new SavedMessagesController$$ExternalSyntheticLambda17(this, r3, r8, r5));
    }

    private boolean updatePinnedOrder(ArrayList<SavedDialog> r7, ArrayList<Long> r8) {
        if (sameOrder(r8, getCurrentPinnedOrder(r7)) == false) goto L5;
        return false;
    L5:
        ArrayList r6 = new ArrayList();
        int r1 = 0;
    L7:
        if (r1 >= r7.size()) goto L12;
        SavedDialog r2 = r7.get(r1);
        if (r2.pinned == false) goto L11;
        r2.pinned = false;
        r6.add(r2);
        r7.remove(r1);
        r1 = r1 - 1;
    L11:
        r1 = r1 + 1;
        goto L7
    L12:
        r7.addAll(r6);
        ArrayList r62 = new ArrayList();
        int r12 = 0;
    L14:
        if (r12 >= r7.size()) goto L19;
        SavedDialog r22 = r7.get(r12);
        int r4 = r8.indexOf(Long.valueOf(r22.dialogId));
        if (r4 < 0) goto L18;
        SavedDialog.m4888$$Nest$fputpinnedOrder(r22, r4);
        r22.pinned = true;
        r62.add(r22);
        r7.remove(r12);
        r12 = r12 - 1;
    L18:
        r12 = r12 + 1;
        goto L14
    L19:
        Collections.sort(r7, new SavedMessagesController$$ExternalSyntheticLambda13());
        Collections.sort(r62, new SavedMessagesController$$ExternalSyntheticLambda14());
        r7.addAll(0, r62);
        return true;
    }

    private void updatePinnedOrderToServer(ArrayList<Long> r7) {
        boolean r0 = updatePinnedOrder(this.loadedDialogs, r7);
        boolean r1 = updatePinnedOrder(this.cachedDialogs, r7);
        if (r0 == true) goto L7;
        if (r1 == true) goto L7;
        return;
    L7:
        TLRPC.TL_messages_reorderPinnedSavedDialogs r02 = new TLRPC.TL_messages_reorderPinnedSavedDialogs();
        r02.force = true;
        int r12 = 0;
    L9:
        if (r12 >= r7.size()) goto L14;
        long r2 = r7.get(r12).longValue();
        TLRPC.TL_inputDialogPeer r4 = new TLRPC.TL_inputDialogPeer();
        TLRPC.InputPeer r22 = MessagesController.getInstance(this.currentAccount).getInputPeer(r2);
        r4.peer = r22;
        if (r22 == null) goto L13;
        r02.order.add(r4);
    L13:
        r12 = r12 + 1;
        goto L9
    L14:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, null);
        update();
    }

    public void checkSavedDialogCount(long r2) {
        SavedDialog r0 = findSavedDialog(r2);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.messagesCountLoaded == true) goto L9;
        hasSavedMessages(r2, null);
        return;
    }

    public void cleanup() {
        this.cachedDialogs.clear();
        this.loadedDialogs.clear();
        this.dialogsLoaded = false;
        this.dialogsCount = 0;
        this.dialogsCountHidden = 0;
        this.dialogsEndReached = false;
        this.loadedCache = true;
        deleteCache();
        this.unsupported = true;
        MessagesController.getMainSettings(this.currentAccount).edit().remove("savedMessagesUnsupported").apply();
    }

    public boolean containsDialog(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.allDialogs.size()) goto L10;
        if (this.allDialogs.get(r1).dialogId == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public void deleteAllDialogs() {
        this.dialogsCount = 0;
        this.allDialogs.clear();
        this.loadedDialogs.clear();
        this.cachedDialogs.clear();
        update();
    }

    public void deleteDialog(long r2) {
        this.dialogsCount -= removeDialog(r2);
        update();
    }

    public void deleteDialogs(ArrayList<Long> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.dialogsCount -= removeDialog(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L4
    L6:
        update();
    }

    public SavedDialog findSavedDialog(long r2) {
        return findSavedDialog(this.allDialogs, r2);
    }

    public SavedDialog findSavedDialog(ArrayList<SavedDialog> r4, long r5) {
        int r3 = 0;
    L4:
        if (r3 >= r4.size()) goto L9;
        SavedDialog r0 = r4.get(r3);
        if (r0.dialogId == r5) goto L7;
        r3 = r3 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return null;
    }

    public int getAllCount() {
        if (this.dialogsEndReached == false) goto L7;
        return this.allDialogs.size();
    L7:
        if (this.dialogsLoaded == false) goto L11;
        return this.dialogsCount - this.dialogsCountHidden;
    L11:
        return this.cachedDialogs.size();
    }

    public int getLoadedCount() {
        return this.loadedDialogs.size();
    }

    public int getMessagesCount(long r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.allDialogs.size()) goto L10;
        SavedDialog r2 = this.allDialogs.get(r1);
        if (r2.dialogId == r6) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return r2.messagesCount;
    L10:
        return 0;
    }

    public int getPinnedCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.allDialogs.size()) goto L9;
        if (this.allDialogs.get(r0).pinned == false) goto L8;
        r1 = r1 + 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public boolean hasDialogs() {
        if (getAllCount() > 0) goto L5;
        return false;
    L5:
        long r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (this.allDialogs.size() == 1) goto L8;
    L10:
        return true;
    L8:
        if (this.allDialogs.get(0).dialogId != r2) goto L10;
        return false;
    }

    public void hasSavedMessages(long r3, Utilities.Callback<Boolean> r5) {
        SavedDialog r0 = findSavedDialog(r3);
        if (r0 == null) goto L12;
        if (r0.messagesCount <= 0) goto L12;
        if (r0.messagesCountLoaded == false) goto L12;
        if (r5 == null) goto L28;
        r5.run(Boolean.TRUE);
        return;
    L28:
        return;
    L12:
        if (DialogObject.isUserDialog(r3) == false) goto L14;
    L18:
        ArrayList<Utilities.Callback<Boolean>> r02 = this.checkMessagesCallbacks.get(r3);
        if (r02 == null) goto L23;
        if (r5 == null) goto L30;
        r02.add(r5);
        return;
    L30:
        return;
    L23:
        ArrayList<Utilities.Callback<Boolean>> r03 = new ArrayList();
        if (r5 == null) goto L26;
        r03.add(r5);
    L26:
        this.checkMessagesCallbacks.put(r3, r03);
        TLRPC.TL_messages_getSavedHistory r52 = new TLRPC.TL_messages_getSavedHistory();
        r52.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r3);
        r52.limit = 1;
        r52.hash = 0;
        r52.offset_id = Integer.MAX_VALUE;
        r52.offset_date = Integer.MAX_VALUE;
        r52.add_offset = -1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new SavedMessagesController$$ExternalSyntheticLambda4(this, r3));
        return;
    L14:
        if (DialogObject.isChatDialog(r3) == true) goto L18;
        if (r5 == null) goto L29;
        r5.run(Boolean.FALSE);
        return;
    }

    public boolean isLoading() {
        return this.dialogsLoading;
    }

    public void loadDialogs(boolean r8) {
        this.loadingCacheOnly = r8;
        if (this.dialogsLoading == false) goto L5;
        return;
    L5:
        if (this.dialogsEndReached == false) goto L7;
        return;
    L7:
        if (this.loadingCache == false) goto L10;
        return;
    L10:
        if (this.loadedCache == true) goto L13;
        loadCache(new SavedMessagesController$$ExternalSyntheticLambda8(this));
        return;
    L13:
        if (r8 == true) goto L39;
        this.dialogsLoading = true;
        TLRPC.TL_messages_getSavedDialogs r0 = new TLRPC.TL_messages_getSavedDialogs();
        if (this.loadedDialogs.isEmpty() == false) goto L18;
        SavedDialog r82 = null;
    L19:
        int r1 = 0;
        if (r82 == null) goto L22;
        r0.offset_id = r82.top_message_id;
        r0.offset_date = r82.getDate();
        r0.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r82.dialogId);
    L23:
        r0.limit = 20;
        ArrayList r83 = new ArrayList();
        r83.addAll(this.allDialogs.subList(Math.min(this.loadedDialogs.size(), this.allDialogs.size()), Math.min(this.loadedDialogs.size() + r0.limit, this.allDialogs.size())));
    L25:
        if (r1 >= r83.size()) goto L31;
        SavedDialog r2 = (SavedDialog) r83.get(r1);
        long r3 = r0.hash;
        if (r2.pinned == false) goto L29;
        long r5 = 1;
    L30:
        long r32 = MediaDataController.calcHash(r3, r5);
        r0.hash = r32;
        long r33 = MediaDataController.calcHash(r32, Math.abs(r2.dialogId));
        r0.hash = r33;
        long r34 = MediaDataController.calcHash(r33, r2.top_message_id);
        r0.hash = r34;
        r0.hash = MediaDataController.calcHash(r34, r2.getDate());
        r1 = r1 + 1;
        goto L25
    L29:
        r5 = 0;
        goto L30
    L31:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SavedMessagesController$$ExternalSyntheticLambda9(this, r83));
        return;
    L22:
        r0.offset_id = Integer.MAX_VALUE;
        r0.offset_date = 0;
        r0.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L23
    L18:
        ArrayList<SavedDialog> r12 = this.loadedDialogs;
        r82 = r12.get(r12.size() - 1);
        goto L19
    }

    public void preloadDialogs(boolean r2) {
        if (this.dialogsLoaded == true) goto L6;
        loadDialogs(r2);
        return;
    }

    public void processUpdate(TLRPC.Update r1) {
        if (processUpdateInternal(r1) == false) goto L6;
        update();
        return;
    }

    public ArrayList<SavedDialog> searchDialogs(String r10) {
        ArrayList<SavedDialog> r0 = new ArrayList();
        if (TextUtils.isEmpty(r10) == true) goto L36;
        String r102 = AndroidUtilities.translitSafe(r10.toLowerCase());
        int r1 = 0;
    L7:
        if (r1 >= this.allDialogs.size()) goto L36;
        SavedDialog r2 = this.allDialogs.get(r1);
        long r3 = r2.dialogId;
        String r6 = null;
        if (r3 != UserObject.ANONYMOUS) goto L12;
        String r32 = LocaleController.getString(R.string.AnonymousForward);
    L21:
        if (r32 == null) goto L35;
        String r33 = AndroidUtilities.translitSafe(r32.toLowerCase());
        if (r33.startsWith(r102) == false) goto L26;
    L34:
        r0.add(r2);
        goto L35
    L26:
        if (r33.contains(" " + r102) == true) goto L34;
        if (r6 == null) goto L35;
        String r34 = AndroidUtilities.translitSafe(r6.toLowerCase());
        if (r34.startsWith(r102) == false) goto L32;
    L33:
        r0.add(r2);
        goto L35
    L32:
        if (r34.contains(" " + r102) == true) goto L33;
    L35:
        r1 = r1 + 1;
        goto L7
    L12:
        if (r3 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L14;
        r32 = LocaleController.getString(R.string.MyNotes);
        r6 = LocaleController.getString(R.string.SavedMessages);
        goto L21
    L14:
        long r35 = r2.dialogId;
        int r4 = this.currentAccount;
        if (r35 < 0) goto L17;
        r32 = UserObject.getUserName(MessagesController.getInstance(r4).getUser(Long.valueOf(r2.dialogId)));
        goto L21
    L17:
        TLRPC.Chat r36 = MessagesController.getInstance(r4).getChat(Long.valueOf(-r2.dialogId));
        if (r36 == null) goto L20;
        r32 = r36.title;
        goto L21
    L20:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L36:
        return r0;
    }

    public void update() {
        updateAllDialogs(true);
        saveCacheSchedule();
    }

    public void update(long r5, TLRPC.messages_Messages r7) {
        boolean r0 = updateSavedDialogs(r7.messages);
        if ((r7 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L11;
        if (updatedDialogCount(r5, r7.count) == true) goto L9;
        if (r0 == true) goto L9;
    L8:
        r0 = false;
    L22:
        if (r0 == false) goto L25;
        AndroidUtilities.runOnUIThread(new SavedMessagesController$$ExternalSyntheticLambda10(this));
        return;
    L25:
        return;
    L9:
        r0 = true;
        goto L22
    L11:
        if ((r7 instanceof TLRPC.TL_messages_messages) == false) goto L17;
        if (updatedDialogCount(r5, r7.messages.size()) == true) goto L9;
        if (r0 == false) goto L8;
    L17:
        if ((r7 instanceof TLRPC.TL_messages_channelMessages) == false) goto L22;
        if (updatedDialogCount(r5, r7.count) == true) goto L9;
        if (r0 == false) goto L8;
        goto L8
    }

    public void updateDeleted(LongSparseArray<ArrayList<Integer>> r12) {
        ArrayList<SavedDialog> r0 = new ArrayList();
        int r2 = 0;
        boolean r3 = false;
    L4:
        if (r2 >= r12.size()) goto L33;
        long r4 = r12.keyAt(r2);
        ArrayList<Integer> r6 = r12.valueAt(r2);
        int r7 = 0;
        int r8 = 0;
    L7:
        if (r7 >= r6.size()) goto L9;
        r8 = Math.max(r8, r6.get(r7).intValue());
        r7 = r7 + 1;
        goto L7
    L9:
        int r72 = 0;
    L11:
        if (r72 >= this.allDialogs.size()) goto L16;
        if (this.allDialogs.get(r72).dialogId == r4) goto L14;
        r72 = r72 + 1;
        goto L11
    L14:
        SavedDialog r42 = this.allDialogs.get(r72);
    L17:
        if (r42 == null) goto L32;
        if (r42.messagesCountLoaded == false) goto L24;
        int r5 = Math.max(0, r42.messagesCount - r6.size());
        int r9 = r42.messagesCount;
        if (r5 == r9) goto L24;
        r42.messagesCount = Math.max(0, r9 - r6.size());
        r3 = true;
    L24:
        if (r42.messagesCountLoaded == false) goto L30;
        if (r42.messagesCount > 0) goto L30;
        removeDialog(r42.dialogId);
    L28:
        r3 = true;
    L30:
        if (r42.top_message_id > r8) goto L32;
        r0.add(r42);
    L32:
        r2 = r2 + 1;
        goto L4
    L16:
        r42 = null;
        goto L17
    L33:
        if (r3 == true) goto L35;
        return;
    L35:
        if (r0.isEmpty() == true) goto L38;
        updateDialogsLastMessage(r0);
        return;
    L38:
        update();
    }

    public boolean updatePinned(ArrayList<Long> r8, boolean r9, boolean r10) {
        ArrayList<Long> r0 = getCurrentPinnedOrder(this.allDialogs);
        ArrayList<Long> r1 = new ArrayList(r0);
        int r2 = r8.size() - 1;
    L4:
        if (r2 < 0) goto L13;
        Long r5 = r8.get(r2);
        r5.longValue();
        if (r9 == true) goto L8;
    L10:
        if (r9 == true) goto L12;
        r1.remove(r5);
    L12:
        r2 = r2 - 1;
        goto L4
    L8:
        if (r1.contains(r5) == true) goto L10;
        r1.add(0, r5);
        goto L12
    L13:
        boolean r82 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r92 = this.currentAccount;
        if (r82 == false) goto L16;
        int r83 = MessagesController.getInstance(r92).savedDialogsPinnedLimitPremium;
    L18:
        if (r1.size() <= r83) goto L21;
        return false;
    L21:
        if (sameOrder(r0, r1) == true) goto L31;
        if (r10 == false) goto L25;
        updatePinnedOrderToServer(r1);
        return true;
    L25:
        boolean r84 = updatePinnedOrder(this.loadedDialogs, r1);
        boolean r7 = updatePinnedOrder(this.cachedDialogs, r1);
        if (r84 == true) goto L30;
        if (r7 == true) goto L30;
        return false;
    L30:
        return true;
    L31:
        return false;
    L16:
        r83 = MessagesController.getInstance(r92).savedDialogsPinnedLimitDefault;
        goto L18
    }

    public boolean updatePinnedOrder(ArrayList<Long> r4) {
        ArrayList<Long> r0 = getCurrentPinnedOrder(this.allDialogs);
        boolean r1 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r2 = this.currentAccount;
        if (r1 == false) goto L5;
        int r12 = MessagesController.getInstance(r2).savedDialogsPinnedLimitPremium;
    L7:
        if (r4.size() <= r12) goto L11;
        return false;
    L11:
        if (sameOrder(r0, r4) == true) goto L15;
        updatePinnedOrderToServer(r4);
        return true;
    L15:
        return true;
    L5:
        r12 = MessagesController.getInstance(r2).savedDialogsPinnedLimitDefault;
        goto L7
    }

    public boolean updateSavedDialog(TLRPC.Message r8) {
        if (r8 != null) goto L5;
        return false;
    L5:
        long r1 = MessageObject.getSavedDialogId(UserConfig.getInstance(this.currentAccount).getClientUserId(), r8);
        int r3 = 0;
    L7:
        if (r3 >= this.allDialogs.size()) goto L13;
        SavedDialog r4 = this.allDialogs.get(r3);
        if (r4.dialogId == r1) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        MessageObject r12 = new MessageObject(this.currentAccount, r8, false, false);
        r4.message = r12;
        r4.top_message_id = r12.getId();
        return true;
    L13:
        return false;
    }

    public boolean updateSavedDialogs(ArrayList<TLRPC.Message> r15) {
        if (r15 != null) goto L5;
        return false;
    L5:
        LongSparseArray r1 = new LongSparseArray();
        LongSparseArray r2 = new LongSparseArray();
        new HashSet();
        long r3 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r5 = 0;
    L7:
        if (r5 >= r15.size()) goto L28;
        TLRPC.Message r6 = r15.get(r5);
        long r8 = MessageObject.getSavedDialogId(r3, r6);
        if (r8 != r3) goto L11;
    L17:
        TLRPC.Message r10 = (TLRPC.Message) r1.get(r8);
        if (r10 != null) goto L20;
    L21:
        r1.put(r8, r6);
    L22:
        Integer r62 = (Integer) r2.get(r8);
        if (r62 != null) goto L25;
        int r63 = 0;
    L26:
        r2.put(r8, Integer.valueOf(r63 + 1));
    L27:
        r5 = r5 + 1;
        goto L7
    L25:
        r63 = r62.intValue();
        goto L26
    L20:
        if (r10.id >= r6.id) goto L22;
    L11:
        if (r6.id < 0) goto L27;
        if (r6.send_state == 0) goto L17;
        if (r6.fwd_from == null) goto L17;
    L28:
        int r32 = 0;
        boolean r4 = false;
    L30:
        if (r32 >= r1.size()) goto L96;
        long r52 = r1.keyAt(r32);
        TLRPC.Message r82 = (TLRPC.Message) r1.valueAt(r32);
        Integer r9 = (Integer) r2.get(r52);
        int r102 = 0;
    L33:
        if (r102 >= this.cachedDialogs.size()) goto L57;
        SavedDialog r11 = this.cachedDialogs.get(r102);
        if (r11.dialogId == r52) goto L36;
        r102 = r102 + 1;
        goto L33
    L36:
        int r103 = r11.top_message_id;
        int r12 = r82.id;
        if (r103 < r12) goto L42;
        if (r12 < 0) goto L40;
    L52:
        int r104 = r11.messagesCount;
        if (r9 == null) goto L55;
        r104 = Math.max(r9.intValue(), r104);
    L55:
        r9 = Integer.valueOf(r104);
        boolean r105 = true;
    L58:
        if (r105 == true) goto L63;
        SavedDialog r42 = SavedDialog.fromMessage(this.currentAccount, r82, true);
        if (r9 == null) goto L62;
        r42.messagesCount = r9.intValue();
    L62:
        this.cachedDialogs.add(r42);
        r4 = true;
    L63:
        int r106 = 0;
    L65:
        if (r106 >= this.loadedDialogs.size()) goto L89;
        SavedDialog r112 = this.loadedDialogs.get(r106);
        if (r112.dialogId == r52) goto L68;
        r106 = r106 + 1;
        goto L65
    L68:
        int r53 = r112.top_message_id;
        int r64 = r82.id;
        if (r53 < r64) goto L74;
        if (r64 < 0) goto L72;
    L84:
        int r54 = r112.messagesCount;
        if (r9 == null) goto L87;
        r54 = Math.max(r9.intValue(), r54);
    L87:
        r9 = Integer.valueOf(r54);
        boolean r55 = true;
    L90:
        if (r55 == true) goto L95;
        SavedDialog r43 = SavedDialog.fromMessage(this.currentAccount, r82, true);
        if (r9 == null) goto L94;
        r43.messagesCount = r9.intValue();
    L94:
        this.loadedDialogs.add(r43);
        r4 = true;
    L95:
        r32 = r32 + 1;
        goto L30
    L72:
        if (r82.date <= r112.getDate()) goto L84;
    L74:
        if (r112.top_message_id >= r82.id) goto L83;
        int r44 = 0;
        int r56 = 0;
    L77:
        if (r44 >= r15.size()) goto L82;
        if (r15.get(r44).id <= r112.top_message_id) goto L81;
        r56 = r56 + 1;
    L81:
        r44 = r44 + 1;
        goto L77
    L82:
        r112.messagesCount += r56;
    L83:
        MessageObject r45 = new MessageObject(this.currentAccount, r82, false, false);
        r112.message = r45;
        r112.top_message_id = r45.getId();
        r4 = true;
        goto L84
    L89:
        r55 = false;
        goto L90
    L40:
        if (r82.date <= r11.getDate()) goto L52;
    L42:
        if (r11.top_message_id >= r82.id) goto L51;
        int r46 = 0;
        int r107 = 0;
    L45:
        if (r46 >= r15.size()) goto L50;
        if (r15.get(r46).id <= r11.top_message_id) goto L49;
        r107 = r107 + 1;
    L49:
        r46 = r46 + 1;
        goto L45
    L50:
        r11.messagesCount += r107;
    L51:
        MessageObject r47 = new MessageObject(this.currentAccount, r82, false, false);
        r11.message = r47;
        r11.top_message_id = r47.getId();
        r4 = true;
        goto L52
    L57:
        r105 = false;
        goto L58
    L96:
        return r4;
    }

    public boolean updatedDialogCount(long r2, int r4) {
        return updatedDialogCount(r2, r4, false);
    }

    public boolean updatedDialogCount(long r6, int r8, boolean r9) {
        int r1 = 0;
    L4:
        if (r1 >= this.allDialogs.size()) goto L15;
        SavedDialog r2 = this.allDialogs.get(r1);
        if (r2.dialogId == r6) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        if (r2.messagesCount == r8) goto L10;
    L12:
        r2.messagesCount = r8;
        r2.messagesCountLoaded = true;
        return true;
    L10:
        if (r2.messagesCountLoaded == true) goto L15;
        if (r9 == true) goto L12;
    L15:
        return false;
    }
}
