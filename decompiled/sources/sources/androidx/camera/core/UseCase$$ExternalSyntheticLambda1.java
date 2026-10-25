package androidx.camera.core;

import androidx.camera.core.RotationProvider;

/* loaded from: classes3.dex */
public final /* synthetic */ class UseCase$$ExternalSyntheticLambda1 implements RotationProvider.Listener {
    public final /* synthetic */ UseCase f$0;

    public /* synthetic */ UseCase$$ExternalSyntheticLambda1(UseCase r1) {
        this.f$0 = r1;
    }

    @Override // androidx.camera.core.RotationProvider.Listener
    public final void onRotationChanged(int r1) {
        this.f$0.onProviderRotationChanged(r1);
    }
}
