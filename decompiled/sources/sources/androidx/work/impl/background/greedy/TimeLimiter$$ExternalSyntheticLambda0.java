package androidx.work.impl.background.greedy;

import androidx.work.impl.StartStopToken;

/* loaded from: classes4.dex */
public final /* synthetic */ class TimeLimiter$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TimeLimiter f$0;
    public final /* synthetic */ StartStopToken f$1;

    public /* synthetic */ TimeLimiter$$ExternalSyntheticLambda0(TimeLimiter r1, StartStopToken r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TimeLimiter.$r8$lambda$dwAYwAFpPKNbMC8c13ZgeRxadTI(this.f$0, this.f$1);
    }
}
