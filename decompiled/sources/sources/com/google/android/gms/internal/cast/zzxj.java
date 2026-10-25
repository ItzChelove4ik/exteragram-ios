package com.google.android.gms.internal.cast;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzxj extends zzxi {
    private final byte[] zzb;

    public zzxj(byte[] r2) {
        super(null);
        r2.getClass();
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final byte zza(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final byte zzb(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final int zzc() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final zzxk zzd(int r2, int r3) {
        byte[] r1 = this.zzb;
        int r22 = zzxk.zzj(0, r3, r1.length);
        if (r22 != 0) goto L7;
        return zzxk.zza;
    L7:
        return new zzxg(r1, 0, r22);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final void zze(zzxd r3) {
        byte[] r2 = this.zzb;
        ((zzxn) r3).zzs(r2, 0, r2.length);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final boolean zzf(zzxk r7) {
        boolean r0 = r7 instanceof zzxj;
        if (r0 == true) goto L5;
        boolean r1 = r7 instanceof zzxg;
        if (r1 == false) goto L25;
        byte[] r2 = this.zzb;
        int r3 = r7.zzc();
        int r4 = r2.length;
        if (r4 <= r3) goto L11;
        int r6 = String.valueOf(r4).length();
        zzxg$$ExternalSyntheticBUOutline1.m((r6 + 18) + String.valueOf(r4).length(), r4);
        return false;
    L11:
        if (r4 > r7.zzc()) goto L20;
        if (r0 == true) goto L14;
        if (r1 == false) goto L19;
        zzxg r72 = (zzxg) r7;
        return zzxk.zzk(r2, 0, r72.zzh(), r72.zzi(), r4);
    L19:
        return r7.zzd(0, r4).equals(zzd(0, r4));
    L14:
        return zzxk.zzk(r2, 0, ((zzxj) r7).zzb, 0, r4);
    L20:
        int r62 = r7.zzc();
        int r73 = String.valueOf(r4).length();
        zzxg$$ExternalSyntheticBUOutline0.m((r73 + 27) + String.valueOf(r62).length(), "Ran off end of other: 0, ", r4, ", ", r62);
        return false;
    L25:
        return r7.zzf(this);
    L5:
        return Arrays.equals(this.zzb, ((zzxj) r7).zzb);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final int zzg(int r1, int r2, int r3) {
        return zzym.zzb(r1, this.zzb, 0, r3);
    }

    public final /* synthetic */ byte[] zzh() {
        return this.zzb;
    }
}
