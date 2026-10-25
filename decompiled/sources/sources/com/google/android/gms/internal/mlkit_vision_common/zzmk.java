package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes4.dex */
public final class zzmk implements zzmb {
    private final zziw zza;
    private zzky zzb;

    private zzmk(zziw r1, int r2) {
        this.zzb = new zzky();
        this.zza = r1;
        zzmw.zza();
    }

    public static zzmb zze(zziw r2) {
        return new zzmk(r2, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb zza(zziv r2) {
        this.zza.zzc(r2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb zzb(zzky r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final String zzc() {
        zzla r1 = this.zza.zzf().zzc();
        if (r1 != null) goto L5;
        return "NA";
    L5:
        if (zzg.zzb(r1.zzk()) == false) goto L7;
        return "NA";
    L7:
        return (String) Preconditions.checkNotNull(r1.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final byte[] zzd(int r3, boolean r4) {
        int r42 = r3 ^ 1;
        zzky r0 = this.zzb;
        if (1 == r42) goto L5;
        boolean r43 = false;
    L6:
        r0.zzf(Boolean.valueOf(r43));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zze(this.zzb.zzm());
        zzmw.zza();     // Catch: UnsupportedEncodingException -> L14
        zziw r2 = this.zza;
        if (r3 == 0) goto L11;
        zziy r22 = r2.zzf();     // Catch: UnsupportedEncodingException -> L14
        zzam r32 = new zzam();     // Catch: UnsupportedEncodingException -> L14
        zzhe.zza.configure(r32);     // Catch: UnsupportedEncodingException -> L14
        return r32.zza().zza(r22);
    L11:
        return new JsonDataEncoderBuilder().configureWith(zzhe.zza).ignoreNullValues(true).build().encode(r2.zzf()).getBytes("utf-8");
    L14:
        e = move-exception;
        throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
    L5:
        r43 = true;
        goto L6
    }
}
