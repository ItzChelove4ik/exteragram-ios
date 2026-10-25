package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zznv extends zzqo {
    private final zznd zza;
    private final zzqr zzb;

    public zznv(zznd r1, zzqr r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzqo) == false) goto L18;
        zzqo r52 = (zzqo) r5;
        zznd r1 = this.zza;
        if (r1 != null) goto L13;
        if (r52.zza() != null) goto L18;
    L16:
        if (this.zzb.equals(r52.zzb()) == false) goto L18;
        return true;
    L13:
        if (r1 == r52.zza()) goto L16;
    L18:
        return false;
    }

    public final int hashCode() {
        zznd r0 = this.zza;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r2 = this.zzb.hashCode();
        return r2 ^ ((r02 ^ 1000003) * 1000003);
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public final String toString() {
        zzqr r0 = this.zzb;
        String r3 = String.valueOf(this.zza);
        String r02 = r0.toString();
        int r1 = r3.length();
        int r12 = r1 + 52;
        StringBuilder r2 = new StringBuilder((r12 + r02.length()) + 1);
        r2.append("SnapshotBlobAndResult{snapshotBlob=");
        r2.append(r3);
        r2.append(", snapshotResult=");
        r2.append(r02);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzqo
    public final zznd zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzqo
    public final zzqr zzb() {
        return this.zzb;
    }
}
