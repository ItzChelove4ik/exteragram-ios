package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzih extends zzif {
    private final byte[] zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzk;

    private zzih(byte[] r2, int r3, int r4, boolean r5) {
        super(null);
        this.zzk = Integer.MAX_VALUE;
        this.zzd = r2;
        this.zzf = r4 + r3;
        this.zzh = r3;
        this.zzi = r3;
        this.zze = r5;
    }

    public /* synthetic */ zzih(byte[] r1, int r2, int r3, boolean r4, zzie r5) {
        this(r1, 0, r3, false);
    }

    private final void zzz() {
        int r0 = this.zzf + this.zzg;
        this.zzf = r0;
        int r1 = r0 - this.zzi;
        int r2 = this.zzk;
        if (r1 <= r2) goto L6;
        int r12 = r1 - r2;
        this.zzg = r12;
        this.zzf = r0 - r12;
        return;
    L6:
        this.zzg = 0;
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzc(int r2) {
        if (r2 < 0) goto L10;
        int r22 = r2 + zzu();
        int r0 = this.zzk;
        if (r22 > r0) goto L8;
        this.zzk = r22;
        zzz();
        return r0;
    L8:
        throw zzjk.zza();
    L10:
        throw zzjk.zzb();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzu() {
        return this.zzh - this.zzi;
    }
}
