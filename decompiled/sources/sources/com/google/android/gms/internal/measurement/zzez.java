package com.google.android.gms.internal.measurement;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class zzez {
    private static volatile zzez zzc;
    protected final Clock zza;
    protected final ExecutorService zzb;
    private final String zzd;
    private final AppMeasurementSdk zze;
    private final List zzf;
    private int zzg;
    private boolean zzh;
    private final String zzi;
    private volatile zzcp zzj;
    private volatile long zzk;

    public zzez(Context r10, Bundle r11) {
        this.zzd = "FA";
        this.zza = DefaultClock.getInstance();
        zzck.zza();
        zzeb r8 = new zzeb(this);
        LinkedBlockingQueue r7 = new LinkedBlockingQueue();
        ThreadPoolExecutor r1 = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, r7, r8);
        r1.allowCoreThreadTimeOut(true);
        this.zzb = Executors.unconfigurableExecutorService(r1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        if (com.google.android.gms.measurement.internal.zzlt.zza(r10, "google_app_id", com.google.android.gms.measurement.internal.zzhu.zza(r10)) == null) goto L10;
        Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, zzez.class.getClassLoader());     // Catch: ClassNotFoundException -> L8
    L8:
        this.zzi = null;
        this.zzh = true;
        Log.w(this.zzd, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Add Google Analytics for Firebase to resume data collection.");
        return;
    L10:
        this.zzi = "fa";
        zzV(new zzdp(this, r10, r11));
        Application r102 = (Application) r10.getApplicationContext();
        if (r102 != null) goto L14;
        Log.w(this.zzd, "Unable to register lifecycle notifications. Application null.");
        return;
    L14:
        r102.registerActivityLifecycleCallbacks(new zzey(this));
    }

    private final void zzV(zzeo r1) {
        this.zzb.execute(r1);
    }

    private final void zzW(Exception r7, boolean r8, boolean r9) {
        this.zzh |= r8;
        if (r8 == false) goto L7;
        Log.w(this.zzd, "Data collection startup failed. No data will be collected.", r7);
        return;
    L7:
        if (r9 == false) goto L9;
        zzez r0 = this;
        Exception r3 = r7;
        r0.zzD(5, "Error with data collection. Data lost.", r3, null, null);
    L10:
        Log.w(r0.zzd, "Error with data collection. Data lost.", r3);
        return;
    L9:
        r0 = this;
        r3 = r7;
        goto L10
    }

    private final void zzX(String r10, String r11, Bundle r12, boolean r13, boolean r14, Long r15, Long r16) {
        zzV(new zzen(this, r15, r16, r10, r11, r12, r13, r14));
    }

    public static zzez zza(Context r3, Bundle r4) {
        Preconditions.checkNotNull(r3);
        if (zzc != null) goto L20;
        monitor-enter(zzez.class);
    L11:
        th = move-exception;
        throw th;
    L7:
        if (zzc == null) goto L9;
    L15:
        monitor-exit(zzez.class);     // Catch: Throwable -> L11
        goto L20
    L9:
        if (r4 != null) goto L13;
        Bundle r42 = new Bundle();     // Catch: Throwable -> L11
    L14:
        zzc = new zzez(r3, r42);     // Catch: Throwable -> L11
        goto L15
    L13:
        r42 = new Bundle(r4);     // Catch: Throwable -> L11
    L20:
        return zzc;
    }

    public final String zzA() {
        zzcm r0 = new zzcm();
        zzV(new zzdx(this, r0));
        return r0.zzc(500);
    }

    public final String zzB() {
        zzcm r0 = new zzcm();
        zzV(new zzdy(this, r0));
        return r0.zzc(500);
    }

    public final Map zzC(String r7, String r8, boolean r9) {
        zzcm r5 = new zzcm();
        zzV(new zzdz(this, r7, r8, r9, r5));
        Bundle r6 = r5.zze(5000);
        if (r6 == null) goto L19;
        if (r6.size() == 0) goto L19;
        HashMap r72 = new HashMap(r6.size());
        Iterator<String> r82 = r6.keySet().iterator();
    L9:
        if (r82.hasNext() == false) goto L17;
        String r92 = r82.next();
        Object r0 = r6.get(r92);
        if ((r0 instanceof Double) == true) goto L16;
        if ((r0 instanceof Long) == true) goto L16;
        if ((r0 instanceof String) == false) goto L9;
    L16:
        r72.put(r92, r0);
        goto L9
    L17:
        return r72;
    L19:
        return Collections.EMPTY_MAP;
    }

    public final void zzD(int r9, String r10, Object r11, Object r12, Object r13) {
        zzV(new zzea(this, false, 5, r10, r11, null, null));
    }

    public final int zzF(String r3) {
        zzcm r0 = new zzcm();
        zzV(new zzed(this, r3, r0));
        Integer r2 = (Integer) zzcm.zzf(r0.zze(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS), Integer.class);
        if (r2 != null) goto L7;
        return 25;
    L7:
        return r2.intValue();
    }

    public final /* synthetic */ void zzM(zzeo r1) {
        zzV(r1);
    }

    public final /* synthetic */ void zzN(Exception r1, boolean r2, boolean r3) {
        zzW(r1, r2, r3);
    }

    public final /* synthetic */ boolean zzO() {
        if (this.zzk < 169) goto L6;
        return true;
    L6:
        return false;
    }

    public final /* synthetic */ boolean zzP() {
        if (this.zzk < 170) goto L6;
        return true;
    L6:
        return false;
    }

    public final /* synthetic */ String zzQ() {
        return this.zzd;
    }

    public final /* synthetic */ boolean zzR() {
        return this.zzh;
    }

    public final /* synthetic */ zzcp zzS() {
        return this.zzj;
    }

    public final /* synthetic */ void zzT(zzcp r1) {
        this.zzj = r1;
    }

    public final /* synthetic */ void zzU(long r1) {
        this.zzk = r1;
    }

    public final AppMeasurementSdk zzb() {
        return this.zze;
    }

    public final zzcp zzc(Context r2, boolean r3) {
        if (r3 == false) goto L6;
        DynamiteModule.VersionPolicy r32 = DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION;     // Catch: DynamiteModule.LoadingException -> L4
    L7:
        return zzco.asInterface(DynamiteModule.load(r2, r32, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
    L6:
        r32 = DynamiteModule.PREFER_LOCAL;     // Catch: DynamiteModule.LoadingException -> L4
    L4:
        e = move-exception;
        this.zzW(e, true, false);
        return null;
    }

    public final void zzf(com.google.android.gms.measurement.internal.zzjq r4) {
        Preconditions.checkNotNull(r4);
        List r0 = this.zzf;
        monitor-enter(r0);
        int r1 = 0;
    L26:
    L12:
        th = move-exception;
        throw th;
    L6:
        if (r1 >= r0.size()) goto L15;
        if (r4.equals(((Pair) r0.get(r1)).first) == true) goto L9;
        r1 = r1 + 1;     // Catch: Throwable -> L12
        goto L26
    L9:
        Log.w(this.zzd, "OnEventListener already registered.");     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L15:
        zzeq r12 = new zzeq(r4);     // Catch: Throwable -> L12
        r0.add(new Pair(r4, r12));     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        if (this.zzj != null) goto L27;
    L22:
        zzV(new zzel(this, r12));
        return;
    L27:
        this.zzj.registerOnMeasurementEventListener(r12);     // Catch: Throwable -> L21
        return;
    L21:
        Log.w(this.zzd, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
        goto L22
    }

    public final void zzi(String r9, String r10, Bundle r11) {
        zzX(r9, r10, r11, true, true, null, null);
    }

    public final void zzk(String r7, String r8, Object r9, boolean r10) {
        zzV(new zzdf(this, r7, r8, r9, r10));
    }

    public final void zzl(Bundle r2) {
        zzV(new zzdg(this, r2));
    }

    public final void zzm(String r2, String r3, Bundle r4) {
        zzV(new zzdh(this, r2, r3, r4));
    }

    public final List zzn(String r3, String r4) {
        zzcm r0 = new zzcm();
        zzV(new zzdi(this, r3, r4, r0));
        List r2 = (List) zzcm.zzf(r0.zze(5000), List.class);
        if (r2 == null) goto L5;
        return r2;
    L5:
        return Collections.EMPTY_LIST;
    }

    public final void zzp(zzdd r2, String r3, String r4) {
        zzV(new zzdk(this, r2, r3, r4));
    }

    public final void zzq(Boolean r2) {
        zzV(new zzdl(this, r2));
    }

    public final void zzs() {
        zzV(new zzdn(this));
    }

    public final void zzu(String r2) {
        zzV(new zzdq(this, r2));
    }

    public final void zzv(String r2) {
        zzV(new zzdr(this, r2));
    }

    public final void zzw(Runnable r2) {
        zzV(new zzdt(this, r2));
    }

    public final String zzx() {
        zzcm r0 = new zzcm();
        zzV(new zzdu(this, r0));
        return r0.zzc(500);
    }

    public final String zzy() {
        zzcm r0 = new zzcm();
        zzV(new zzdv(this, r0));
        return r0.zzc(50);
    }

    public final long zzz() {
        zzcm r0 = new zzcm();
        zzV(new zzdw(this, r0));
        Long r02 = r0.zzd(500);
        if (r02 != null) goto L7;
        long r03 = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int r2 = this.zzg + 1;
        this.zzg = r2;
        return r03 + r2;
    L7:
        return r02.longValue();
    }
}
