package androidx.camera.camera2.compat;

import androidx.camera.camera2.compat.ZoomCompat;
import androidx.camera.camera2.impl.CameraProperties;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class ZoomCompat_Bindings_Companion_ProvideZoomCompatFactory implements Provider {
    public static ZoomCompat provideZoomCompat(CameraProperties r1) {
        return (ZoomCompat) Preconditions.checkNotNullFromProvides(ZoomCompat.Bindings.Companion.provideZoomCompat(r1));
    }
}
