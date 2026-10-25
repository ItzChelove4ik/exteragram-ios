package androidx.camera.core.processing.concurrent;

import androidx.camera.core.DynamicRange;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Map;

/* loaded from: classes3.dex */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ DynamicRange f$1;
    public final /* synthetic */ Map f$2;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$3;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda4(DualSurfaceProcessor r1, DynamicRange r2, Map r3, CallbackToFutureAdapter.Completer r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DualSurfaceProcessor.m624$r8$lambda$gvtYv8V6pqQmb0lqnGgcqf1W30(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
