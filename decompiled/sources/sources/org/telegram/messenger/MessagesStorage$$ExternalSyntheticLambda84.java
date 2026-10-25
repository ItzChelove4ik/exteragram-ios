package org.telegram.messenger;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_ephemeral;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda84 implements Vector.TLDeserializer {
    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda84() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_ephemeral.EphemeralMessage.TLdeserialize(r1, r2, r3);
    }
}
