package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzit extends zziy {
    private final String zza;
    private final zzmo zzb;

    public /* synthetic */ zzit(String r1, zzmo r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zziy) == false) goto L12;
        zziy r52 = (zziy) r5;
        if (this.zza.equals(r52.zza()) == false) goto L12;
        if (this.zzb.equals(r52.zzb()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r2 = this.zzb.hashCode();
        return r2 ^ (r0 * 1000003);
    }

    public final String toString() {
        String r0 = this.zzb.toString();
        int r1 = r0.length();
        String r4 = this.zza;
        StringBuilder r2 = new StringBuilder(((r4.length() + 30) + r1) + 1);
        r2.append("WalleFunctionCall{name=");
        r2.append(r4);
        r2.append(", args=");
        r2.append(r0);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziy
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zziy
    public final zzmo zzb() {
        return this.zzb;
    }
}
