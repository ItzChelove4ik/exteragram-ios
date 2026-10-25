package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzim extends zzir {
    private final double zza;

    public zzim(double r2) {
        super(null);
        this.zza = r2;
    }

    public final boolean equals(Object r5) {
        if ((r5 instanceof zziw) == false) goto L10;
        zziw r52 = (zziw) r5;
        if (r52.zzb() != 4) goto L10;
        if (Double.doubleToLongBits(this.zza) != Double.doubleToLongBits(r52.zzc())) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        double r0 = this.zza;
        long r2 = Double.doubleToLongBits(r0) >>> 32;
        return (int) (Double.doubleToLongBits(r0) ^ r2);
    }

    public final String toString() {
        double r0 = this.zza;
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 28);
        r2.append("WalleArgsValue{doubleValue=");
        r2.append(r0);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final int zzb() {
        return 4;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzir, com.google.android.gms.internal.mlkit_genai_prompt.zziw
    public final double zzc() {
        return this.zza;
    }
}
