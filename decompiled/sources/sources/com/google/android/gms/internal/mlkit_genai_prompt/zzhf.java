package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;

/* loaded from: classes4.dex */
final class zzhf extends zzhu {
    private final zzml zza;
    private final ParcelFileDescriptor zzb;

    public /* synthetic */ zzhf(zzml r1, ParcelFileDescriptor r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhu) == false) goto L18;
        zzhu r52 = (zzhu) r5;
        if (this.zza.equals(r52.zza()) == false) goto L18;
        ParcelFileDescriptor r4 = this.zzb;
        if (r4 != null) goto L15;
        if (r52.zzb() != null) goto L18;
    L17:
        return true;
    L15:
        if (r4.equals(r52.zzb()) == true) goto L17;
    L18:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        ParcelFileDescriptor r2 = this.zzb;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r22 ^ (r0 * 1000003);
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r3 = String.valueOf(this.zzb);
        int r12 = r1 + 46;
        StringBuilder r2 = new StringBuilder((r12 + r3.length()) + 1);
        r2.append("LlmPrefixParam{promptPrefix=");
        r2.append(r0);
        r2.append(", cacheFileToRead=");
        r2.append(r3);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhu
    public final zzml zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhu
    public final ParcelFileDescriptor zzb() {
        return this.zzb;
    }
}
