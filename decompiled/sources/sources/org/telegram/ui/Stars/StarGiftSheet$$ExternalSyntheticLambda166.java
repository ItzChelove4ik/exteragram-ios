package org.telegram.ui.Stars;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda166 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;
    public final /* synthetic */ TLRPC.PaymentForm f$2;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ CharSequence f$5;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda166(StarGiftSheet r1, TL_stars.TL_starGiftUnique r2, TLRPC.PaymentForm r3, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r4, long r5, CharSequence r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r10, int r11) {
        StarGiftSheet.$r8$lambda$5vgS5xE_avPSJQ4wzFc_nU4eoeo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
