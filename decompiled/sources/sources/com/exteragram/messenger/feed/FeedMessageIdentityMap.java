package com.exteragram.messenger.feed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
final class FeedMessageIdentityMap {
    private final HashMap<MessageCompositeID, Integer> generatedIds;
    private final HashMap<GroupKey, ArrayList<MessageObject>> groupMembers;
    private int lastGeneratedId;
    private final HashMap<MessageCompositeID, MessageObject> messagesByRealId;
    private final ConcurrentHashMap<Integer, MessageCompositeID> realIdsByGeneratedId;

    /* loaded from: classes4.dex */
    public static final class GroupKey {
        final long dialog_id;
        final long groupedId;

        public GroupKey(long r1, long r3) {
            this.dialog_id = r1;
            this.groupedId = r3;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (GroupKey.class != r7.getClass()) goto L15;
            GroupKey r72 = (GroupKey) r7;
            if (this.dialog_id != r72.dialog_id) goto L15;
            if (this.groupedId != r72.groupedId) goto L15;
            return true;
        }

        public int hashCode() {
            return (Long.hashCode(this.dialog_id) * 31) + Long.hashCode(this.groupedId);
        }
    }

    public static final class MessageCompositeID {
        final long dialog_id;
        final int id;

        public MessageCompositeID(long r1, int r3) {
            this.dialog_id = r1;
            this.id = r3;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (MessageCompositeID.class != r7.getClass()) goto L15;
            MessageCompositeID r72 = (MessageCompositeID) r7;
            if (this.dialog_id != r72.dialog_id) goto L15;
            if (this.id != r72.id) goto L15;
            return true;
        }

        public int hashCode() {
            return (Long.hashCode(this.dialog_id) * 31) + this.id;
        }
    }

    public FeedMessageIdentityMap() {
        this.generatedIds = new HashMap();
        this.realIdsByGeneratedId = new ConcurrentHashMap();
        this.messagesByRealId = new HashMap();
        this.groupMembers = new HashMap();
        this.lastGeneratedId = 2147483637;
    }

    private void addToGroup(MessageObject r4, long r5) {
        if (r4.hasValidGroupId() == true) goto L6;
        r4.isPrimaryGroupMessage = false;
        return;
    L6:
        GroupKey r0 = new GroupKey(r5, r4.messageOwner.grouped_id);
        ArrayList<MessageObject> r52 = this.groupMembers.get(r0);
        if (r52 != null) goto L10;
        r52 = new ArrayList();
        this.groupMembers.put(r0, r52);
    L10:
        if (r52.contains(r4) == true) goto L12;
        r52.add(r4);
    L12:
        electPrimary(r52);
    }

    private static void electPrimary(ArrayList<MessageObject> r6) {
        MessageObject r0 = null;
        int r2 = 0;
    L4:
        if (r2 >= r6.size()) goto L11;
        MessageObject r3 = r6.get(r2);
        if (r0 != null) goto L8;
    L9:
        r0 = r3;
    L10:
        r2 = r2 + 1;
        goto L4
    L8:
        if (r3.getRealId() <= r0.getRealId()) goto L10;
    L11:
        int r22 = 0;
    L13:
        if (r22 >= r6.size()) goto L19;
        MessageObject r32 = r6.get(r22);
        if (r6.get(r22) != r0) goto L17;
        boolean r4 = true;
    L18:
        r32.isPrimaryGroupMessage = r4;
        r22 = r22 + 1;
        goto L13
    L17:
        r4 = false;
        goto L18
    }

    private void removeFromGroup(MessageObject r4, long r5) {
        if (r4.hasValidGroupId() == false) goto L14;
        GroupKey r0 = new GroupKey(r5, r4.messageOwner.grouped_id);
        ArrayList<MessageObject> r52 = this.groupMembers.get(r0);
        if (r52 != null) goto L8;
        return;
    L8:
        r52.remove(r4);
        r4.isPrimaryGroupMessage = false;
        if (r52.isEmpty() == false) goto L12;
        this.groupMembers.remove(r0);
        return;
    L12:
        electPrimary(r52);
        return;
    }

    public void clear() {
        this.generatedIds.clear();
        this.realIdsByGeneratedId.clear();
        this.messagesByRealId.clear();
        this.groupMembers.clear();
        this.lastGeneratedId = 2147483637;
    }

    public MessageObject getByAnyId(long r3, int r5) {
        MessageObject r0 = this.messagesByRealId.get(new MessageCompositeID(r3, r5));
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r02 = resolveRealMessageId(r3, r5);
        if (r02 != r5) goto L8;
        return null;
    L8:
        return this.messagesByRealId.get(new MessageCompositeID(r3, r02));
    }

    public MessageObject getByRealId(long r2, int r4) {
        return this.messagesByRealId.get(new MessageCompositeID(r2, r4));
    }

    public boolean isEmpty() {
        return this.realIdsByGeneratedId.isEmpty();
    }

    public void purge(MessageObject r5) {
        MessageCompositeID r0 = new MessageCompositeID(r5.getDialogId(), r5.getRealId());
        this.generatedIds.remove(r0);
        this.messagesByRealId.remove(r0);
        this.realIdsByGeneratedId.remove(Integer.valueOf(r5.getId()));
        removeFromGroup(r5, r0.dialog_id);
    }

    public boolean register(MessageObject r6) {
        int r0 = r6.getRealId();
        MessageCompositeID r1 = new MessageCompositeID(r6.getDialogId(), r0);
        Integer r2 = this.generatedIds.get(r1);
        if (r2 != null) goto L5;
        int r22 = this.lastGeneratedId;
        this.lastGeneratedId = r22 - 1;
        r2 = Integer.valueOf(r22);
        this.generatedIds.put(r1, r2);
    L5:
        this.realIdsByGeneratedId.put(r2, r1);
        if (this.messagesByRealId.containsKey(r1) == true) goto L8;
        this.messagesByRealId.put(r1, r6);
        addToGroup(r6, r1.dialog_id);
        boolean r5 = true;
    L9:
        TLRPC.Message r62 = r6.messageOwner;
        r62.realId = r0;
        r62.id = r2.intValue();
        return r5;
    L8:
        r5 = false;
        goto L9
    }

    public void replace(MessageObject r5) {
        MessageCompositeID r0 = new MessageCompositeID(r5.getDialogId(), r5.getRealId());
        this.generatedIds.put(r0, Integer.valueOf(r5.getId()));
        this.realIdsByGeneratedId.put(Integer.valueOf(r5.getId()), r0);
        MessageObject r1 = this.messagesByRealId.put(r0, r5);
        if (r1 == null) goto L5;
        removeFromGroup(r1, r0.dialog_id);
    L5:
        addToGroup(r5, r0.dialog_id);
    }

    public long resolveRealDialogId(int r1) {
        MessageCompositeID r0 = this.realIdsByGeneratedId.get(Integer.valueOf(r1));
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.dialog_id;
    }

    public int resolveRealMessageId(long r3, int r5) {
        MessageCompositeID r2 = this.realIdsByGeneratedId.get(Integer.valueOf(r5));
        if (r2 != null) goto L5;
    L8:
        return r5;
    L5:
        if (r2.dialog_id != r3) goto L8;
        return r2.id;
    }
}
