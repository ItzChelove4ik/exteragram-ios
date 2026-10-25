package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda129 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda129(StarGiftSheet r1, Utilities.Callback r2, long r3, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        StarGiftSheet.m18975$r8$lambda$ViLFGviVcaT6Z8tcMvKZ1KMOAU(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10);
    }
}
