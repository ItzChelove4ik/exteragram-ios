package com.google.firebase.sessions.dagger.internal;

import c.f$$ExternalSyntheticBUOutline2;
import dagger.internal.Preconditions$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class Preconditions {
    public static <T> void checkBuilderRequirement(T r0, Class<T> r1) {
        if (r0 == null) goto L4;
        return;
    L4:
        Preconditions$$ExternalSyntheticBUOutline0.m(r1.getCanonicalName(), " must be set");
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static <T> T checkNotNull(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    public static <T> T checkNotNullFromProvides(T r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m("Cannot return null from a non-@Nullable @Provides method");
        return null;
    }
}
