package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes.dex */
public final /* synthetic */ class TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1 implements Vector.TLDeserializer {
    public /* synthetic */ TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_stars.StarsAmount.TLdeserialize(r1, r2, r3);
    }
}
