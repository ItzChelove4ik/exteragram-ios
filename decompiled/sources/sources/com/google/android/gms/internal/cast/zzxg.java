package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzxg extends zzxi {
    private final byte[] zzb;
    private final int zzc;
    private final int zzd;

    public zzxg(byte[] r3, int r4, int r5) {
        super(null);
        zzxk.zzj(r4, r4 + r5, r3.length);
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final byte zza(int r4) {
        int r0 = this.zzd;
        if (((r0 - (r4 + 1)) | r4) >= 0) goto L11;
        if (r4 >= 0) goto L8;
        StringBuilder r1 = new StringBuilder(String.valueOf(r4).length() + 11);
        r1.append("Index < 0: ");
        r1.append(r4);
        throw new ArrayIndexOutOfBoundsException(r1.toString());
    L8:
        int r12 = String.valueOf(r4).length();
        StringBuilder r2 = new StringBuilder((r12 + 18) + String.valueOf(r0).length());
        r2.append("Index > length: ");
        r2.append(r4);
        r2.append(", ");
        r2.append(r0);
        throw new ArrayIndexOutOfBoundsException(r2.toString());
    L11:
        return this.zzb[this.zzc + r4];
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final byte zzb(int r2) {
        int r0 = this.zzc;
        return this.zzb[r0 + r2];
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final int zzc() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final zzxk zzd(int r2, int r3) {
        int r32 = zzxk.zzj(r2, r3, this.zzd);
        if (r32 != 0) goto L7;
        return zzxk.zza;
    L7:
        return new zzxg(this.zzb, this.zzc + r2, r32);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final void zze(zzxd r3) {
        ((zzxn) r3).zzs(this.zzb, this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final boolean zzf(zzxk r5) {
        boolean r0 = r5 instanceof zzxj;
        if (r0 == false) goto L5;
    L9:
        int r1 = this.zzd;
        if (r1 <= r5.zzc()) goto L12;
        int r4 = String.valueOf(r1).length();
        zzxg$$ExternalSyntheticBUOutline1.m((r4 + 18) + String.valueOf(r1).length(), r1);
        return false;
    L12:
        if (r1 > r5.zzc()) goto L22;
        if (r0 == false) goto L17;
        return zzxk.zzk(this.zzb, this.zzc, ((zzxj) r5).zzh(), 0, r1);
    L17:
        if ((r5 instanceof zzxg) == false) goto L20;
        zzxg r52 = (zzxg) r5;
        return zzxk.zzk(this.zzb, this.zzc, r52.zzb, r52.zzc, r1);
    L20:
        zzxk r53 = r5.zzd(0, r1);
        int r02 = this.zzc;
        return r53.equals(zzd(r02, r1 + r02));
    L22:
        int r42 = r5.zzc();
        int r54 = String.valueOf(r1).length();
        zzxg$$ExternalSyntheticBUOutline0.m((r54 + 27) + String.valueOf(r42).length(), "Ran off end of other: 0, ", r1, ", ", r42);
        return false;
    L5:
        if ((r5 instanceof zzxg) == true) goto L9;
        return r5.zzf(this);
    }

    @Override // com.google.android.gms.internal.cast.zzxk
    public final int zzg(int r1, int r2, int r3) {
        return zzym.zzb(r1, this.zzb, this.zzc, r3);
    }

    public final /* synthetic */ byte[] zzh() {
        return this.zzb;
    }

    public final /* synthetic */ int zzi() {
        return this.zzc;
    }
}
