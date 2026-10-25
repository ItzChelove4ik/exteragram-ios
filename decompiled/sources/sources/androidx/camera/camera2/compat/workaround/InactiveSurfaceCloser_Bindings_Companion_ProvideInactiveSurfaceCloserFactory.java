package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class InactiveSurfaceCloser_Bindings_Companion_ProvideInactiveSurfaceCloserFactory implements Provider {
    public static InactiveSurfaceCloser provideInactiveSurfaceCloser(CameraQuirks r1) {
        return (InactiveSurfaceCloser) Preconditions.checkNotNullFromProvides(InactiveSurfaceCloser.Bindings.Companion.provideInactiveSurfaceCloser(r1));
    }
}
