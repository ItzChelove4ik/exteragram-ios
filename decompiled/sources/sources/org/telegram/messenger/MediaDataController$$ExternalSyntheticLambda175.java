package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda175 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.Document f$1;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda175(MediaDataController r1, TLRPC.Document r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        MediaDataController.$r8$lambda$MIYQGC7mTvCUIjjy3tK1afK8goE(this.f$0, this.f$1, r2, r3);
    }
}
