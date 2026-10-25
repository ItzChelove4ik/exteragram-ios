package com.exteragram.messenger.utils.chats;

import androidx.collection.LongSparseArray;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.TabCounterMode;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0002&'B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\f\u001a\u00020\u0003J\u000e\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fJp\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015J\u0006\u0010\u001f\u001a\u00020\u0011J$\u0010 \u001a\u001e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b0\bj\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b`\tH\u0002J(\u0010\"\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00132\u0006\u0010%\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/exteragram/messenger/utils/chats/FolderCounters;", _UrlKt.FRAGMENT_ENCODE_SET, "account", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "mainCount", "filterCounts", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recountScheduled", _UrlKt.FRAGMENT_ENCODE_SET, "getMainUnreadCount", "getUnreadCount", "filter", "Lorg/telegram/messenger/MessagesController$DialogFilter;", PluginsConstants.UPDATE, _UrlKt.FRAGMENT_ENCODE_SET, "filters", _UrlKt.FRAGMENT_ENCODE_SET, "usersDict", "Landroidx/collection/LongSparseArray;", "Lorg/telegram/tgnet/TLRPC$User;", "encUsersDict", "encryptedChatsByUsersCount", "Lorg/telegram/messenger/support/LongSparseIntArray;", "chatsDict", "Lorg/telegram/tgnet/TLRPC$Chat;", "mutedDialogs", "archivedDialogs", "dialogsWithMentions", "scheduleRecount", "loadForumMutes", _UrlKt.FRAGMENT_ENCODE_SET, NotificationBadge.NewHtcHomeBadger.COUNT, "dialogs", "Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;", "flags", "UnreadDialog", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FolderCounters {
    public static final Companion Companion = null;
    private static final FolderCounters[] instances = null;
    private static final Object[] lockObjects = null;
    private final int account;
    private HashMap<Integer, Integer> filterCounts;
    private int mainCount;
    private volatile boolean recountScheduled;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0007H\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "RECOUNT_DELAY_MS", _UrlKt.FRAGMENT_ENCODE_SET, "FORUM_TYPES", _UrlKt.FRAGMENT_ENCODE_SET, "instances", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/utils/chats/FolderCounters;", "[Lcom/exteragram/messenger/utils/chats/FolderCounters;", "lockObjects", "[Ljava/lang/Object;", "getInstance", "num", "recountAll", _UrlKt.FRAGMENT_ENCODE_SET, "isUnmutedOnly", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        /* renamed from: $r8$lambda$mmuf5hkB-A5cLvdcQiUTHwQwckM, reason: not valid java name */
        public static void m1776$r8$lambda$mmuf5hkBA5cLvdcQiUTHwQwckM(MessagesStorage r1) {
            r1.resetAllUnreadCounters(false);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$isUnmutedOnly(Companion r0) {
            return r0.isUnmutedOnly();
        }

        private final boolean isUnmutedOnly() {
            if (ExteraConfig.getTabCounterMode() != TabCounterMode.UNMUTED) goto L6;
            return true;
        L6:
            return false;
        }

        @JvmStatic
        public final FolderCounters getInstance(int r3) {
            FolderCounters r2 = FolderCounters.access$getInstances$cp()[r3];
            if (r2 != null) goto L16;
            Object r22 = FolderCounters.access$getLockObjects$cp()[r3];
            monitor-enter(r22);
            FolderCounters r0 = FolderCounters.access$getInstances$cp()[r3];     // Catch: Throwable -> L9
            if (r0 != null) goto L11;
            r0 = new FolderCounters(r3, null);     // Catch: Throwable -> L9
            FolderCounters.access$getInstances$cp()[r3] = r0;     // Catch: Throwable -> L9
        L11:
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r22);
            return r0;
        L9:
            th = move-exception;
            throw th;
        L16:
            return r2;
        }

        @JvmStatic
        public final void recountAll() {
            if (isUnmutedOnly() == false) goto L12;
            int r3 = 0;
        L7:
            if (r3 >= 16) goto L16;
            if (UserConfig.getInstance(r3).isClientActivated() == false) goto L11;
            MessagesStorage r0 = MessagesStorage.getInstance(r3);
            r0.getStorageQueue().postRunnable(new FolderCounters$Companion$$ExternalSyntheticLambda0(r0));
        L11:
            r3 = r3 + 1;
            goto L7
        L16:
            return;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "typeFlags", _UrlKt.FRAGMENT_ENCODE_SET, "weight", "archived", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JIIZ)V", "getId", "()J", "getTypeFlags", "()I", "getWeight", "getArchived", "()Z", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnreadDialog {
        private final boolean archived;
        private final long id;
        private final int typeFlags;
        private final int weight;

        public UnreadDialog(long r1, int r3, int r4, boolean r5) {
            this.id = r1;
            this.typeFlags = r3;
            this.weight = r4;
            this.archived = r5;
        }

        public final boolean getArchived() {
            return this.archived;
        }

        public final long getId() {
            return this.id;
        }

        public final int getTypeFlags() {
            return this.typeFlags;
        }

        public final int getWeight() {
            return this.weight;
        }
    }

    public static void $r8$lambda$70DlWAvopMmbZjuP5Rkp7m_mrV0(FolderCounters r0, int r1, HashMap r2) {
        r0.mainCount = r1;
        r0.filterCounts = r2;
    }

    public static void $r8$lambda$a7yR4wl_zPymeOD2mbHLE9R84bk(FolderCounters r1, MessagesStorage r2) {
        r1.recountScheduled = false;
        r2.resetAllUnreadCounters(false);
    }

    static {
        Companion = new Companion(null);
        instances = new FolderCounters[16];
        Object[] r1 = new Object[16];
        int r2 = 0;
    L3:
        if (r2 >= 16) goto L5;
        r1[r2] = new Object();
        r2 = r2 + 1;
        goto L3
    L5:
        lockObjects = r1;
    }

    private FolderCounters(int r1) {
        this.account = r1;
        this.filterCounts = new HashMap();
    }

    public /* synthetic */ FolderCounters(int r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ FolderCounters[] access$getInstances$cp() {
        return instances;
    }

    public static final /* synthetic */ Object[] access$getLockObjects$cp() {
        return lockObjects;
    }

    private final int count(List<UnreadDialog> r6, int r7, MessagesController.DialogFilter r8) {
        HashSet r5 = null;
        if (r8 == null) goto L7;
        ArrayList<Long> r0 = r8.alwaysShow;
        if (r0 == null) goto L7;
        HashSet r02 = CollectionsKt.toHashSet(r0);
    L8:
        if (r8 == null) goto L12;
        ArrayList<Long> r82 = r8.neverShow;
        if (r82 == null) goto L12;
        r5 = CollectionsKt.toHashSet(r82);
    L12:
        Iterator<UnreadDialog> r62 = r6.iterator();
        int r83 = 0;
    L14:
        if (r62.hasNext() == false) goto L32;
        UnreadDialog r1 = r62.next();
        if (r5 == null) goto L20;
        if (r5.contains(Long.valueOf(r1.getId())) == true) goto L14;
    L20:
        if (r02 == null) goto L25;
        if (r02.contains(Long.valueOf(r1.getId())) != true) goto L25;
    L31:
        r83 = r83 + r1.getWeight();
    L25:
        if (r1.getArchived() == false) goto L30;
        if ((MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED & r7) != 0) goto L14;
    L30:
        if ((r1.getTypeFlags() & r7) != r1.getTypeFlags()) goto L14;
    L32:
        return r83;
    L7:
        r02 = null;
        goto L8
    }

    @JvmStatic
    public static final FolderCounters getInstance(int r1) {
        return Companion.getInstance(r1);
    }

    private final HashMap<Long, Boolean> loadForumMutes() {
        MessagesStorage r0 = MessagesStorage.getInstance(this.account);
        MessagesController r10 = MessagesController.getInstance(this.account);
        HashMap<Long, Boolean> r1 = new HashMap();
        SQLiteCursor r2 = null;
        r2 = r0.getDatabase().queryFinalized("SELECT did, topic_id, unread_mentions FROM topics WHERE unread_count > 0 OR unread_mentions > 0", new Object[0]);     // Catch: Throwable -> L16 Exception -> L18
    L4:
        if (r2.next() == false) goto L23;
        long r3 = r2.longValue(0);     // Catch: Throwable -> L16 Exception -> L18
        if (Intrinsics.areEqual(r1.get(Long.valueOf(r3)), Boolean.FALSE) == true) goto L4;
        if (r0.isForum(r3, 13) == false) goto L4;
        Long r6 = Long.valueOf(r3);     // Catch: Throwable -> L16 Exception -> L18
        if (r2.intValue(2) != 0) goto L20;
        boolean r7 = true;
        if (r10.isDialogMuted(r3, r2.longValue(1)) == false) goto L20;
    L21:
        r1.put(r6, Boolean.valueOf(r7));     // Catch: Throwable -> L16 Exception -> L18
    L20:
        r7 = false;
        goto L21
    L23:
        r2.dispose();
        return r1;
    L18:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L16
        if (r2 == null) goto L28;
        r2.dispose();
    L28:
        return r1;
    L16:
        th = move-exception;
        if (r2 == null) goto L31;
        r2.dispose();
    L31:
        throw th;
    }

    public final int getMainUnreadCount() {
        if (Companion.access$isUnmutedOnly(Companion) == false) goto L7;
        return this.mainCount;
    L7:
        return MessagesStorage.getInstance(this.account).getMainUnreadCount();
    }

    public final int getUnreadCount(MessagesController.DialogFilter r2) {
        if (Companion.access$isUnmutedOnly(Companion) == false) goto L11;
        Integer r1 = this.filterCounts.get(Integer.valueOf(r2.id));
        if (r1 != null) goto L7;
        return 0;
    L7:
        return r1.intValue();
    L11:
        return r2.unreadCount;
    }

    public final void scheduleRecount() {
        if (Companion.access$isUnmutedOnly(Companion) == true) goto L5;
        return;
    L5:
        if (this.recountScheduled == true) goto L10;
        this.recountScheduled = true;
        MessagesStorage r0 = MessagesStorage.getInstance(this.account);
        r0.getStorageQueue().postRunnable(new FolderCounters$$ExternalSyntheticLambda1(this, r0), 500);
        return;
    }

    public final void update(List<? extends MessagesController.DialogFilter> r21, LongSparseArray<TLRPC.User> r22, LongSparseArray<TLRPC.User> r23, LongSparseIntArray r24, LongSparseArray<TLRPC.Chat> r25, LongSparseArray<Boolean> r26, LongSparseArray<Boolean> r27, LongSparseArray<Integer> r28) {
        if (Companion.access$isUnmutedOnly(Companion) == true) goto L5;
        return;
    L5:
        HashMap<Long, Boolean> r4 = loadForumMutes();
        ArrayList r5 = new ArrayList();
        int r6 = r22.size();
        int r8 = 0;
    L6:
        if (r8 >= r6) goto L28;
        TLRPC.User r10 = r22.valueAt(r8);
        Boolean r11 = r4.get(Long.valueOf(r10.id));
        if (r11 == null) goto L10;
        boolean r112 = r11.booleanValue();
    L11:
        if (r112 == true) goto L27;
        if (ChatObject.isUserCollapsedInCommunity(r25, r10) == false) goto L17;
        int r113 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
    L15:
        int r15 = r113;
        long r13 = r10.id;
        r5.add(new UnreadDialog(r13, r15, 1, r27.containsKey(r13)));
        goto L27
    L17:
        if (r10.bot == false) goto L20;
        r113 = MessagesController.DIALOG_FILTER_FLAG_BOTS;
        goto L15
    L20:
        if (r10.self == false) goto L22;
    L25:
        r113 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        goto L15
    L22:
        if (r10.contact == true) goto L25;
        r113 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
    L27:
        r8 = r8 + 1;
        goto L6
    L10:
        r112 = r26.containsKey(r10.id);
        goto L11
    L28:
        int r62 = r23.size();
        int r82 = 0;
    L29:
        if (r82 >= r62) goto L45;
        TLRPC.User r102 = r23.valueAt(r82);
        int r18 = r24.get(r102.id, 0);
        if (r18 == 0) goto L44;
        if (r26.containsKey(r102.id) == true) goto L44;
        if (r102.self == false) goto L38;
    L42:
        int r114 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
    L41:
        int r17 = r114;
        long r103 = r102.id;
        r5.add(new UnreadDialog(r103, r17, r18, r27.containsKey(r103)));
        goto L44
    L38:
        if (r102.contact == true) goto L42;
        r114 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
    L44:
        r82 = r82 + 1;
        goto L29
    L45:
        int r63 = r25.size();
        int r83 = 0;
    L46:
        if (r83 >= r63) goto L75;
        TLRPC.Chat r9 = r25.valueAt(r83);
        if (r9 != null) goto L51;
    L74:
        r83 = r83 + 1;
        goto L46
    L51:
        if (ChatObject.isCommunity(r9) == true) goto L74;
        long r132 = -r9.id;
        Boolean r104 = r4.get(Long.valueOf(r132));
        if (r104 == null) goto L56;
        boolean r115 = r104.booleanValue();
    L62:
        if (r115 == true) goto L74;
        if (ChatObject.isChatCollapsedInCommunity(r25, r9) == false) goto L68;
        int r92 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
    L73:
        r5.add(new UnreadDialog(r132, r92, 1, r27.containsKey(r132)));
        goto L74
    L68:
        if (ChatObject.isChannel(r9) == true) goto L70;
    L72:
        r92 = MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        goto L73
    L70:
        if (r9.megagroup == true) goto L72;
        r92 = MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        goto L73
    L56:
        if (r26.containsKey(r132) == false) goto L61;
        if (r28.containsKey(r132) == true) goto L61;
        r115 = true;
    L61:
        r115 = false;
        goto L62
    L75:
        int r1 = count(r5, MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS | MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED, null);
        HashMap r2 = new HashMap();
        Iterator<? extends MessagesController.DialogFilter> r3 = r21.iterator();
    L77:
        if (r3.hasNext() == false) goto L79;
        MessagesController.DialogFilter r42 = r3.next();
        r2.put(Integer.valueOf(r42.id), Integer.valueOf(count(r5, r42.flags, r42)));
        goto L77
    L79:
        AndroidUtilities.runOnUIThread(new FolderCounters$$ExternalSyntheticLambda0(this, r1, r2));
    }
}
