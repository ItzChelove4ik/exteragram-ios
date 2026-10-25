package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stats;

/* loaded from: classes6.dex */
public final /* synthetic */ class TL_stats$TL_megagroupStats$$ExternalSyntheticLambda2 implements Vector.TLDeserializer {
    public /* synthetic */ TL_stats$TL_megagroupStats$$ExternalSyntheticLambda2() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_stats.TL_statsGroupTopInviter.TLdeserialize(r1, r2, r3);
    }
}
