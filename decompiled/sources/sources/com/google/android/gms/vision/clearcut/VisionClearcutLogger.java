package com.google.android.gms.vision.clearcut;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.internal.vision.zzfe;
import com.google.android.gms.internal.vision.zzfi$zzo;
import com.google.android.gms.internal.vision.zzio;
import com.google.android.gms.vision.L;

@Keep
/* loaded from: classes5.dex */
public class VisionClearcutLogger {
    private final ClearcutLogger zza;
    private boolean zzb;

    public VisionClearcutLogger(@RecentlyNonNull Context r4) {
        this.zzb = true;
        this.zza = new ClearcutLogger(r4, "VISION", null);
    }

    public final void zza(int r3, zzfi$zzo r4) {
        byte[] r42 = r4.zzh();
        if (r3 >= 0) goto L5;
    L22:
        L.i("Illegal event code: %d", new Object[]{Integer.valueOf(r3)});
        return;
    L5:
        if (r3 > 3) goto L22;
    L12:
        e = move-exception;
        zzfe.zza(e);
        L.e(e, "Failed to log", new Object[0]);
        return;
    L9:
        if (this.zzb == false) goto L14;
        this.zza.newEvent(r42).setEventCode(r3).log();     // Catch: Exception -> L12
        return;
    L14:
        zzfi$zzo.zza r2 = zzfi$zzo.zza();     // Catch: Exception -> L12
        zzio r32 = zzio.zzc();     // Catch: Exception -> L17
        r2.zza(r42, 0, r42.length, r32);     // Catch: Exception -> L17
        L.e("Would have logged:\n%s", new Object[]{r2.toString()});     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        L.e(e, "Parsing error", new Object[0]);     // Catch: Exception -> L12
    }
}
