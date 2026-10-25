package com.google.android.gms.vision.clearcut;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.vision.zzfi$zza;
import com.google.android.gms.internal.vision.zzfi$zzf;
import com.google.android.gms.internal.vision.zzfi$zzi;
import com.google.android.gms.internal.vision.zzfi$zzj;
import com.google.android.gms.internal.vision.zzfi$zzn;
import com.google.android.gms.internal.vision.zzfi$zzo;
import com.google.android.gms.internal.vision.zzjb;
import com.google.android.gms.internal.vision.zzs;
import com.google.android.gms.vision.L;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: classes5.dex */
public class LogUtils {
    public LogUtils() {
    }

    public static zzfi$zza zza(Context r2) {
        zzfi$zza.zza r0 = zzfi$zza.zza().zza(r2.getPackageName());
        String r22 = zzb(r2);
        if (r22 == null) goto L6;
        r0.zzb(r22);
    L6:
        return (zzfi$zza) ((zzjb) r0.zzf());
    }

    public static zzfi$zzo zza(long r1, int r3, String r4, String r5, List<zzfi$zzn> r6, zzs r7) {
        zzfi$zzi.zza r42 = zzfi$zzi.zza();
        zzfi$zzf.zzb r12 = zzfi$zzf.zza().zza(r5).zza(r1).zzb(r3);
        r12.zza(r6);
        ArrayList r2 = new ArrayList();
        r2.add((zzfi$zzf) ((zzjb) r12.zzf()));
        return (zzfi$zzo) ((zzjb) zzfi$zzo.zza().zza((zzfi$zzi) ((zzjb) r42.zza(r2).zza((zzfi$zzj) ((zzjb) zzfi$zzj.zza().zzb(r7.zzb).zza(r7.zza).zzc(r7.zzc).zzd(r7.zzd).zzf())).zzf())).zzf());
    }

    private static String zzb(Context r3) {
        return Wrappers.packageManager(r3).getPackageInfo(r3.getPackageName(), 0).versionName;
    L4:
        e = move-exception;
        L.e(e, "Unable to find calling package info for %s", new Object[]{r3.getPackageName()});
        return null;
    }
}
