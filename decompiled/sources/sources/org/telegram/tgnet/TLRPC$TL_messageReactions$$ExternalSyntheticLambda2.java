package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_messageReactions$$ExternalSyntheticLambda2 implements Vector.TLDeserializer {
    public /* synthetic */ TLRPC$TL_messageReactions$$ExternalSyntheticLambda2() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.MessageReactor.TLdeserialize(r1, r2, r3);
    }
}
