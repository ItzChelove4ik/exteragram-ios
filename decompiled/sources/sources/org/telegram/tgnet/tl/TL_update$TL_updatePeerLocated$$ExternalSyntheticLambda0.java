package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public final /* synthetic */ class TL_update$TL_updatePeerLocated$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ TL_update$TL_updatePeerLocated$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.PeerLocated.TLdeserialize(r1, r2, r3);
    }
}
