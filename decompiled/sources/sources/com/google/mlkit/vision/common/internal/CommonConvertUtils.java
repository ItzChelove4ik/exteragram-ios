package com.google.mlkit.vision.common.internal;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class CommonConvertUtils {
    public static int convertToMVRotation(int r1) {
        if (r1 != 0) goto L4;
        return 0;
    L4:
        if (r1 != 90) goto L6;
        return 1;
    L6:
        if (r1 != 180) goto L8;
        return 2;
    L8:
        if (r1 != 270) goto L11;
        return 3;
    L11:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid rotation: ", r1);
        return 0;
    }
}
