package com.exteragram.messenger.feed.ads;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.feed.FeedChatIntegration;
import com.exteragram.messenger.feed.FeedMessageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import org.telegram.messenger.MessageObject;

/* loaded from: classes4.dex */
public final class FeedAdInjector {
    private final HashMap<MessageObject, MessageObject> adByAnchor;
    private final int currentAccount;
    private final FeedChatIntegration.Host host;
    private final HashMap<MessageObject, Integer> slotOrdinalByAnchor;

    public static final class AnchorSlot extends RecordTag {
        private final MessageObject anchor;
        private final int ordinal;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof AnchorSlot) == false) goto L10;
            AnchorSlot r32 = (AnchorSlot) r3;
            if (this.ordinal == r32.ordinal) goto L7;
            return false;
        L7:
            if (Objects.equals(this.anchor, r32.anchor) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.anchor, Integer.valueOf(this.ordinal)};
        }

        /* renamed from: -$$Nest$fgetanchor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessageObject m1425$$Nest$fgetanchor(AnchorSlot r0) {
            return r0.anchor;
        }

        /* renamed from: -$$Nest$fgetordinal, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1426$$Nest$fgetordinal(AnchorSlot r0) {
            return r0.ordinal;
        }

        private AnchorSlot(MessageObject r1, int r2) {
            this.anchor = r1;
            this.ordinal = r2;
        }

        public /* synthetic */ AnchorSlot(MessageObject r1, int r2, FeedAdInjectorIA r3) {
            this(r1, r2);
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return FeedAdInjector$AnchorSlot$$ExternalSyntheticRecord0.m(this.ordinal, this.anchor);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), AnchorSlot.class, "anchor;ordinal");
        }
    }

    public FeedAdInjector(int r2, FeedChatIntegration.Host r3) {
        this.adByAnchor = new HashMap();
        this.slotOrdinalByAnchor = new HashMap();
        this.currentAccount = r2;
        this.host = r3;
    }

    private ArrayList<AnchorSlot> computeNewAnchors(ArrayList<MessageObject> r13, MessageObject r14, FeedAdController r15) {
        ArrayList r0 = new ArrayList();
        HashMap r1 = new HashMap();
        int r3 = 0;
    L4:
        if (r3 >= r13.size()) goto L9;
        if (FeedMessageUtils.isPostRow(r13.get(r3)) == false) goto L8;
        r1.put(r13.get(r3), Integer.valueOf(r0.size()));
        r0.add(Integer.valueOf(r3));
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        ArrayList<AnchorSlot> r32 = new ArrayList();
        int r4 = r0.size();
        if (r4 == 0) goto L57;
        int r5 = r15.getFirstAfter();
        int r6 = r15.getEffectiveEvery();
        int r152 = r15.getMinTrailing();
        if (r14 == null) goto L15;
        int r142 = r13.indexOf(r14);
    L16:
        if (r142 < 0) goto L22;
        int r7 = r0.size();
        int r8 = 0;
        int r9 = 0;
    L18:
        if (r9 >= r7) goto L23;
        Object r10 = r0.get(r9);
        r9 = r9 + 1;
        if (((Integer) r10).intValue() >= r142) goto L23;
        r8 = r8 + 1;
    L23:
        TreeSet r143 = new TreeSet();
        int r72 = r8 - r5;
    L24:
        if (r72 < 0) goto L26;
        r143.add(Integer.valueOf(r72));
        r72 = r72 - r6;
        goto L24
    L26:
        int r82 = r8 + r5;
    L28:
        if (r82 > (r4 - 1)) goto L30;
        r143.add(Integer.valueOf(r82));
        r82 = r82 + r6;
        goto L28
    L30:
        ArrayList r42 = new ArrayList();
        Iterator<MessageObject> r52 = this.adByAnchor.keySet().iterator();
    L32:
        if (r52.hasNext() == false) goto L40;
        MessageObject r73 = r52.next();
        if (r1.containsKey(r73) == false) goto L32;
        Integer r83 = this.slotOrdinalByAnchor.get(r73);
        if (r83 != null) goto L39;
        r83 = (Integer) r1.get(r73);
    L39:
        r42.add(r83);
        goto L32
    L40:
        Iterator r144 = r143.iterator();
    L42:
        if (r144.hasNext() == false) goto L57;
        Integer r53 = (Integer) r144.next();
        int r74 = r53.intValue();
        if (r74 < r152) goto L42;
        MessageObject r84 = r13.get(lastGroupMemberIndex(r13, ((Integer) r0.get(r74)).intValue()));
        if (((Integer) r1.get(r84)) == null) goto L42;
        if (this.adByAnchor.containsKey(r84) == true) goto L42;
        int r92 = r42.size();
        int r102 = 0;
    L52:
        if (r102 >= r92) goto L56;
        Object r11 = r42.get(r102);
        r102 = r102 + 1;
        if (Math.abs(r74 - ((Integer) r11).intValue()) >= r6) goto L52;
    L56:
        r32.add(new AnchorSlot(r84, r74, null));
        r42.add(r53);
        goto L42
    L22:
        r8 = 0;
        goto L23
    L15:
        r142 = -1;
    L57:
        return r32;
    }

    private static int lastGroupMemberIndex(ArrayList<MessageObject> r6, int r7) {
        long r0 = r6.get(r7).getGroupId();
        if (r0 != 0) goto L5;
        return r7;
    L5:
        int r2 = r7;
        r7 = r7 + 1;
        if (r7 >= r6.size()) goto L11;
        MessageObject r3 = r6.get(r7);
        if (FeedMessageUtils.isPostRow(r3) == false) goto L11;
        if (r3.getGroupId() == r0) goto L5;
    L11:
        return r2;
    }

    private boolean removeAd(ArrayList<MessageObject> r2, MessageObject r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r32 = r2.indexOf(r3);
        if (r32 < 0) goto L9;
        r2.remove(r32);
        this.host.notifyMessageRemoved(r32);
        return true;
    L9:
        return false;
    }

    private boolean revalidateKeptAds(ArrayList<MessageObject> r10, FeedAdController r11) {
        int r1 = 0;
        if (this.adByAnchor.isEmpty() == false) goto L5;
        return false;
    L5:
        ArrayList r0 = new ArrayList(this.adByAnchor.keySet());
        int r2 = r0.size();
        boolean r3 = false;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L18;
        Object r5 = r0.get(r4);
        r4 = r4 + 1;
        MessageObject r52 = (MessageObject) r5;
        int r6 = r10.indexOf(r52);
        if (r6 < 0) goto L6;
        int r7 = lastGroupMemberIndex(r10, r6);
        if (r7 == r6) goto L6;
        MessageObject r62 = this.adByAnchor.remove(r52);
        Integer r53 = this.slotOrdinalByAnchor.remove(r52);
        MessageObject r72 = r10.get(r7);
        if (this.adByAnchor.containsKey(r72) == true) goto L14;
        this.adByAnchor.put(r72, r62);
        if (r53 == null) goto L6;
        this.slotOrdinalByAnchor.put(r72, r53);
        goto L6
    L14:
        r3 = r3 | removeAd(r10, r62);
        goto L6
    L18:
        HashMap r02 = new HashMap();
        int r22 = 0;
    L20:
        if (r1 >= r10.size()) goto L25;
        if (FeedMessageUtils.isPostRow(r10.get(r1)) == false) goto L24;
        r02.put(r10.get(r1), Integer.valueOf(r22));
        r22 = r22 + 1;
    L24:
        r1 = r1 + 1;
        goto L20
    L25:
        int r12 = r11.getBaseEvery();
        int r112 = r11.getMinTrailing();
        TreeMap r23 = new TreeMap();
        Iterator<MessageObject> r42 = this.adByAnchor.keySet().iterator();
    L27:
        if (r42.hasNext() == false) goto L31;
        MessageObject r54 = r42.next();
        Integer r63 = (Integer) r02.get(r54);
        if (r63 == null) goto L27;
        r23.put(r63, r54);
        goto L27
    L31:
        Iterator r03 = r23.entrySet().iterator();
        int r43 = Integer.MIN_VALUE;
    L33:
        if (r03.hasNext() == false) goto L42;
        Map.Entry r55 = (Map.Entry) r03.next();
        int r64 = ((Integer) r55.getKey()).intValue();
        if (r64 < r112) goto L41;
        if (r43 == Integer.MIN_VALUE) goto L40;
        if ((r64 - r43) < r12) goto L41;
    L40:
        r43 = r64;
    L41:
        MessageObject r56 = (MessageObject) r55.getValue();
        r3 = r3 | removeAd(r10, this.adByAnchor.remove(r56));
        this.slotOrdinalByAnchor.remove(r56);
        goto L33
    L42:
        return r3;
    }

    public void clear() {
        this.adByAnchor.clear();
        this.slotOrdinalByAnchor.clear();
    }

    public void refresh(MessageObject r11) {
        if (this.host.isListReady() == false) goto L57;
        ArrayList<MessageObject> r0 = this.host.getMessages();
        FeedAdController r1 = FeedAdController.getInstance(this.currentAccount);
        boolean r2 = r1.isEnabled();
        ArrayList r3 = new ArrayList(this.adByAnchor.keySet());
        int r4 = r3.size();
        int r5 = 0;
        boolean r6 = false;
        int r7 = 0;
    L6:
        if (r7 >= r4) goto L12;
        Object r8 = r3.get(r7);
        r7 = r7 + 1;
        MessageObject r82 = (MessageObject) r8;
        if (r2 == false) goto L11;
        if (r0.contains(r82) == true) goto L6;
    L11:
        r6 = r6 | removeAd(r0, this.adByAnchor.remove(r82));
        this.slotOrdinalByAnchor.remove(r82);
        goto L6
    L12:
        if (r2 == false) goto L34;
        boolean r22 = revalidateKeptAds(r0, r1) | r6;
        Iterator<Map.Entry<MessageObject, MessageObject>> r32 = this.adByAnchor.entrySet().iterator();
    L15:
        if (r32.hasNext() == false) goto L24;
        Map.Entry<MessageObject, MessageObject> r42 = r32.next();
        MessageObject r72 = r42.getKey();
        MessageObject r43 = r42.getValue();
        int r83 = r0.indexOf(r72);
        if (r83 < 0) goto L15;
        int r9 = r0.indexOf(r43);
        int r84 = r83 + 1;
        if (r9 == r84) goto L15;
        if (r9 < 0) goto L23;
        r0.remove(r9);
        this.host.notifyMessageRemoved(r9);
        r84 = r0.indexOf(r72) + 1;
    L23:
        r0.add(r84, r43);
        this.host.notifyMessageInserted(r84);
        r22 = true;
        goto L15
    L24:
        ArrayList<AnchorSlot> r112 = computeNewAnchors(r0, r11, r1);
        int r33 = r112.size();
    L25:
        if (r5 >= r33) goto L33;
        AnchorSlot r44 = r112.get(r5);
        r5 = r5 + 1;
        AnchorSlot r45 = r44;
        MessageObject r73 = AnchorSlot.m1425$$Nest$fgetanchor(r45);
        FeedAd r85 = r1.nextAd();
        if (r85 == null) goto L33;
        int r92 = r0.indexOf(r73);
        if (r92 < 0) goto L25;
        MessageObject r23 = FeedAdFactory.createAdMessageObject(this.currentAccount, r85);
        r23.stableId = this.host.nextStableId();
        this.adByAnchor.put(r73, r23);
        this.slotOrdinalByAnchor.put(r73, Integer.valueOf(AnchorSlot.m1426$$Nest$fgetordinal(r45)));
        int r93 = r92 + 1;
        r0.add(r93, r23);
        this.host.notifyMessageInserted(r93);
        r22 = true;
    L33:
        r6 = r22;
    L34:
        if (r6 == false) goto L56;
        this.host.onFeedListChanged();
        this.host.invalidateVisiblePart();
        return;
    L56:
        return;
    }
}
