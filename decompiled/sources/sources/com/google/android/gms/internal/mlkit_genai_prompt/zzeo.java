package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
public abstract class zzeo {
    public zzeo() {
    }

    public static zzem zzq() {
        zzef r0 = new zzef();
        r0.zzb(0.0f);
        r0.zzc(1);
        r0.zzd(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        r0.zzf(0);
        r0.zzg(0);
        r0.zzh(0);
        r0.zze(1);
        r0.zzl(6);
        r0.zzj(0);
        return r0;
    }

    public abstract zzml zza();

    public abstract ParcelFileDescriptor zzb();

    public abstract zzml zzc();

    public abstract float zzd();

    public abstract int zze();

    public abstract int zzf();

    public abstract int zzg();

    public abstract zzev zzh();

    public abstract int zzi();

    public abstract int zzj();

    public abstract int zzk();

    public abstract String zzl();

    @Deprecated
    public abstract String zzm();

    public abstract int zzn();

    public abstract int zzo();

    public abstract zzwh zzp();
}
