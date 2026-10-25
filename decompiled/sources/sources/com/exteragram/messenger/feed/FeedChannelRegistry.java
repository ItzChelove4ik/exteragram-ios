package com.exteragram.messenger.feed;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class FeedChannelRegistry implements NotificationCenter.NotificationCenterDelegate {
    private static final FeedChannelRegistry[] instances = null;
    private static final Object[] locks = null;
    private boolean built;
    private final HashSet<Long> channelIds;
    public final int currentAccount;
    private final ArrayList<Listener> listeners;
    private final Runnable rebuildRunnable;
    private boolean rebuildScheduled;

    public interface Listener {
        void onFeedChannelsChanged(HashSet<Long> r1, HashSet<Long> r2);
    }

    public static /* synthetic */ void $r8$lambda$atevc_fqLZJXqPER1VHQLfFbTL0(FeedChannelRegistry r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$yE63IJxsqBGB5Hgb4Xj4umWcTN0(FeedChannelRegistry r0, int r1) {
        r0.lambda$new$1(r1);
    }

    static {
        instances = new FeedChannelRegistry[16];
        locks = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        locks[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private FeedChannelRegistry(int r2) {
        this.channelIds = new HashSet();
        this.listeners = new ArrayList();
        this.rebuildRunnable = new FeedChannelRegistry$$ExternalSyntheticLambda0(this);
        this.currentAccount = r2;
        AndroidUtilities.runOnUIThread(new FeedChannelRegistry$$ExternalSyntheticLambda1(this, r2));
    }

    private void ensureBuilt() {
        if (this.built == true) goto L6;
        this.built = true;
        rebuild(false);
        return;
    }

    public static FeedChannelRegistry getInstance(int r3) {
        FeedChannelRegistry[] r0 = instances;
        FeedChannelRegistry r1 = r0[r3];
        if (r1 != null) goto L15;
        Object r2 = locks[r3];
        monitor-enter(r2);
        FeedChannelRegistry r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new FeedChannelRegistry(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r2);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r1;
    }

    private /* synthetic */ void lambda$new$0() {
        this.rebuildScheduled = false;
        rebuild(true);
    }

    private /* synthetic */ void lambda$new$1(int r2) {
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.dialogsNeedReload);
    }

    private void rebuild(boolean r8) {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        LongSparseArray<TLRPC.Dialog> r1 = r0.dialogs_dict;
        HashSet r2 = new HashSet();
        int r3 = 0;
    L4:
        if (r3 >= r1.size()) goto L14;
        TLRPC.Dialog r4 = r1.valueAt(r3);
        if (r4 == null) goto L13;
        if (DialogObject.isChatDialog(r4.id) == false) goto L13;
        if (FeedController.isEligibleChannel(r0.getChat(Long.valueOf(-r4.id))) == false) goto L13;
        r2.add(Long.valueOf(r4.id));
    L13:
        r3 = r3 + 1;
        goto L4
    L14:
        Iterator r02 = r2.iterator();
        HashSet<Long> r12 = null;
        HashSet<Long> r32 = null;
    L16:
        if (r02.hasNext() == false) goto L22;
        Long r42 = (Long) r02.next();
        if (this.channelIds.contains(r42) == true) goto L16;
        if (r32 != null) goto L21;
        r32 = new HashSet();
    L21:
        r32.add(r42);
        goto L16
    L22:
        Iterator<Long> r03 = this.channelIds.iterator();
    L24:
        if (r03.hasNext() == false) goto L30;
        Long r43 = r03.next();
        if (r2.contains(r43) == true) goto L24;
        if (r12 != null) goto L29;
        r12 = new HashSet();
    L29:
        r12.add(r43);
        goto L24
    L30:
        if (r32 != null) goto L33;
        if (r12 != null) goto L33;
        return;
    L33:
        this.channelIds.clear();
        this.channelIds.addAll(r2);
        if (r8 == false) goto L62;
        if (r32 != null) goto L39;
        r32 = new HashSet();
    L39:
        if (r12 != null) goto L42;
        r12 = new HashSet();
    L42:
        int r82 = this.listeners.size() - 1;
    L43:
        if (r82 < 0) goto L63;
        this.listeners.get(r82).onFeedChannelsChanged(r32, r12);
        r82 = r82 - 1;
        goto L43
    L63:
        return;
    }

    public void addListener(Listener r2) {
        ensureBuilt();
        if (this.listeners.contains(r2) == true) goto L6;
        this.listeners.add(r2);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.dialogsNeedReload) goto L8;
        ensureBuilt();
        if (this.rebuildScheduled == true) goto L9;
        this.rebuildScheduled = true;
        AndroidUtilities.runOnUIThread(this.rebuildRunnable, 500);
        return;
    L9:
        return;
    }
}
