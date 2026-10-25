package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzin extends zzir {
    private final int zza;

    public zzin(int r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zziw) == false) goto L10;
        zziw r42 = (zziw) r4;
        if (r42.zzb() != 3) goto L10;
        if (this.zza != r42.zzd()) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }

    public final String toString() {
        int r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 25);
        r1.append("WalleArgsValue{intValue=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 3;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzd() {
        return this.zza;
    }
}
