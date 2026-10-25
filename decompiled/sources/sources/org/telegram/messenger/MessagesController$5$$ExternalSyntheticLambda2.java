package org.telegram.messenger;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$5$$ExternalSyntheticLambda2 implements Vector.TLDeserializer {
    public /* synthetic */ MessagesController$5$$ExternalSyntheticLambda2() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_account.WebBrowserSettings.TLdeserialize(r1, r2, r3);
    }
}
