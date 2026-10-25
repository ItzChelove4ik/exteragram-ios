package org.telegram.messenger;

import org.telegram.messenger.FileLoadOperation;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class FileLoadOperation$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ FileLoadOperation f$0;
    public final /* synthetic */ FileLoadOperation.RequestInfo f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLObject f$4;

    public /* synthetic */ FileLoadOperation$$ExternalSyntheticLambda2(FileLoadOperation r1, FileLoadOperation.RequestInfo r2, int r3, int r4, TLObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        FileLoadOperation.$r8$lambda$BCAhGT9xkszqkO4gBgMos4WJ75k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
