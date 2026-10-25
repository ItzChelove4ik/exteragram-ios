package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzavc {
    private static zzml zza;
    private static final zzmo zzb = null;
    private final String zzc;
    private final String zzd;
    private final zzauv zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;

    static {
        zzb = zzmo.zzb("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zzavc(Context r2, SharedPrefManager r3, zzauv r4, String r5) {
        new HashMap();
        new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zzavl.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzava(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        Objects.requireNonNull(r3);
        this.zzh = r42.scheduleCallable(new zzavb(r3));
        zzmo r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    private static synchronized zzml zzd() {
        monitor-enter(zzavc.class);
        zzml r1 = zza;     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(zzavc.class);
        return r1;
    L8:
        LocaleListCompat r12 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());     // Catch: Throwable -> L12
        zzmh r2 = new zzmh();     // Catch: Throwable -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L14;
        r2.zzd(CommonUtils.languageTagFromLocale(r12.get(r3)));     // Catch: Throwable -> L12
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        zzml r13 = r2.zze();     // Catch: Throwable -> L12
        zza = r13;     // Catch: Throwable -> L12
        monitor-exit(zzavc.class);
        return r13;
    L12:
        th = move-exception;
        throw th;
    }

    public final void zza(zzauu r4, zzanu r5) {
        Task r0 = this.zzg;
        if (r0.isSuccessful() == false) goto L5;
        String r02 = (String) r0.getResult();
    L6:
        MLTaskExecutor.workerThreadExecutor().execute(new zzauz(this, r4, r5, r02));
        return;
    L5:
        String r03 = this.zzi;
        r02 = LibraryVersion.getInstance().getVersion(r03);
        goto L6
    }

    public final /* synthetic */ String zzb() {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzc(zzauu r3, zzanu r4, String r5) {
        r3.zzc(r4);
        String r42 = r3.zzd();
        zzasz r0 = new zzasz();
        r0.zza(this.zzc);
        r0.zzb(this.zzd);
        r0.zze(zzd());
        r0.zzh(Boolean.TRUE);
        r0.zzd(r42);
        r0.zzc(r5);
        Task r43 = this.zzh;
        if (r43.isSuccessful() == false) goto L5;
        String r44 = (String) r43.getResult();
    L6:
        r0.zzf(r44);
        r0.zzj(10);
        r0.zzk(Integer.valueOf(this.zzj));
        r3.zzb(r0);
        this.zze.zza(r3);
        return;
    L5:
        r44 = this.zzf.getMlSdkInstanceId();
        goto L6
    }
}
