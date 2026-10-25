package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzh;
import com.google.android.gms.internal.stats.zzi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes.dex */
public class WakeLock {
    private static final long zzb = 0;
    private static volatile ScheduledExecutorService zzc;
    private static final Object zzd = null;
    private static volatile zzd zze;
    com.google.android.gms.internal.stats.zzb zza;
    private final Object zzf;
    private final PowerManager.WakeLock zzg;
    private int zzh;
    private Future<?> zzi;
    private long zzj;
    private final Set<Object> zzk;
    private boolean zzl;
    private int zzm;
    private Clock zzn;
    private WorkSource zzo;
    private final String zzp;
    private final String zzq;
    private final Context zzr;
    private final Map<String, zzc> zzs;
    private AtomicInteger zzt;
    private final ScheduledExecutorService zzu;

    static {
        zzb = 31622400000L;
        zzc = null;
        zzd = new Object();
        zze = new zzb();
    }

    public WakeLock(Context r7, int r8, String r9) {
        String r0 = r7.getPackageName();
        this.zzf = new Object();
        this.zzh = 0;
        this.zzk = new HashSet();
        this.zzl = true;
        this.zzn = DefaultClock.getInstance();
        this.zzs = new HashMap();
        this.zzt = new AtomicInteger(0);
        Preconditions.checkNotNull(r7, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(r9, "WakeLock: wakeLockName must not be empty");
        this.zzr = r7.getApplicationContext();
        this.zzq = r9;
        this.zza = null;
        if ("com.google.android.gms".equals(r7.getPackageName()) == true) goto L9;
        String r3 = String.valueOf(r9);
        if (r3.length() == 0) goto L7;
        String r32 = "*gcore*:".concat(r3);
    L8:
        this.zzp = r32;
    L10:
        PowerManager r33 = (PowerManager) r7.getSystemService("power");
        if (r33 == null) goto L35;
        PowerManager.WakeLock r82 = r33.newWakeLock(r8, r9);
        this.zzg = r82;
        if (WorkSourceUtil.hasWorkSourcePermission(r7) == true) goto L15;
    L20:
        ScheduledExecutorService r72 = zzc;
        if (r72 != null) goto L33;
        Object r83 = zzd;
        monitor-enter(r83);
        r72 = zzc;     // Catch: Throwable -> L27
        if (r72 != null) goto L29;
        zzh.zza();     // Catch: Throwable -> L27
        r72 = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));     // Catch: Throwable -> L27
        zzc = r72;     // Catch: Throwable -> L27
    L29:
        monitor-exit(r83);     // Catch: Throwable -> L27
    L27:
        th = move-exception;
        throw th;
    L33:
        this.zzu = r72;
        return;
    L15:
        if (Strings.isEmptyOrWhitespace(r0) == false) goto L17;
        r0 = r7.getPackageName();
    L17:
        WorkSource r73 = WorkSourceUtil.fromPackage(r7, r0);
        this.zzo = r73;
        if (r73 == null) goto L20;
        zze(r82, r73);
        goto L20
    L35:
        StringBuilder r74 = new StringBuilder(29);
        r74.append("expected a non-null reference", 0, 29);
        throw new zzi(r74.toString());
    L7:
        r32 = new String("*gcore*:");
        goto L8
    L9:
        this.zzp = r9;
        goto L10
    }

    public static /* synthetic */ void zza(WakeLock r4) {
        Object r0 = r4.zzf;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (r4.isHeld() == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        Log.e("WakeLock", String.valueOf(r4.zzp).concat(" ** IS FORCE-RELEASED ON TIMEOUT **"));     // Catch: Throwable -> L8
        r4.zzc();     // Catch: Throwable -> L8
        if (r4.isHeld() == true) goto L14;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L14:
        r4.zzh = 1;     // Catch: Throwable -> L8
        r4.zzd(0);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    private final String zzb(String r1) {
        if (this.zzl == false) goto L5;
        TextUtils.isEmpty(null);
    L5:
        return null;
    }

    private final void zzc() {
        if (this.zzk.isEmpty() == true) goto L10;
        ArrayList r0 = new ArrayList(this.zzk);
        this.zzk.clear();
        if (r0.size() > 0) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.get(0));
        throw null;
    }

    private final void zzd(int r6) {
        Object r62 = this.zzf;
        monitor-enter(r62);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (isHeld() == true) goto L11;
        monitor-exit(r62);     // Catch: Throwable -> L8
        return;
    L11:
        if (this.zzl == false) goto L17;
        int r0 = this.zzh - 1;
        this.zzh = r0;     // Catch: Throwable -> L8
        if (r0 <= 0) goto L18;
        monitor-exit(r62);     // Catch: Throwable -> L8
        return;
    L18:
        zzc();     // Catch: Throwable -> L8
        Iterator<zzc> r02 = this.zzs.values().iterator();     // Catch: Throwable -> L8
    L20:
        if (r02.hasNext() == false) goto L22;
        r02.next().zza = 0;     // Catch: Throwable -> L8
        goto L20
    L22:
        this.zzs.clear();     // Catch: Throwable -> L8
        Future<?> r03 = this.zzi;     // Catch: Throwable -> L8
        if (r03 == null) goto L25;
        r03.cancel(false);     // Catch: Throwable -> L8
        this.zzi = null;     // Catch: Throwable -> L8
        this.zzj = 0;     // Catch: Throwable -> L8
    L25:
        this.zzm = 0;     // Catch: Throwable -> L8
        if (this.zzg.isHeld() == true) goto L52;
        Log.e("WakeLock", String.valueOf(this.zzp).concat(" should be held!"));     // Catch: Throwable -> L8
    L48:
        monitor-exit(r62);     // Catch: Throwable -> L8
        return;
    L52:
        this.zzg.release();     // Catch: Throwable -> L32 RuntimeException -> L34
    L29:
        if (this.zza == null) goto L48;
        this.zza = null;     // Catch: Throwable -> L8
    L34:
        e = move-exception;
        if (e.getClass().equals(RuntimeException.class) == false) goto L42;
        Log.e("WakeLock", String.valueOf(this.zzp).concat(" failed to release!"), e);     // Catch: Throwable -> L32
        if (this.zza == null) goto L48;
        this.zza = null;     // Catch: Throwable -> L8
        goto L48
    L42:
        throw e;     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        if (this.zza == null) goto L46;
        this.zza = null;     // Catch: Throwable -> L8
    L46:
        throw th;     // Catch: Throwable -> L8
    L17:
        this.zzh = 0;     // Catch: Throwable -> L8
        goto L18
    }

    private static void zze(PowerManager.WakeLock r0, WorkSource r1) {
        r0.setWorkSource(r1);     // Catch: Throwable -> L4
        return;
    L4:
        e = move-exception;
        Log.wtf("WakeLock", e.toString());
    }

    public void acquire(long r10) {
        this.zzt.incrementAndGet();
        long r0 = zzb;
        long r2 = LongCompanionObject.MAX_VALUE;
        long r02 = Math.max(Math.min(LongCompanionObject.MAX_VALUE, r0), 1);
        if (r10 <= 0) goto L5;
        r02 = Math.min(r10, r02);
    L5:
        Object r102 = this.zzf;
        monitor-enter(r102);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (isHeld() == true) goto L12;
        this.zza = com.google.android.gms.internal.stats.zzb.zza(false, null);     // Catch: Throwable -> L10
        this.zzg.acquire();     // Catch: Throwable -> L10
        this.zzn.elapsedRealtime();     // Catch: Throwable -> L10
    L12:
        this.zzh++;
        this.zzm++;
        zzb(null);     // Catch: Throwable -> L10
        zzc r11 = this.zzs.get(null);     // Catch: Throwable -> L10
        if (r11 != null) goto L15;
        r11 = new zzc(null);     // Catch: Throwable -> L10
        this.zzs.put(null, r11);     // Catch: Throwable -> L10
    L15:
        r11.zza++;
        long r5 = this.zzn.elapsedRealtime();     // Catch: Throwable -> L10
        if ((LongCompanionObject.MAX_VALUE - r5) <= r02) goto L19;
        r2 = r5 + r02;     // Catch: Throwable -> L10
    L19:
        if (r2 <= this.zzj) goto L24;
        this.zzj = r2;     // Catch: Throwable -> L10
        Future<?> r112 = this.zzi;     // Catch: Throwable -> L10
        if (r112 == null) goto L23;
        r112.cancel(false);     // Catch: Throwable -> L10
    L23:
        this.zzi = this.zzu.schedule(new zza(this), r02, TimeUnit.MILLISECONDS);     // Catch: Throwable -> L10
    L24:
        monitor-exit(r102);     // Catch: Throwable -> L10
    }

    public boolean isHeld() {
        Object r0 = this.zzf;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.zzh <= 0) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L7:
        r1 = false;
        goto L8
    }

    public void release() {
        if (this.zzt.decrementAndGet() >= 0) goto L5;
        Log.e("WakeLock", String.valueOf(this.zzp).concat(" release without a matched acquire!"));
    L5:
        Object r0 = this.zzf;
        monitor-enter(r0);
        zzb(null);     // Catch: Throwable -> L15
        if (this.zzs.containsKey(null) == false) goto L17;
        zzc r2 = this.zzs.get(null);     // Catch: Throwable -> L15
        if (r2 == null) goto L18;
        int r3 = r2.zza - 1;
        r2.zza = r3;     // Catch: Throwable -> L15
        if (r3 != 0) goto L18;
        this.zzs.remove(null);     // Catch: Throwable -> L15
    L18:
        zzd(0);     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        return;
    L17:
        Log.w("WakeLock", String.valueOf(this.zzp).concat(" counter does not exist"));     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    }

    public void setReferenceCounted(boolean r2) {
        Object r0 = this.zzf;
        monitor-enter(r0);
        this.zzl = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
