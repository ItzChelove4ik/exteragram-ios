package androidx.camera.core.impl.utils;

import androidx.camera.core.Logger;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class CameraOrientationUtil {
    public static int getRelativeImageRotation(int r3, int r4, boolean r5) {
        if (r5 == false) goto L4;
        int r0 = ((r4 - r3) + 360) % 360;
    L6:
        if (Logger.isVerboseEnabled("CameraOrientationUtil") == false) goto L8;
        Logger.d("CameraOrientationUtil", String.format("getRelativeImageRotation: destRotationDegrees=%s, sourceRotationDegrees=%s, isOppositeFacing=%s, result=%s", new Object[]{Integer.valueOf(r3), Integer.valueOf(r4), Boolean.valueOf(r5), Integer.valueOf(r0)}));
    L8:
        return r0;
    L4:
        r0 = (r4 + r3) % 360;
        goto L6
    }

    public static int surfaceRotationToDegrees(int r1) {
        if (r1 != 0) goto L4;
        return 0;
    L4:
        if (r1 != 1) goto L6;
        return 90;
    L6:
        if (r1 != 2) goto L8;
        return 180;
    L8:
        if (r1 != 3) goto L11;
        return 270;
    L11:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unsupported surface rotation: ", r1);
        return 0;
    }
}
