package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import android.net.Uri;

/* loaded from: classes4.dex */
public abstract class zzhs {
    public zzhs() {
    }

    public static zzhs zzi(int r9, Bitmap r10) {
        return new zzhd(0, null, r10, null, null, null, null, 1);
    }

    public static zzhs zzj(int r9, String r10) {
        return new zzhd(r9, r10, null, null, null, null, null, 0);
    }

    public abstract int zza();

    public abstract String zzb();

    public abstract Bitmap zzc();

    public abstract Uri zzd();

    public abstract Uri zze();

    public abstract zzhq zzf();

    public abstract zzhr zzg();

    public abstract int zzh();
}
