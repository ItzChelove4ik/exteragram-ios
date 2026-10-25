package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzavr {
    public int zza;
    public long zzb;
    public Object zzc;
    public final zzawl zzd;
    public int zze;

    public zzavr(zzawl r1) {
        r1.getClass();
        this.zzd = r1;
    }

    public static /* synthetic */ String zza(int r2, int r3) {
        int r0 = String.valueOf(r3).length();
        int r02 = r0 + 13;
        StringBuilder r1 = new StringBuilder(r02 + String.valueOf(r2).length());
        r1.append("Index:");
        r1.append(r3);
        r1.append(", Size:");
        r1.append(r2);
        return r1.toString();
    }
}
