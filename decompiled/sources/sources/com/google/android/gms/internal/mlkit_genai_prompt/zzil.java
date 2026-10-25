package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzil extends zzir {
    private final boolean zza;

    public zzil(boolean r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zziw) == false) goto L10;
        zziw r42 = (zziw) r4;
        if (r42.zzb() != 2) goto L10;
        if (this.zza != r42.zza()) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        if (this.zza == false) goto L6;
        return 1231;
    L6:
        return 1237;
    }

    public final String toString() {
        boolean r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 26);
        r1.append("WalleArgsValue{boolValue=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final boolean zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 2;
    }
}
