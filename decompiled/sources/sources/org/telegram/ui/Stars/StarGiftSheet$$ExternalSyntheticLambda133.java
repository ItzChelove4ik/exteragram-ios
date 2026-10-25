package org.telegram.ui.Stars;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda133 implements Runnable {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ CharSequence f$2;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$3;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda133(StarGiftSheet r1, TLObject r2, CharSequence r3, TL_stars.TL_starGiftUnique r4, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r5, TLRPC.TL_error r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarGiftSheet.$r8$lambda$Dbym5fCyoqEjHaSltgXJ1D1NbjE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
