package com.exteragram.messenger.feed;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
final class FeedUnreadTracker {
    private final int currentAccount;
    private final Runnable flushRunnable;
    private boolean flushScheduled;
    private final LongSparseArray<Integer> pendingMaxReadId;
    private final LongSparseArray<Integer> readInboxMaxByDialog;
    private final ArrayList<MessageObject> timeline;

    /* renamed from: $r8$lambda$F3Msl8DV0mLgn9dSYgpbsB-Se9Q, reason: not valid java name */
    public static /* synthetic */ void m1423$r8$lambda$F3Msl8DV0mLgn9dSYgpbsBSe9Q(FeedUnreadTracker r0) {
        r0.flush();
    }

    public FeedUnreadTracker(int r2, ArrayList<MessageObject> r3) {
        this.readInboxMaxByDialog = new LongSparseArray();
        this.pendingMaxReadId = new LongSparseArray();
        this.flushRunnable = new FeedUnreadTracker$$ExternalSyntheticLambda0(this);
        this.currentAccount = r2;
        this.timeline = r3;
    }

    private ArrayList<TLRPC.Dialog> collectUnreadFeedDialogs() {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        FeedConfig r10 = FeedConfig.getInstance(this.currentAccount);
        boolean r1 = r10.isIncludeArchived();
        LongSparseArray<TLRPC.Dialog> r2 = r0.dialogs_dict;
        ArrayList<TLRPC.Dialog> r3 = new ArrayList();
        int r4 = 0;
    L4:
        if (r4 >= r2.size()) goto L24;
        TLRPC.Dialog r5 = r2.valueAt(r4);
        if (r5 == null) goto L23;
        if (r5.unread_count <= 0) goto L23;
        long r6 = r5.id;
        if (DialogObject.isChatDialog(r6) == false) goto L23;
        if (r10.isExcluded(r6) == true) goto L23;
        if (r1 == true) goto L20;
        if (r5.folder_id == 1) goto L23;
    L20:
        if (FeedController.isEligibleChannel(r0.getChat(Long.valueOf(-r6))) == false) goto L23;
        r3.add(r5);
    L23:
        r4 = r4 + 1;
        goto L4
    L24:
        return r3;
    }

    private LongSparseArray<Integer> countPendingTimelineRows() {
        LongSparseArray<Integer> r0 = new LongSparseArray();
        int r1 = 0;
    L4:
        if (r1 >= this.timeline.size()) goto L16;
        MessageObject r3 = this.timeline.get(r1);
        if (r3 == null) goto L15;
        long r4 = r3.getDialogId();
        int r6 = this.pendingMaxReadId.get(r4, 0).intValue();
        if (r6 == 0) goto L15;
        int r32 = r3.getRealId();
        if (r32 <= this.readInboxMaxByDialog.get(r4, 0).intValue()) goto L15;
        if (r32 > r6) goto L15;
        r0.put(r4, Integer.valueOf(r0.get(r4, 0).intValue() + 1));
    L15:
        r1 = r1 + 1;
        goto L4
    L16:
        return r0;
    }

    private void flush() {
        int r0 = 0;
        this.flushScheduled = false;
        if (this.pendingMaxReadId.isEmpty() == false) goto L5;
        return;
    L5:
        MessagesController r3 = MessagesController.getInstance(this.currentAccount);
        int r8 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        LongSparseArray<Integer> r2 = countPendingTimelineRows();
    L6:
        int r4 = this.pendingMaxReadId.size();
        LongSparseArray<Integer> r5 = this.pendingMaxReadId;
        if (r0 >= r4) goto L13;
        long r42 = r5.keyAt(r0);
        Integer r6 = this.pendingMaxReadId.valueAt(r0);
        int r62 = r6.intValue();
        if (r62 <= this.readInboxMaxByDialog.get(r42, 0).intValue()) goto L12;
        this.readInboxMaxByDialog.put(r42, r6);
        r3.markDialogAsRead(r42, r62, 0, r8, false, 0, Math.max(r2.get(r42, 0).intValue(), 1), true, 0);
    L12:
        r0 = r0 + 1;
        goto L6
    L13:
        r5.clear();
    }

    private int getEffectiveReadInboxMax(long r3) {
        return Math.max(this.readInboxMaxByDialog.get(r3, 0).intValue(), this.pendingMaxReadId.get(r3, 0).intValue());
    }

    public void applyReadInboxMax(long r3, int r5) {
        if (r5 <= this.readInboxMaxByDialog.get(r3, 0).intValue()) goto L6;
        this.readInboxMaxByDialog.put(r3, Integer.valueOf(r5));
        return;
    }

