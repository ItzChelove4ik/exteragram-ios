package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes4.dex */
final class zzaya implements zzayg {
    private final zzaxw zza;
    private final zzayq zzb;
    private final boolean zzc;
    private final zzawm zzd;

    private zzaya(zzayq r1, zzawm r2, zzaxw r3) {
        this.zzb = r1;
        this.zzc = false;
        this.zzd = r2;
        this.zza = r3;
    }

    public static zzaya zze(zzayq r1, zzawm r2, zzaxw r3) {
        return new zzaya(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final Object zza() {
        zzaxw r1 = this.zza;
        if ((r1 instanceof zzawy) == false) goto L7;
        return ((zzawy) r1).zzG();
    L7:
        return r1.zzS().zzH();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final boolean zzb(Object r2, Object r3) {
        if (((zzawy) r2).zzc.equals(((zzawy) r3).zzc) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L10;
        return true;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final int zzc(Object r2) {
        int r0 = ((zzawy) r2).zzc.hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzd(Object r2, Object r3) {
        zzayh.zzd(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzayh.zzc(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzg(Object r1, byte[] r2, int r3, int r4, zzavr r5) {
        zzawy r0 = (zzawy) r1;
        if (r0.zzc != zzayr.zza()) goto L6;
        r0.zzc = zzayr.zzb();
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzh(Object r1) {
        ((zzawy) r1).zzc.zzd();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final boolean zzi(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }
}
