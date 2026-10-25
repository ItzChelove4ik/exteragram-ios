package com.google.android.recaptcha.internal;

import android.app.Application;
import androidx.core.content.ContextCompat;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* loaded from: classes5.dex */
public final class zzjt {
    private final Application zza;
    private final zzko zzb;
    private final Mutex zzc;
    private zzjj zzd;

    public zzjt(Application r2, zzko r3) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = MutexKt.Mutex$default(false, 1, null);
    }

    public static /* synthetic */ Object zza(zzjt r7, String r8, long r9, zziw r11, Continuation r12, int r13, Object r14) {
        if ((r13 & 4) == 0) goto L5;
        r11 = zziw.zza;
    L5:
        zziw r2 = r11;
        if ((r13 & 2) == 0) goto L8;
        r9 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L8:
        long r4 = r9;
        return BuildersKt.withContext(r7.zzb.zze().zzc().getCoroutineContext(), new zzjq(r7, r2, r8, r4, null), r12);
    }

    public static final /* synthetic */ Mutex zzb(zzjt r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ zzko zzc(zzjt r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zzjj zzd(zzjt r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ void zze(zzjt r9, long r10, String r12) {
        if (r12.length() == 0) goto L14;
        if (r10 < 5000) goto L12;
        if (ContextCompat.checkSelfPermission(r9.zza, "android.permission.INTERNET") != 0) goto L10;
        return;
    L10:
        throw new zzhg(zzhf.zzc, zzhe.zzy, null, null, 12, null);
    L12:
        throw new zzhg(zzhf.zzj, zzhe.zzm, null, null, 12, null);
    L14:
        throw new zzhg(zzhf.zzd, zzhe.zze, null, null, 12, null);
    }

    public static final /* synthetic */ void zzf(zzjt r0, zzjj r1) {
        r0.zzd = r1;
    }
}
