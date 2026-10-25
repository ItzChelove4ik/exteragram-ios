package org.chromium.net.impl;

import org.chromium.net.ApiVersion;

/* loaded from: classes5.dex */
public abstract class VersionSafeCallbacks$ApiVersion {
    public static String getCronetVersion() {
        return ApiVersion.getCronetVersion();
    }

    public static int getMaximumAvailableApiLevel() {
        if (Integer.parseInt(getCronetVersion().split("\\.")[0]) >= 59) goto L7;
        return ApiVersion.getApiLevel();
    L7:
        return ApiVersion.getMaximumAvailableApiLevel();
    }
}
