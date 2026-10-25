package com.exteragram.messenger.feed;

import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import com.exteragram.messenger.feed.FeedConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
final class FeedTimelineLoader {
    private final AtomicInteger channelCacheEpoch;
    private volatile ChannelSet channelSetCache;
    private final int currentAccount;

    public static final class ChannelEnumeration {
        int cacheEpoch;
        final ArrayList<TLRPC.Chat> channels;
        int configGeneration;
        boolean failed;
        boolean hasChannels;
        final ArrayList<ChannelSnapshot> included;

        public ChannelEnumeration() {
            this.included = new ArrayList();
            this.channels = new ArrayList();
        }
    }

    public static final class ChannelSet {
        final ArrayList<TLRPC.Chat> channels;
        final int configGen;
        boolean failed;
        boolean hasChannels;
        final ArrayList<long[]> includedRows;
        final int sessionGen;

        public ChannelSet(int r2, int r3) {
            this.includedRows = new ArrayList();
            this.channels = new ArrayList();
            this.sessionGen = r2;
            this.configGen = r3;
        }
    }

    public static final class ChannelSnapshot {
        int depthDate;
        int depthMid;
        final long dialogId;
        boolean hasCached;
        boolean hasHole;
        int holeEnd;
        boolean incomplete;
        boolean localStartReached;
        final int readInboxMax;
        final int topMessage;
        final int unreadCount;

        public ChannelSnapshot(long r1, int r3, int r4, int r5) {
            this.dialogId = r1;
            this.readInboxMax = r3;
            this.unreadCount = r4;
            this.topMessage = r5;
        }
    }

    public static final class Cursor {
        int date;
        int mid;
        long uid;

        public Cursor() {
        }

