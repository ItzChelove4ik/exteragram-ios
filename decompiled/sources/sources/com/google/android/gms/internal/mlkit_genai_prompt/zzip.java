package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzip extends zzir {
    private final long zza;

    public zzip(long r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r5) {
        if ((r5 instanceof zziw) == false) goto L10;
        zziw r52 = (zziw) r5;
        if (r52.zzb() != 6) goto L10;
        if (this.zza != r52.zzf()) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        long r0 = this.zza;
        return (int) (r0 ^ (r0 >>> 32));
    }

    public final String toString() {
        long r0 = this.zza;
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 26);
        r2.append("WalleArgsValue{longValue=");
        r2.append(r0);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 6;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final long zzf() {
        return this.zza;
    }
}
