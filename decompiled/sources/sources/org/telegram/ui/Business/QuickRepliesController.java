package org.telegram.ui.Business;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes6.dex */
public class QuickRepliesController {
    private static volatile QuickRepliesController[] Instance;
    private static final Object[] lockObjects = null;
    public final int currentAccount;
    private ArrayList<QuickReply> filtered;
    private boolean loaded;
    private boolean loading;
    public final ArrayList<QuickReply> localReplies;
    public final ArrayList<QuickReply> replies;

    /* loaded from: classes3.dex */
    public class QuickReply {
        public int id;
        public boolean local;
        public HashSet<Integer> localIds;
        public int messagesCount;
        public String name;
        public int order;
        final /* synthetic */ QuickRepliesController this$0;
        public MessageObject topMessage;
        public int topMessageId;

        public QuickReply(QuickRepliesController r1) {
            this.this$0 = r1;
            this.localIds = new HashSet();
        }

        public int getMessagesCount() {
            if (this.local == false) goto L7;
            return this.localIds.size();
        L7:
            return this.messagesCount;
        }

        public int getTopMessageId() {
            MessageObject r0 = this.topMessage;
            if (r0 == null) goto L7;
            return r0.getId();
        L7:
            return this.topMessageId;
        }

        public boolean isSpecial() {
            return QuickRepliesController.isSpecial(this.name);
        }
    }

