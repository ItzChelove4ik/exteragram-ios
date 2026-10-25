package androidx.core.util;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ObjectsCompat {
    public static boolean equals(Object r0, Object r1) {
        return Objects.equals(r0, r1);
    }

    public static int hash(Object... r0) {
        return Objects.hash(r0);
    }

    public static <T> T requireNonNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static <T> T requireNonNull(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }
}
