package org.telegram.ui.Stories;

import java.util.ArrayList;
import org.telegram.messenger.Timer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class StoriesStorage$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ StoriesStorage f$0;
    public final /* synthetic */ Timer.Task f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ Timer f$5;
    public final /* synthetic */ int[] f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ StoriesStorage$$ExternalSyntheticLambda2(StoriesStorage r1, Timer.Task r2, ArrayList r3, long r4, boolean r6, Timer r7, int[] r8, Runnable r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        StoriesStorage.m19724$r8$lambda$FPp505R03c8eTGSl13t6hmdIVk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
