package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzli {
    private static zzu zza;
    private static final zzw zzb = null;
    private final String zzc;
    private final String zzd;
    private final zzlh zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk;
    private final Map zzl;

    static {
        zzb = zzw.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zzli(Context r2, SharedPrefManager r3, zzlh r4, String r5) {
        this.zzk = new HashMap();
        this.zzl = new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zzlu.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzle(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        r3.getClass();
        this.zzh = r42.scheduleCallable(new zzlf(r3));
        zzw r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    private static synchronized zzu zzf() {
        monitor-enter(zzli.class);
        zzu r1 = zza;     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(zzli.class);
        return r1;
    L8:
        LocaleListCompat r12 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());     // Catch: Throwable -> L12
        zzr r2 = new zzr();     // Catch: Throwable -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L14;
        r2.zzb(CommonUtils.languageTagFromLocale(r12.get(r3)));     // Catch: Throwable -> L12
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        zzu r13 = r2.zzc();     // Catch: Throwable -> L12
        zza = r13;     // Catch: Throwable -> L12
        monitor-exit(zzli.class);
        return r13;
    L12:
        th = move-exception;
        throw th;
    }

    private final String zzg() {
        if (this.zzg.isSuccessful() == false) goto L7;
        return (String) this.zzg.getResult();
    L7:
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ String zza() {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzb(zzla r3, zzhy r4, String r5) {
        r3.zzb(r4);
        String r42 = r3.zzd();
        zzkc r0 = new zzkc();
        r0.zzb(this.zzc);
        r0.zzc(this.zzd);
        r0.zzh(zzf());
        r0.zzg(Boolean.TRUE);
        r0.zzl(r42);
        r0.zzj(r5);
        if (this.zzh.isSuccessful() == false) goto L5;
        String r43 = (String) this.zzh.getResult();
    L6:
        r0.zzi(r43);
        r0.zzd(10);
        r0.zzk(Integer.valueOf(this.zzj));
        r3.zzc(r0);
        this.zze.zza(r3);
        return;
    L5:
        r43 = this.zzf.getMlSdkInstanceId();
        goto L6
    }

    public final void zzc(zzla r2, zzhy r3) {
        zzd(r2, r3, zzg());
    }

    public final void zzd(zzla r3, zzhy r4, String r5) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzlg(this, r3, r4, r5));
    }

    public final void zze(com.google.mlkit.nl.languageid.internal.zzf r7, zzhy r8) {
        long r0 = SystemClock.elapsedRealtime();
        if (this.zzk.get(r8) != null) goto L6;
    L8:
        this.zzk.put(r8, Long.valueOf(r0));
        zzd(r7.zza(), r8, zzg());
        return;
    L6:
        if ((r0 - ((Long) this.zzk.get(r8)).longValue()) > NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
    }
}
