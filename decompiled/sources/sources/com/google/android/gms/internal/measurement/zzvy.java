package com.google.android.gms.internal.measurement;

import android.os.Build;
import android.os.Trace;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayDeque;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public abstract class zzvy {
    static final zzrg zza = null;
    private static final ImmutableSet zzc = null;
    private static final AtomicReference zzd = null;
    private static final WeakHashMap zze = null;
    private static final zzvx zzf = null;

    static {
        zzc = ImmutableSet.of("androidx.fragment.app.FragmentViewLifecycleOwner.handleLifecycleEvent", "com.google.android.libraries.logging.logger.transmitters.clearcut", "com.google.android.libraries.performance.primes.transmitter.clearcut", "com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl", "com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl");
        zzd = new AtomicReference(ImmutableSet.of());
        zza = new zzrg("tiktok_systrace");
        zze = new WeakHashMap();
        zzf = new zzvx();
        new ArrayDeque();
        new ArrayDeque();
    }

    public static ImmutableSet zza() {
        return (ImmutableSet) zzd.get();
    }

    public static zzws zzb(boolean r2) {
        zzwq r22 = zzd();
        zzws r0 = r22.zzb;
        if (r0 == null) goto L9;
        if (r0 == zzwg.zza) goto L9;
        return r0;
    L9:
        return zzwd.zzi(r22);
    }

    public static zzws zzc(zzwq r3, zzws r4) {
        r3.getClass();
        zzws r0 = r3.zzb;
        if (r0 == r4) goto L17;
        if (r0 != null) goto L12;
        if (Build.VERSION.SDK_INT < 29) goto L9;
        boolean r1 = Trace.isEnabled();
    L10:
        r3.zza = r1;
        goto L12
    L9:
        r1 = zzrk.zza(zza);
    L12:
        if (r3.zza == false) goto L14;
        zzwr.zza(r0, r4);
    L14:
        if (r0 == r4) goto L17;
        r3.zzb = r4;
        return r0;
    L17:
        return r4;
    }

    public static zzwq zzd() {
        return (zzwq) zzf.get();
    }

    public static /* synthetic */ WeakHashMap zze() {
        return zze;
    }
}
