package com.google.android.gms.internal.mlkit_vision_label;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class zzs {
    public static int zza(int r1, int r2, String r3) {
        if (r1 < 0) goto L7;
        if (r1 >= r2) goto L7;
        return r1;
    L7:
        if (r1 < 0) goto L12;
        if (r2 >= 0) goto L11;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("negative size: ", r2);
        return 0;
    L11:
        String r12 = zzt.zza("%s (%s) must be less than size (%s)", new Object[]{"index", Integer.valueOf(r1), Integer.valueOf(r2)});
    L14:
        throw new IndexOutOfBoundsException(r12);
    L12:
        r12 = zzt.zza("%s (%s) must not be negative", new Object[]{"index", Integer.valueOf(r1)});
        goto L14
    }

    public static int zzb(int r0, int r1, String r2) {
        if (r0 < 0) goto L5;
        if (r0 > r1) goto L5;
        return r0;
    L5:
        IntList$$ExternalSyntheticBUOutline0.m(zze(r0, r1, "index"));
        return 0;
    }

    public static void zzc(int r1, int r2, int r3) {
        if (r1 < 0) goto L8;
        if (r2 < r1) goto L8;
        if (r2 > r3) goto L8;
        return;
    L8:
        if (r1 < 0) goto L15;
        if (r1 > r3) goto L15;
        if (r2 < 0) goto L14;
        if (r2 > r3) goto L14;
        String r12 = zzt.zza("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(r2), Integer.valueOf(r1)});
    L17:
        throw new IndexOutOfBoundsException(r12);
    L14:
        r12 = zze(r2, r3, "end index");
    L15:
        r12 = zze(r1, r3, "start index");
        goto L17
    }

    public static void zzd(boolean r0, Object r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m((String) r1);
    }

    private static String zze(int r0, int r1, String r2) {
        if (r0 < 0) goto L4;
        if (r1 >= 0) goto L7;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("negative size: ", r1);
        return null;
    L7:
        return zzt.zza("%s (%s) must not be greater than size (%s)", new Object[]{r2, Integer.valueOf(r0), Integer.valueOf(r1)});
    L4:
        return zzt.zza("%s (%s) must not be negative", new Object[]{r2, Integer.valueOf(r0)});
    }
}
