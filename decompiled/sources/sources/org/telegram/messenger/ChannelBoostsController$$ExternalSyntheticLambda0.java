package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChannelBoostsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;

    public /* synthetic */ ChannelBoostsController$$ExternalSyntheticLambda0(TLObject r1, Utilities.Callback r2, TLRPC.TL_error r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelBoostsController.$r8$lambda$01Fplo7yr8leugbyDScQPYOwcXo(this.f$0, this.f$1, this.f$2);
    }
}
