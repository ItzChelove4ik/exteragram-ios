package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_messages_channelMessages$$ExternalSyntheticLambda1 implements Vector.TLDeserializer {
    public /* synthetic */ TLRPC$TL_messages_channelMessages$$ExternalSyntheticLambda1() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.TL_forumTopic.TLdeserialize(r1, r2, r3);
    }
}
