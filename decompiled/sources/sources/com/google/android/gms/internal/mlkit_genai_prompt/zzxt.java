package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class zzxt implements zzxi {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    public zzxt(String r1, String r2, String r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzxt) == true) goto L8;
        return false;
    L8:
        zzxt r52 = (zzxt) r5;
        if (Intrinsics.areEqual(this.zza, r52.zza) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.zzb, r52.zzb) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.zzc, r52.zzc) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.zzd, r52.zzd) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final int hashCode() {
        int r0 = (this.zza.hashCode() * 31) + this.zzb.hashCode();
        int r02 = (r0 * 31) + this.zzc.hashCode();
        return (r02 * 31) + this.zzd.hashCode();
    }

    public final String toString() {
        String r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        String r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        String r4 = this.zzc;
        int r5 = String.valueOf(r4).length();
        String r7 = this.zzd;
        int r12 = ((((r1 + 34) + r3) + 15) + r5) + 12;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r7).length()) + 1);
        r32.append("ExplicitCacheMetadata(name=");
        r32.append(r0);
        r32.append(", path=");
        r32.append(r2);
        r32.append(", promptPrefix=");
        r32.append(r4);
        r32.append(", modelName=");
        r32.append(r7);
        r32.append(")");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzxi
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzxi
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzxi
    public final JSONObject zzc() {
        JSONObject r0 = new JSONObject();
        r0.put("name", this.zza);
        r0.put("path", this.zzb);
        r0.put("prompt_prefix", this.zzc);
        r0.put("model_name", this.zzd);
        return r0;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzd;
    }
}
