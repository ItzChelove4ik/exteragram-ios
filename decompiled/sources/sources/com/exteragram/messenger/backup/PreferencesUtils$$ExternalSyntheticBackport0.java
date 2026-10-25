package com.exteragram.messenger.backup;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes.dex */
public abstract /* synthetic */ class PreferencesUtils$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Set m(Object[] r5) {
        HashSet r0 = new HashSet(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        Object r3 = r5[r2];
        Objects.requireNonNull(r3);
        if (r0.add(r3) == false) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        Native$$ExternalSyntheticBUOutline5.m("duplicate element: ", r3);
        return null;
    L10:
        return Collections.unmodifiableSet(r0);
    }
}
