package com.google.android.gms.internal.mlkit_vision_common;

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
public final class zzmj {
    private static zzp zza;
    private static final zzr zzb = null;
    private final String zzc;
    private final String zzd;
    private final zzmc zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk;
    private final Map zzl;

    static {
        zzb = zzr.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zzmj(Context r2, SharedPrefManager r3, zzmc r4, String r5) {
        this.zzk = new HashMap();
        this.zzl = new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zzmw.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzmg(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        r3.getClass();
        this.zzh = r42.scheduleCallable(new zzmh(r3));
        zzr r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    private static synchronized zzp zzd() {
        monitor-enter(zzmj.class);
        zzp r1 = zza;     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(zzmj.class);
        return r1;
    L8:
        LocaleListCompat r12 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());     // Catch: Throwable -> L12
        zzm r2 = new zzm();     // Catch: Throwable -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L14;
        r2.zzb(CommonUtils.languageTagFromLocale(r12.get(r3)));     // Catch: Throwable -> L12
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        zzp r13 = r2.zzc();     // Catch: Throwable -> L12
        zza = r13;     // Catch: Throwable -> L12
        monitor-exit(zzmj.class);
        return r13;
    L12:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ String zza() {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    public final /* synthetic */ void zzb(zzmb r3, zziv r4, String r5) {
        r3.zza(r4);
        String r42 = r3.zzc();
        zzky r0 = new zzky();
        r0.zzb(this.zzc);
        r0.zzc(this.zzd);
        r0.zzh(zzd());
        r0.zzg(Boolean.TRUE);
        r0.zzl(r42);
        r0.zzj(r5);
        if (this.zzh.isSuccessful() == false) goto L5;
        String r43 = (String) this.zzh.getResult();
    L6:
        r0.zzi(r43);
        r0.zzd(10);
        r0.zzk(Integer.valueOf(this.zzj));
        r3.zzb(r0);
        this.zze.zza(r3);
        return;
    L5:
        r43 = this.zzf.getMlSdkInstanceId();
        goto L6
    }

    public final void zzc(zzmt r10, zziv r11) {
        long r0 = SystemClock.elapsedRealtime();
        if (this.zzk.get(r11) != null) goto L6;
    L8:
        this.zzk.put(r11, Long.valueOf(r0));
        int r02 = r10.zza;
        int r1 = r10.zzb;
        int r2 = r10.zzc;
        int r3 = r10.zzd;
        int r4 = r10.zze;
        long r5 = r10.zzf;
        int r102 = r10.zzg;
        zzin r7 = new zzin();
        if (r02 != (-1)) goto L11;
        zzii r03 = zzii.zzg;
    L24:
        r7.zzd(r03);
        if (r1 != 1) goto L27;
        zzio r04 = zzio.zzb;
    L37:
        r7.zzf(r04);
        r7.zzc(Integer.valueOf(r2));
        r7.zze(Integer.valueOf(r3));
        r7.zzg(Integer.valueOf(r4));
        r7.zzb(Long.valueOf(r5));
        r7.zzh(Integer.valueOf(r102));
        zziq r103 = r7.zzj();
        zziw r05 = new zziw();
        r05.zzd(r103);
        zzmb r104 = zzmk.zze(r05);
        if (this.zzg.isSuccessful() == false) goto L40;
        String r06 = (String) this.zzg.getResult();
    L41:
        MLTaskExecutor.workerThreadExecutor().execute(new zzmi(this, r104, r11, r06));
        return;
    L40:
        r06 = LibraryVersion.getInstance().getVersion(this.zzi);
        goto L41
    L27:
        if (r1 != 2) goto L29;
        r04 = zzio.zzc;
        goto L37
    L29:
        if (r1 != 3) goto L31;
        r04 = zzio.zzd;
        goto L37
    L31:
        if (r1 == 4) goto L33;
        r04 = zzio.zzf;
        goto L37
    L33:
        r04 = zzio.zze;
        goto L37
    L11:
        if (r02 != 35) goto L13;
        r03 = zzii.zze;
        goto L24
    L13:
        if (r02 != 842094169) goto L15;
        r03 = zzii.zzd;
        goto L24
    L15:
        if (r02 != 16) goto L17;
        r03 = zzii.zzb;
        goto L24
    L17:
        if (r02 == 17) goto L19;
        r03 = zzii.zza;
        goto L24
    L19:
        r03 = zzii.zzc;
        goto L24
    L6:
        if ((r0 - ((Long) this.zzk.get(r11)).longValue()) > NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
    }
}
