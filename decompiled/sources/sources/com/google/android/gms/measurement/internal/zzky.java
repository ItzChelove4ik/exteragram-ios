package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzky implements Application.ActivityLifecycleCallbacks, zzkw {
    final /* synthetic */ zzlj zza;

    public zzky(zzlj r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity r1, Bundle r2) {
        zza(com.google.android.gms.internal.measurement.zzdd.zza(r1), r2);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity r1) {
        zzb(com.google.android.gms.internal.measurement.zzdd.zza(r1));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity r1) {
        zzc(com.google.android.gms.internal.measurement.zzdd.zza(r1));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity r1) {
        zzd(com.google.android.gms.internal.measurement.zzdd.zza(r1));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity r1, Bundle r2) {
        zze(com.google.android.gms.internal.measurement.zzdd.zza(r1), r2);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity r1) {
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zza(com.google.android.gms.internal.measurement.zzdd r8, Bundle r9) {
        zzlj r0 = this.zza;     // Catch: Throwable -> L10 RuntimeException -> L12
        zzic r1 = r0.zzu;     // Catch: Throwable -> L10 RuntimeException -> L12
        r1.zzaW().zzk().zza("onActivityCreated");     // Catch: Throwable -> L10 RuntimeException -> L12
        Intent r2 = r8.zzc;     // Catch: Throwable -> L10 RuntimeException -> L12
        if (r2 == null) goto L41;
        Uri r3 = r2.getData();     // Catch: Throwable -> L10 RuntimeException -> L12
        if (r3 != null) goto L7;
    L14:
        Bundle r32 = r2.getExtras();     // Catch: Throwable -> L10 RuntimeException -> L12
        Uri r4 = null;
        if (r32 == null) goto L19;
        String r33 = r32.getString("com.android.vending.referral_url");     // Catch: Throwable -> L10 RuntimeException -> L12
        if (TextUtils.isEmpty(r33) == true) goto L19;
        r3 = Uri.parse(r33);     // Catch: Throwable -> L10 RuntimeException -> L12
    L9:
        r4 = r3;
    L19:
        if (r4 == null) goto L41;
        if (r4.isHierarchical() == false) goto L41;
        if (r1.zzk().zzj(r2) == false) goto L27;
        String r02 = "gs";
    L26:
        String r5 = r02;
        String r6 = r4.getQueryParameter("referrer");     // Catch: Throwable -> L10 RuntimeException -> L12
        if (r9 != null) goto L32;
        boolean r03 = true;
    L31:
        boolean r34 = r03;
        zzhz r04 = r1.zzaX();     // Catch: Throwable -> L10 RuntimeException -> L12
        zzky r22 = this;
        r04.zzj(new zzkx(r22, r34, r4, r5, r6));     // Catch: Throwable -> L37 RuntimeException -> L39
    L45:
        zzic r7 = r22.zza.zzu;
    L42:
        r7.zzs().zzn(r8, r9);
        return;
    L39:
        e = e;
    L44:
        r22.zza.zzu.zzaW().zzb().zzb("Throwable caught in onActivityCreated", e);     // Catch: Throwable -> L37
        goto L45
    L32:
        r03 = false;
        goto L31
    L27:
        r02 = "auto";
        goto L26
    L7:
        if (r3.isHierarchical() == true) goto L9;
    L41:
        r7 = r0.zzu;
    L37:
        th = th;
    L46:
        r22.zza.zzu.zzs().zzn(r8, r9);
        throw th;
    L12:
        e = e;
        r22 = this;
    L10:
        th = th;
        r22 = this;
        goto L46
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzb(com.google.android.gms.internal.measurement.zzdd r1) {
        this.zza.zzu.zzs().zzt(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzc(com.google.android.gms.internal.measurement.zzdd r4) {
        zzic r3 = this.zza.zzu;
        r3.zzs().zzq(r4);
        zzoc r32 = r3.zzh();
        zzic r42 = r32.zzu;
        long r0 = r42.zzba().elapsedRealtime();
        r42.zzaX().zzj(new zznv(r32, r0));
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzd(com.google.android.gms.internal.measurement.zzdd r6) {
        zzic r5 = this.zza.zzu;
        zzoc r0 = r5.zzh();
        zzic r1 = r0.zzu;
        long r2 = r1.zzba().elapsedRealtime();
        r1.zzaX().zzj(new zznu(r0, r2));
        r5.zzs().zzp(r6);
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zze(com.google.android.gms.internal.measurement.zzdd r1, Bundle r2) {
        this.zza.zzu.zzs().zzs(r1, r2);
    }
}
