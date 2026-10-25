package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsCollections$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StarsController.GiftsCollections f$0;

    public /* synthetic */ StarsController$GiftsCollections$$ExternalSyntheticLambda0(StarsController.GiftsCollections r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StarsController.GiftsCollections.m19145$r8$lambda$3DcNoxECVkEJ87EZyBr3Mu8fnc(this.f$0, r1, r2);
    }
}
