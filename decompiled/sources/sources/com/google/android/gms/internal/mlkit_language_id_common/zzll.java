package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes4.dex */
public final class zzll implements zzla {
    private final zzhz zza;
    private zzkc zzb;
    private final int zzc;

    private zzll(zzhz r2, int r3) {
        this.zzb = new zzkc();
        this.zza = r2;
        zzlu.zza();
        this.zzc = r3;
    }

    public static zzla zzf(zzhz r2) {
        return new zzll(r2, 0);
    }

    public static zzla zzg(zzhz r1, int r2) {
        return new zzll(r1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzla
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzla
    public final zzla zzb(zzhy r2) {
        this.zza.zzd(r2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzla
    public final zzla zzc(zzkc r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzla
    public final String zzd() {
        zzke r1 = this.zza.zzg().zzd();
        if (r1 != null) goto L5;
        return "NA";
    L5:
        if (zzl.zzb(r1.zzk()) == false) goto L7;
        return "NA";
    L7:
        return (String) Preconditions.checkNotNull(r1.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzla
    public final byte[] zze(int r3, boolean r4) {
        zzkc r42 = this.zzb;
        if (1 == (r3 ^ 1)) goto L5;
        boolean r0 = false;
    L6:
        r42.zzf(Boolean.valueOf(r0));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzf(this.zzb.zzm());
        zzlu.zza();     // Catch: UnsupportedEncodingException -> L14
        zzhz r2 = this.zza;
        if (r3 == 0) goto L11;
        zzib r22 = r2.zzg();     // Catch: UnsupportedEncodingException -> L14
        zzaq r32 = new zzaq();     // Catch: UnsupportedEncodingException -> L14
        zzgl.zza.configure(r32);     // Catch: UnsupportedEncodingException -> L14
        return r32.zza().zza(r22);
    L11:
        return new JsonDataEncoderBuilder().configureWith(zzgl.zza).ignoreNullValues(true).build().encode(r2.zzg()).getBytes("utf-8");
    L14:
        e = move-exception;
        throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
    L5:
        r0 = true;
        goto L6
    }
}
