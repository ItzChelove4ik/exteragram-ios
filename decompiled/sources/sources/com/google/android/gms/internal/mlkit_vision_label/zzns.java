package com.google.android.gms.internal.mlkit_vision_label;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes4.dex */
public final class zzns implements zznf {
    private final zzkg zza;
    private zzmh zzb;
    private final int zzc;

    private zzns(zzkg r2, int r3) {
        this.zzb = new zzmh();
        this.zza = r2;
        zzob.zza();
        this.zzc = r3;
    }

    public static zznf zzf(zzkg r2) {
        return new zzns(r2, 0);
    }

    public static zznf zzg(zzkg r1, int r2) {
        return new zzns(r1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zznf
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zznf
    public final zznf zzb(zzkf r2) {
        this.zza.zzf(r2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zznf
    public final zznf zzc(zzmh r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zznf
    public final String zzd() {
        zzmj r1 = this.zza.zzj().zzf();
        if (r1 != null) goto L5;
        return "NA";
    L5:
        if (zzt.zzb(r1.zzk()) == false) goto L7;
        return "NA";
    L7:
        return (String) Preconditions.checkNotNull(r1.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zznf
    public final byte[] zze(int r3, boolean r4) {
        zzmh r42 = this.zzb;
        if (1 == (r3 ^ 1)) goto L5;
        boolean r0 = false;
    L6:
        r42.zzf(Boolean.valueOf(r0));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzi(this.zzb.zzm());
        zzob.zza();     // Catch: UnsupportedEncodingException -> L14
        zzkg r2 = this.zza;
        if (r3 == 0) goto L11;
        zzki r22 = r2.zzj();     // Catch: UnsupportedEncodingException -> L14
        zzcq r32 = new zzcq();     // Catch: UnsupportedEncodingException -> L14
        zzip.zza.configure(r32);     // Catch: UnsupportedEncodingException -> L14
        return r32.zza().zza(r22);
    L11:
        return new JsonDataEncoderBuilder().configureWith(zzip.zza).ignoreNullValues(true).build().encode(r2.zzj()).getBytes("utf-8");
    L14:
        e = move-exception;
        throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
    L5:
        r0 = true;
        goto L6
    }
}
