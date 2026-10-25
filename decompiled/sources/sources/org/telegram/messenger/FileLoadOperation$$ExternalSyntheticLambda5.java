package org.telegram.messenger;

import org.telegram.messenger.FileLoadOperation;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class FileLoadOperation$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ FileLoadOperation f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ FileLoadOperation.RequestInfo f$2;

    public /* synthetic */ FileLoadOperation$$ExternalSyntheticLambda5(FileLoadOperation r1, int r2, FileLoadOperation.RequestInfo r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        FileLoadOperation.m4008$r8$lambda$PGmBHKfUhzxTmozPlzOM9C4_e0(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
