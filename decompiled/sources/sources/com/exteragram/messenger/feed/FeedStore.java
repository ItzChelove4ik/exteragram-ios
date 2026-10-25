package com.exteragram.messenger.feed;

import com.exteragram.messenger.feed.FeedTimelineLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.MessageObject;

/* loaded from: classes.dex */
public final class FeedStore {
    private int count;
    private boolean endReached;
    private final HashSet<Long> hiddenDialogIds;
    private final FeedMessageIdentityMap identityMap;
    private final ArrayList<MessageObject> messages;
    private final FeedTimelineLoader.Cursor newestCursor;
    private final FeedTimelineLoader.Cursor oldestCursor;

    public FeedStore() {
        this.messages = new ArrayList();
        this.identityMap = new FeedMessageIdentityMap();
        this.hiddenDialogIds = new HashSet();
        this.oldestCursor = new FeedTimelineLoader.Cursor();
        this.newestCursor = new FeedTimelineLoader.Cursor();
    }

    public static int compareTimeline(int r0, long r1, int r3, int r4, long r5, int r7) {
        if (r0 == r4) goto L6;
        return Integer.compare(r0, r4);
    L6:
        if (r1 == r5) goto L10;
        return Long.compare(r1, r5);
    L10:
        return Integer.compare(r3, r7);
    }

    private int findMergeIndex(MessageObject r11, int r12) {
    L3:
        if (r12 >= this.messages.size()) goto L10;
        MessageObject r0 = this.messages.get(r12);
        if (r0 == null) goto L9;
        if (compareTimeline(r0.messageOwner.date, r0.getDialogId(), r0.getRealId(), r11.messageOwner.date, r11.getDialogId(), r11.getRealId()) < 0) goto L10;
    L9:
        r12 = r12 + 1;
    L10:
        if (r12 <= 0) goto L24;
        if (r12 >= this.messages.size()) goto L24;
        MessageObject r112 = this.messages.get(r12 - 1);
        MessageObject r02 = this.messages.get(r12);
        if (r112 == null) goto L24;
        if (r02 == null) goto L24;
        if (r112.getGroupId() == 0) goto L24;
        if (r112.getGroupId() != r02.getGroupId()) goto L24;
        if (r112.getDialogId() != r02.getDialogId()) goto L24;
        r12 = r12 + 1;
    L24:
        return r12;
    }

