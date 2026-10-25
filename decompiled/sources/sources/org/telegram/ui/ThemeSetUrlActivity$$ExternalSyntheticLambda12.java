package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeSetUrlActivity$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ ThemeSetUrlActivity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ThemeSetUrlActivity$$ExternalSyntheticLambda12(ThemeSetUrlActivity r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ThemeSetUrlActivity.$r8$lambda$iP0Y7eLk3sHgR_ED7s2qGDd2DR4(this.f$0, this.f$1, r2, r3);
    }
}
