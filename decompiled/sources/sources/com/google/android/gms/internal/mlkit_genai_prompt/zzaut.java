package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzaut extends zzaux {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    public /* synthetic */ zzaut(String r1, boolean r2, int r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzaux) == false) goto L14;
        zzaux r52 = (zzaux) r5;
        if (this.zza.equals(r52.zza()) == false) goto L14;
        if (this.zzb != r52.zzb()) goto L14;
        if (this.zzc != r52.zzc()) goto L14;
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
        boolean r0 = this.zzb;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzc;
        int r3 = String.valueOf(r2).length();
        String r6 = this.zza;
        StringBuilder r4 = new StringBuilder(((((r6.length() + 48) + r1) + 19) + r3) + 1);
        r4.append("MLKitLoggingOptions{libraryName=");
        r4.append(r6);
        r4.append(", enableFirelog=");
        r4.append(r0);
        r4.append(", firelogEventType=");
        r4.append(r2);
        r4.append("}");
        return r4.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaux
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaux
    public final boolean zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaux
    public final int zzc() {
        return this.zzc;
    }
}
