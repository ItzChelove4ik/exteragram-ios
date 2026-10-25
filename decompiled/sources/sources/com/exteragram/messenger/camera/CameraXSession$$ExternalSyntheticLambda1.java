package com.exteragram.messenger.camera;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ SurfaceRequest f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda1(SurfaceRequest r1, Surface r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        CameraXSession.m1140$r8$lambda$jewnXiGL03oQsgonhL2QZ_SOiI(this.f$0, this.f$1, (SurfaceRequest.Result) r2);
    }
}
