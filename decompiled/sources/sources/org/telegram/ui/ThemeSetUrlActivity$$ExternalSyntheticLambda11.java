package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeSetUrlActivity$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ ThemeSetUrlActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TL_account.updateTheme f$2;

    public /* synthetic */ ThemeSetUrlActivity$$ExternalSyntheticLambda11(ThemeSetUrlActivity r1, TLRPC.TL_error r2, TL_account.updateTheme r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ThemeSetUrlActivity.m20854$r8$lambda$bpaTEfimMi7JwCA85429ymtGEg(this.f$0, this.f$1, this.f$2);
    }
}
