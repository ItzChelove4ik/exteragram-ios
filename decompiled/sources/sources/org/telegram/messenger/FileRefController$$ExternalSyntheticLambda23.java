package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileRefController$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ FileRefController f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ FileRefController$$ExternalSyntheticLambda23(FileRefController r1, String r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        FileRefController.$r8$lambda$TnBVAujtfk0a9yiAMVfDaV7V4c8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