    private static boolean isPagingRow(MessageObject r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.isDateObject == false) goto L6;
        return false;
    L6:
        if (r1.messageOwner != null) goto L8;
        return false;
    L8:
        if (r1.getRealId() <= 0) goto L15;
        return true;
    L15:
        return false;
    }

    private void onRowsRemoved() {
        if (rebuildPagingCursorsFromLoadedRows() == true) goto L5;
        this.endReached = false;
    L5:
        updateCount();
    }

    private void purgeRow(MessageObject r1, ArrayList<Integer> r2, HashSet<Integer> r3) {
        this.identityMap.purge(r1);
        if (r3.add(Integer.valueOf(r1.getId())) == false) goto L6;
        r2.add(Integer.valueOf(r1.getId()));
        return;
    }

    private boolean rebuildPagingCursorsFromLoadedRows() {
        boolean r1 = this.oldestCursor.isEmpty();
        this.newestCursor.isEmpty();
        int r5 = 0;
        int r10 = 0;
        int r13 = 0;
        int r15 = 0;
        int r18 = 0;
        long r11 = 0;
        long r16 = 0;
    L4:
        if (r5 >= this.messages.size()) goto L23;
        MessageObject r6 = this.messages.get(r5);
        if (isPagingRow(r6) == false) goto L22;
        int r7 = r6.messageOwner.date;
        long r72 = r6.getDialogId();
        int r9 = r6.getRealId();
        if (r10 == 0) goto L13;
        int r14 = compareTimeline(r7, r72, r9, r10, r11, r13);
        int r142 = r9;
        int r92 = r13;
        long r12 = r72;
        long r73 = r11;
        if (r14 <= 0) goto L16;
    L14:
        r10 = r7;
        r73 = r12;
        r92 = r142;
    L16:
        if (r15 != 0) goto L18;
    L21:
        r15 = r7;
        r16 = r12;
        r18 = r142;
    L20:
        r11 = r73;
        r13 = r92;
        goto L22
    L18:
        if (compareTimeline(r7, r12, r142, r15, r16, r18) >= 0) goto L20;
    L13:
        r12 = r72;
        r142 = r9;
    L22:
        r5 = r5 + 1;
        goto L4
    L23:
        long r74 = r11;
        int r93 = r13;
        if (r10 != 0) goto L27;
        this.oldestCursor.set(0, 0, 0);
        this.newestCursor.set(0, 0, 0);
        return false;
    L27:
        if (r1 == true) goto L31;
        FeedTimelineLoader.Cursor r17 = this.oldestCursor;
        int r112 = r15;
        long r122 = r16;
        int r143 = r18;
        r15 = r112;
        if (compareTimeline(r112, r122, r143, r17.date, r17.uid, r17.mid) <= 0) goto L32;
        this.endReached = false;
    L32:
        this.newestCursor.set(r10, r74, r93);
        this.oldestCursor.set(r15, r122, r143);
        return true;
    L31:
        r122 = r16;
        r143 = r18;
        goto L32
    }

    private void updateCount() {
        int r0 = getVisibleCount();
        int r1 = 0;
        if (r0 != 0) goto L6;
    L10:
        this.count = r1;
        return;
    L6:
        if (this.endReached == true) goto L9;
        r1 = 3;
    L9:
        r1 = r1 + r0;
        goto L10
    }

    public ArrayList<MessageObject> appendMessages(ArrayList<MessageObject> r7, boolean r8) {
        ArrayList<MessageObject> r0 = new ArrayList(r7.size());
        int r1 = r7.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        MessageObject r4 = r7.get(r3);
        r3 = r3 + 1;
        MessageObject r42 = r4;
        if (this.identityMap.register(r42) == false) goto L3;
        r0.add(r42);
        goto L3
    L7:
        if (r8 == false) goto L9;
        ArrayList r72 = new ArrayList(r0);
        Collections.reverse(r72);
        this.messages.addAll(0, r72);
    L10:
        updateCount();
        return r0;
    L9:
        this.messages.addAll(r0);
        goto L10
    }

    public boolean applyIncludedDialogs(HashSet<Long> r6) {
        HashSet<Long> r0 = getLoadedDialogIds();
        Iterator<Long> r1 = r0.iterator();
        boolean r2 = false;
    L4:
        if (r1.hasNext() == false) goto L8;
        Long r3 = r1.next();
        if (r6.contains(r3) == true) goto L4;
        r2 = r2 | this.hiddenDialogIds.add(r3);
        goto L4
    L8:
        Iterator<Long> r12 = this.hiddenDialogIds.iterator();
    L10:
        if (r12.hasNext() == false) goto L16;
        Long r32 = r12.next();
        if (r6.contains(r32) == true) goto L15;
        if (r0.contains(r32) == true) goto L10;
    L15:
        r12.remove();
        r2 = true;
        goto L10
    L16:
        if (r2 == false) goto L18;
        updateCount();
    L18:
        return r2;
    }

    public void clear() {
        this.messages.clear();
        this.identityMap.clear();
        this.hiddenDialogIds.clear();
        this.endReached = false;
        this.count = 0;
        this.oldestCursor.set(0, 0, 0);
        this.newestCursor.set(0, 0, 0);
    }

    public ArrayList<Integer> deleteHistory(long r10, int r12, boolean[] r13) {
        ArrayList<Integer> r0 = new ArrayList();
        HashSet<Integer> r1 = new HashSet();
        int r2 = this.messages.size() - 1;
        boolean r5 = false;
    L3:
        if (r2 < 0) goto L15;
        MessageObject r6 = this.messages.get(r2);
        if (r6 == null) goto L14;
        if (r6.getDialogId() != r10) goto L14;
        if (r6.getRealId() <= 0) goto L14;
        if (r6.getRealId() > r12) goto L14;
        this.messages.remove(r2);
        purgeRow(r6, r0, r1);
        r5 = true;
    L14:
        r2 = r2 - 1;
        goto L3
    L15:
        if (r5 == true) goto L17;
    L20:
        r13[0] = r5;
        return r0;
    L17:
        if (hasMessagesForDialog(r10) == true) goto L19;
        this.hiddenDialogIds.remove(Long.valueOf(r10));
    L19:
        onRowsRemoved();
        goto L20
    }

    public ArrayList<Integer> deleteMessages(long r11, ArrayList<Integer> r13, boolean[] r14) {
        ArrayList<Integer> r0 = new ArrayList();
        if (r13 != null) goto L5;
        return r0;
    L5:
        HashSet r1 = new HashSet(r13);
        HashSet<Integer> r2 = new HashSet();
        int r4 = 0;
        boolean r5 = false;
    L7:
        if (r4 >= r13.size()) goto L13;
        MessageObject r6 = this.identityMap.getByRealId(r11, r13.get(r4).intValue());
        if (r6 == null) goto L12;
        this.messages.remove(r6);
        purgeRow(r6, r0, r2);
        r5 = true;
    L12:
        r4 = r4 + 1;
        goto L7
    L13:
        int r132 = this.messages.size() - 1;
    L14:
        if (r132 < 0) goto L24;
        MessageObject r42 = this.messages.get(r132);
        if (r42 == null) goto L23;
        if (r42.getDialogId() != r11) goto L23;
        if (r1.contains(Integer.valueOf(r42.getRealId())) == false) goto L23;
        this.messages.remove(r132);
        purgeRow(r42, r0, r2);
        r5 = true;
    L23:
        r132 = r132 - 1;
        goto L14
    L24:
        if (r5 == true) goto L26;
    L29:
        r14[0] = r5;
        return r0;
    L26:
        if (hasMessagesForDialog(r11) == true) goto L28;
        this.hiddenDialogIds.remove(Long.valueOf(r11));
    L28:
        onRowsRemoved();
        goto L29
    }

    public int getCount() {
        return this.count;
    }

    public HashSet<Long> getHiddenSnapshot() {
        return new HashSet(this.hiddenDialogIds);
    }

    public HashSet<Long> getLoadedDialogIds() {
        HashSet<Long> r0 = new HashSet();
        int r1 = 0;
    L4:
        if (r1 >= this.messages.size()) goto L9;
        MessageObject r2 = this.messages.get(r1);
        if (r2 == null) goto L8;
        r0.add(Long.valueOf(r2.getDialogId()));
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    public MessageObject getMessage(long r1, int r3) {
        return this.identityMap.getByAnyId(r1, r3);
    }

    public ArrayList<MessageObject> getMessages() {
        return this.messages;
    }

    public FeedTimelineLoader.Cursor getNewestCursor() {
        return this.newestCursor;
    }

    public FeedTimelineLoader.Cursor getOldestCursor() {
        return this.oldestCursor;
    }

    public int getVisibleCount() {
        if (this.hiddenDialogIds.isEmpty() == true) goto L5;
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r0 >= this.messages.size()) goto L15;
        MessageObject r2 = this.messages.get(r0);
        if (r2 == null) goto L14;
        if (this.hiddenDialogIds.contains(Long.valueOf(r2.getDialogId())) == true) goto L14;
        r1 = r1 + 1;
    L14:
        r0 = r0 + 1;
        goto L8
    L15:
        return r1;
    L5:
        return this.messages.size();
    }

    public ArrayList<MessageObject> getVisibleMessages() {
        if (this.hiddenDialogIds.isEmpty() == true) goto L5;
        ArrayList<MessageObject> r0 = new ArrayList(this.messages.size());
        int r1 = 0;
    L8:
        if (r1 >= this.messages.size()) goto L15;
        MessageObject r2 = this.messages.get(r1);
        if (r2 == null) goto L14;
        if (this.hiddenDialogIds.contains(Long.valueOf(r2.getDialogId())) == true) goto L14;
        r0.add(r2);
    L14:
        r1 = r1 + 1;
        goto L8
    L15:
        return r0;
    L5:
        return new ArrayList(this.messages);
    }

    public boolean hasMessagesForDialog(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.messages.size()) goto L12;
        MessageObject r2 = this.messages.get(r1);
        if (r2 == null) goto L11;
        if (r2.getDialogId() != r5) goto L11;
        return true;
    L11:
        r1 = r1 + 1;
        goto L4
    L12:
        return false;
    }

    public boolean hasNoSyntheticIds() {
        return this.identityMap.isEmpty();
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public boolean isEndReached() {
        return this.endReached;
    }

    public ArrayList<MessageObject> mergeRows(ArrayList<MessageObject> r11) {
        ArrayList<MessageObject> r0 = new ArrayList(r11.size());
        int r1 = r11.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        MessageObject r4 = r11.get(r3);
        r3 = r3 + 1;
        MessageObject r42 = r4;
        if (this.identityMap.register(r42) == false) goto L3;
        r0.add(r42);
        goto L3
    L7:
        int r112 = 0;
    L9:
        if (r2 >= r0.size()) goto L24;
        MessageObject r12 = r0.get(r2);
        int r32 = r2 + 1;
        long r43 = r12.getGroupId();
    L12:
        if (r43 == 0) goto L20;
        if (r32 >= r0.size()) goto L20;
        if (r0.get(r32).getGroupId() != r43) goto L20;
        if (r0.get(r32).getDialogId() != r12.getDialogId()) goto L20;
        r32 = r32 + 1;
    L20:
        r112 = findMergeIndex(r12, r112);
    L21:
        if (r2 >= r32) goto L23;
        this.messages.add(r112, r0.get(r2));
        r2 = r2 + 1;
        r112 = r112 + 1;
        goto L21
    L23:
        r2 = r32;
        goto L9
    L24:
        updateCount();
        return r0;
    }

    public void replaceMessage(MessageObject r2, MessageObject r3) {
        if (r2 == null) goto L10;
        if (r3 == null) goto L11;
        int r22 = this.messages.indexOf(r2);
        if (r22 < 0) goto L8;
        this.messages.set(r22, r3);
    L8:
        this.identityMap.replace(r3);
        return;
    L11:
        return;
    }

    public long resolveRealDialogId(int r1) {
        return this.identityMap.resolveRealDialogId(r1);
    }

    public int resolveRealMessageId(long r1, int r3) {
        return this.identityMap.resolveRealMessageId(r1, r3);
    }

    public void setEndReached(boolean r1) {
        this.endReached = r1;
        updateCount();
    }

    public boolean setHidden(long r2, boolean r4) {
        HashSet<Long> r0 = this.hiddenDialogIds;
        Long r22 = Long.valueOf(r2);
        if (r4 == false) goto L5;
        boolean r23 = r0.add(r22);
    L6:
        if (r23 == false) goto L8;
        updateCount();
    L8:
        return r23;
    L5:
        r23 = r0.remove(r22);
        goto L6
    }

    public boolean trim(int r14) {
        if (r14 > 0) goto L5;
    L34:
        return false;
    L5:
        if (this.messages.size() <= r14) goto L34;
        MessageObject r142 = this.messages.get(r14 - 1);
        int r7 = r142.messageOwner.date;
        long r8 = r142.getDialogId();
        int r10 = r142.getRealId();
        int r143 = this.messages.size() - 1;
        boolean r1 = false;
    L8:
        if (r143 < 0) goto L15;
        MessageObject r11 = this.messages.get(r143);
        if (r11 == null) goto L14;
        if (compareTimeline(r11.messageOwner.date, r11.getDialogId(), r11.getRealId(), r7, r8, r10) >= 0) goto L14;
        this.messages.remove(r143);
        this.identityMap.purge(r11);
        r1 = true;
    L14:
        r143 = r143 - 1;
        goto L8
    L15:
        if (r1 == true) goto L18;
        return false;
    L18:
        if (this.messages.isEmpty() == false) goto L20;
        this.oldestCursor.set(0, 0, 0);
    L32:
        this.endReached = false;
        updateCount();
        return true;
    L20:
        int r144 = 0;
        int r9 = 0;
        int r12 = 0;
        long r102 = 0;
    L22:
        if (r144 >= this.messages.size()) goto L31;
        MessageObject r13 = this.messages.get(r144);
        if (r13 == null) goto L30;
        if (r9 != 0) goto L28;
    L29:
        r9 = r13.messageOwner.date;
        long r3 = r13.getDialogId();
        r12 = r13.getRealId();
        r102 = r3;
        goto L30
    L28:
        if (compareTimeline(r13.messageOwner.date, r13.getDialogId(), r13.getRealId(), r9, r102, r12) < 0) goto L29;
    L30:
        r144 = r144 + 1;
        goto L22
    L31:
        this.oldestCursor.set(r9, r102, r12);
        goto L32
    }
}
