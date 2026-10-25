package com.google.android.gms.internal.cast;

import java.util.Arrays;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzaae {
    private static final zzaae zza = null;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    static {
        zza = new zzaae(0, new int[0], new Object[0], false);
    }

    private zzaae(int r1, int[] r2, Object[] r3, boolean r4) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = r2;
        this.zzd = r3;
        this.zzf = r4;
    }

    public static zzaae zza() {
        return zza;
    }

    public static zzaae zzb(zzaae r3, zzaae r4) {
        int r0 = r3.zzb;
        int r02 = r4.zzb;
        int[] r03 = Arrays.copyOf(r3.zzc, 0);
        System.arraycopy(r4.zzc, 0, r03, 0, 0);
        Object[] r32 = Arrays.copyOf(r3.zzd, 0);
        System.arraycopy(r4.zzd, 0, r32, 0, 0);
        return new zzaae(0, r03, r32, true);
    }

    public final boolean equals(Object r3) {
        if (this != r3) goto L6;
        return true;
    L6:
        if (r3 != null) goto L9;
        return false;
    L9:
        if ((r3 instanceof zzaae) == true) goto L11;
        return false;
    L11:
        zzaae r32 = (zzaae) r3;
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final void zzc() {
        if (this.zzf == false) goto L6;
        this.zzf = false;
        return;
    }

    public final int zze() {
        int r0 = this.zze;
        if (r0 != (-1)) goto L6;
        this.zze = 0;
        return 0;
    L6:
        return r0;
    }

    public final int zzf() {
        int r0 = this.zze;
        if (r0 != (-1)) goto L6;
        this.zze = 0;
        return 0;
    L6:
        return r0;
    }

    public final void zzg(StringBuilder r1, int r2) {
    }

    public final zzaae zzh(zzaae r4) {
        if (r4.equals(zza) == false) goto L5;
        return this;
    L5:
        if (this.zzf == false) goto L8;
        int[] r0 = this.zzc;
        int r1 = r0.length;
        System.arraycopy(r4.zzc, 0, r0, 0, 0);
        System.arraycopy(r4.zzd, 0, this.zzd, 0, 0);
        this.zzb = 0;
        return this;
    L8:
        Type$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
