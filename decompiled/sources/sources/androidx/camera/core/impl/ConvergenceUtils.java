package androidx.camera.core.impl;

import androidx.camera.core.Logger;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class ConvergenceUtils {
    private static final Set<CameraCaptureMetaData$AeState> AE_CONVERGED_STATE_SET = null;
    private static final Set<CameraCaptureMetaData$AeState> AE_TORCH_AS_FLASH_CONVERGED_STATE_SET = null;
    private static final Set<CameraCaptureMetaData$AfState> AF_CONVERGED_STATE_SET = null;
    private static final Set<CameraCaptureMetaData$AwbState> AWB_CONVERGED_STATE_SET = null;

    static {
        AF_CONVERGED_STATE_SET = Collections.unmodifiableSet(EnumSet.of(CameraCaptureMetaData$AfState.PASSIVE_FOCUSED, CameraCaptureMetaData$AfState.PASSIVE_NOT_FOCUSED, CameraCaptureMetaData$AfState.LOCKED_FOCUSED, CameraCaptureMetaData$AfState.LOCKED_NOT_FOCUSED));
        AWB_CONVERGED_STATE_SET = Collections.unmodifiableSet(EnumSet.of(CameraCaptureMetaData$AwbState.CONVERGED, CameraCaptureMetaData$AwbState.UNKNOWN));
        CameraCaptureMetaData$AeState r0 = CameraCaptureMetaData$AeState.CONVERGED;
        CameraCaptureMetaData$AeState r1 = CameraCaptureMetaData$AeState.FLASH_REQUIRED;
        CameraCaptureMetaData$AeState r2 = CameraCaptureMetaData$AeState.UNKNOWN;
        Set<CameraCaptureMetaData$AeState> r02 = Collections.unmodifiableSet(EnumSet.of(r0, r1, r2));
        AE_CONVERGED_STATE_SET = r02;
        EnumSet r03 = EnumSet.copyOf(r02);
        r03.remove(r1);
        r03.remove(r2);
        AE_TORCH_AS_FLASH_CONVERGED_STATE_SET = Collections.unmodifiableSet(r03);
    }

    public static boolean is3AConverged(CameraCaptureResult r6, boolean r7) {
        if (r6.getAfMode() != CameraCaptureMetaData$AfMode.OFF) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (r6.getAeMode() != CameraCaptureMetaData$AeMode.OFF) goto L12;
        boolean r1 = true;
    L13:
        if (r7 == false) goto L20;
        if (r1 == false) goto L16;
    L19:
        boolean r72 = true;
    L25:
        if (r6.getAwbMode() != CameraCaptureMetaData$AwbMode.OFF) goto L28;
    L29:
        boolean r12 = true;
    L31:
        Logger.d("ConvergenceUtils", "checkCaptureResult, AE=" + r6.getAeState() + " AF =" + r6.getAfState() + " AWB=" + r6.getAwbState());
        if (r0 == false) goto L36;
        if (r72 == false) goto L36;
        if (r12 == false) goto L36;
        return true;
    L36:
        return false;
    L28:
        if (AWB_CONVERGED_STATE_SET.contains(r6.getAwbState()) == true) goto L29;
        r12 = false;
        goto L31
    L16:
        if (AE_TORCH_AS_FLASH_CONVERGED_STATE_SET.contains(r6.getAeState()) == true) goto L19;
    L18:
        r72 = false;
        goto L25
    L20:
        if (r1 == true) goto L19;
        if (AE_CONVERGED_STATE_SET.contains(r6.getAeState()) == false) goto L18;
    L12:
        r1 = false;
        goto L13
    L5:
        if (AF_CONVERGED_STATE_SET.contains(r6.getAfState()) == true) goto L8;
        r0 = false;
        goto L10
    }
}
