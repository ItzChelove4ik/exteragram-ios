package com.google.android.gms.internal.fido;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes4.dex */
public abstract class zzhp implements Comparable {
    public zzhp() {
    }

    private final zzhp zzc(Class r4) {
        if (r4.isInstance(this) == false) goto L7;
        return (zzhp) r4.cast(this);
    L7:
        throw new zzho("Expected a " + r4.getName() + " value, but got " + getClass().getName());
    }

    public static int zzd(byte r0) {
        return (r0 >> 5) & 7;
    }

    public static zzhk zzg(long r1) {
        return new zzhk(r1);
    }

    public static zzhn zzi(String r1) {
        return new zzhn(r1);
    }

    public static zzhp zzj(byte... r1) {
        r1.getClass();
        ByteArrayInputStream r0 = new ByteArrayInputStream(Arrays.copyOf(r1, r1.length));
        return zzhq.zza(r0, new zzhs(r0));
    }

    public static zzhp zzk(InputStream r1) {
        return zzhq.zza(r1, new zzhs(r1));
    }

    public abstract int zza();

    public int zzb() {
        return 0;
    }

    public final zzhi zze() {
        return (zzhi) zzc(zzhi.class);
    }

    public final zzhk zzf() {
        return (zzhk) zzc(zzhk.class);
    }

    public final zzhm zzh() {
        return (zzhm) zzc(zzhm.class);
    }
}
