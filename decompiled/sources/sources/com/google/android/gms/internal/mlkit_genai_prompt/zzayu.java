package com.google.android.gms.internal.mlkit_genai_prompt;

import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzayu extends zzayw {
    public zzayu(Unsafe r1) {
        super(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final boolean zza(Object r1, long r2) {
        if (zzayx.zza == false) goto L7;
        return zzayx.zzp(r1, r2);
    L7:
        return zzayx.zzq(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final void zzb(Object r1, long r2, boolean r4) {
        if (zzayx.zza == false) goto L6;
        zzayx.zzr(r1, r2, r4);
        return;
    L6:
        zzayx.zzs(r1, r2, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final float zzc(Object r1, long r2) {
        return Float.intBitsToFloat(this.zza.getInt(r1, r2));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final void zzd(Object r1, long r2, float r4) {
        int r42 = Float.floatToIntBits(r4);
        this.zza.putInt(r1, r2, r42);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final double zze(Object r1, long r2) {
        return Double.longBitsToDouble(this.zza.getLong(r1, r2));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayw
    public final void zzf(Object r1, long r2, double r4) {
        long r42 = Double.doubleToLongBits(r4);
        this.zza.putLong(r1, r2, r42);
    }
}
