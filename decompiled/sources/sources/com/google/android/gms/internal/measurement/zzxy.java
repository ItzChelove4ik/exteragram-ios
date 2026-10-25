package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzxy extends zzzj {
    private Object[] zza;
    private int zzb;

    public zzxy() {
        this.zza = new Object[8];
        this.zzb = 0;
    }

    private final int zzh(zzyl r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.zzb) goto L9;
        if (this.zza[r0 + r0].equals(r4) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("Metadata{");
        int r1 = 0;
    L4:
        if (r1 >= this.zzb) goto L6;
        r0.append(" '");
        r0.append(zzb(r1));
        r0.append("': ");
        r0.append(zzc(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        r0.append(" }");
        return r0.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final zzyl zzb(int r2) {
        if (r2 >= this.zzb) goto L7;
        return (zzyl) this.zza[r2 + r2];
    L7:
        throw new IndexOutOfBoundsException();
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final Object zzc(int r2) {
        if (r2 >= this.zzb) goto L7;
        return this.zza[(r2 + r2) + 1];
    L7:
        throw new IndexOutOfBoundsException();
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final Object zzd(zzyl r3) {
        int r0 = zzh(r3);
        if (r0 != (-1)) goto L5;
        return null;
    L5:
        return r3.zze(this.zza[(r0 + r0) + 1]);
    }

    public final void zze(zzyl r5, Object r6) {
        if (r5.zzf() == true) goto L9;
        int r0 = zzh(r5);
        if (r0 == (-1)) goto L9;
        zzabr.zza(r6, "metadata value");
        this.zza[(r0 + r0) + 1] = r6;
        return;
    L9:
        int r02 = this.zzb + 1;
        Object[] r2 = this.zza;
        int r3 = r2.length;
        if ((r02 + r02) <= r3) goto L12;
        this.zza = Arrays.copyOf(r2, r3 + r3);
    L12:
        Object[] r03 = this.zza;
        int r22 = this.zzb;
        zzabr.zza(r5, "metadata key");
        r03[r22 + r22] = r5;
        Object[] r52 = this.zza;
        int r04 = this.zzb;
        zzabr.zza(r6, "metadata value");
        r52[(r04 + r04) + 1] = r6;
        this.zzb++;
    }

    public final void zzf(zzyl r6) {
        int r0 = zzh(r6);
        if (r0 < 0) goto L14;
        int r02 = r0 + r0;
        int r1 = r02 + 2;
    L5:
        int r2 = this.zzb;
        if (r1 >= (r2 + r2)) goto L11;
        Object r22 = this.zza[r1];
        if (r22.equals(r6) == true) goto L10;
        Object[] r3 = this.zza;
        r3[r02] = r22;
        r3[r02 + 1] = r3[r1 + 1];
        r02 = r02 + 2;
    L10:
        r1 = r1 + 2;
        goto L5
    L11:
        this.zzb = r2 - ((r1 - r02) >> 1);
    L12:
        if (r02 >= r1) goto L19;
        this.zza[r02] = null;
        r02 = r02 + 1;
        goto L12
    L19:
        return;
    }
}
