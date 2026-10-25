package androidx.camera.core.internal.utils;

import android.util.Size;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.CameraOrientationUtil;

/* loaded from: classes3.dex */
public abstract class UseCaseConfigUtil {
    public static void updateTargetRotationAndRelatedConfigs(UseCaseConfig.Builder<?, ?, ?> r4, int r5) {
        ImageOutputConfig r0 = (ImageOutputConfig) r4.getUseCaseConfig();
        int r2 = r0.getTargetRotation(-1);
        if (r2 == (-1)) goto L5;
        if (r2 != r5) goto L5;
    L6:
        if (r2 == (-1)) goto L16;
        if (r5 == (-1)) goto L17;
        if (r2 == r5) goto L20;
        int r1 = CameraOrientationUtil.surfaceRotationToDegrees(r2);
        if ((Math.abs(CameraOrientationUtil.surfaceRotationToDegrees(r5) - r1) % 180) != 90) goto L18;
        Size r52 = r0.getTargetResolution(null);
        if (r52 == null) goto L19;
        ((ImageOutputConfig.Builder) r4).setTargetResolution(new Size(r52.getHeight(), r52.getWidth()));
        return;
    L19:
        return;
    L18:
        return;
    L20:
        return;
    L17:
        return;
    L16:
        return;
    L5:
        ((ImageOutputConfig.Builder) r4).setTargetRotation(r5);
        goto L6
    }
}
