package org.telegram.messenger;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TranslateController$PollText$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ TranslateController$PollText$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.PollAnswer.TLdeserialize(r1, r2, r3);
    }
}
