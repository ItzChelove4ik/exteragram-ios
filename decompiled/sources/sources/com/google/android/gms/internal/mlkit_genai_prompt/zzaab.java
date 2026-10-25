package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class zzaab implements zzxi {
    private final String zza;
    private final String zzb;
    private final long zzc;
    private final String zzd;

    public zzaab(String r1, String r2, long r3, String r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r5;
    }

    public final boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzaab) == true) goto L8;
        return false;
    L8:
        zzaab r82 = (zzaab) r8;
        if (Intrinsics.areEqual(this.zza, r82.zza) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.zzb, r82.zzb) == true) goto L15;
        return false;
    L15:
        if (this.zzc == r82.zzc) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.zzd, r82.zzd) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final int hashCode() {
        int r0 = (this.zza.hashCode() * 31) + this.zzb.hashCode();
        int r02 = (r0 * 31) + Long.hashCode(this.zzc);
        return (r02 * 31) + this.zzd.hashCode();
    }

    public final String toString() {
        String r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        String r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        long r4 = this.zzc;
        int r6 = String.valueOf(r4).length();
        String r8 = this.zzd;
        int r12 = ((((r1 + 34) + r3) + 17) + r6) + 12;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r8).length()) + 1);
        r32.append("ImplicitCacheMetadata(name=");
        r32.append(r0);
        r32.append(", path=");
        r32.append(r2);
        r32.append(", lastAccessTime=");
        r32.append(r4);
        r32.append(", modelName=");
        r32.append(r8);
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
        r0.put("id", this.zza);
        r0.put("path", this.zzb);
        r0.put("last_access_time", this.zzc);
        r0.put("model_name", this.zzd);
        return r0;
    }

    public final long zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzd;
    }
}
