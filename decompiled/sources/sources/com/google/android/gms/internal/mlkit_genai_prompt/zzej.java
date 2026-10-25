package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzej extends zzeu {
    private final zzdi zza;
    private final zzdy zzb;
    private final zzdq zzc;

    public /* synthetic */ zzej(zzdi r1, zzdy r2, zzdq r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzeu) == false) goto L14;
        zzeu r52 = (zzeu) r5;
        if (this.zza.equals(r52.zza()) == false) goto L14;
        if (this.zzb.equals(r52.zzb()) == false) goto L14;
        if (this.zzc.equals(r52.zzc()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = (r0 * 1000003) ^ this.zzb.hashCode();
        int r3 = this.zzc.hashCode();
        return r3 ^ (r02 * 1000003);
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = this.zzb.toString();
        int r3 = r2.length();
        String r5 = this.zzc.toString();
        int r12 = ((r1 + 54) + r3) + 10;
        StringBuilder r32 = new StringBuilder((r12 + r5.length()) + 1);
        r32.append("CortanaServiceOptions{aiCoreClient=");
        r32.append(r0);
        r32.append(", downloadCallback=");
        r32.append(r2);
        r32.append(", feature=");
        r32.append(r5);
        r32.append("}");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeu
    public final zzdi zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeu
    public final zzdy zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeu
    public final zzdq zzc() {
        return this.zzc;
    }
}
