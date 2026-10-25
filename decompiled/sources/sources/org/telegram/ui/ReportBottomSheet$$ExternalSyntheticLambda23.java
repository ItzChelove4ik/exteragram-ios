package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ ReportBottomSheet f$0;
    public final /* synthetic */ CharSequence f$1;
    public final /* synthetic */ byte[] f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda23(ReportBottomSheet r1, CharSequence r2, byte[] r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ReportBottomSheet.$r8$lambda$4dP6GcZQU6PjDemQ6CNeJ9bRhzI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
