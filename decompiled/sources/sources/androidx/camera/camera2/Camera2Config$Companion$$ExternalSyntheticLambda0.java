package androidx.camera.camera2;

import android.content.Context;
import androidx.camera.camera2.adapter.CameraSurfaceAdapter;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import java.util.Set;

/* loaded from: classes3.dex */
public final /* synthetic */ class Camera2Config$Companion$$ExternalSyntheticLambda0 implements CameraDeviceSurfaceManager.Provider {
    public /* synthetic */ Camera2Config$Companion$$ExternalSyntheticLambda0() {
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager.Provider
    public final CameraDeviceSurfaceManager newInstance(Context r1, Object r2, Set r3) {
        return new CameraSurfaceAdapter(r1, r2, r3);
    }
}
