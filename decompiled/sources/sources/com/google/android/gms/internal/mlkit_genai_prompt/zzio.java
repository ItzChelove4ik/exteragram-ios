package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzio extends zzir {
    private final zzml zza;

    public zzio(zzml r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zziw) == false) goto L10;
        zziw r42 = (zziw) r4;
        if (r42.zzb() != 5) goto L10;
        if (this.zza.equals(r42.zze()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        String r2 = String.valueOf(this.zza);
        StringBuilder r1 = new StringBuilder(r2.length() + 26);
        r1.append("WalleArgsValue{listValue=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 5;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final zzml zze() {
        return this.zza;
    }
}
