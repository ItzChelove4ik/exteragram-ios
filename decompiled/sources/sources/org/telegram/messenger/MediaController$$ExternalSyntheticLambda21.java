package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaController$$ExternalSyntheticLambda21 implements RequestDelegate {
    public final /* synthetic */ MediaController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaController$$ExternalSyntheticLambda21(MediaController r1, int r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MediaController.$r8$lambda$MiHmKQV9eVHsTGARmUNU2zz87Uw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
