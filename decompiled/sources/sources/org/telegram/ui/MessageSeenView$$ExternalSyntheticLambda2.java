package org.telegram.ui;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageSeenView$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ MessageSeenView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ HashMap f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ MessageSeenView$$ExternalSyntheticLambda2(MessageSeenView r1, int r2, HashMap r3, ArrayList r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MessageSeenView.m16109$r8$lambda$0X1wkrCpC8sXPF0Fa4wTCqXrmU(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
