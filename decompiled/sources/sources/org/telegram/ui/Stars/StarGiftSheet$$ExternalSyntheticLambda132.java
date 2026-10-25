package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda132 implements Runnable {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStarGiftTransfer f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Utilities.Callback f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda132(StarGiftSheet r1, TLObject r2, TLRPC.TL_inputInvoiceStarGiftTransfer r3, long r4, long r6, Utilities.Callback r8, TLRPC.TL_error r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
        this.f$6 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarGiftSheet.m18951$r8$lambda$5zBIt4zEZYPIoxwbhNgVs3XsxI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
