package com.google.android.gms.internal.mlkit_vision_label;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zznp {
    private static zzbe zza;
    private static final zzbg zzb = null;
    private final String zzc;
    private final String zzd;
    private final zzno zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk;
    private final Map zzl;

    static {
        zzb = zzbg.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zznp(Context r2, SharedPrefManager r3, zzno r4, String r5) {
        this.zzk = new HashMap();
        this.zzl = new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zzob.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zznj(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        r3.getClass();
        this.zzh = r42.scheduleCallable(new zznk(r3));
        zzbg r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    public static long zza(List r2, double r3) {
        return ((Long) r2.get(Math.max(((int) Math.ceil((r3 / 100.0d) * r2.size())) - 1, 0))).longValue();
    }

    private static synchronized zzbe zzi() {
        monitor-enter(zznp.class);
        zzbe r1 = zza;     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(zznp.class);
        return r1;
    L8:
        LocaleListCompat r12 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());     // Catch: Throwable -> L12
        zzbb r2 = new zzbb();     // Catch: Throwable -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L14;
        r2.zzb(CommonUtils.languageTagFromLocale(r12.get(r3)));     // Catch: Throwable -> L12
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        zzbe r13 = r2.zzc();     // Catch: Throwable -> L12
        zza = r13;     // Catch: Throwable -> L12
        monitor-exit(zznp.class);
        return r13;
    L12:
        th = move-exception;
        throw th;
    }

    private final String zzj() {
        if (this.zzg.isSuccessful() == false) goto L7;
        return (String) this.zzg.getResult();
    L7:
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    private final boolean zzk(zzkf r1, long r2, long r4) {
        if (this.zzk.get(r1) != null) goto L6;
        return true;
    L6:
        if ((r2 - ((Long) this.zzk.get(r1)).longValue()) <= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
        return true;
    L8:
        return false;
    }

    public final /* synthetic */ String zzb() {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzc(zznf r3, zzkf r4, String r5) {
        r3.zzb(r4);
        String r42 = r3.zzd();
        zzmh r0 = new zzmh();
        r0.zzb(this.zzc);
        r0.zzc(this.zzd);
        r0.zzh(zzi());
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

    public final void zzd(zznf r2, zzkf r3) {
        zze(r2, r3, zzj());
    }

    public final void zze(zznf r3, zzkf r4, String r5) {
        MLTaskExecutor.workerThreadExecutor().execute(new zznl(this, r3, r4, r5));
    }

    public final /* synthetic */ void zzf(zzkf r11, com.google.mlkit.vision.label.defaults.thin.zzg r12) {
        zzbj r122 = (zzbj) this.zzl.get(r11);
        if (r122 == null) goto L15;
        Iterator r0 = r122.zzq().iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        Object r1 = r0.next();
        ArrayList r2 = new ArrayList(r122.zzc(r1));
        Collections.sort(r2);
        zzjl r3 = new zzjl();
        int r4 = r2.size();
        int r5 = 0;
        long r6 = 0;
    L8:
        if (r5 >= r4) goto L10;
        Object r8 = r2.get(r5);
        r5 = r5 + 1;
        r6 = r6 + ((Long) r8).longValue();
        goto L8
    L10:
        r3.zza(Long.valueOf(r6 / r2.size()));
        r3.zzc(Long.valueOf(zza(r2, 100.0d)));
        r3.zzf(Long.valueOf(zza(r2, 75.0d)));
        r3.zzd(Long.valueOf(zza(r2, 50.0d)));
        r3.zzb(Long.valueOf(zza(r2, 25.0d)));
        r3.zze(Long.valueOf(zza(r2, 0.0d)));
        zzjn r32 = r3.zzg();
        int r22 = r2.size();
        zzkg r42 = new zzkg();
        r42.zze(zzkd.zzb);
        zzde r52 = new zzde();
        r52.zza(Integer.valueOf(r22));
        r52.zzc((zzdh) r1);
        r52.zzb(r32);
        r42.zzd(r52.zze());
        zze(zzns.zzf(r42), r11, zzj());
        goto L6
    L11:
        this.zzl.remove(r11);
        return;
    }

    public final /* synthetic */ void zzg(zzkf r8, Object r9, long r10, com.google.mlkit.vision.label.defaults.thin.zzg r12) {
        if (this.zzl.containsKey(r8) == true) goto L5;
        this.zzl.put(r8, zzaj.zzr());
    L5:
        ((zzbj) this.zzl.get(r8)).zzo(r9, Long.valueOf(r10));
        long r3 = SystemClock.elapsedRealtime();
        if (zzk(r8, r3, 30) == true) goto L8;
        return;
    L8:
        this.zzk.put(r8, Long.valueOf(r3));
        MLTaskExecutor.workerThreadExecutor().execute(new zznn(this, r8, r12, null));
    }

    public final void zzh(com.google.mlkit.vision.label.defaults.thin.zzf r7, zzkf r8) {
        long r2 = SystemClock.elapsedRealtime();
        if (zzk(r8, r2, 30) == true) goto L5;
        return;
    L5:
        this.zzk.put(r8, Long.valueOf(r2));
        zze(r7.zza(), r8, zzj());
    }
}
