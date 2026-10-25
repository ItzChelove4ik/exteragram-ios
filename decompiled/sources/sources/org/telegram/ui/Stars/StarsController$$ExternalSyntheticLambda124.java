package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda124 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStars f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda124(StarsController r1, TLRPC.TL_error r2, Utilities.Callback2 r3, TLObject r4, TLRPC.TL_inputInvoiceStars r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19116$r8$lambda$LycbN3r_ngPcRG4hiURwPlLHNY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
