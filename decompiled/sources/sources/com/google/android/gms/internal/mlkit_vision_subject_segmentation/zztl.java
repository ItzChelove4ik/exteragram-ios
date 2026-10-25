package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

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
public final class zztl {
    private static zzav zza;
    private static final zzax zzb = null;
    private final String zzc;
    private final String zzd;
    private final zztb zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk;
    private final Map zzl;

    static {
        zzb = zzax.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zztl(Context r2, SharedPrefManager r3, zztb r4, String r5) {
        this.zzk = new HashMap();
        this.zzl = new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zztx.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzth(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        r3.getClass();
        this.zzh = r42.scheduleCallable(new zzti(r3));
        zzax r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    public static long zza(List r4, double r5) {
        double r52 = r5 / 100.0d;
        return ((Long) r4.get(Math.max(((int) Math.ceil(r52 * r4.size())) - 1, 0))).longValue();
    }

    private static synchronized zzav zzi() {
        monitor-enter(zztl.class);
        zzav r1 = zza;     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(zztl.class);
        return r1;
    L8:
        LocaleListCompat r12 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());     // Catch: Throwable -> L12
        zzas r2 = new zzas();     // Catch: Throwable -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L14;
        r2.zza(CommonUtils.languageTagFromLocale(r12.get(r3)));     // Catch: Throwable -> L12
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        zzav r13 = r2.zzb();     // Catch: Throwable -> L12
        zza = r13;     // Catch: Throwable -> L12
        monitor-exit(zztl.class);
        return r13;
    L12:
        th = move-exception;
        throw th;
    }

    private final String zzj() {
        if (this.zzg.isSuccessful() == true) goto L5;
        String r1 = this.zzi;
        return LibraryVersion.getInstance().getVersion(r1);
    L5:
        return (String) this.zzg.getResult();
    }

    private final boolean zzk(zzob r1, long r2, long r4) {
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

    public final /* synthetic */ void zzc(zzta r3, zzob r4, String r5) {
        r3.zzb(r4);
        String r42 = r3.zzd();
        zzsa r0 = new zzsa();
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

    public final void zzd(zzta r2, zzob r3) {
        zze(r2, r3, zzj());
    }

    public final void zze(zzta r3, zzob r4, String r5) {
        MLTaskExecutor.workerThreadExecutor().execute(new zztf(this, r3, r4, r5));
    }

    public final void zzf(zztk r7, zzob r8) {
        long r2 = SystemClock.elapsedRealtime();
        if (zzk(r8, r2, 30) == true) goto L5;
        return;
    L5:
        this.zzk.put(r8, Long.valueOf(r2));
        zze(r7.zza(), r8, zzj());
    }

    public final /* synthetic */ void zzg(zzob r11, com.google.mlkit.vision.segmentation.subject.internal.zzh r12) {
        zzba r122 = (zzba) this.zzl.get(r11);
        if (r122 == null) goto L15;
        Iterator r0 = r122.zzo().iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        Object r1 = r0.next();
        ArrayList r2 = new ArrayList(r122.zzc(r1));
        Collections.sort(r2);
        zzna r3 = new zzna();
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
        zznc r32 = r3.zzg();
        int r22 = r2.size();
        zzoc r42 = new zzoc();
        r42.zze(zznz.zzb);
        zzdt r52 = new zzdt();
        r52.zza(Integer.valueOf(r22));
        r52.zzc((zzdw) r1);
        r52.zzb(r32);
        r42.zzd(r52.zze());
        zze(zzto.zzf(r42), r11, zzj());
        goto L6
    L11:
        this.zzl.remove(r11);
        return;
    }

    public final /* synthetic */ void zzh(zzob r8, Object r9, long r10, com.google.mlkit.vision.segmentation.subject.internal.zzh r12) {
        if (this.zzl.containsKey(r8) == true) goto L5;
        this.zzl.put(r8, zzz.zzp());
    L5:
        ((zzba) this.zzl.get(r8)).zzm(r9, Long.valueOf(r10));
        long r3 = SystemClock.elapsedRealtime();
        if (zzk(r8, r3, 30) == true) goto L8;
        return;
    L8:
        this.zzk.put(r8, Long.valueOf(r3));
        MLTaskExecutor.workerThreadExecutor().execute(new zztg(this, r8, r12));
    }
}
