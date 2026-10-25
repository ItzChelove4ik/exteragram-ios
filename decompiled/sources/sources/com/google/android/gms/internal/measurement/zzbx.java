package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzbx extends zzcd {
    private final String zzb;
    private final int zzc;
    private final int zzd;

    public /* synthetic */ zzbx(String r1, boolean r2, int r3, zzbv r4, int r5, byte[] r6) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r5;
    }

    public final boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof zzcd) == false) goto L18;
        zzcd r62 = (zzcd) r6;
        if (this.zzb.equals(r62.zza()) == false) goto L18;
        r62.zzb();
        int r1 = this.zzc;
        int r3 = r62.zzd();
        if (r1 == 0) goto L17;
        if (r1 != r3) goto L18;
        r62.zzc();
        int r5 = this.zzd;
        int r63 = r62.zze();
        if (r5 == 0) goto L16;
        if (r63 != 1) goto L18;
        return true;
    L16:
        throw null;
    L17:
        throw null;
    L18:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzb.hashCode() ^ 1000003;
        int r2 = this.zzc;
        if (r2 == 0) goto L9;
        int r02 = (((r0 * 1000003) ^ 1237) * 1000003) ^ r2;
        if (this.zzd != 0) goto L7;
        throw null;
    L7:
        return (r02 * (-721379959)) ^ 1;
    L9:
        throw null;
    }

    public final String toString() {
        int r0 = this.zzc;
        String r1 = "null";
        if (r0 != 1) goto L5;
        String r02 = "ALL_CHECKS";
    L16:
        if (this.zzd != 1) goto L19;
        r1 = "READ_AND_WRITE";
    L19:
        String r5 = this.zzb;
        StringBuilder r4 = new StringBuilder(((((String.valueOf(r5).length() + 73) + r02.length()) + 52) + r1.length()) + 1);
        r4.append("FileComplianceOptions{fileOwner=");
        r4.append(r5);
        r4.append(", hasDifferentDmaOwner=false, fileChecks=");
        r4.append(r02);
        r4.append(", multipleProductIdGroupsResolver=null, filePurpose=");
        r4.append(r1);
        r4.append("}");
        return r4.toString();
    L5:
        if (r0 != 2) goto L7;
        r02 = "SKIP_COMPLIANCE_CHECK";
        goto L16
    L7:
        if (r0 != 3) goto L9;
        r02 = "SKIP_SECURITY_CHECK";
        goto L16
    L9:
        if (r0 == 4) goto L11;
        r02 = "null";
        goto L16
    L11:
        r02 = "NO_CHECKS";
        goto L16
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final zzbv zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final int zze() {
        return this.zzd;
    }
}
