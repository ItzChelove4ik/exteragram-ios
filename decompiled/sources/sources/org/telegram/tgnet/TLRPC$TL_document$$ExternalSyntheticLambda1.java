package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_document$$ExternalSyntheticLambda1 implements Vector.TLDeserializer {
    public final /* synthetic */ TLRPC.TL_document f$0;

    public /* synthetic */ TLRPC$TL_document$$ExternalSyntheticLambda1(TLRPC.TL_document r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.TL_document.$r8$lambda$Ue4T8YXoUvJEGMXRLLOssWmTCV8(this.f$0, r1, r2, r3);
    }
}
