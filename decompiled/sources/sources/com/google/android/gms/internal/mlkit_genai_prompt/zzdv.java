package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzdv extends zzdz {
    private final int zza;

    public zzdv(int r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzdz) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.zza != ((zzdz) r4).zza()) goto L10;
        return true;
    }

    public final int hashCode() {
        return this.zza ^ 1000003;
    }

    public final String toString() {
        int r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 22);
        r1.append("TokenInfo{tokenCount=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdz
    public final int zza() {
        return this.zza;
    }
}
