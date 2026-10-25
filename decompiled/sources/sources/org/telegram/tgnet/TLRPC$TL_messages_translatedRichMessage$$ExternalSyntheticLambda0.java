package org.telegram.tgnet;

import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes6.dex */
public final /* synthetic */ class TLRPC$TL_messages_translatedRichMessage$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ TLRPC$TL_messages_translatedRichMessage$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_iv.RichMessage.TLdeserialize(r1, r2, r3);
    }
}
