package org.telegram.messenger;

import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda7(TopicsController r1, boolean r2, long r3, HashSet r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        TopicsController.$r8$lambda$T7_cMtzy9bptiGfz7CfRqs5bA1c(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
