package androidx.camera.core.internal;

/* loaded from: classes3.dex */
final class AutoValue_ImmutableZoomState extends ImmutableZoomState {
    private final float linearZoom;
    private final float maxZoomRatio;
    private final float minZoomRatio;
    private final float zoomRatio;

    public AutoValue_ImmutableZoomState(float r1, float r2, float r3, float r4) {
        this.zoomRatio = r1;
        this.maxZoomRatio = r2;
        this.minZoomRatio = r3;
        this.linearZoom = r4;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ImmutableZoomState) == false) goto L16;
        ImmutableZoomState r52 = (ImmutableZoomState) r5;
        if (Float.floatToIntBits(this.zoomRatio) != Float.floatToIntBits(r52.getZoomRatio())) goto L16;
        if (Float.floatToIntBits(this.maxZoomRatio) != Float.floatToIntBits(r52.getMaxZoomRatio())) goto L16;
        if (Float.floatToIntBits(this.minZoomRatio) != Float.floatToIntBits(r52.getMinZoomRatio())) goto L16;
        if (Float.floatToIntBits(this.linearZoom) != Float.floatToIntBits(r52.getLinearZoom())) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getLinearZoom() {
        return this.linearZoom;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.maxZoomRatio;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.minZoomRatio;
    }

    @Override // androidx.camera.core.internal.ImmutableZoomState, androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.zoomRatio;
    }

    public int hashCode() {
        int r0 = (((((Float.floatToIntBits(this.zoomRatio) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.maxZoomRatio)) * 1000003) ^ Float.floatToIntBits(this.minZoomRatio)) * 1000003;
        return Float.floatToIntBits(this.linearZoom) ^ r0;
    }

    public String toString() {
        return "ImmutableZoomState{zoomRatio=" + this.zoomRatio + ", maxZoomRatio=" + this.maxZoomRatio + ", minZoomRatio=" + this.minZoomRatio + ", linearZoom=" + this.linearZoom + "}";
    }
}
