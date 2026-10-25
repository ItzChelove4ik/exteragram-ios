package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public enum zzabx extends Enum implements zzady {
    public static final zzabx zza = null;
    public static final zzabx zzb = null;
    public static final zzabx zzc = null;
    public static final zzabx zzd = null;
    public static final zzabx zze = null;
    private static final /* synthetic */ zzabx[] zzg = null;
    private final int zzf;

    static {
        zzabx r0 = new zzabx("PURPOSE_RESTRICTION_NOT_ALLOWED", 0, 0);
        zza = r0;
        zzabx r1 = new zzabx("PURPOSE_RESTRICTION_REQUIRE_CONSENT", 1, 1);
        zzb = r1;
        zzabx r2 = new zzabx("PURPOSE_RESTRICTION_REQUIRE_LEGITIMATE_INTEREST", 2, 2);
        zzc = r2;
        zzabx r3 = new zzabx("PURPOSE_RESTRICTION_UNDEFINED", 3, 3);
        zzd = r3;
        zzabx r4 = new zzabx("UNRECOGNIZED", 4, -1);
        zze = r4;
        zzg = new zzabx[]{r0, r1, r2, r3, r4};
    }

    zzabx(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zzabx[] values() {
        return (zzabx[]) zzg.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzf);
    }

    @Override // com.google.android.gms.internal.measurement.zzady
    public final int zza() {
        if (this != zze) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Can't get the number of an unknown enum value.");
        return 0;
    L5:
        return this.zzf;
    }
}
