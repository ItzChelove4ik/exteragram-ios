package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.TL_messageRange.TLdeserialize(r1, r2, r3);
    }
}
