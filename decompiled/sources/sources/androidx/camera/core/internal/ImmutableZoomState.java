package androidx.camera.core.internal;

import androidx.camera.core.ZoomState;

/* loaded from: classes3.dex */
public abstract class ImmutableZoomState implements ZoomState {
    public ImmutableZoomState() {
    }

    public static ZoomState create(float r1, float r2, float r3, float r4) {
        return new AutoValue_ImmutableZoomState(r1, r2, r3, r4);
    }

    @Override // androidx.camera.core.ZoomState
    public abstract float getLinearZoom();

    @Override // androidx.camera.core.ZoomState
    public abstract float getMaxZoomRatio();

    @Override // androidx.camera.core.ZoomState
    public abstract float getMinZoomRatio();

    @Override // androidx.camera.core.ZoomState
    public abstract float getZoomRatio();
}
