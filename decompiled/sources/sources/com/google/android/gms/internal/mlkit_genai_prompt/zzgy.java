package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzgy extends zzha {
    private final String zza;

    public /* synthetic */ zzgy(String r1, byte[] r2) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzha) == true) goto L8;
        return false;
    L8:
        return this.zza.equals(((zzha) r2).zza());
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String r2 = this.zza;
        StringBuilder r0 = new StringBuilder(r2.length() + 35);
        r0.append("LegionResultMetadata{modelVersion=");
        r0.append(r2);
        r0.append("}");
        return r0.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzha
    public final String zza() {
        return this.zza;
    }
}
