package androidx.camera.core.impl;

import android.content.Context;
import androidx.camera.core.CameraInfo;

/* loaded from: classes3.dex */
public interface CameraConfigProvider {
    public static final CameraConfigProvider EMPTY = null;

    static /* synthetic */ CameraConfig $r8$lambda$7WTJfU0r9J9r4DloMvQnvbitKag(CameraInfo r0, Context r1) {
        return null;
    }

    static {
        EMPTY = new CameraConfigProvider$$ExternalSyntheticLambda0();
    }

    CameraConfig getConfig(CameraInfo r1, Context r2);
}
