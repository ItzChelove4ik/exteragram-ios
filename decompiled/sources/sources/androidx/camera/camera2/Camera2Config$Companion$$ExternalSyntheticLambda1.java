package androidx.camera.camera2;

import android.content.Context;
import androidx.camera.camera2.adapter.CameraUseCaseAdapter;
import androidx.camera.core.impl.UseCaseConfigFactory;

/* loaded from: classes3.dex */
public final /* synthetic */ class Camera2Config$Companion$$ExternalSyntheticLambda1 implements UseCaseConfigFactory.Provider {
    public /* synthetic */ Camera2Config$Companion$$ExternalSyntheticLambda1() {
    }

    @Override // androidx.camera.core.impl.UseCaseConfigFactory.Provider
    public final UseCaseConfigFactory newInstance(Context r1) {
        return new CameraUseCaseAdapter(r1);
    }
}