        public boolean isEmpty() {
            if (this.date != 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void set(int r1, long r2, int r4) {
            this.date = r1;
            this.uid = r2;
            this.mid = r4;
        }
    }

    /* loaded from: classes4.dex */
    public static final class NewerPage {
        final ArrayList<TLRPC.Chat> chats;
        boolean failed;
        final Cursor first;
        boolean hasMore;
        final ArrayList<TLRPC.Message> messages;
        final ArrayList<TLRPC.User> users;

        public NewerPage() {
            this.messages = new ArrayList();
            this.users = new ArrayList();
            this.chats = new ArrayList();
            this.first = new Cursor();
        }
    }

    /* loaded from: classes4.dex */
    public static final class OlderPage {
        final ArrayList<long[]> backfillCandidates;
        final ArrayList<TLRPC.Chat> chats;
        boolean failed;
        final Cursor first;
        boolean hasIncomplete;
        final Cursor last;
        int lastChunkRowCount;
        final ArrayList<TLRPC.Message> messages;
        final ArrayList<TLRPC.User> users;

        public OlderPage() {
            this.messages = new ArrayList();
            this.users = new ArrayList();
            this.chats = new ArrayList();
            this.backfillCandidates = new ArrayList();
            this.last = new Cursor();
            this.first = new Cursor();
        }
    }

    /* loaded from: classes4.dex */
    public static final class WindowPage {
        final ArrayList<TLRPC.Chat> chats;
        boolean failed;
        final ArrayList<TLRPC.Message> messages;
        boolean truncated;
        final ArrayList<TLRPC.User> users;

        public WindowPage() {
            this.messages = new ArrayList();
            this.users = new ArrayList();
            this.chats = new ArrayList();
        }
    }

    /* renamed from: $r8$lambda$4LSIuD87ztyDn-jvK-p79_BpgdA, reason: not valid java name */
    public static /* synthetic */ int m1422$r8$lambda$4LSIuD87ztyDnjvKp79_BpgdA(long[] r5, long[] r6) {
        return Long.compare(r6[2], r5[2]);
    }

    public FeedTimelineLoader(int r2) {
        this.channelCacheEpoch = new AtomicInteger();
        this.currentAccount = r2;
    }

    private static void appendCursorBound(StringBuilder r2, Cursor r3, boolean r4, boolean r5) {
        if (r4 == false) goto L4;
        String r42 = "<";
    L5:
        r2.append(" AND (date ");
        r2.append(r42);
        r2.append(' ');
        r2.append(r3.date);
        r2.append(" OR date = ");
        r2.append(r3.date);
        r2.append(" AND (uid ");
        r2.append(r42);
        r2.append(' ');
        r2.append(r3.uid);
        r2.append(" OR uid = ");
        r2.append(r3.uid);
        r2.append(" AND mid ");
        r2.append(r42);
        if (r5 == false) goto L8;
        String r43 = "= ";
    L9:
        r2.append(r43);
        r2.append(r3.mid);
        r2.append("))");
        return;
    L8:
        r43 = " ";
        goto L9
    L4:
        r42 = ">";
        goto L5
    }

    private ChannelSet buildChannelSet(boolean r20, HashSet<Long> r21, int r22, int r23) {
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        ChannelSet r1 = new ChannelSet(r22, r23);
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        SQLiteCursor r5 = r0.getDatabase().queryFinalized("SELECT did, inbox_max, unread_count, last_mid, folder_id FROM dialogs WHERE did < 0", new Object[0]);     // Catch: Exception -> L10
    L5:
        if (r5.next() == false) goto L12;
        long r11 = r5.longValue(0);     // Catch: Exception -> L10
        if (DialogObject.isChatDialog(r11) == false) goto L5;
        r2.add(new long[]{r11, r5.intValue(1), r5.intValue(2), r5.intValue(3), r5.intValue(4)});     // Catch: Exception -> L10
        r3.add(Long.valueOf(-r11));     // Catch: Exception -> L10
        goto L5
    L12:
        r5.dispose();     // Catch: Exception -> L10
        if (r2.isEmpty() == true) goto L37;
        ArrayList<TLRPC.Chat> r52 = new ArrayList();
        r0.getChatsInternal(TextUtils.join(",", r3), r52);     // Catch: Exception -> L38
        LongSparseArray r02 = new LongSparseArray();
        int r32 = 0;
    L20:
        if (r32 >= r52.size()) goto L22;
        r02.put(r52.get(r32).id, r52.get(r32));
        r32 = r32 + 1;
        goto L20
    L22:
        int r33 = 0;
    L24:
        if (r33 >= r2.size()) goto L37;
        long[] r53 = (long[]) r2.get(r33);
        long r9 = r53[0];
        TLRPC.Chat r6 = (TLRPC.Chat) r02.get(-r9);
        if (FeedController.isEligibleChannel(r6) == true) goto L29;
    L36:
        r33 = r33 + 1;
        goto L24
    L29:
        if (r53[4] != 1) goto L32;
        if (r20 == false) goto L36;
    L32:
        r1.hasChannels = true;
        r1.channels.add(r6);
        if (r21.contains(Long.valueOf(r9)) == true) goto L36;
        r1.includedRows.add(new long[]{r9, r53[1], r53[2], r53[3]});
    L38:
        e = move-exception;
        FileLog.e(e);
        r1.failed = true;
        return r1;
    L37:
        return r1;
    L10:
        e = move-exception;
        FileLog.e(e);
        r1.failed = true;
        return r1;
    }

    private static void clusterGroupedMessages(ArrayList<TLRPC.Message> r10) {
        if (r10.size() < 3) goto L38;
        HashMap r0 = new HashMap();
        int r1 = 0;
        int r2 = 0;
        boolean r3 = false;
    L7:
        if (r2 >= r10.size()) goto L17;
        long r7 = r10.get(r2).grouped_id;
        if (r7 == 0) goto L16;
        ArrayList r4 = (ArrayList) r0.get(Long.valueOf(r7));
        if (r4 != null) goto L14;
        r4 = new ArrayList();
        r0.put(Long.valueOf(r7), r4);
    L15:
        r4.add(r10.get(r2));
        goto L16
    L14:
        r3 = true;
    L16:
        r2 = r2 + 1;
        goto L7
    L17:
        if (r3 == true) goto L19;
        return;
    L19:
        ArrayList r22 = new ArrayList(r10.size());
        HashSet r32 = new HashSet();
    L21:
        if (r1 >= r10.size()) goto L29;
        TLRPC.Message r42 = r10.get(r1);
        long r72 = r42.grouped_id;
        if (r72 != 0) goto L26;
        r22.add(r42);
    L28:
        r1 = r1 + 1;
        goto L21
    L26:
        if (r32.add(Long.valueOf(r72)) == false) goto L28;
        r22.addAll((Collection) r0.get(Long.valueOf(r72)));
        goto L28
    L29:
        r10.clear();
        r10.addAll(r22);
        return;
    }

    private static int compareDesc(Cursor r7, Cursor r8) {
        int r0 = r7.date;
        int r1 = r8.date;
        if (r0 == r1) goto L7;
        if (r0 <= r1) goto L6;
        return -1;
    L6:
        return 1;
    L7:
        long r02 = r7.uid;
        long r4 = r8.uid;
        if (r02 == r4) goto L14;
        if (r02 <= r4) goto L12;
        return -1;
    L12:
        return 1;
    L14:
        return -Integer.compare(r7.mid, r8.mid);
    }

    private void completeTrailingAlbum(MessagesStorage r7, OlderPage r8, ArrayList<Long> r9, ArrayList<Long> r10) {
        if (r8.messages.isEmpty() == true) goto L29;
        TLRPC.Message r0 = r8.messages.get(r0.size() - 1);
        if (r0.grouped_id != 0) goto L8;
        return;
    L8:
        SQLiteCursor r72 = r7.getDatabase().queryFinalized("SELECT data, mid, date, uid FROM messages_v2 WHERE uid = " + r0.dialog_id + " AND mid > 0 AND mid < " + r0.id + " ORDER BY date DESC, mid DESC LIMIT 9", new Object[0]);
    L24:
        if (r72.next() == false) goto L20;
        TLRPC.Message r1 = readMessage(r72);     // Catch: Throwable -> L18
        if (r1 == null) goto L20;
        if (r1.grouped_id != r0.grouped_id) goto L20;
        r8.messages.add(r1);     // Catch: Throwable -> L18
        MessagesStorage.addUsersAndChatsFromMessage(r1, r9, r10, null);     // Catch: Throwable -> L18
    L20:
        r72.dispose();
        return;
    L18:
        th = move-exception;
        r72.dispose();
        throw th;
    }

    private Cursor findUnreadBoundary(MessagesStorage r9, ArrayList<ChannelSnapshot> r10, int r11) {
        StringBuilder r0 = new StringBuilder();
        Cursor r1 = null;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r3 >= r10.size()) goto L26;
        ChannelSnapshot r5 = r10.get(r3);
        if (r5.topMessage > r5.readInboxMax) goto L10;
        if (r5.unreadCount > 0) goto L10;
    L13:
        if (r4 <= 0) goto L25;
        if (r4 != 64) goto L17;
    L18:
        Cursor r42 = queryUnreadBoundary(r9, r0, r11);
        if (r42 == null) goto L24;
        if (r1 != null) goto L22;
    L23:
        r1 = r42;
        goto L24
    L22:
        if (compareDesc(r42, r1) > 0) goto L23;
    L24:
        r0.setLength(0);
        r4 = 0;
        goto L25
    L17:
        if (r3 == (r10.size() - 1)) goto L18;
    L25:
        r3 = r3 + 1;
    L10:
        if (r0.length() <= 0) goto L12;
        r0.append(" OR ");
    L12:
        r0.append("uid = ");
        r0.append(r5.dialogId);
        r0.append(" AND mid > ");
        r0.append(r5.readInboxMax);
        r4 = r4 + 1;
        goto L13
    L26:
        return r1;
    }

