package androidx.camera.camera2.adapter;

import androidx.camera.camera2.internal.ZoomMath;
import androidx.camera.core.ZoomState;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\tJ\u000f\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\tJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/camera/camera2/adapter/ZoomValue;", "Landroidx/camera/core/ZoomState;", _UrlKt.FRAGMENT_ENCODE_SET, "zoomRatio", "minZoomRatio", "maxZoomRatio", "<init>", "(FFF)V", "getZoomRatio", "()F", "getMaxZoomRatio", "getMinZoomRatio", "getLinearZoom", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "F", "linearZoom", "Ljava/lang/Float;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ZoomValue implements ZoomState {
    private Float linearZoom;
    private final float maxZoomRatio;
    private final float minZoomRatio;
    private final float zoomRatio;

    public ZoomValue(float r1, float r2, float r3) {
        this.zoomRatio = r1;
        this.minZoomRatio = r2;
        this.maxZoomRatio = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ZoomValue) == true) goto L8;
        return false;
    L8:
        ZoomValue r52 = (ZoomValue) r5;
        if (Float.compare(this.zoomRatio, r52.zoomRatio) == 0) goto L12;
        return false;
    L12:
        if (Float.compare(this.minZoomRatio, r52.minZoomRatio) == 0) goto L15;
        return false;
    L15:
        if (Float.compare(this.maxZoomRatio, r52.maxZoomRatio) == 0) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // androidx.camera.core.ZoomState
    public float getLinearZoom() {
        Float r0 = this.linearZoom;
        if (r0 == null) goto L7;
        return r0.floatValue();
    L7:
        return ZoomMath.INSTANCE.getLinearZoomFromZoomRatio(this.zoomRatio, this.minZoomRatio, this.maxZoomRatio);
    }

    @Override // androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.maxZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.minZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.zoomRatio;
    }

    public int hashCode() {
        return (((Float.hashCode(this.zoomRatio) * 31) + Float.hashCode(this.minZoomRatio)) * 31) + Float.hashCode(this.maxZoomRatio);
    }

    public String toString() {
        return "ZoomValue(zoomRatio=" + this.zoomRatio + ", minZoomRatio=" + this.minZoomRatio + ", maxZoomRatio=" + this.maxZoomRatio + ')';
    }
}
