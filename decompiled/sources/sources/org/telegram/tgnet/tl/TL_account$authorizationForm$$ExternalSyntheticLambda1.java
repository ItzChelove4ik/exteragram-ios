package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public final /* synthetic */ class TL_account$authorizationForm$$ExternalSyntheticLambda1 implements Vector.TLDeserializer {
    public /* synthetic */ TL_account$authorizationForm$$ExternalSyntheticLambda1() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.SecureValueError.TLdeserialize(r1, r2, r3);
    }
}
