package com.google.android.gms.measurement.internal;

import java.util.HashMap;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final /* synthetic */ class zzhr implements Callable {
    private final /* synthetic */ zzht zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzhr(zzht r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        zzht r0 = this.zza;
        zzaw r1 = r0.zzg.zzj();
        String r5 = this.zzb;
        zzh r12 = r1.zzu(r5);
        HashMap r2 = new HashMap();
        r2.put("platform", "android");
        r2.put("package_name", r5);
        r0.zzu.zzc().zzi();
        r2.put("gmp_version", 161000L);
        if (r12 == null) goto L8;
        String r52 = r12.zzr();
        if (r52 == null) goto L7;
        r2.put("app_version", r52);
    L7:
        r2.put("app_version_int", Long.valueOf(r12.zzt()));
        r2.put("dynamite_version", Long.valueOf(r12.zzB()));
    L8:
        return r2;
    }
}