    private static void loadChannelDepths(MessagesStorage r11, ArrayList<ChannelSnapshot> r12) {
        LongSparseArray r0 = new LongSparseArray(r12.size());
        int r2 = 0;
    L4:
        if (r2 >= r12.size()) goto L6;
        ChannelSnapshot r3 = r12.get(r2);
        r3.depthMid = 0;
        r3.depthDate = Integer.MAX_VALUE;
        r3.hasCached = false;
        r3.localStartReached = false;
        r0.put(r3.dialogId, r3);
        r2 = r2 + 1;
        goto L4
    L6:
        int r22 = 0;
    L8:
        if (r22 >= r12.size()) goto L28;
        int r32 = r22 + 64;
        int r5 = Math.min(r32, r12.size());
        StringBuilder r6 = new StringBuilder();
    L10:
        if (r22 >= r5) goto L15;
        if (r6.length() <= 0) goto L14;
        r6.append(" UNION ALL ");
    L14:
        ChannelSnapshot r7 = r12.get(r22);
        int r8 = Math.max(r7.holeEnd, 1);
        r6.append("SELECT uid, mid, date FROM (SELECT uid, mid, date FROM messages_v2 WHERE uid = ");
        r6.append(r7.dialogId);
        r6.append(" AND mid >= ");
        r6.append(r8);
        r6.append(" ORDER BY date ASC, mid ASC LIMIT 1)");
        r22 = r22 + 1;
        goto L10
    L15:
        SQLiteCursor r23 = r11.getDatabase().queryFinalized(r6.toString(), new Object[0]);
    L39:
        if (r23.next() == false) goto L25;
        ChannelSnapshot r52 = (ChannelSnapshot) r0.get(r23.longValue(0));     // Catch: Throwable -> L23
        if (r52 == null) goto L39;
        r52.depthMid = r23.intValue(1);     // Catch: Throwable -> L23
        r52.depthDate = r23.intValue(2);     // Catch: Throwable -> L23
        r52.hasCached = true;     // Catch: Throwable -> L23
        goto L39
    L25:
        r23.dispose();
        r22 = r32;
    L23:
        th = move-exception;
        r23.dispose();
        throw th;
    L28:
        int r112 = 0;
    L30:
        if (r112 >= r12.size()) goto L38;
        ChannelSnapshot r02 = r12.get(r112);
        if (r02.hasHole == false) goto L34;
    L36:
        boolean r24 = false;
    L37:
        r02.localStartReached = r24;
        r112 = r112 + 1;
        goto L30
    L34:
        if (r02.hasCached == false) goto L36;
        r24 = true;
        goto L37
    }

