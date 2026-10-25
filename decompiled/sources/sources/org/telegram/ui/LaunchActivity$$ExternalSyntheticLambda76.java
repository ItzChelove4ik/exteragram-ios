package org.telegram.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda76 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ Integer f$4;
    public final /* synthetic */ Integer f$5;
    public final /* synthetic */ Long f$6;
    public final /* synthetic */ Integer f$7;
    public final /* synthetic */ AtomicBoolean f$8;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda76(LaunchActivity r1, int[] r2, int r3, Runnable r4, Integer r5, Integer r6, Long r7, Integer r8, AtomicBoolean r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        LaunchActivity.$r8$lambda$UB3QRxTV1JlkRWFJNnKNVVxJZes(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r12, r13);
    }
}
