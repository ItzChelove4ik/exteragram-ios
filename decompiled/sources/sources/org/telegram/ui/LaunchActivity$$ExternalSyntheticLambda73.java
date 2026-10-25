package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda73 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ TLRPC.TL_wallPaper f$2;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda73(LaunchActivity r1, Runnable r2, TLRPC.TL_wallPaper r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LaunchActivity.$r8$lambda$Or4XAZFzmdBhR5REeDY2X_ir4fY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
