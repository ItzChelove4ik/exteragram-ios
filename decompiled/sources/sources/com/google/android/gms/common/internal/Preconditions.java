package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class Preconditions {
    public static void checkArgument(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public static void checkArgument(boolean r0, Object r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalArgumentException(String.valueOf(r1));
    }

    public static void checkArgument(boolean r0, String r1, Object... r2) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalArgumentException(String.format(r1, r2));
    }

    public static void checkHandlerThread(Handler r4) {
        Looper r0 = Looper.myLooper();
        if (r0 == r4.getLooper()) goto L9;
        if (r0 == null) goto L6;
        String r02 = r0.getThread().getName();
    L7:
        String r42 = r4.getLooper().getThread().getName();
        int r2 = String.valueOf(r42).length();
        StringBuilder r3 = new StringBuilder(((r2 + 35) + String.valueOf(r02).length()) + 1);
        r3.append("Must be called on ");
        r3.append(r42);
        r3.append(" thread, but got ");
        r3.append(r02);
        r3.append(".");
        throw new IllegalStateException(r3.toString());
    L6:
        r02 = "null current looper";
        goto L7
    }

    public static void checkHandlerThread(Handler r1, String r2) {
        if (Looper.myLooper() != r1.getLooper()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m(r2);
    }

    public static void checkMainThread(String r1) {
        if (com.google.android.gms.common.util.zze.zza() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static String checkNotEmpty(String r1) {
        if (TextUtils.isEmpty(r1) == true) goto L5;
        return r1;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Given String is empty or null");
        return null;
    }

    public static String checkNotEmpty(String r1, Object r2) {
        if (TextUtils.isEmpty(r1) == true) goto L6;
        return r1;
    L6:
        throw new IllegalArgumentException(String.valueOf(r2));
    }

    public static void checkNotGoogleApiHandlerThread() {
        checkNotGoogleApiHandlerThread("Must not be called on GoogleApiHandler thread.");
    }

    public static void checkNotGoogleApiHandlerThread(String r2) {
        Looper r0 = Looper.myLooper();
        if (r0 != null) goto L5;
        return;
    L5:
        if (java.util.Objects.equals(r0.getThread().getName(), "GoogleApiHandler") == false) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m(r2);
        return;
    }

    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    public static void checkNotMainThread(String r1) {
        if (com.google.android.gms.common.util.zze.zza() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static <T> T checkNotNull(T r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m("null reference");
        return null;
    }

    public static <T> T checkNotNull(T r0, Object r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        throw new NullPointerException(String.valueOf(r1));
    }

    public static int checkNotZero(int r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Given Integer is zero");
        return 0;
    }

    public static void checkState(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public static void checkState(boolean r0, Object r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalStateException(String.valueOf(r1));
    }

    public static void checkState(boolean r0, String r1, Object... r2) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalStateException(String.format(r1, r2));
    }
}
