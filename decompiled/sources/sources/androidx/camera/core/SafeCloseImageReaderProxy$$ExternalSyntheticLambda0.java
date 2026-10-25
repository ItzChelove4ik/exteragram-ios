package androidx.camera.core;

import androidx.camera.core.impl.ImageReaderProxy;

/* loaded from: classes3.dex */
public final /* synthetic */ class SafeCloseImageReaderProxy$$ExternalSyntheticLambda0 implements ImageReaderProxy.OnImageAvailableListener {
    public final /* synthetic */ SafeCloseImageReaderProxy f$0;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$1;

    public /* synthetic */ SafeCloseImageReaderProxy$$ExternalSyntheticLambda0(SafeCloseImageReaderProxy r1, ImageReaderProxy.OnImageAvailableListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
    public final void onImageAvailable(ImageReaderProxy r2) {
        SafeCloseImageReaderProxy.$r8$lambda$Y9AMDHHHqfIdIOm9bxU2nToQD6w(this.f$0, this.f$1, r2);
    }
}
