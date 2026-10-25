package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzpu {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzvb zzb = null;
    private static final Object zzc = null;
    private static volatile zztt zzd;

    static {
        zzb = new zzvb(zznu.zzb());
        zzc = new Object();
        zzd = null;
    }

    public static ListenableFuture zza(zzlk r3, String r4, String r5) {
        zztq r52 = zztr.zzh();
        zzrz r0 = zzsa.zza(r3.zzc());
        r0.zzb("phenotype");
        r0.zzc("all_accounts.pb");
        r52.zza(r0.zzd());
        r52.zzb(zznu.zzb());
        r52.zzc(zzb);
        r52.zzf(false);
        zztr r53 = r52.zzg();
        zztt r02 = zzd;
        if (r02 != null) goto L16;
        Object r1 = zzc;
        monitor-enter(r1);
        r02 = zzd;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        zztu r03 = new zztu();     // Catch: Throwable -> L9
        r03.zza(r3.zzg());     // Catch: Throwable -> L9
        r03.zzb(r3.zzi());     // Catch: Throwable -> L9
        r03.zzc(zzui.zza());     // Catch: Throwable -> L9
        r02 = r03.zzd();     // Catch: Throwable -> L9
        zzd = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    L16:
        return r02.zza(r53).zzb(new zzpt(r4, _UrlKt.FRAGMENT_ENCODE_SET), r3.zzg());
    }
}
