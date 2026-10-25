package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda131 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStarGiftTransfer f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda131(StarGiftSheet r1, TLRPC.TL_inputInvoiceStarGiftTransfer r2, long r3, long r5, Utilities.Callback r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        StarGiftSheet.$r8$lambda$3SPhKaGRhjQZ9e8c7bCBoz3OSm8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11);
    }
}
