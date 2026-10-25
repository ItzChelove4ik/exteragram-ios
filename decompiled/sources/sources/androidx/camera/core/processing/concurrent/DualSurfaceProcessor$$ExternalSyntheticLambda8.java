package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceTexture f$1;
    public final /* synthetic */ Surface f$2;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda8(DualSurfaceProcessor r1, SurfaceTexture r2, Surface r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r3) {
        DualSurfaceProcessor.m620$r8$lambda$NAKKUHmbm6lwk7NskA4M9WlNg(this.f$0, this.f$1, this.f$2, (SurfaceRequest.Result) r3);
    }
}
