package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
final class zzma extends zzme {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    public /* synthetic */ zzma(String r1, boolean r2, int r3, zzlz r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzme) == false) goto L14;
        zzme r52 = (zzme) r5;
        if (this.zza.equals(r52.zzb()) == false) goto L14;
        if (this.zzb != r52.zzc()) goto L14;
        if (this.zzc != r52.zza()) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        if (true == this.zzb) goto L5;
        int r2 = 1237;
    L6:
        int r4 = this.zzc;
        return r4 ^ (((r0 * 1000003) ^ r2) * 1000003);
    L5:
        r2 = 1231;
        goto L6
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.zza + ", enableFirelog=" + this.zzb + ", firelogEventType=" + this.zzc + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final boolean zzc() {
        return this.zzb;
    }
}
