package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.measurement.internal.zzlk;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class FirebaseAnalytics {
    private static volatile FirebaseAnalytics zza;
    private final zzez zzb;

    public FirebaseAnalytics(zzez r1) {
        Preconditions.checkNotNull(r1);
        this.zzb = r1;
    }

    @Keep
    public static FirebaseAnalytics getInstance(Context r2) {
        if (zza != null) goto L16;
        monitor-enter(FirebaseAnalytics.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zza != null) goto L11;
        zza = new FirebaseAnalytics(zzez.zza(r2, null));     // Catch: Throwable -> L9
    L11:
        monitor-exit(FirebaseAnalytics.class);     // Catch: Throwable -> L9
    L16:
        return zza;
    }

    @Keep
    public static zzlk getScionFrontendApiImplementation(Context r0, Bundle r1) {
        zzez r02 = zzez.zza(r0, r1);
        if (r02 != null) goto L7;
        return null;
    L7:
        return new zzd(r02);
    }

    @Keep
    public String getFirebaseInstanceId() {
        return (String) Tasks.await(FirebaseInstallations.getInstance().getId(), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L10:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e.getCause());
        return null;
    L9:
        throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
    }

    public void resetAnalyticsData() {
        this.zzb.zzs();
    }

    public void setAnalyticsCollectionEnabled(boolean r1) {
        this.zzb.zzq(Boolean.valueOf(r1));
    }

    @Keep
    @Deprecated
    public void setCurrentScreen(Activity r1, String r2, String r3) {
        this.zzb.zzp(zzdd.zza(r1), r2, r3);
    }
}
