package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* loaded from: classes4.dex */
public final class zzto implements zzta {
    private final zzoc zza;
    private zzsa zzb;
    private final int zzc;

    private zzto(zzoc r2, int r3) {
        this.zzb = new zzsa();
        this.zza = r2;
        zztx.zza();
        this.zzc = r3;
    }

    public static zzta zzf(zzoc r2) {
        return new zzto(r2, 0);
    }

    public static zzta zzg(zzoc r1, int r2) {
        return new zzto(r1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta
    public final zzta zzb(zzob r2) {
        this.zza.zzf(r2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta
    public final zzta zzc(zzsa r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta
    public final String zzd() {
        zzsc r1 = this.zza.zzk().zzg();
        if (r1 != null) goto L5;
        return "NA";
    L5:
        if (zzj.zzb(r1.zzk()) == false) goto L7;
        return "NA";
    L7:
        return (String) Preconditions.checkNotNull(r1.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta
    public final byte[] zze(int r3, boolean r4) {
        int r42 = r3 ^ 1;
        zzsa r0 = this.zzb;
        if (1 == r42) goto L5;
        boolean r43 = false;
    L6:
        r0.zzf(Boolean.valueOf(r43));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzj(this.zzb.zzm());
        zztx.zza();     // Catch: UnsupportedEncodingException -> L14
        zzoc r2 = this.zza;
        if (r3 == 0) goto L11;
        zzoe r22 = r2.zzk();     // Catch: UnsupportedEncodingException -> L14
        zzch r32 = new zzch();     // Catch: UnsupportedEncodingException -> L14
        zzlw.zza.configure(r32);     // Catch: UnsupportedEncodingException -> L14
        return r32.zza().zza(r22);
    L11:
        return new JsonDataEncoderBuilder().configureWith(zzlw.zza).ignoreNullValues(true).build().encode(r2.zzk()).getBytes("utf-8");
    L14:
        e = move-exception;
        throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
    L5:
        r43 = true;
        goto L6
    }
}
