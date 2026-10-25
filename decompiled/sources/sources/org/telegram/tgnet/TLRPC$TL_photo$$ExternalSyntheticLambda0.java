package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_photo$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public final /* synthetic */ TLRPC.TL_photo f$0;

    public /* synthetic */ TLRPC$TL_photo$$ExternalSyntheticLambda0(TLRPC.TL_photo r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.TL_photo.$r8$lambda$VjF8RQcVcN5aCCGq_lEGBZZTsE4(this.f$0, r1, r2, r3);
    }
}
