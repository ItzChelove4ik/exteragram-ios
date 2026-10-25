package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zziu extends zzja {
    private final zzml zzb;

    public zziu(zzml r1) {
        if (r1 == null) goto L6;
        this.zzb = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null functionDeclarations");
        throw null;
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzja) == true) goto L8;
        return false;
    L8:
        return this.zzb.equals(((zzja) r2).zza());
    }

    public final int hashCode() {
        return this.zzb.hashCode() ^ 1000003;
    }

    public final String toString() {
        String r2 = this.zzb.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 33);
        r1.append("WalleTools{functionDeclarations=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzja
    public final zzml zza() {
        return this.zzb;
    }
}
