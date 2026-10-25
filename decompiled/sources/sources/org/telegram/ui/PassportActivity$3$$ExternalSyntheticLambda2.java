package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$3$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ PassportActivity.AnonymousClass3 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$3;
    public final /* synthetic */ TL_account.verifyEmail f$4;

    public /* synthetic */ PassportActivity$3$$ExternalSyntheticLambda2(PassportActivity.AnonymousClass3 r1, TLRPC.TL_error r2, Runnable r3, PassportActivity.ErrorRunnable r4, TL_account.verifyEmail r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.AnonymousClass3.$r8$lambda$AOSi3WDjpqQ6_zJKh1j4JSM_VqE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
