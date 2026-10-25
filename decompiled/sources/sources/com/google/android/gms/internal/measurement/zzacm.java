package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzacm extends zzacp {
    private final byte[] zzb;
    private final int zzc;
    private final int zzd;

    public zzacm(byte[] r3, int r4, int r5) {
        super(null);
        zzacr.zzn(r4, r4 + r5, r3.length);
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final byte zza(int r2) {
        int r0 = this.zzc;
        return this.zzb[r0 + r2];
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final int zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final zzacr zzc(int r2, int r3) {
        int r32 = zzacr.zzn(r2, r3, this.zzd);
        if (r32 != 0) goto L7;
        return zzacr.zza;
    L7:
        return new zzacm(this.zzb, this.zzc + r2, r32);
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final void zzd(byte[] r1, int r2, int r3, int r4) {
        System.arraycopy(this.zzb, this.zzc, r1, 0, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final void zze(zzacj r3) {
        r3.zza(this.zzb, this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final boolean zzf(zzacr r5) {
        boolean r0 = r5 instanceof zzacq;
        if (r0 == false) goto L5;
    L9:
        int r1 = this.zzd;
        if (r1 <= r5.zzb()) goto L12;
        int r4 = String.valueOf(r1).length();
        zzxg$$ExternalSyntheticBUOutline1.m((r4 + 18) + String.valueOf(r1).length(), r1);
        return false;
    L12:
        if (r1 > r5.zzb()) goto L22;
        if (r0 == false) goto L17;
        return zzacr.zzo(this.zzb, this.zzc, ((zzacq) r5).zzh(), 0, r1);
    L17:
        if ((r5 instanceof zzacm) == false) goto L20;
        zzacm r52 = (zzacm) r5;
        return zzacr.zzo(this.zzb, this.zzc, r52.zzb, r52.zzc, r1);
    L20:
        zzacr r53 = r5.zzc(0, r1);
        int r02 = this.zzc;
        return r53.equals(zzc(r02, r1 + r02));
    L22:
        int r42 = r5.zzb();
        int r54 = String.valueOf(r1).length();
        zzxg$$ExternalSyntheticBUOutline0.m((r54 + 27) + String.valueOf(r42).length(), "Ran off end of other: 0, ", r1, ", ", r42);
        return false;
    L5:
        if ((r5 instanceof zzacm) == true) goto L9;
        return r5.zzf(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzacr
    public final int zzg(int r1, int r2, int r3) {
        return zzaed.zzb(r1, this.zzb, this.zzc, r3);
    }

    public final /* synthetic */ byte[] zzh() {
        return this.zzb;
    }

    public final /* synthetic */ int zzi() {
        return this.zzc;
    }
}
