package com.google.android.gms.internal.measurement;

import java.util.Set;

/* loaded from: classes4.dex */
final class zzzy extends zzaaa {
    private final zzzj zza;
    private final zzzj zzb;
    private final int[] zzc;
    private final int zzd;

    public /* synthetic */ zzzy(zzzj r9, zzzj r10, byte[] r11) {
        super(null);
        this.zza = r9;
        this.zzb = r10;
        int r92 = r10.zza();
        if (r92 > 28) goto L5;
        boolean r102 = true;
    L6:
        zzabr.zzb(r102, "metadata size too large");
        int[] r93 = new int[r92];
        this.zzc = r93;
        long r1 = 0;
        int r103 = 0;
        int r3 = 0;
    L8:
        if (r103 >= r93.length) goto L27;
        zzyl r4 = zzi(r103);
        long r5 = r4.zzi() | r1;
        if (r5 != r1) goto L25;
        int r12 = 0;
    L13:
        if (r12 >= r3) goto L18;
        if (r4.equals(zzi(r93[r12] & 31)) == true) goto L19;
        r12 = r12 + 1;
    L19:
        if (r12 == (-1)) goto L25;
        if (r4.zzf() == false) goto L23;
        int r2 = r93[r12] | (1 << (r103 + 4));
    L24:
        r93[r12] = r2;
    L26:
        r103 = r103 + 1;
        r1 = r5;
        goto L8
    L23:
        r2 = r103;
        goto L24
    L18:
        r12 = -1;
    L25:
        r93[r3] = r103;
        r3 = r3 + 1;
        goto L26
    L27:
        this.zzd = r3;
        return;
    L5:
        r102 = false;
        goto L6
    }

    private final zzyl zzi(int r3) {
        zzzj r0 = this.zza;
        int r1 = r0.zza();
        if (r3 < r1) goto L7;
        return this.zzb.zzb(r3 - r1);
    L7:
        return r0.zzb(r3);
    }

    private final Object zzj(int r3) {
        zzzj r0 = this.zza;
        int r1 = r0.zza();
        if (r3 < r1) goto L7;
        return this.zzb.zzc(r3 - r1);
    L7:
        return r0.zzc(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final void zza(zzzq r6, Object r7) {
        int r0 = 0;
    L4:
        if (r0 >= this.zzd) goto L10;
        int r1 = this.zzc[r0];
        zzyl r2 = zzi(r1 & 31);
        if (r2.zzf() == true) goto L8;
        r6.zza(r2, r2.zze(zzj(r1)), r7);
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        r6.zzb(r2, new zzzx(this, r2, r1, null), r7);
        goto L9
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final int zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final Set zzc() {
        return new zzzw(this);
    }

    public final /* synthetic */ zzyl zzd(int r1) {
        return zzi(r1);
    }

    public final /* synthetic */ Object zze(int r1) {
        return zzj(r1);
    }

    public final /* synthetic */ int[] zzf() {
        return this.zzc;
    }

    public final /* synthetic */ int zzg() {
        return this.zzd;
    }
}
