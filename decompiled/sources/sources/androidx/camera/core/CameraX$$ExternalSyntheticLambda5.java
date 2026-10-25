package androidx.camera.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraX$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ CameraX f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$5;

    public /* synthetic */ CameraX$$ExternalSyntheticLambda5(CameraX r1, Executor r2, long r3, int r5, Context r6, CallbackToFutureAdapter.Completer r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraX.$r8$lambda$gWQLJvFf0jycoyEFmkMCIA6OUZI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
