package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzla {
    public static void zza(boolean r0, Object r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m((String) r1);
    }

    public static void zzb(boolean r0, String r1, Object r2, Object r3) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(zzlb.zzd(r1, new Object[]{r2, r3}));
    }

    public static void zzc(boolean r0, Object r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m((String) r1);
    }

    public static Object zzd(Object r0, Object r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m((String) r1);
        return null;
    }

    public static Object zze(Object r0, String r1, Object r2) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(zzlb.zzd("AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", new Object[]{r2}));
        return null;
    }

    public static int zzf(int r1, int r2, String r3) {
        if (r1 < 0) goto L7;
        if (r1 >= r2) goto L7;
        return r1;
    L7:
        if (r1 < 0) goto L12;
        if (r2 >= 0) goto L11;
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r2).length() + 15, "negative size: ", r2);
        return 0;
    L11:
        String r12 = zzlb.zzd("%s (%s) must be less than size (%s)", new Object[]{"index", Integer.valueOf(r1), Integer.valueOf(r2)});
    L14:
        throw new IndexOutOfBoundsException(r12);
    L12:
        r12 = zzlb.zzd("%s (%s) must not be negative", new Object[]{"index", Integer.valueOf(r1)});
        goto L14
    }

    public static int zzg(int r0, int r1, String r2) {
        if (r0 < 0) goto L5;
        if (r0 > r1) goto L5;
        return r0;
    L5:
        IntList$$ExternalSyntheticBUOutline0.m(zzi(r0, r1, "index"));
        return 0;
    }

    public static void zzh(int r1, int r2, int r3) {
        if (r1 < 0) goto L8;
        if (r2 < r1) goto L8;
        if (r2 > r3) goto L8;
        return;
    L8:
        if (r1 < 0) goto L15;
        if (r1 > r3) goto L15;
        if (r2 < 0) goto L14;
        if (r2 > r3) goto L14;
        String r12 = zzlb.zzd("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(r2), Integer.valueOf(r1)});
    L17:
        throw new IndexOutOfBoundsException(r12);
    L14:
        r12 = zzi(r2, r3, "end index");
    L15:
        r12 = zzi(r1, r3, "start index");
        goto L17
    }

    private static String zzi(int r0, int r1, String r2) {
        if (r0 < 0) goto L4;
        if (r1 >= 0) goto L7;
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r1).length() + 15, "negative size: ", r1);
        return null;
    L7:
        return zzlb.zzd("%s (%s) must not be greater than size (%s)", new Object[]{r2, Integer.valueOf(r0), Integer.valueOf(r1)});
    L4:
        return zzlb.zzd("%s (%s) must not be negative", new Object[]{r2, Integer.valueOf(r0)});
    }
}
