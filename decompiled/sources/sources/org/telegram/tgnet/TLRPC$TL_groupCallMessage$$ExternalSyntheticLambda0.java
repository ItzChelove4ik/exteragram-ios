package org.telegram.tgnet;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.json.TLJsonParser;

/* loaded from: classes6.dex */
public final /* synthetic */ class TLRPC$TL_groupCallMessage$$ExternalSyntheticLambda0 implements Utilities.CallbackReturn {
    public /* synthetic */ TLRPC$TL_groupCallMessage$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return TLRPC.TL_textWithEntities.TLJsonDeserialize((TLJsonParser) r1);
    }
}
