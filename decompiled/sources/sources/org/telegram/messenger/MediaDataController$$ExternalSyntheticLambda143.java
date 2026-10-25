package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda143 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ TLRPC.TL_messages_faveSticker f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda143(MediaDataController r1, Object r2, TLRPC.TL_messages_faveSticker r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MediaDataController.$r8$lambda$SUeplJhyJA1Ku6ozx0bZd9G6LaM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
