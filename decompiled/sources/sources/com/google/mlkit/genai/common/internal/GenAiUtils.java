package com.google.mlkit.genai.common.internal;

import android.content.Context;

/* loaded from: classes5.dex */
public abstract class GenAiUtils {
    public static boolean isAiCoreCompatible(Context r1) {
        if (zza(r1) < 193575) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isSystemPromptSupported(Context r1) {
        if (zza(r1) < 457627) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isThinkingModeSupported(Context r1) {
        if (zza(r1) < 457627) goto L6;
        return true;
    L6:
        return false;
    }

    private static int zza(Context r2) {
        return r2.getPackageManager().getPackageInfo("com.google.android.aicore", 0).versionCode;
    L5:
        return 0;
    }
}
