package androidx.camera.core.processing;

import androidx.camera.core.impl.Quirks$$ExternalSyntheticBackport0;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class TargetUtils {
    public static String getHumanReadableName(int r2) {
        ArrayList r0 = new ArrayList();
        if ((r2 & 4) == 0) goto L6;
        r0.add("IMAGE_CAPTURE");
    L6:
        if ((r2 & 1) == 0) goto L9;
        r0.add("PREVIEW");
    L9:
        if ((r2 & 2) == 0) goto L12;
        r0.add("VIDEO_CAPTURE");
    L12:
        return Quirks$$ExternalSyntheticBackport0.m("|", r0);
    }

    public static boolean isSuperset(int r0, int r1) {
        if ((r0 & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }
}
