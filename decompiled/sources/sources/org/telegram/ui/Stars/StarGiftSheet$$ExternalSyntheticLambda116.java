package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda116 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ CharSequence f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda116(StarGiftSheet r1, CharSequence r2, TL_stars.TL_starGiftUnique r3, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarGiftSheet.$r8$lambda$U9XrMbh9k9_uWtDMuPzM1Ae0YgI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
