package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda106 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.TL_channels_getMessages f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda106(MediaDataController r1, long r2, long r4, TLRPC.TL_channels_getMessages r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MediaDataController.$r8$lambda$I9xs9PtBzMtpEZVYvcnuzCPUBfQ(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10);
    }
}
