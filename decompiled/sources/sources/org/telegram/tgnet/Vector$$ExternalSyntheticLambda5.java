package org.telegram.tgnet;

import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda5 implements Vector.TLDeserializer {
    public /* synthetic */ Vector$$ExternalSyntheticLambda5() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return Vector.Int.TLDeserialize(r1, r2, r3);
    }
}
