package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda100 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Integer f$1;
    public final /* synthetic */ Integer f$2;
    public final /* synthetic */ byte[] f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ int f$8;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda100(LaunchActivity r1, Integer r2, Integer r3, byte[] r4, long r5, Runnable r7, String r8, int r9, int r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r13, TLRPC.TL_error r14) {
        LaunchActivity.$r8$lambda$lbdgq7YGCmAzQAGc5a6rhzT1onM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r13, r14);
    }
}
