package androidx.camera.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraX$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ CameraX f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ CameraX$$ExternalSyntheticLambda4(CameraX r1, Context r2, Executor r3, int r4, CallbackToFutureAdapter.Completer r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraX.m584$r8$lambda$rcLbpxfditYP58cYRRHyT98STc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
