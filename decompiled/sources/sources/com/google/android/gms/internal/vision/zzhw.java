package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzhw extends zzid {
    private final int zzc;
    private final int zzd;

    public zzhw(byte[] r2, int r3, int r4) {
        super(r2);
        zzht.zzb(r3, r3 + r4, r2.length);
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.internal.vision.zzid, com.google.android.gms.internal.vision.zzht
    public final byte zza(int r4) {
        int r0 = zza();
        if (((r0 - (r4 + 1)) | r4) >= 0) goto L10;
        if (r4 >= 0) goto L7;
        StringBuilder r02 = new StringBuilder(22);
        r02.append("Index < 0: ");
        r02.append(r4);
        throw new ArrayIndexOutOfBoundsException(r02.toString());
    L7:
        StringBuilder r1 = new StringBuilder(40);
        r1.append("Index > length: ");
        r1.append(r4);
        r1.append(", ");
        r1.append(r0);
        throw new ArrayIndexOutOfBoundsException(r1.toString());
    L10:
        return this.zzb[this.zzc + r4];
    }

    @Override // com.google.android.gms.internal.vision.zzid, com.google.android.gms.internal.vision.zzht
    public final int zza() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.vision.zzid, com.google.android.gms.internal.vision.zzht
    public final byte zzb(int r2) {
        return this.zzb[this.zzc + r2];
    }

    @Override // com.google.android.gms.internal.vision.zzid
    public final int zze() {
        return this.zzc;
    }
}
