package com.google.android.gms.internal.play_billing;

import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.fido.zzgu$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class zzeg extends zzef {
    protected final byte[] zza;

    public zzeg(byte[] r2) {
        super(null);
        r2.getClass();
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzei) == true) goto L9;
        return false;
    L9:
        if (zzd() == ((zzei) r8).zzd()) goto L12;
        return false;
    L12:
        if (zzd() != 0) goto L15;
        return true;
    L15:
        if ((r8 instanceof zzeg) == false) goto L38;
        zzeg r82 = (zzeg) r8;
        int r1 = zzi();
        int r3 = r82.zzi();
        if (r1 == 0) goto L22;
        if (r3 == 0) goto L22;
        if (r1 == r3) goto L22;
        return false;
    L22:
        int r12 = zzd();
        if (r12 <= r82.zzd()) goto L25;
        zzgu$$ExternalSyntheticBUOutline0.m(r12, zzd());
        return false;
    L25:
        if (r12 > r82.zzd()) goto L33;
        byte[] r7 = this.zza;
        byte[] r32 = r82.zza;
        r82.zzc();
        int r83 = 0;
        int r4 = 0;
    L27:
        if (r83 >= r12) goto L32;
        if (r7[r83] != r32[r4]) goto L30;
        r83 = r83 + 1;
        r4 = r4 + 1;
        goto L27
    L30:
        return false;
    L32:
        return true;
    L33:
        Dex$$ExternalSyntheticBUOutline1.m("Ran off end of other: 0, ", r12, ", ", r82.zzd());
        return false;
    L38:
        return r8.equals(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public byte zza(int r1) {
        return this.zza[r1];
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public byte zzb(int r1) {
        return this.zza[r1];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final int zze(int r1, int r2, int r3) {
        return zzfo.zzb(r1, this.zza, 0, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final zzei zzf(int r2, int r3) {
        int r22 = zzei.zzh(0, r3, zzd());
        if (r22 != 0) goto L7;
        return zzei.zzb;
    L7:
        return new zzec(this.zza, 0, r22);
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final void zzg(zzdz r3) {
        int r0 = zzd();
        ((zzem) r3).zzc(this.zza, 0, r0);
    }
}
