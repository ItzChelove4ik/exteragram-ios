package com.exteragram.messenger.preferences.utils;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class SettingsRegistry$$ExternalSyntheticBackport1 {
    public static /* synthetic */ Map m(Map.Entry[] r5) {
        HashMap r0 = new HashMap(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        Map.Entry r3 = r5[r2];
        Object r4 = r3.getKey();
        Objects.requireNonNull(r4);
        Object r32 = r3.getValue();
        Objects.requireNonNull(r32);
        if (r0.put(r4, r32) != null) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        Native$$ExternalSyntheticBUOutline5.m("duplicate key: ", r4);
        return null;
    L10:
        return Collections.unmodifiableMap(r0);
    }
}