    private int loadChunk(MessagesStorage r6, String r7, int r8, OlderPage r9, ArrayList<Long> r10, ArrayList<Long> r11) {
        StringBuilder r0 = new StringBuilder("SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN (");
        r0.append(r7);
        r0.append(") AND mid > 0");
        if (r8 <= 0) goto L5;
        r0.append(" AND date >= ");
        r0.append(r8);
    L5:
        int r82 = 0;
        if (r9.last.isEmpty() == true) goto L8;
        appendCursorBound(r0, r9.last, true, false);
    L8:
        r0.append(" ORDER BY date DESC, uid DESC, mid DESC LIMIT ");
        r0.append(30);
        SQLiteCursor r62 = r6.getDatabase().queryFinalized(r0.toString(), new Object[0]);
    L10:
        if (r62.next() == false) goto L17;
        r82 = r82 + 1;
        r9.last.set(r62.intValue(2), r62.longValue(3), r62.intValue(1));
        if (r9.first.isEmpty() == false) goto L14;
        Cursor r72 = r9.first;
        Cursor r02 = r9.last;
        r72.set(r02.date, r02.uid, r02.mid);
    L14:
        TLRPC.Message r73 = readMessage(r62);
        if (r73 == null) goto L10;
        r9.messages.add(r73);
        MessagesStorage.addUsersAndChatsFromMessage(r73, r10, r11, null);
        goto L10
    L17:
        r62.dispose();
        return r82;
    }

