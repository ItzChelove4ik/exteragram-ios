package androidx.camera.core;

import java.util.Set;

/* loaded from: classes3.dex */
public interface CameraPresenceListener {
    void onCamerasAdded(Set<CameraIdentifier> r1);

    void onCamerasRemoved(Set<CameraIdentifier> r1);
}
