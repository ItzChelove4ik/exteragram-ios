package org.telegram.tgnet;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public class VectorLegacy {
    private VectorLegacy() {
    }

    public static ArrayList<Long> deserialize_IntAsLong(InputSerializedData r4, boolean r5) {
        ArrayList<Integer> r42 = Vector.deserializeInt(r4, r5);
        ArrayList<Long> r52 = new ArrayList(r42.size());
        int r0 = r42.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Integer r2 = r42.get(r1);
        r1 = r1 + 1;
        r52.add(Long.valueOf(r2.intValue()));
        goto L3
    L5:
        return r52;
    }

    public static ArrayList<TLRPC.Peer> deserialize_IntUserIdAsPeer(InputSerializedData r6, boolean r7) {
        ArrayList<Integer> r62 = Vector.deserializeInt(r6, r7);
        ArrayList<TLRPC.Peer> r72 = new ArrayList(r62.size());
        int r0 = r62.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Integer r2 = r62.get(r1);
        r1 = r1 + 1;
        int r22 = r2.intValue();
        TLRPC.TL_peerUser r3 = new TLRPC.TL_peerUser();
        r3.user_id = r22;
        r72.add(r3);
        goto L3
    L5:
        return r72;
    }

    public static ArrayList<TLRPC.Peer> deserialize_LongUserIdAsPeer(InputSerializedData r5, boolean r6) {
        ArrayList<Long> r52 = Vector.deserializeLong(r5, r6);
        ArrayList<TLRPC.Peer> r62 = new ArrayList(r52.size());
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Long r2 = r52.get(r1);
        r1 = r1 + 1;
        long r22 = r2.longValue();
        TLRPC.TL_peerUser r4 = new TLRPC.TL_peerUser();
        r4.user_id = r22;
        r62.add(r4);
        goto L3
    L5:
        return r62;
    }

    public static void serialize_LongAsInt(OutputSerializedData r5, ArrayList<Long> r6) {
        ArrayList r0 = new ArrayList(r6.size());
        int r1 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Long r3 = r6.get(r2);
        r2 = r2 + 1;
        r0.add(Integer.valueOf((int) r3.longValue()));
        goto L3
    L5:
        Vector.serializeInt(r5, r0);
    }

    public static void serialize_PeerAsIntUserId(OutputSerializedData r4, ArrayList<TLRPC.Peer> r5) {
        r4.writeInt32(Vector.constructor);
        int r0 = r5.size();
        r4.writeInt32(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4.writeInt32((int) r5.get(r1).user_id);
        r1 = r1 + 1;
        goto L3
    }

    public static void serialize_PeerAsLongUserId(OutputSerializedData r4, ArrayList<TLRPC.Peer> r5) {
        r4.writeInt32(Vector.constructor);
        int r0 = r5.size();
        r4.writeInt32(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4.writeInt64(r5.get(r1).user_id);
        r1 = r1 + 1;
        goto L3
    }
}
