package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocaleController$$ExternalSyntheticLambda17 implements RequestDelegate {
    public final /* synthetic */ LocaleController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ LocaleController$$ExternalSyntheticLambda17(LocaleController r1, boolean r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LocaleController.m4235$r8$lambda$FCunhbozLbGYmln_ERn0NZAdZo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
