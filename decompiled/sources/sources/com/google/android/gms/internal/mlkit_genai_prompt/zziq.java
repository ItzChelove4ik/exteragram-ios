package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zziq extends zzir {
    private final String zza;

    public zziq(String r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zziw) == false) goto L9;
        zziw r42 = (zziw) r4;
        if (r42.zzb() != 1) goto L9;
        if (this.zza.equals(r42.zzg()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        String r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 28);
        r1.append("WalleArgsValue{stringValue=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final String zzg() {
        return this.zza;
    }
}