    public void clear() {
        if (this.flushScheduled == false) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.flushRunnable);
        this.flushScheduled = false;
    L5:
        flush();
        this.readInboxMaxByDialog.clear();
    }

    public int countUnreadBelow(ArrayList<MessageObject> r6, int r7) {
        int r0 = 0;
        if (r6 != null) goto L5;
    L23:
        return 0;
    L5:
        if (this.readInboxMaxByDialog.isEmpty() == true) goto L23;
        int r72 = Math.min(r7, r6.size());
        int r1 = 0;
    L8:
        if (r0 >= r72) goto L22;
        MessageObject r2 = r6.get(r0);
        if (r2 == null) goto L21;
        if (r2.isDateObject == true) goto L21;
        if (r2.type == 6) goto L21;
        if (r2.isSponsored() == true) goto L21;
        if (isUnread(r2) == false) goto L21;
        r1 = r1 + 1;
    L21:
        r0 = r0 + 1;
        goto L8
    L22:
        return r1;
    }

    public int findFirstUnreadIndex(ArrayList<MessageObject> r4) {
        if (r4 != null) goto L5;
    L13:
        return -1;
    L5:
        if (this.readInboxMaxByDialog.isEmpty() == true) goto L13;
        int r1 = r4.size() - 1;
    L8:
        if (r1 < 0) goto L13;
        if (isUnread(r4.get(r1)) == true) goto L11;
        r1 = r1 - 1;
        goto L8
    L11:
        return r1;
    }

    public int getUnreadCount() {
        ArrayList<TLRPC.Dialog> r4 = collectUnreadFeedDialogs();
        int r0 = r4.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        TLRPC.Dialog r3 = r4.get(r2);
        r2 = r2 + 1;
        r1 = r1 + r3.unread_count;
        goto L3
    L5:
        return r1;
    }

    public boolean isUnread(MessageObject r5) {
        if (r5 != null) goto L5;
    L11:
        return false;
    L5:
        if (r5.isSponsored() == true) goto L11;
        if (r5.getRealId() <= getEffectiveReadInboxMax(r5.getDialogId())) goto L11;
        return true;
    }

    public void markAllRead() {
        MessagesController r2 = MessagesController.getInstance(this.currentAccount);
        HashSet r1 = new HashSet();
        ArrayList<TLRPC.Dialog> r14 = collectUnreadFeedDialogs();
        int r15 = r14.size();
        boolean r3 = false;
        int r4 = 0;
    L3:
        if (r4 >= r15) goto L5;
        int r16 = r4 + 1;
        TLRPC.Dialog r42 = r14.get(r4);
        r2.markMentionsAsRead(r42.id, 0);
        long r5 = r42.id;
        int r52 = r42.top_message;
        r2.markDialogAsRead(r5, r52, r52, r42.last_message_date, false, 0, 0, true, 0);
        this.readInboxMaxByDialog.put(r42.id, Integer.valueOf(r42.top_message));
        r1.add(Long.valueOf(r42.id));
        r4 = r16;
        r14 = r14;
        r3 = r3;
        goto L3
    L5:
        boolean r19 = r3;
        FeedConfig r32 = FeedConfig.getInstance(this.currentAccount);
        boolean r43 = r32.isIncludeArchived();
        int r53 = r19 ? 1 : 0;
    L7:
        if (r53 >= this.timeline.size()) goto L24;
        MessageObject r6 = this.timeline.get(r53);
        if (r6 == null) goto L23;
        long r7 = r6.getDialogId();
        if (r32.isExcluded(r7) == true) goto L23;
        if (r43 == true) goto L20;
        TLRPC.Dialog r9 = r2.dialogs_dict.get(r7);
        if (r9 == null) goto L20;
        if (r9.folder_id == 1) goto L23;
    L20:
        r1.add(Long.valueOf(r7));
        int r62 = r6.getRealId();
        if (r62 <= this.readInboxMaxByDialog.get(r7, Integer.valueOf(r19 ? 1 : 0)).intValue()) goto L23;
        this.readInboxMaxByDialog.put(r7, Integer.valueOf(r62));
    L23:
        r53 = r53 + 1;
        goto L7
    L24:
        Iterator r12 = r1.iterator();
    L26:
        if (r12.hasNext() == false) goto L29;
        this.pendingMaxReadId.remove(((Long) r12.next()).longValue());
        goto L26
    L29:
        if (this.pendingMaxReadId.isEmpty() == true) goto L31;
        return;
    L31:
        if (this.flushScheduled == false) goto L43;
        AndroidUtilities.cancelRunOnUIThread(this.flushRunnable);
        this.flushScheduled = r19;
        return;
    }

    public void onPostSeen(long r3, int r5) {
        if (r3 == 0) goto L18;
        if (r5 > 0) goto L7;
        return;
    L7:
        if (r5 <= getEffectiveReadInboxMax(r3)) goto L21;
        Integer r0 = this.pendingMaxReadId.get(r3);
        if (r0 != null) goto L12;
    L14:
        this.pendingMaxReadId.put(r3, Integer.valueOf(r5));
        if (this.flushScheduled == true) goto L19;
        this.flushScheduled = true;
        AndroidUtilities.runOnUIThread(this.flushRunnable, 1000);
        return;
    L19:
        return;
    L12:
        if (r0.intValue() < r5) goto L14;
        return;
    L21:
        return;
    }
}
