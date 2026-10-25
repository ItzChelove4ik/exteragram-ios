package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda7 implements Executor {
    public final /* synthetic */ BackgroundThreadStateHandler f$0;

    public /* synthetic */ DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda7(BackgroundThreadStateHandler r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.f$0.runInBackground(r1);
    }
}
