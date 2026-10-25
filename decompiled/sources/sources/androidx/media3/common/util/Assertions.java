package androidx.media3.common.util;

import android.text.TextUtils;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class Assertions {
    @Pure
    public static void checkArgument(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    @Pure
    public static void checkArgument(boolean r0, Object r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalArgumentException(String.valueOf(r1));
    }

    @Pure
    public static int checkIndex(int r0, int r1, int r2) {
        if (r0 < r1) goto L6;
        if (r0 >= r2) goto L6;
        return r0;
    L6:
        throw new IndexOutOfBoundsException();
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static String checkNotEmpty(String r1) {
        if (TextUtils.isEmpty(r1) == true) goto L5;
        return r1;
    L5:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkNotNull(T r0, Object r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        throw new NullPointerException(String.valueOf(r1));
    }

    @Pure
    public static void checkState(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    @Pure
    public static void checkState(boolean r0, Object r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalStateException(String.valueOf(r1));
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkStateNotNull(T r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkStateNotNull(T r0, Object r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        throw new IllegalStateException(String.valueOf(r1));
    }
}
