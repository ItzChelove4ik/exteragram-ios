package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsList$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ StarsController.GiftsList f$0;

    public /* synthetic */ StarsController$GiftsList$$ExternalSyntheticLambda4(StarsController.GiftsList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StarsController.GiftsList.m19150$r8$lambda$e78YbGIubqcyGaOdw3hmk91zpA(this.f$0, r1, r2);
    }
}
