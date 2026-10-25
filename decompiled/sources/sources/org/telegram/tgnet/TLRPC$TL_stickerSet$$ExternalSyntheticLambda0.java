package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_stickerSet$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public final /* synthetic */ TLRPC.TL_stickerSet f$0;

    public /* synthetic */ TLRPC$TL_stickerSet$$ExternalSyntheticLambda0(TLRPC.TL_stickerSet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.TL_stickerSet.m5396$r8$lambda$wB9tbgI9JgOMnIeJ5BrX6_FaJw(this.f$0, r1, r2, r3);
    }
}
