package org.telegram.tgnet;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.json.TLJsonParser;

/* loaded from: classes6.dex */
public final /* synthetic */ class TLRPC$TL_textWithEntities$$ExternalSyntheticLambda0 implements Utilities.CallbackReturn {
    public /* synthetic */ TLRPC$TL_textWithEntities$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return TLRPC.MessageEntity.TLJsonDeserialize((TLJsonParser) r1);
    }
}
