package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda16 implements RequestDelegate {
    public final /* synthetic */ FactCheckController f$0;
    public final /* synthetic */ TLRPC.TL_getFactCheck f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ HashMap f$3;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda16(FactCheckController r1, TLRPC.TL_getFactCheck r2, ArrayList r3, HashMap r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        FactCheckController.m3996$r8$lambda$EvefuM2DWexX6YT77dm7f5qJGY(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
