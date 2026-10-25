package com.exteragram.messenger.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import com.exteragram.messenger.camera.CameraXSession;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda9 implements Preview.SurfaceProvider {
    public final /* synthetic */ CameraXSession.PreviewSizeListener f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ SurfaceTexture f$2;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda9(CameraXSession.PreviewSizeListener r1, Context r2, SurfaceTexture r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.camera.core.Preview.SurfaceProvider
    public final void onSurfaceRequested(SurfaceRequest r3) {
        CameraXSession.$r8$lambda$293GI9xWXKNG5kPKaSxWt6Uy9f4(this.f$0, this.f$1, this.f$2, r3);
    }
}
