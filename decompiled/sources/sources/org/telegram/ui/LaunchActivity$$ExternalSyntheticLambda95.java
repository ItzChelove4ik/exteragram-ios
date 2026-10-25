package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda95 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TL_account.authorizationForm f$3;
    public final /* synthetic */ TL_account.getAuthorizationForm f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ String f$7;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda95(LaunchActivity r1, Runnable r2, int r3, TL_account.authorizationForm r4, TL_account.getAuthorizationForm r5, String r6, String r7, String r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        LaunchActivity.$r8$lambda$a_23XaUKR_ow7phOwoQoz85UZBo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r11, r12);
    }
}
