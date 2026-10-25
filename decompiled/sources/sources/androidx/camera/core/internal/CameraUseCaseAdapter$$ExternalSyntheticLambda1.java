package androidx.camera.core.internal;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraUseCaseAdapter$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ Surface f$0;
    public final /* synthetic */ SurfaceTexture f$1;

    public /* synthetic */ CameraUseCaseAdapter$$ExternalSyntheticLambda1(Surface r1, SurfaceTexture r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        CameraUseCaseAdapter.m602$r8$lambda$LnyTrDpxDU4Lj0jFr7wqOCUqwI(this.f$0, this.f$1, (SurfaceRequest.Result) r2);
    }
}
