package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzhb extends zzho {
    private final zzml zza;

    public zzhb(zzml r1) {
        if (r1 == null) goto L6;
        this.zza = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null citationSources");
        throw null;
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzho) == true) goto L8;
        return false;
    L8:
        return this.zza.equals(((zzho) r2).zza());
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 34);
        r1.append("CitationMetadata{citationSources=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzho
    public final zzml zza() {
        return this.zza;
    }
}
