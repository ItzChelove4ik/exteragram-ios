package com.google.common.base;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class Preconditions {
    private static String badElementIndex(int r0, int r1, String r2) {
        if (r0 < 0) goto L4;
        if (r1 >= 0) goto L7;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("negative size: ", r1);
        return null;
    L7:
        return Strings.lenientFormat("%s (%s) must be less than size (%s)", new Object[]{r2, Integer.valueOf(r0), Integer.valueOf(r1)});
    L4:
        return Strings.lenientFormat("%s (%s) must not be negative", new Object[]{r2, Integer.valueOf(r0)});
    }

    private static String badPositionIndex(int r0, int r1, String r2) {
        if (r0 < 0) goto L4;
        if (r1 >= 0) goto L7;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("negative size: ", r1);
        return null;
    L7:
        return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", new Object[]{r2, Integer.valueOf(r0), Integer.valueOf(r1)});
    L4:
        return Strings.lenientFormat("%s (%s) must not be negative", new Object[]{r2, Integer.valueOf(r0)});
    }

    private static String badPositionIndexes(int r0, int r1, int r2) {
        if (r0 < 0) goto L13;
        if (r0 > r2) goto L13;
        if (r1 < 0) goto L11;
        if (r1 > r2) goto L11;
        return Strings.lenientFormat("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(r1), Integer.valueOf(r0)});
    L11:
        return badPositionIndex(r1, r2, "end index");
    L13:
        return badPositionIndex(r0, r2, "start index");
    }

    public static void checkArgument(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public static void checkArgument(boolean r0, Object r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(Platform.stringValueOf(r1));
    }

    public static void checkArgument(boolean r0, String r1, char r2) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(Strings.lenientFormat(r1, new Object[]{Character.valueOf(r2)}));
    }

    public static void checkArgument(boolean r0, String r1, int r2, int r3) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(Strings.lenientFormat(r1, new Object[]{Integer.valueOf(r2), Integer.valueOf(r3)}));
    }

    public static void checkArgument(boolean r0, String r1, long r2) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(Strings.lenientFormat(r1, new Object[]{Long.valueOf(r2)}));
    }

    public static void checkArgument(boolean r0, String r1, Object r2) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(Platform.lenientFormat(r1, new Object[]{r2}));
    }

    public static int checkElementIndex(int r1, int r2) {
        return checkElementIndex(r1, r2, "index");
    }

    public static int checkElementIndex(int r0, int r1, String r2) {
        if (r0 < 0) goto L5;
        if (r0 >= r1) goto L5;
        return r0;
    L5:
        IntList$$ExternalSyntheticBUOutline0.m(badElementIndex(r0, r1, r2));
        return 0;
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static <T> T checkNotNull(T r0, Object r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(Platform.stringValueOf(r1));
        return null;
    }

    public static <T> T checkNotNull(T r0, String r1, Object r2) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(Platform.lenientFormat(r1, new Object[]{r2}));
        return null;
    }

    public static int checkPositionIndex(int r1, int r2) {
        return checkPositionIndex(r1, r2, "index");
    }

    public static int checkPositionIndex(int r0, int r1, String r2) {
        if (r0 < 0) goto L5;
        if (r0 > r1) goto L5;
        return r0;
    L5:
        IntList$$ExternalSyntheticBUOutline0.m(badPositionIndex(r0, r1, r2));
        return 0;
    }

    public static void checkPositionIndexes(int r0, int r1, int r2) {
        if (r0 < 0) goto L6;
        if (r1 < r0) goto L6;
        if (r1 > r2) goto L6;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(badPositionIndexes(r0, r1, r2));
    }

    public static void checkState(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public static void checkState(boolean r0, Object r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m(Platform.stringValueOf(r1));
    }

    public static void checkState(boolean r0, String r1, int r2) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m(Strings.lenientFormat(r1, new Object[]{Integer.valueOf(r2)}));
    }

    public static void checkState(boolean r0, String r1, Object r2) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m(Platform.lenientFormat(r1, new Object[]{r2}));
    }
}
