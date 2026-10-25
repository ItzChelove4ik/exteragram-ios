package androidx.camera.core.processing;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ SurfaceTexture f$2;
    public final /* synthetic */ Surface f$3;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda8(DefaultSurfaceProcessor r1, SurfaceRequest r2, SurfaceTexture r3, Surface r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r4) {
        DefaultSurfaceProcessor.m606$r8$lambda$6ehatn4QOFUxJsVy7XqEaXdA8(this.f$0, this.f$1, this.f$2, this.f$3, (SurfaceRequest.Result) r4);
    }
}
