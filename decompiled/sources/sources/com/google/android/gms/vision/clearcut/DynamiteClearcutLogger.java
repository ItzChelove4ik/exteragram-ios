package com.google.android.gms.vision.clearcut;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.internal.vision.zze;
import com.google.android.gms.internal.vision.zzfi$zzo;
import com.google.android.gms.internal.vision.zzi;
import com.google.android.gms.vision.L;
import java.util.concurrent.ExecutorService;

@Keep
/* loaded from: classes5.dex */
public class DynamiteClearcutLogger {
    private static final ExecutorService zza = null;
    private zzb zzb;
    private VisionClearcutLogger zzc;

    static {
        zza = zze.zza().zza(2, zzi.zza);
    }

    public DynamiteClearcutLogger(@RecentlyNonNull Context r4) {
        this.zzb = new zzb(0.03333333333333333d);
        this.zzc = new VisionClearcutLogger(r4);
    }

    public static /* synthetic */ VisionClearcutLogger zza(DynamiteClearcutLogger r0) {
        return r0.zzc;
    }

    public final void zza(int r3, zzfi$zzo r4) {
        if (r3 == 3) goto L5;
    L8:
        zza.execute(new zza(this, r3, r4));
        return;
    L5:
        if (this.zzb.zza() == true) goto L8;
        L.v("Skipping image analysis log due to rate limiting", new Object[0]);
    }
}
