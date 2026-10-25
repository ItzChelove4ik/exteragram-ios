package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda87 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda87(LaunchActivity r1, int r2, String r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LaunchActivity.$r8$lambda$F3S0tbIDNp9jhgT0GU73GvVC9dw(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