    private Cursor queryUnreadBoundary(MessagesStorage r3, StringBuilder r4, int r5) {
        StringBuilder r2 = new StringBuilder("SELECT date, uid, mid FROM messages_v2 WHERE mid > 0 AND (");
        r2.append(r4);
        r2.append(")");
        if (r5 <= 0) goto L5;
        r2.append(" AND date >= ");
        r2.append(r5);
    L5:
        r2.append(" ORDER BY date ASC, uid ASC, mid ASC LIMIT 1");
        SQLiteCursor r22 = r3.getDatabase().queryFinalized(r2.toString(), new Object[0]);     // Catch: Exception -> L17
    L12:
        th = move-exception;
        r22.dispose();     // Catch: Exception -> L17
        throw th;     // Catch: Exception -> L17
    L8:
        if (r22.next() == false) goto L14;
        Cursor r32 = new Cursor();     // Catch: Throwable -> L12
        r32.set(r22.intValue(0), r22.longValue(1), r22.intValue(2));     // Catch: Throwable -> L12
        r22.dispose();     // Catch: Exception -> L17
        return r32;
    L14:
        r22.dispose();     // Catch: Exception -> L17
        return null;
    L17:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private TLRPC.Message readMessage(SQLiteCursor r6) {
        NativeByteBuffer r1 = r6.byteBufferValue(0);
        if (r1 != null) goto L5;
        return null;
    L5:
        TLRPC.Message r0 = TLRPC.Message.TLdeserialize(r1, r1.readInt32(false), false);
        if (r0 != null) goto L9;
        r1.reuse();
        return null;
    L9:
        r0.readAttachPath(r1, UserConfig.getInstance(this.currentAccount).clientUserId);
        r1.reuse();
        if ((r0 instanceof TLRPC.TL_messageEmpty) == false) goto L12;
    L16:
        return null;
    L12:
        if (r0.action != null) goto L16;
        r0.id = r6.intValue(1);
        r0.date = r6.intValue(2);
        r0.dialog_id = r6.longValue(3);
        return r0;
    }

    public ChannelEnumeration enumerateChannels(FeedConfig r17, int r18, boolean r19) {
        boolean r3 = r19;
        int r4 = 0;
    L3:
        monitor-enter(this);
        ChannelSet r5 = this.channelSetCache;     // Catch: Throwable -> L45
        int r6 = this.channelCacheEpoch.get();     // Catch: Throwable -> L45
        monitor-exit(this);     // Catch: Throwable -> L45
        FeedConfig.Snapshot r7 = r17.snapshot();
        int r8 = r7.getGeneration();
        if (r3 == true) goto L13;
        if (r5 == null) goto L13;
        if (r5.sessionGen != r18) goto L13;
        if (r5.configGen != r8) goto L13;
    L26:
        if (r5.failed == true) goto L40;
        if (r4 >= 3) goto L40;
        monitor-enter(this);
    L34:
        th = move-exception;
        throw th;
    L31:
        if (r6 == this.channelCacheEpoch.get()) goto L32;
        monitor-exit(this);     // Catch: Throwable -> L34
        r4 = r4 + 1;     // Catch: Throwable -> L34
        r3 = true;
        goto L3
    L32:
        monitor-exit(this);     // Catch: Throwable -> L34
    L40:
        ChannelEnumeration r0 = new ChannelEnumeration();
        r0.hasChannels = r5.hasChannels;
        r0.failed = r5.failed;
        r0.configGeneration = r5.configGen;
        r0.cacheEpoch = r6;
        r0.channels.addAll(r5.channels);
        int r1 = 0;
    L42:
        if (r1 >= r5.includedRows.size()) goto L44;
        long[] r32 = r5.includedRows.get(r1);
        r0.included.add(new ChannelSnapshot(r32[0], (int) r32[1], (int) r32[2], (int) r32[3]));
        r1 = r1 + 1;
        goto L42
    L44:
        return r0;
    L13:
        r5 = buildChannelSet(r7.getIncludeArchived(), new HashSet(r7.getExcludedChannels()), r18, r8);
        if (r5.failed == true) goto L26;
        monitor-enter(this);
        if (r6 != this.channelCacheEpoch.get()) goto L21;
        this.channelSetCache = r5;     // Catch: Throwable -> L19
    L21:
        monitor-exit(this);     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        throw th;
    L45:
        th = move-exception;
        throw th;
    }

    public synchronized int getChannelCacheEpoch() {
        monitor-enter(this);
        int r0 = this.channelCacheEpoch.get();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void invalidateChannelCache() {
        monitor-enter(this);
        this.channelCacheEpoch.incrementAndGet();     // Catch: Throwable -> L6
        this.channelSetCache = null;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isEnumerationCurrent(ChannelEnumeration r2) {
        monitor-enter(this);
        if (r2 != null) goto L13;
    L10:
        boolean r22 = false;
    L11:
        monitor-exit(this);
        return r22;
    L13:
        if (r2.cacheEpoch != this.channelCacheEpoch.get()) goto L10;
        r22 = true;
    L7:
        th = move-exception;
        throw th;
    }

    public WindowPage loadChannelWindow(ArrayList<Long> r9, Cursor r10, Cursor r11) {
        WindowPage r1 = new WindowPage();
        if (r9.isEmpty() == false) goto L5;
    L30:
        return r1;
    L5:
        if (r10.isEmpty() == true) goto L30;
        if (r11.isEmpty() == true) goto L30;
        MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);     // Catch: Exception -> L16
        ArrayList<Long> r4 = new ArrayList();     // Catch: Exception -> L16
        ArrayList r5 = new ArrayList();     // Catch: Exception -> L16
        StringBuilder r6 = new StringBuilder("SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN (");     // Catch: Exception -> L16
        r6.append(TextUtils.join(",", r9));     // Catch: Exception -> L16
        r6.append(") AND mid > 0");     // Catch: Exception -> L16
        appendCursorBound(r6, r10, true, true);     // Catch: Exception -> L16
        int r92 = 0;
        appendCursorBound(r6, r11, false, true);     // Catch: Exception -> L16
        r6.append(" ORDER BY date DESC, uid DESC, mid DESC LIMIT ");     // Catch: Exception -> L16
        r6.append(501);     // Catch: Exception -> L16
        SQLiteCursor r102 = r3.getDatabase().queryFinalized(r6.toString(), new Object[0]);     // Catch: Exception -> L16
    L12:
        if (r102.next() == false) goto L21;
        r92 = r92 + 1;     // Catch: Exception -> L16
        if (r92 > 500) goto L15;
        TLRPC.Message r112 = readMessage(r102);     // Catch: Exception -> L16
        if (r112 == null) goto L12;
        r1.messages.add(r112);     // Catch: Exception -> L16
        MessagesStorage.addUsersAndChatsFromMessage(r112, r4, r5, null);     // Catch: Exception -> L16
        goto L12
    L15:
        r1.truncated = true;     // Catch: Exception -> L16
    L21:
        r102.dispose();     // Catch: Exception -> L16
        if (r4.isEmpty() == true) goto L25;
        r3.getUsersInternal(r4, r1.users);     // Catch: Exception -> L16
    L25:
        if (r5.isEmpty() == true) goto L29;
        r3.getChatsInternal(TextUtils.join(",", r5), r1.chats);     // Catch: Exception -> L16
    L29:
        clusterGroupedMessages(r1.messages);
    L16:
        e = move-exception;
        FileLog.e(e);
        r1.failed = true;
        r1.messages.clear();
        r1.users.clear();
        r1.chats.clear();
        goto L29
    }

    public NewerPage loadNewerPage(ArrayList<ChannelSnapshot> r14, Cursor r15) {
        NewerPage r1 = new NewerPage();
        r1.first.set(r15.date, r15.uid, r15.mid);
        ArrayList r3 = new ArrayList(r14.size());     // Catch: Exception -> L6
        int r4 = r14.size();     // Catch: Exception -> L6
        boolean r5 = false;
        int r6 = 0;
    L4:
        if (r6 >= r4) goto L8;
        ChannelSnapshot r7 = r14.get(r6);     // Catch: Exception -> L6
        r6 = r6 + 1;     // Catch: Exception -> L6
        r3.add(Long.valueOf(r7.dialogId));     // Catch: Exception -> L6
        goto L4
    L8:
        MessagesStorage r142 = MessagesStorage.getInstance(this.currentAccount);     // Catch: Exception -> L6
        ArrayList<Long> r42 = new ArrayList();     // Catch: Exception -> L6
        ArrayList r62 = new ArrayList();     // Catch: Exception -> L6
        StringBuilder r72 = new StringBuilder("SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN (");     // Catch: Exception -> L6
        r72.append(TextUtils.join(",", r3));     // Catch: Exception -> L6
        r72.append(") AND mid > 0");     // Catch: Exception -> L6
        appendCursorBound(r72, r15, false, false);     // Catch: Exception -> L6
        r72.append(" ORDER BY date ASC, uid ASC, mid ASC LIMIT ");     // Catch: Exception -> L6
        r72.append(50);     // Catch: Exception -> L6
        SQLiteCursor r32 = r142.getDatabase().queryFinalized(r72.toString(), new Object[0]);     // Catch: Exception -> L6
        int r73 = 0;
    L10:
        if (r32.next() == false) goto L14;
        r73 = r73 + 1;     // Catch: Exception -> L6
        r1.first.set(r32.intValue(2), r32.longValue(3), r32.intValue(1));     // Catch: Exception -> L6
        TLRPC.Message r8 = readMessage(r32);     // Catch: Exception -> L6
        if (r8 == null) goto L10;
        r1.messages.add(r8);     // Catch: Exception -> L6
        MessagesStorage.addUsersAndChatsFromMessage(r8, r42, r62, null);     // Catch: Exception -> L6
        goto L10
    L14:
        r32.dispose();     // Catch: Exception -> L6
        if (r73 != 50) goto L17;
        r5 = true;
    L17:
        r1.hasMore = r5;     // Catch: Exception -> L6
        if (r42.isEmpty() == true) goto L21;
        r142.getUsersInternal(r42, r1.users);     // Catch: Exception -> L6
    L21:
        if (r62.isEmpty() == true) goto L25;
        r142.getChatsInternal(TextUtils.join(",", r62), r1.chats);     // Catch: Exception -> L6
    L25:
        clusterGroupedMessages(r1.messages);
        return r1;
    L6:
        e = move-exception;
        FileLog.e(e);
        r1.failed = true;
        goto L25
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OlderPage loadOlderPage(ArrayList<ChannelSnapshot> r22, Cursor r23, HashSet<Long> r24) {
        OlderPage r4 = new OlderPage();
        boolean r3 = r23.isEmpty();
        r4.last.set(r23.date, r23.uid, r23.mid);
        boolean r8 = true;
        ArrayList r9 = new ArrayList(r22.size());     // Catch: Exception -> L6
        int r2 = r22.size();     // Catch: Exception -> L6
        int r10 = 0;
        int r5 = 0;
    L4:
        if (r5 >= r2) goto L8;
        ChannelSnapshot r6 = r22.get(r5);     // Catch: Exception -> L6
        r5 = r5 + 1;     // Catch: Exception -> L6
        r9.add(Long.valueOf(r6.dialogId));     // Catch: Exception -> L6
        goto L4
    L8:
        String r25 = TextUtils.join(",", r9);     // Catch: Exception -> L6
        MessagesStorage r52 = MessagesStorage.getInstance(this.currentAccount);     // Catch: Exception -> L6
        HashMap r62 = new HashMap();     // Catch: Exception -> L6
        SQLiteCursor r11 = r52.getDatabase().queryFinalized("SELECT uid, max(end) FROM messages_holes WHERE uid IN (" + r25 + ") GROUP BY uid", new Object[0]);     // Catch: Exception -> L6
    L10:
        if (r11.next() == false) goto L12;
        r62.put(Long.valueOf(r11.longValue(0)), Integer.valueOf(r11.intValue(1)));     // Catch: Exception -> L6
        goto L10
    L12:
        r11.dispose();     // Catch: Exception -> L6
        int r112 = r22.size();     // Catch: Exception -> L6
        int r12 = 0;
    L13:
        if (r12 >= r112) goto L23;
        ChannelSnapshot r13 = r22.get(r12);     // Catch: Exception -> L6
        r12 = r12 + 1;     // Catch: Exception -> L6
        ChannelSnapshot r132 = r13;     // Catch: Exception -> L6
        Integer r14 = (Integer) r62.get(Long.valueOf(r132.dialogId));     // Catch: Exception -> L6
        if (r14 == null) goto L17;
        boolean r15 = true;
    L18:
        r132.hasHole = r15;     // Catch: Exception -> L6
        if (r15 == false) goto L21;
        int r142 = r14.intValue();     // Catch: Exception -> L6
    L22:
        r132.holeEnd = r142;     // Catch: Exception -> L6
        goto L13
    L21:
        r142 = 0;
        goto L22
    L17:
        r15 = false;
        goto L18
    L23:
        loadChannelDepths(r52, r22);     // Catch: Exception -> L6
        int r63 = r22.size();     // Catch: Exception -> L6
        boolean r113 = r3;
        int r32 = 0;
        int r122 = 0;
    L24:
        if (r122 >= r63) goto L48;
        ChannelSnapshot r133 = r22.get(r122);     // Catch: Exception -> L6
        r122 = r122 + 1;     // Catch: Exception -> L6
        ChannelSnapshot r134 = r133;     // Catch: Exception -> L6
        if (r134.localStartReached == true) goto L31;
        if (r24.contains(Long.valueOf(r134.dialogId)) == true) goto L31;
        boolean r143 = r8;
    L32:
        r134.incomplete = r143;     // Catch: Exception -> L6
        if (r143 == 0) goto L46;
        r4.hasIncomplete = r8;     // Catch: Exception -> L6
        int r33 = Math.max(r32, r134.depthDate);     // Catch: Exception -> L6
        if (r134.hasCached == false) goto L37;
        int r232 = r10;
        boolean r16 = r113;
        long r102 = r134.depthMid;     // Catch: Exception -> L6
    L41:
        ArrayList<long[]> r144 = r4.backfillCandidates;     // Catch: Exception -> L6
        boolean r17 = r8;
        ArrayList r18 = r9;
        long r82 = r134.dialogId;     // Catch: Exception -> L44
        String r19 = r25;
        long r26 = r134.depthDate;     // Catch: Exception -> L44
        long[] r135 = new long[3];     // Catch: Exception -> L44
        r135[r232] = r82;     // Catch: Exception -> L44
        r135[r17 ? 1 : 0] = r102;     // Catch: Exception -> L44
        r135[2] = r26;     // Catch: Exception -> L44
        r144.add(r135);     // Catch: Exception -> L44
        r32 = r33;
    L47:
        r10 = r232;
        r113 = r16;
        r8 = r17;
        r9 = r18;
        r25 = r19;
    L44:
        e = e;
    L77:
        FileLog.e(e);
        r4.failed = r17;
    L78:
        clusterGroupedMessages(r4.messages);
        return r4;
    L37:
        r232 = r10;
        r16 = r113;
        int r103 = Math.max(r134.holeEnd, r134.topMessage);     // Catch: Exception -> L6
        if (r103 <= 0) goto L40;
        r102 = r103 + 1;     // Catch: Exception -> L6
        goto L41
    L40:
        r102 = 0;
        goto L41
    L46:
        r19 = r25;
        r17 = r8;
        r18 = r9;
        r232 = r10;
        r16 = r113;
    L31:
        r143 = r10;
        goto L32
    L48:
        String r192 = r25;
        r17 = r8;
        ArrayList r182 = r9;
        int r233 = r10;
        boolean r162 = r113;
        r4.backfillCandidates.sort(new FeedTimelineLoader$$ExternalSyntheticLambda0());     // Catch: Exception -> L44
        if (r32 != Integer.MAX_VALUE) goto L51;
        return r4;
    L51:
        if (r162 == false) goto L54;
        Cursor r1 = findUnreadBoundary(r52, r22, r32);     // Catch: Exception -> L44
    L53:
        Cursor r83 = r1;
        ArrayList<Long> r53 = new ArrayList();     // Catch: Exception -> L44
        ArrayList<Long> r64 = new ArrayList();     // Catch: Exception -> L44
        int r92 = r233;
    L56:
        int r104 = loadChunk(r52, r192, r32, r4, r53, r64);     // Catch: Exception -> L44
        r4.lastChunkRowCount = r104;     // Catch: Exception -> L44
        r92 = r92 + r104;     // Catch: Exception -> L44
        if (r104 < 30) goto L64;
        if (r83 == null) goto L64;
        if (r92 >= 200) goto L64;
        if (compareDesc(r4.last, r83) < 0) goto L56;
    L64:
        completeTrailingAlbum(r52, r4, r53, r64);     // Catch: Exception -> L44
        int r0 = r182.size();     // Catch: Exception -> L44
        int r105 = r233;
    L65:
        if (r105 >= r0) goto L71;
        ArrayList r27 = r182;
        Object r34 = r27.get(r105);     // Catch: Exception -> L44
        r105 = r105 + 1;     // Catch: Exception -> L44
        long r84 = -((Long) r34).longValue();
        if (r64.contains(Long.valueOf(r84)) == true) goto L69;
        r64.add(Long.valueOf(r84));     // Catch: Exception -> L44
    L69:
        r182 = r27;
        goto L65
    L71:
        if (r53.isEmpty() == true) goto L74;
        r52.getUsersInternal(r53, r4.users);     // Catch: Exception -> L44
    L74:
        if (r64.isEmpty() == true) goto L78;
        r52.getChatsInternal(TextUtils.join(",", r64), r4.chats);     // Catch: Exception -> L44
        goto L78
    L54:
        r1 = null;
    L6:
        e = e;
        r17 = r8;
        goto L77
    }
}
