package androidx.camera.lifecycle;

import androidx.camera.core.CameraIdentifier;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_LifecycleCameraRepository_Key extends LifecycleCameraRepository.Key {
    private final CameraIdentifier cameraIdentifier;
    private final int lifecycleOwnerHash;

    public AutoValue_LifecycleCameraRepository_Key(int r1, CameraIdentifier r2) {
        this.lifecycleOwnerHash = r1;
        if (r2 == null) goto L6;
        this.cameraIdentifier = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null cameraIdentifier");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof LifecycleCameraRepository.Key) == false) goto L12;
        LifecycleCameraRepository.Key r52 = (LifecycleCameraRepository.Key) r5;
        if (this.lifecycleOwnerHash != r52.getLifecycleOwnerHash()) goto L12;
        if (this.cameraIdentifier.equals(r52.getCameraIdentifier()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.Key
    public CameraIdentifier getCameraIdentifier() {
        return this.cameraIdentifier;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.Key
    public int getLifecycleOwnerHash() {
        return this.lifecycleOwnerHash;
    }

    public int hashCode() {
        int r0 = (this.lifecycleOwnerHash ^ 1000003) * 1000003;
        return this.cameraIdentifier.hashCode() ^ r0;
    }

    public String toString() {
        return "Key{lifecycleOwnerHash=" + this.lifecycleOwnerHash + ", cameraIdentifier=" + this.cameraIdentifier + "}";
    }
}