    /* renamed from: $r8$lambda$0SopKeY1GOl54AXr2D-UZMj361g, reason: not valid java name */
    public static /* synthetic */ void m6396$r8$lambda$0SopKeY1GOl54AXr2DUZMj361g(QuickRepliesController r0, MessagesStorage r1, QuickReply r2, long r3) {
        r0.lambda$updateTopMessage$16(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$1A-yuHVelo9MjPdUJNcZ33KQesk, reason: not valid java name */
    public static /* synthetic */ void m6397$r8$lambda$1AyuHVelo9MjPdUJNcZ33KQesk(QuickRepliesController r0, MessagesStorage r1) {
        r0.lambda$saveToCache$4(r1);
    }

    /* renamed from: $r8$lambda$1oJMlKOo3dwC-btPYrmUJoexMlI, reason: not valid java name */
    public static /* synthetic */ void m6398$r8$lambda$1oJMlKOo3dwCbtPYrmUJoexMlI(QuickRepliesController r0, TLRPC.Message r1, String r2, int r3) {
        r0.lambda$processUpdate$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$4V4SUz9nYOMpbWKX3ae3MkDK6DY(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda25());
    }

    /* renamed from: $r8$lambda$5kMEz7afJ-C1bRJjyoXDjTsM07I, reason: not valid java name */
    public static /* synthetic */ void m6399$r8$lambda$5kMEz7afJC1bRJjyoXDjTsM07I(QuickRepliesController r0, TLObject r1) {
        r0.lambda$load$2(r1);
    }

    /* renamed from: $r8$lambda$8WiR6dnjHfrZI7l-O7HOjL4VsE4, reason: not valid java name */
    public static /* synthetic */ void m6400$r8$lambda$8WiR6dnjHfrZI7lO7HOjL4VsE4(QuickRepliesController r0, MessagesStorage r1, long r2, Runnable r4) {
        r0.lambda$load$1(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$AR4UodbEIfNfKeUoECl8nXZTcSI(MessagesStorage r2, QuickReply r3) {
        SQLitePreparedStatement r0 = null;
        r0 = r2.getDatabase().executeFast("REPLACE INTO business_replies VALUES(?, ?, ?, ?);");     // Catch: Throwable -> L6 Exception -> L8
        r0.requery();     // Catch: Throwable -> L6 Exception -> L8
        r0.bindInteger(1, r3.id);     // Catch: Throwable -> L6 Exception -> L8
        r0.bindString(2, r3.name);     // Catch: Throwable -> L6 Exception -> L8
        r0.bindInteger(3, r3.order);     // Catch: Throwable -> L6 Exception -> L8
        r0.bindInteger(4, r3.messagesCount);     // Catch: Throwable -> L6 Exception -> L8
        r0.step();     // Catch: Throwable -> L6 Exception -> L8
        r0.dispose();
        return;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        if (r0 == null) goto L19;
        r0.dispose();
        return;
    L19:
        return;
    L6:
        th = move-exception;
        if (r0 == null) goto L15;
        r0.dispose();
    L15:
        throw th;
    }

    /* renamed from: $r8$lambda$CcMGPdJ4mXrWLU0JeEK4dbS-byk, reason: not valid java name */
    public static /* synthetic */ void m6401$r8$lambda$CcMGPdJ4mXrWLU0JeEK4dbSbyk(QuickRepliesController r0, TLRPC.Update r1) {
        r0.lambda$processUpdate$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$EwYGmZW7ZH0aK1Dg1PXrysYBOcI(QuickRepliesController r0, TLRPC.Update r1) {
        r0.lambda$processUpdate$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$FRkaQ1eL1ejginr8m4oJfF0URoY(QuickRepliesController r0, TLRPC.Update r1) {
        r0.lambda$processUpdate$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$IZyd_9A10W9tQVaRzAR4af8QwKQ(QuickRepliesController r0, TLObject r1, ArrayList r2, TLRPC.TL_messages_sendQuickReplyMessages r3, TLRPC.TL_error r4) {
        r0.lambda$sendQuickReplyTo$24(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$IlYkbDO4pT633PiIRVfxrzhaKzI(QuickReply r0, QuickReply r1) {
        return r0.order - r1.order;
    }

    /* renamed from: $r8$lambda$Lt4gBLlWHHkvc0tI-J-sWBR12Vs, reason: not valid java name */
    public static /* synthetic */ void m6402$r8$lambda$Lt4gBLlWHHkvc0tIJsWBR12Vs(QuickRepliesController r0, ArrayList r1, TLRPC.TL_messages_sendQuickReplyMessages r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendQuickReplyTo$25(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$RNQMFALXqWz32fXde-megZYtbzY, reason: not valid java name */
    public static /* synthetic */ void m6403$r8$lambda$RNQMFALXqWz32fXdemegZYtbzY(QuickRepliesController r0, ArrayList r1, ArrayList r2, ArrayList r3, Runnable r4) {
        r0.lambda$load$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RydZKfuF2MZQ3yyLCHPNfKOIt0M() {
    }

    public static /* synthetic */ void $r8$lambda$SeFBaSSvuRxXGI3BgqiugRrI1p8(MessagesStorage r2, ArrayList r3) {
        r2.getDatabase().executeFast(String.format("DELETE FROM quick_replies_messages WHERE topic_id IN (%s)", new Object[]{TextUtils.join(", ", r3)})).stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$UvTmZmGBayh0kQv6ZsvM4qPqfag(QuickRepliesController r0, ArrayList r1, ArrayList r2, QuickReply r3, MessageObject r4) {
        r0.lambda$updateTopMessage$15(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$aeGYKWH1LXZ31D_ScI7cVyeoX0s() {
    }

    /* renamed from: $r8$lambda$bH-K3ut4ePBfrswrOWBKAHIirt4, reason: not valid java name */
    public static /* synthetic */ void m6404$r8$lambda$bHK3ut4ePBfrswrOWBKAHIirt4(MessagesStorage r3, int r4) {
        SQLiteDatabase r32 = r3.getDatabase();     // Catch: Exception -> L5
        r32.executeFast("DELETE FROM business_replies WHERE topic_id = " + r4).stepThis().dispose();     // Catch: Exception -> L5
        r32.executeFast("DELETE FROM quick_replies_messages WHERE topic_id = " + r4).stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$l7Kf5haYHXF_hpa14iutBrNzAYg(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda20());
    }

    /* renamed from: $r8$lambda$lG8QvbC-rUDrs3KIwCcA2R1snp8, reason: not valid java name */
    public static /* synthetic */ void m6405$r8$lambda$lG8QvbCrUDrs3KIwCcA2R1snp8(QuickRepliesController r0) {
        r0.lambda$checkLocalMessages$23();
    }

    public static /* synthetic */ void $r8$lambda$osO0EMNuE6fwQAiJc0S1kf3DMO0(QuickRepliesController r0, ArrayList r1, QuickReply r2, TLRPC.TL_messages_sendQuickReplyMessages r3) {
        r0.lambda$sendQuickReplyTo$26(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$rvmBvgNoBJ5V29MRQ4JKZlWDX4w(QuickRepliesController r0, TLRPC.Update r1) {
        r0.lambda$processUpdate$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$vG9lYXD2QyZGiyR7a3Wz093LGhE(QuickRepliesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$load$3(r1, r2);
    }

    /* renamed from: $r8$lambda$vQGX-INKrm_qz1O2pj3k66SOugo, reason: not valid java name */
    public static /* synthetic */ void m6406$r8$lambda$vQGXINKrm_qz1O2pj3k66SOugo(QuickRepliesController r0, MessagesStorage r1, QuickReply r2, TLRPC.TL_messages_sendQuickReplyMessages r3) {
        r0.lambda$sendQuickReplyTo$27(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$z94vlEvUKe90dH8KC7co8cg7_OA() {
    }

    public static /* synthetic */ void $r8$lambda$zKsHgny0_FQW9t7B5Ze74pfO3FU(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda17());
    }

    static {
        Instance = new QuickRepliesController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private QuickRepliesController(int r2) {
        this.replies = new ArrayList();
        this.localReplies = new ArrayList();
        this.filtered = new ArrayList();
        this.currentAccount = r2;
    }

    private void addReply(QuickReply r4) {
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        r0.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda11(r0, r4));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    private void ensureLoaded(Runnable r2) {
        if (this.loaded == false) goto L6;
        r2.run();
        return;
    L6:
        load(true, r2);
    }

    public static QuickRepliesController getInstance(int r3) {
        QuickRepliesController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        QuickRepliesController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        QuickRepliesController[] r03 = Instance;     // Catch: Throwable -> L9
        QuickRepliesController r2 = new QuickRepliesController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    public static boolean isSpecial(String r1) {
        if ("hello".equalsIgnoreCase(r1) == false) goto L5;
        return true;
    L5:
        if ("away".equalsIgnoreCase(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$checkLocalMessages$23() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$load$0(ArrayList r4, ArrayList r5, ArrayList r6, Runnable r7) {
        this.loading = false;
        MessagesController.getInstance(this.currentAccount).putUsers(r4, true);
        MessagesController.getInstance(this.currentAccount).putChats(r5, true);
        this.replies.clear();
        this.replies.addAll(r6);
        if (r7 == null) goto L5;
        r7.run();
    L6:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    L5:
        load(false);
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v6, types: [org.telegram.tgnet.TLRPC$Message] */
    /* JADX WARN: Type inference failed for: r5v9, types: [org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.NativeByteBuffer] */
    /* JADX WARN: Type inference failed for: r7v1, types: [org.telegram.SQLite.SQLiteCursor] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5, types: [org.telegram.SQLite.SQLiteCursor] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [org.telegram.SQLite.SQLiteCursor] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v5 */
    private /* synthetic */ void lambda$load$1(MessagesStorage r20, long r21, Runnable r23) {
        QuickRepliesController r1 = this;
        ArrayList r4 = new ArrayList();
        ArrayList<TLRPC.User> r2 = new ArrayList();
        ArrayList<TLRPC.Chat> r3 = new ArrayList();
        SQLiteDatabase r6 = r20.getDatabase();     // Catch: Throwable -> L33 Exception -> L35
        ?? r8 = 0;
        ?? r7 = r6.queryFinalized("SELECT topic_id, name, order_value, count FROM business_replies ORDER BY order_value ASC", new Object[0]);     // Catch: Throwable -> L33 Exception -> L35
    L48:
        int r11 = 2;
        int r12 = 1;
        if (r7.next() == false) goto L12;
        QuickReply r9 = new QuickReply(r1);     // Catch: Throwable -> L7 Exception -> L9
        r9.id = r7.intValue(0);     // Catch: Throwable -> L7 Exception -> L9
        r9.name = r7.stringValue(1);     // Catch: Throwable -> L7 Exception -> L9
        r9.order = r7.intValue(2);     // Catch: Throwable -> L7 Exception -> L9
        r9.messagesCount = r7.intValue(3);     // Catch: Throwable -> L7 Exception -> L9
        r4.add(r9);     // Catch: Throwable -> L7 Exception -> L9
        goto L48
    L12:
        r7.dispose();     // Catch: Throwable -> L7 Exception -> L9
        ArrayList<Long> r92 = new ArrayList();     // Catch: Throwable -> L7 Exception -> L9
        ArrayList r13 = new ArrayList();     // Catch: Throwable -> L7 Exception -> L9
        int r14 = 0;
        r7 = r7;
    L13:
        if (r14 >= r4.size()) goto L26;
        QuickReply r15 = (QuickReply) r4.get(r14);     // Catch: Throwable -> L7 Exception -> L9
        r7 = r6.queryFinalized("SELECT data, send_state, mid, date, topic_id, ttl FROM quick_replies_messages WHERE topic_id = ? ORDER BY mid ASC", new Object[]{Integer.valueOf(r15.id)});     // Catch: Throwable -> L7 Exception -> L9
        if (r7.next() == false) goto L24;
        ?? r5 = r7.byteBufferValue(r8);     // Catch: Throwable -> L7 Exception -> L9
        if (r5 == 0) goto L24;
        ?? r10 = TLRPC.Message.TLdeserialize(r5, r5.readInt32(r8), r8);     // Catch: Throwable -> L7 Exception -> L9
        r10.send_state = r7.intValue(r12);     // Catch: Throwable -> L7 Exception -> L9
        r10.readAttachPath(r5, r21);     // Catch: Throwable -> L7 Exception -> L9
        r5.reuse();     // Catch: Throwable -> L7 Exception -> L9
        r10.id = r7.intValue(r11);     // Catch: Throwable -> L7 Exception -> L9
        r10.date = r7.intValue(3);     // Catch: Throwable -> L7 Exception -> L9
        r10.flags |= TLObject.FLAG_30;
        r10.quick_reply_shortcut_id = r7.intValue(4);     // Catch: Throwable -> L7 Exception -> L9
        r10.ttl = r7.intValue(5);     // Catch: Throwable -> L7 Exception -> L9
        ArrayList r82 = r13;
        MessagesStorage.addUsersAndChatsFromMessage(r10, r92, r82, null);     // Catch: Throwable -> L7 Exception -> L9
        int r112 = r1.currentAccount;     // Catch: Throwable -> L7 Exception -> L9
        ArrayList r16 = r4;
        int r17 = 1;
        boolean r42 = false;
        MessageObject r52 = new MessageObject(r112, r10, false, true);     // Catch: Throwable -> L7 Exception -> L22
        r15.topMessage = r52;     // Catch: Throwable -> L7 Exception -> L22
        r15.topMessageId = r10.id;     // Catch: Throwable -> L7 Exception -> L22
        r52.generateThumbs(false);     // Catch: Throwable -> L7 Exception -> L22
        r15.topMessage.applyQuickReply(r15.name, r15.id);     // Catch: Throwable -> L7 Exception -> L22
    L25:
        r7.dispose();     // Catch: Throwable -> L7 Exception -> L22
        r14 = r14 + 1;     // Catch: Throwable -> L7 Exception -> L22
        r12 = r17;
        r13 = r82;
        r11 = 2;
        r1 = this;
        r8 = r42;
        r4 = r16;
        r7 = r7;
    L22:
        e = e;
        SQLiteCursor r72 = r7;
    L11:
        SQLiteCursor r53 = r72;
    L46:
        FileLog.e(e);     // Catch: Throwable -> L42
        if (r53 == null) goto L40;
        r53.dispose();
    L40:
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda13(this, r2, r3, r16, r23));
        return;
    L42:
        th = th;
    L43:
        if (r53 == null) goto L45;
        r53.dispose();
    L45:
        throw th;
    L24:
        r16 = r4;
        r42 = r8;
        r17 = r12;
        r82 = r13;
        goto L25
    L26:
        r16 = r4;
        ArrayList r83 = r13;
        if (r83.isEmpty() == true) goto L30;
        r20.getChatsInternal(TextUtils.join(",", r83), r3);     // Catch: Throwable -> L7 Exception -> L22
    L30:
        if (r92.isEmpty() == true) goto L32;
        r20.getUsersInternal(r92, r2);     // Catch: Throwable -> L7 Exception -> L22
    L32:
        r7.dispose();
    L9:
        e = e;
        r16 = r4;
        r72 = r7;
    L7:
        th = th;
        r53 = r7;
        goto L43
    L35:
        e = e;
        r16 = r4;
        r53 = null;
    L33:
        th = th;
        r53 = null;
        goto L43
    }

    private /* synthetic */ void lambda$load$2(TLObject r12) {
        ArrayList r3 = null;
        if ((r12 instanceof TLRPC.TL_messages_quickReplies) == false) goto L19;
        TLRPC.TL_messages_quickReplies r122 = (TLRPC.TL_messages_quickReplies) r12;
        MessagesController.getInstance(this.currentAccount).putUsers(r122.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r122.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r122.users, r122.chats, true, true);
        ArrayList r0 = new ArrayList();
        int r4 = 0;
    L6:
        if (r4 >= r122.quick_replies.size()) goto L18;
        TLRPC.TL_quickReply r5 = r122.quick_replies.get(r4);
        QuickReply r6 = new QuickReply(this);
        r6.id = r5.shortcut_id;
        r6.name = r5.shortcut;
        r6.messagesCount = r5.count;
        r6.topMessageId = r5.top_message;
        r6.order = r4;
        int r7 = 0;
    L9:
        if (r7 >= r122.messages.size()) goto L14;
        TLRPC.Message r8 = r122.messages.get(r7);
        if (r8.id == r5.top_message) goto L15;
        r7 = r7 + 1;
    L15:
        if (r8 == null) goto L17;
        MessageObject r72 = new MessageObject(this.currentAccount, r8, false, true);
        r6.topMessage = r72;
        r72.generateThumbs(false);
        r6.topMessage.applyQuickReply(r5.shortcut, r5.shortcut_id);
    L17:
        r0.add(r6);
        r4 = r4 + 1;
        goto L6
    L14:
        r8 = null;
        goto L15
    L18:
        r3 = r0;
    L19:
        this.loading = false;
        if (r3 == null) goto L22;
        this.replies.clear();
        this.replies.addAll(r3);
    L22:
        this.loaded = true;
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$load$3(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda14(this, r1));
    }

    private /* synthetic */ void lambda$processUpdate$17(TLRPC.Message r18, String r19, int r20) {
        if ((r18.flags & TLObject.FLAG_30) == 0) goto L16;
        QuickReply r4 = findReply(r18.quick_reply_shortcut_id);
        if (r4 != null) goto L10;
        QuickReply r42 = new QuickReply(this);
        r42.id = r18.quick_reply_shortcut_id;
        r42.topMessageId = r18.id;
        MessageObject r7 = new MessageObject(this.currentAccount, r18, false, true);
        r42.topMessage = r7;
        r7.generateThumbs(false);
        if (r19 == null) goto L9;
        r42.name = r19;
        deleteLocalReply(r19);
    L9:
        r42.topMessage.applyQuickReply(r19, r20);
        r42.messagesCount = 1;
        this.replies.add(r42);
        updateOrder();
        addReply(r42);
        goto L16
    L10:
        int r72 = r4.topMessageId;
        int r8 = r18.id;
        if (r72 != r8) goto L14;
        r4.topMessageId = r8;
        MessageObject r73 = new MessageObject(this.currentAccount, r18, false, true);
        r4.topMessage = r73;
        r73.generateThumbs(false);
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        goto L16
    L14:
        if ((r18.flags & 32768) != 0) goto L16;
        r4.messagesCount++;
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    L16:
        if (r19 != null) goto L20;
        if (r20 != 0) goto L21;
        ArrayList<TLRPC.Message> r9 = new ArrayList();
        r9.add(r18);
        MessagesStorage.getInstance(this.currentAccount).putMessages(r9, true, true, false, DownloadController.getInstance(this.currentAccount).getAutodownloadMask(), 5, r18.quick_reply_shortcut_id);
        long r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        ArrayList<MessageObject> r43 = new ArrayList();
        r43.add(new MessageObject(this.currentAccount, r18, true, true));
        MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(r2, r43, 5);
        return;
    L21:
        return;
    }

    private /* synthetic */ void lambda$processUpdate$18(TLRPC.Update r9) {
        ArrayList<TLRPC.TL_quickReply> r92 = ((TL_update.TL_updateQuickReplies) r9).quick_replies;
        ArrayList r0 = new ArrayList(this.replies);
        this.replies.clear();
        int r2 = 0;
    L4:
        if (r2 >= r92.size()) goto L21;
        TLRPC.TL_quickReply r3 = r92.get(r2);
        int r4 = 0;
    L7:
        if (r4 >= r0.size()) goto L12;
        if (((QuickReply) r0.get(r4)).id == r3.shortcut_id) goto L10;
        r4 = r4 + 1;
        goto L7
    L10:
        QuickReply r42 = (QuickReply) r0.get(r4);
    L13:
        if (r42 != null) goto L15;
        r42 = new QuickReply(this);
    L15:
        r42.id = r3.shortcut_id;
        r42.name = r3.shortcut;
        r42.messagesCount = r3.count;
        r42.order = r2;
        r42.topMessageId = r3.top_message;
        MessageObject r5 = r42.topMessage;
        if (r5 == null) goto L20;
        if (r5.getId() == r3.top_message) goto L20;
        r42.topMessage = null;
    L20:
        this.replies.add(r42);
        deleteLocalReply(r42.name);
        r2 = r2 + 1;
        goto L4
    L12:
        r42 = null;
        goto L13
    L21:
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$processUpdate$19(TLRPC.Update r3) {
        TLRPC.TL_quickReply r32 = ((TL_update.TL_updateNewQuickReply) r3).quick_reply;
        QuickReply r0 = findReply(r32.shortcut_id);
        if (r0 == null) goto L10;
        r0.name = r32.shortcut;
        r0.messagesCount = r32.count;
        r0.topMessageId = r32.top_message;
        MessageObject r1 = r0.topMessage;
        if (r1 != null) goto L7;
    L11:
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    L7:
        if (r1.getId() == r32.top_message) goto L11;
        r0.topMessage = null;
        updateTopMessage(r0);
        return;
    L10:
        QuickReply r02 = new QuickReply(this);
        r02.id = r32.shortcut_id;
        r02.name = r32.shortcut;
        r02.messagesCount = r32.count;
        r02.topMessageId = r32.top_message;
        updateOrder();
        this.replies.add(r02);
        deleteLocalReply(r02.name);
        goto L11
    }

    private /* synthetic */ void lambda$processUpdate$21(TLRPC.Update r4) {
        QuickReply r42 = findReply(((TL_update.TL_updateDeleteQuickReply) r4).shortcut_id);
        if (r42 == null) goto L6;
        this.replies.remove(r42);
        deleteLocalReply(r42.name);
        int r43 = r42.id;
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        r0.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda15(r0, r43));
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$processUpdate$22(TLRPC.Update r4) {
        TL_update.TL_updateDeleteQuickReplyMessages r42 = (TL_update.TL_updateDeleteQuickReplyMessages) r4;
        QuickReply r0 = findReply(r42.shortcut_id);
        if (r0 == null) goto L16;
        int r1 = r0.messagesCount - r42.messages.size();
        r0.messagesCount = r1;
        if (r1 > 0) goto L8;
        this.replies.remove(r0);
    L8:
        if (r42.messages.contains(Integer.valueOf(r0.getTopMessageId())) == false) goto L10;
    L14:
        r0.topMessage = null;
        updateTopMessage(r0);
        return;
    L10:
        if (r0.topMessage == null) goto L14;
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$saveToCache$4(MessagesStorage r5) {
        SQLitePreparedStatement r0 = null;
        SQLiteDatabase r52 = r5.getDatabase();     // Catch: Throwable -> L8 Exception -> L10
        r52.executeFast("DELETE FROM business_replies").stepThis().dispose();     // Catch: Throwable -> L8 Exception -> L10
        r0 = r52.executeFast("REPLACE INTO business_replies VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L8 Exception -> L10
        int r53 = 0;
    L4:
        if (r53 >= this.replies.size()) goto L12;
        QuickReply r1 = this.replies.get(r53);     // Catch: Throwable -> L8 Exception -> L10
        r0.requery();     // Catch: Throwable -> L8 Exception -> L10
        r0.bindInteger(1, r1.id);     // Catch: Throwable -> L8 Exception -> L10
        r0.bindString(2, r1.name);     // Catch: Throwable -> L8 Exception -> L10
        r0.bindInteger(3, r1.order);     // Catch: Throwable -> L8 Exception -> L10
        r0.bindInteger(4, r1.messagesCount);     // Catch: Throwable -> L8 Exception -> L10
        r0.step();     // Catch: Throwable -> L8 Exception -> L10
        r53 = r53 + 1;
        goto L4
    L12:
        if (r0 == null) goto L26;
        r0.dispose();
        return;
    L26:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r0 == null) goto L27;
        r0.dispose();
        return;
    L27:
        return;
    L8:
        th = move-exception;
        if (r0 == null) goto L21;
        r0.dispose();
    L21:
        throw th;
    }

    private /* synthetic */ void lambda$sendQuickReplyTo$24(TLObject r4, ArrayList r5, TLRPC.TL_messages_sendQuickReplyMessages r6, TLRPC.TL_error r7) {
        if ((r4 instanceof TLRPC.TL_messages_messages) == false) goto L13;
        ArrayList<TLRPC.Message> r42 = ((TLRPC.TL_messages_messages) r4).messages;
        r5.clear();
        int r72 = r42.size();
        int r0 = 0;
        int r1 = 0;
    L5:
        if (r1 >= r72) goto L7;
        TLRPC.Message r2 = r42.get(r1);
        r1 = r1 + 1;
        r5.add(Integer.valueOf(r2.id));
        goto L5
    L7:
        r6.id = r5;
    L9:
        if (r0 >= r5.size()) goto L11;
        r6.random_id.add(Long.valueOf(Utilities.random.nextLong()));
        r0 = r0 + 1;
        goto L9
    L11:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r6, null);
        return;
    L13:
        FileLog.e("received " + r4 + " " + r7 + " on getQuickReplyMessages when trying to send quick reply");
    }

    private /* synthetic */ void lambda$sendQuickReplyTo$25(ArrayList r7, TLRPC.TL_messages_sendQuickReplyMessages r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda27(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$sendQuickReplyTo$26(ArrayList r4, QuickReply r5, TLRPC.TL_messages_sendQuickReplyMessages r6) {
        if (r4.isEmpty() == false) goto L5;
    L13:
        TLRPC.TL_messages_getQuickReplyMessages r0 = new TLRPC.TL_messages_getQuickReplyMessages();
        r0.shortcut_id = r5.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new QuickRepliesController$$ExternalSyntheticLambda26(this, r4, r6));
        return;
    L5:
        if (r4.size() < r5.getMessagesCount()) goto L13;
        r6.id = r4;
        int r52 = 0;
    L9:
        if (r52 >= r4.size()) goto L11;
        r6.random_id.add(Long.valueOf(Utilities.random.nextLong()));
        r52 = r52 + 1;
        goto L9
    L11:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r6, null);
    }

    private /* synthetic */ void lambda$sendQuickReplyTo$27(MessagesStorage r5, QuickReply r6, TLRPC.TL_messages_sendQuickReplyMessages r7) {
        ArrayList r0 = new ArrayList();
        SQLiteCursor r1 = null;
        r1 = r5.getDatabase().queryFinalized("SELECT id FROM quick_replies_messages WHERE topic_id = ?", new Object[]{Integer.valueOf(r6.id)});     // Catch: Throwable -> L8 Exception -> L10
    L4:
        if (r1.next() == false) goto L12;
        r0.add(Integer.valueOf(r1.intValue(0)));     // Catch: Throwable -> L8 Exception -> L10
    L12:
        r1.dispose();
    L16:
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda24(this, r0, r6, r7));
        return;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r1 == null) goto L16;
    L8:
        th = move-exception;
        if (r1 == null) goto L20;
        r1.dispose();
    L20:
        throw th;
    }

    private /* synthetic */ void lambda$updateTopMessage$15(ArrayList r3, ArrayList r4, QuickReply r5, MessageObject r6) {
        MessagesController.getInstance(this.currentAccount).putUsers(r3, true);
        MessagesController.getInstance(this.currentAccount).putChats(r4, true);
        r5.topMessage = r6;
        if (r6 == null) goto L5;
        r6.applyQuickReply(r5.name, r5.id);
    L5:
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$updateTopMessage$16(MessagesStorage r11, QuickReply r12, long r13) {
        SQLiteCursor r1 = null;
        MessageObject r14 = null;
        r14 = null;
        r1 = null;
        ArrayList<Long> r0 = new ArrayList();     // Catch: Throwable -> L23 Exception -> L25
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L23 Exception -> L25
        SQLiteCursor r3 = r11.getDatabase().queryFinalized("SELECT data, send_state, mid, date, topic_id, ttl FROM quick_replies_messages WHERE topic_id = ? ORDER BY mid ASC", new Object[]{Integer.valueOf(r12.id)});     // Catch: Throwable -> L23 Exception -> L25
    L12:
        e = move-exception;
        Exception r10 = e;
        r1 = r3;
    L27:
        FileLog.e(r10);     // Catch: Throwable -> L23
        if (r1 == null) goto L39;
        r1.dispose();
        return;
    L39:
        return;
    L10:
        th = move-exception;
        Throwable r102 = th;
        r1 = r3;
    L31:
        if (r1 == null) goto L40;
        r1.dispose();
        throw r102;
    L40:
        throw r102;
    L5:
        if (r3.next() == false) goto L9;
        NativeByteBuffer r5 = r3.byteBufferValue(0);     // Catch: Throwable -> L10 Exception -> L12
        if (r5 == null) goto L9;
        TLRPC.Message r6 = TLRPC.Message.TLdeserialize(r5, r5.readInt32(false), false);     // Catch: Throwable -> L10 Exception -> L12
        r6.send_state = r3.intValue(1);     // Catch: Throwable -> L10 Exception -> L12
        r6.readAttachPath(r5, r13);     // Catch: Throwable -> L10 Exception -> L12
        r5.reuse();     // Catch: Throwable -> L10 Exception -> L12
        r6.id = r3.intValue(2);     // Catch: Throwable -> L10 Exception -> L12
        r6.date = r3.intValue(3);     // Catch: Throwable -> L10 Exception -> L12
        r6.flags |= TLObject.FLAG_30;
        r6.quick_reply_shortcut_id = r3.intValue(4);     // Catch: Throwable -> L10 Exception -> L12
        r6.ttl = r3.intValue(5);     // Catch: Throwable -> L10 Exception -> L12
        MessagesStorage.addUsersAndChatsFromMessage(r6, r0, r2, null);     // Catch: Throwable -> L10 Exception -> L12
        r14 = new MessageObject(this.currentAccount, r6, false, true);     // Catch: Throwable -> L10 Exception -> L12
    L9:
        MessageObject r9 = r14;
        r3.dispose();     // Catch: Throwable -> L10 Exception -> L12
        ArrayList<TLRPC.User> r62 = new ArrayList();     // Catch: Throwable -> L10 Exception -> L12
        ArrayList<TLRPC.Chat> r7 = new ArrayList();     // Catch: Throwable -> L10 Exception -> L12
        if (r2.isEmpty() == true) goto L18;
        r11.getChatsInternal(TextUtils.join(",", r2), r7);     // Catch: Throwable -> L10 Exception -> L12
    L18:
        if (r0.isEmpty() == true) goto L20;
        r11.getUsersInternal(r0, r62);     // Catch: Throwable -> L10 Exception -> L12
    L20:
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda16(this, r62, r7, r12, r9));     // Catch: Throwable -> L10 Exception -> L12
        r3.dispose();
        return;
    L25:
        e = move-exception;
        r10 = e;
    L23:
        th = move-exception;
        r102 = th;
        goto L31
    }

    private void load(boolean r2) {
        load(r2, null);
    }

    private void load(boolean r9, Runnable r10) {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.loaded == true) goto L37;
        this.loading = true;
        if (r9 == false) goto L11;
        MessagesStorage r2 = MessagesStorage.getInstance(this.currentAccount);
        r2.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda7(this, r2, UserConfig.getInstance(this.currentAccount).getClientUserId(), r10));
        return;
    L11:
        TLRPC.TL_messages_getQuickReplies r8 = new TLRPC.TL_messages_getQuickReplies();
        r8.hash = 0;
        int r22 = 0;
    L13:
        if (r22 >= this.replies.size()) goto L31;
        QuickReply r3 = this.replies.get(r22);
        long r4 = MediaDataController.calcHash(r8.hash, r3.id);
        r8.hash = r4;
        String r6 = r3.name;
        if (r6 != null) goto L17;
        long r62 = 0;
    L18:
        long r42 = MediaDataController.calcHash(r4, r62);
        r8.hash = r42;
        MessageObject r63 = r3.topMessage;
        if (r63 != null) goto L21;
        long r64 = 0;
    L22:
        long r43 = MediaDataController.calcHash(r42, r64);
        r8.hash = r43;
        MessageObject r32 = r3.topMessage;
        if (r32 == null) goto L29;
        TLRPC.Message r33 = r32.messageOwner;
        if (r33 == null) goto L29;
        if ((r33.flags & 32768) == 0) goto L29;
        r8.hash = MediaDataController.calcHash(r43, r33.edit_date);
    L30:
        r22 = r22 + 1;
    L29:
        r8.hash = MediaDataController.calcHash(r43, 0);
        goto L30
    L21:
        r64 = r63.getId();
        goto L22
    L17:
        r62 = QuickRepliesController$$ExternalSyntheticBackport0.m(Utilities.MD5(r6).substring(0, 16), 16);
        goto L18
    L31:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r8, new QuickRepliesController$$ExternalSyntheticLambda8(this));
        return;
    }

    private void saveToCache() {
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        r0.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda12(this, r0));
    }

    private void updateOrder() {
        int r0 = 0;
    L4:
        if (r0 >= this.replies.size()) goto L6;
        this.replies.get(r0).order = r0;
        r0 = r0 + 1;
        goto L4
    }

    private void updateTopMessage(QuickReply r8) {
        if (r8 != null) goto L4;
        return;
    L4:
        long r5 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);
        r3.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda10(this, r3, r8, r5));
    }

    public boolean canAddNew() {
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r1 >= this.replies.size()) goto L22;
        if (r2 == 0) goto L7;
    L10:
        r2 = 1;
    L11:
        if (r3 == 0) goto L13;
    L16:
        r3 = 1;
    L17:
        if (r2 == 0) goto L20;
        if (r3 != 0) goto L22;
    L20:
        r1 = r1 + 1;
        goto L4
    L13:
        if ("away".equalsIgnoreCase(this.replies.get(r1).name) == true) goto L16;
        r3 = 0;
        goto L17
    L7:
        if ("hello".equalsIgnoreCase(this.replies.get(r1).name) == true) goto L10;
        r2 = 0;
    L22:
        if (((this.replies.size() + (r2 ^ 1)) + (r3 ^ 1)) >= MessagesController.getInstance(this.currentAccount).quickRepliesLimit) goto L24;
        return true;
    L24:
        return false;
    }

    public void checkLocalMessages(ArrayList<MessageObject> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L20;
        MessageObject r2 = r6.get(r1);
        r1 = r1 + 1;
        MessageObject r22 = r2;
        if (r22.isSending() == false) goto L3;
        if (findReply(r22.getQuickReplyId()) != null) goto L3;
        if (r22.getQuickReplyName() == null) goto L3;
        if (findReply(r22.getQuickReplyName()) != null) goto L3;
        QuickReply r3 = findLocalReply(r22.getQuickReplyName());
        if (r3 != null) goto L19;
        r3 = new QuickReply(this);
        r3.local = true;
        r3.name = r22.getQuickReplyName();
        r3.id = -1;
        r3.topMessage = r22;
        r3.topMessageId = r22.getId();
        this.localReplies.add(r3);
    L19:
        r3.localIds.add(Integer.valueOf(r22.getId()));
        AndroidUtilities.runOnUIThread(new QuickRepliesController$$ExternalSyntheticLambda1(this));
        goto L3
    }

    public void deleteLocalMessage(int r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.localReplies.size()) goto L13;
        QuickReply r2 = this.localReplies.get(r1);
        if (r2.localIds.contains(Integer.valueOf(r6)) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        r2.localIds.remove(Integer.valueOf(r6));
        if (r2.getMessagesCount() > 0) goto L10;
        this.localReplies.remove(r2);
    L10:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    }

    public void deleteLocalMessages(ArrayList<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Integer r2 = r4.get(r1);
        r1 = r1 + 1;
        deleteLocalMessage(r2.intValue());
        goto L3
    }

    public void deleteLocalReply(String r2) {
        QuickReply r22 = findLocalReply(r2);
        if (r22 == null) goto L6;
        this.localReplies.remove(r22);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        return;
    }

    public void deleteReplies(ArrayList<Integer> r9) {
        int r1 = 0;
    L4:
        if (r1 >= r9.size()) goto L10;
        if (findReply(r9.get(r1).intValue()) != null) goto L8;
        r9.remove(r1);
        r1 = r1 - 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L10:
        if (r9.isEmpty() == false) goto L12;
        return;
    L12:
        int r12 = 0;
    L14:
        if (r12 >= r9.size()) goto L26;
        QuickReply r2 = findReply(r9.get(r12).intValue());
        this.replies.remove(r2);
        deleteLocalReply(r2.name);
        TLRPC.TL_messages_deleteQuickReplyShortcut r4 = new TLRPC.TL_messages_deleteQuickReplyShortcut();
        r4.shortcut_id = r2.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new QuickRepliesController$$ExternalSyntheticLambda21());
        if ("hello".equals(r2.name) == false) goto L21;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.updateBusinessGreetingMessage(), null);
        TLRPC.UserFull r22 = MessagesController.getInstance(this.currentAccount).getUserFull(UserConfig.getInstance(this.currentAccount).getClientUserId());
        if (r22 == null) goto L25;
        r22.flags2 &= -5;
        r22.business_greeting_message = null;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r22, true);
    L25:
        r12 = r12 + 1;
        goto L14
    L21:
        if ("away".equals(r2.name) == false) goto L25;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.updateBusinessAwayMessage(), null);
        TLRPC.UserFull r23 = MessagesController.getInstance(this.currentAccount).getUserFull(UserConfig.getInstance(this.currentAccount).getClientUserId());
        if (r23 == null) goto L25;
        r23.flags2 &= -9;
        r23.business_away_message = null;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r23, true);
        goto L25
    L26:
        saveToCache();
        MessagesStorage r13 = MessagesStorage.getInstance(this.currentAccount);
        r13.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda22(r13, r9));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    public QuickReply findLocalReply(String r5) {
        ArrayList<QuickReply> r4 = this.localReplies;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        QuickReply r2 = r4.get(r1);
        r1 = r1 + 1;
        QuickReply r22 = r2;
        if (TextUtils.equals(r5, r22.name) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public QuickReply findReply(long r6) {
        ArrayList<QuickReply> r5 = this.replies;
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        QuickReply r2 = r5.get(r1);
        r1 = r1 + 1;
        QuickReply r22 = r2;
        if (r22.id != r6) goto L3;
        return r22;
    L7:
        return null;
    }

    public QuickReply findReply(String r5) {
        ArrayList<QuickReply> r4 = this.replies;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        QuickReply r2 = r4.get(r1);
        r1 = r1 + 1;
        QuickReply r22 = r2;
        if (TextUtils.equals(r5, r22.name) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public ArrayList<QuickReply> getFilteredReplies() {
        this.filtered.clear();
        int r0 = 0;
    L4:
        if (r0 >= this.replies.size()) goto L10;
        if (this.replies.get(r0).isSpecial() == true) goto L8;
        this.filtered.add(this.replies.get(r0));
    L8:
        r0 = r0 + 1;
        goto L4
    L10:
        return this.filtered;
    }

    public boolean hasReplies() {
        return !this.replies.isEmpty();
    }

    public boolean isNameBusy(String r1, int r2) {
        QuickReply r0 = findReply(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.id == r2) goto L10;
        return true;
    L10:
        return false;
    }

    public void load() {
        load(true, null);
    }

    public boolean processUpdate(TLRPC.Update r3, String r4, int r5) {
        if ((r3 instanceof TL_update.TL_updateQuickReplyMessage) == false) goto L7;
        ensureLoaded(new QuickRepliesController$$ExternalSyntheticLambda2(this, ((TL_update.TL_updateQuickReplyMessage) r3).message, r4, r5));
        return true;
    L7:
        if ((r3 instanceof TL_update.TL_updateQuickReplies) == false) goto L11;
        ensureLoaded(new QuickRepliesController$$ExternalSyntheticLambda3(this, r3));
        return true;
    L11:
        if ((r3 instanceof TL_update.TL_updateNewQuickReply) == false) goto L15;
        ensureLoaded(new QuickRepliesController$$ExternalSyntheticLambda4(this, r3));
        return true;
    L15:
        if ((r3 instanceof TL_update.TL_updateDeleteQuickReply) == false) goto L19;
        ensureLoaded(new QuickRepliesController$$ExternalSyntheticLambda5(this, r3));
        return true;
    L19:
        if ((r3 instanceof TL_update.TL_updateDeleteQuickReplyMessages) == false) goto L22;
        ensureLoaded(new QuickRepliesController$$ExternalSyntheticLambda6(this, r3));
        return true;
    L22:
        return false;
    }

    public void renameReply(int r3, String r4) {
        QuickReply r0 = findReply(r3);
        if (r0 != null) goto L5;
        return;
    L5:
        r0.name = r4;
        TLRPC.TL_messages_editQuickReplyShortcut r02 = new TLRPC.TL_messages_editQuickReplyShortcut();
        r02.shortcut_id = r3;
        r02.shortcut = r4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new QuickRepliesController$$ExternalSyntheticLambda9());
        saveToCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
    }

    public void reorder() {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L3:
        int r3 = this.replies.size();
        ArrayList<QuickReply> r4 = this.replies;
        if (r2 >= r3) goto L6;
        r0.add(Integer.valueOf(r4.get(r2).id));
        r2 = r2 + 1;
        goto L3
    L6:
        Collections.sort(r4, new QuickRepliesController$$ExternalSyntheticLambda18());
        int r22 = 0;
    L8:
        if (r22 >= this.replies.size()) goto L18;
        if (this.replies.get(r22).id != ((Integer) r0.get(r22)).intValue()) goto L11;
        r22 = r22 + 1;
        goto L8
    L11:
        TLRPC.TL_messages_reorderQuickReplies r02 = new TLRPC.TL_messages_reorderQuickReplies();
    L13:
        if (r1 >= this.replies.size()) goto L15;
        r02.order.add(Integer.valueOf(this.replies.get(r1).id));
        r1 = r1 + 1;
        goto L13
    L15:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new QuickRepliesController$$ExternalSyntheticLambda19());
        saveToCache();
        return;
    }

    public void sendQuickReplyTo(long r3, QuickReply r5) {
        if (r5 == null) goto L9;
        TLRPC.TL_messages_sendQuickReplyMessages r0 = new TLRPC.TL_messages_sendQuickReplyMessages();
        TLRPC.InputPeer r32 = MessagesController.getInstance(this.currentAccount).getInputPeer(r3);
        r0.peer = r32;
        if (r32 != null) goto L7;
        return;
    L7:
        r0.shortcut_id = r5.id;
        MessagesStorage r33 = MessagesStorage.getInstance(this.currentAccount);
        r33.getStorageQueue().postRunnable(new QuickRepliesController$$ExternalSyntheticLambda23(this, r33, r5, r0));
        return;
    }
}
