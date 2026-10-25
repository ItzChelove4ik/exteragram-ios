package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes5.dex */
public final class zzal extends zzje {
    private Boolean zza;
    private String zzb;
    private zzak zzc;
    private Boolean zzd;

    public zzal(zzic r1) {
        super(r1);
        this.zzc = zzaj.zza;
    }

    public static final long zzF() {
        return ((Long) zzfy.zzd.zzb(null)).longValue();
    }

    public static final int zzG() {
        return Math.max(0, ((Integer) zzfy.zzi.zzb(null)).intValue());
    }

    public static final long zzH() {
        return ((Integer) zzfy.zzk.zzb(null)).intValue();
    }

    public static final long zzI() {
        return ((Long) zzfy.zzR.zzb(null)).longValue();
    }

    public static final long zzJ() {
        return ((Long) zzfy.zzM.zzb(null)).longValue();
    }

    private final String zzK(String r4, String r5) {
        String r42 = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{r4, _UrlKt.FRAGMENT_ENCODE_SET});     // Catch: InvocationTargetException -> L5 IllegalAccessException -> L7 NoSuchMethodException -> L9 ClassNotFoundException -> L11
        Preconditions.checkNotNull(r42);     // Catch: InvocationTargetException -> L5 IllegalAccessException -> L7 NoSuchMethodException -> L9 ClassNotFoundException -> L11
        return r42;
    L11:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Could not find SystemProperties class", e);
    L17:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Could not access SystemProperties.get()", e);
    L9:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Could not find SystemProperties.get() method", e);
    L5:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("SystemProperties.get() threw an exception", e);
        goto L17
    }

    public final String zzA() {
        return zzK("debug.firebase.analytics.app", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public final String zzB() {
        return zzK("debug.deferred.deeplink", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public final boolean zzC(String r2) {
        if (this.zzu.zzk().zzaa(r2) == false) goto L7;
        return true;
    L7:
        return "1".equals(this.zzc.zza(r2, "gaia_collection_enabled"));
    }

    public final boolean zzD(String r2) {
        return "1".equals(this.zzc.zza(r2, "measurement.event_sampling_enabled"));
    }

    public final boolean zzE() {
        if (this.zza != null) goto L8;
        Boolean r0 = zzr("app_measurement_lite");
        this.zza = r0;
        if (r0 != null) goto L8;
        this.zza = Boolean.FALSE;
    L8:
        if (this.zza.booleanValue() == false) goto L10;
        return true;
    L10:
        if (this.zzu.zzp() == false) goto L16;
        return false;
    L16:
        return true;
    }

    public final void zza(zzak r1) {
        this.zzc = r1;
    }

    public final String zzb() {
        this.zzu.zzaV();
        return "FA";
    }

    public final int zzc() {
        if (this.zzu.zzk().zzaj(201500000, true) == false) goto L6;
        return 100;
    L6:
        return 25;
    }

    public final int zzd(String r4) {
        return zzn(r4, zzfy.zzX, 25, 100);
    }

    public final int zze(String r3, boolean r4) {
        if (r4 == true) goto L5;
        return MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    L5:
        return zzn(r3, zzfy.zzag, 100, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
    }

    public final int zzf(String r1, boolean r2) {
        return Math.max(zze(r1, r2), 256);
    }

    public final int zzh(String r4) {
        return zzn(r4, zzfy.zzW, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, CastStatusCodes.AUTHENTICATION_FAILED);
    }

    public final long zzi() {
        this.zzu.zzaV();
        return 161000;
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzj() {
        if (this.zzd != null) goto L25;
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L6:
        if (this.zzd != null) goto L20;
        zzic r0 = this.zzu;     // Catch: Throwable -> L14
        ApplicationInfo r1 = r0.zzaZ().getApplicationInfo();     // Catch: Throwable -> L14
        String r2 = ProcessUtils.getMyProcessName();     // Catch: Throwable -> L14
        if (r1 == null) goto L18;
        String r12 = r1.processName;     // Catch: Throwable -> L14
        boolean r3 = false;
        if (r12 != null) goto L12;
    L16:
        this.zzd = Boolean.valueOf(r3);     // Catch: Throwable -> L14
        goto L18
    L12:
        if (r12.equals(r2) == false) goto L16;
        r3 = true;
    L18:
        if (this.zzd != null) goto L20;
        this.zzd = Boolean.TRUE;     // Catch: Throwable -> L14
        r0.zzaW().zzb().zza("My process not in the list of running processes");     // Catch: Throwable -> L14
    L20:
        monitor-exit(this);     // Catch: Throwable -> L14
    L25:
        return this.zzd.booleanValue();
    }

    public final String zzk(String r2, zzfx r3) {
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return (String) r3.zzb(null);
    L7:
        return (String) r3.zzb(this.zzc.zza(r2, r3.zza()));
    }

    public final long zzl(String r3, zzfx r4) {
        if (TextUtils.isEmpty(r3) == true) goto L5;
        String r2 = this.zzc.zza(r3, r4.zza());
        if (TextUtils.isEmpty(r2) == true) goto L9;
        return ((Long) r4.zzb(Long.valueOf(Long.parseLong(r2)))).longValue();
    L13:
        return ((Long) r4.zzb(null)).longValue();
    L9:
        return ((Long) r4.zzb(null)).longValue();
    L5:
        return ((Long) r4.zzb(null)).longValue();
    }

    public final int zzm(String r3, zzfx r4) {
        if (TextUtils.isEmpty(r3) == true) goto L5;
        String r2 = this.zzc.zza(r3, r4.zza());
        if (TextUtils.isEmpty(r2) == true) goto L9;
        return ((Integer) r4.zzb(Integer.valueOf(Integer.parseInt(r2)))).intValue();
    L13:
        return ((Integer) r4.zzb(null)).intValue();
    L9:
        return ((Integer) r4.zzb(null)).intValue();
    L5:
        return ((Integer) r4.zzb(null)).intValue();
    }

    public final int zzn(String r1, zzfx r2, int r3, int r4) {
        return Math.max(Math.min(zzm(r1, r2), r4), r3);
    }

    public final double zzo(String r3, zzfx r4) {
        if (TextUtils.isEmpty(r3) == true) goto L5;
        String r2 = this.zzc.zza(r3, r4.zza());
        if (TextUtils.isEmpty(r2) == true) goto L9;
        return ((Double) r4.zzb(Double.valueOf(Double.parseDouble(r2)))).doubleValue();
    L13:
        return ((Double) r4.zzb(null)).doubleValue();
    L9:
        return ((Double) r4.zzb(null)).doubleValue();
    L5:
        return ((Double) r4.zzb(null)).doubleValue();
    }

    public final boolean zzp(String r3, zzfx r4) {
        if (TextUtils.isEmpty(r3) == true) goto L5;
        String r2 = this.zzc.zza(r3, r4.zza());
        if (TextUtils.isEmpty(r2) == false) goto L11;
        return ((Boolean) r4.zzb(null)).booleanValue();
    L11:
        return ((Boolean) r4.zzb(Boolean.valueOf("1".equals(r2)))).booleanValue();
    L5:
        return ((Boolean) r4.zzb(null)).booleanValue();
    }

    public final Bundle zzq() {
        zzic r1 = this.zzu;     // Catch: PackageManager.NameNotFoundException -> L7
        if (r1.zzaZ().getPackageManager() != null) goto L9;
        r1.zzaW().zzb().zza("Failed to load metadata: PackageManager is null");     // Catch: PackageManager.NameNotFoundException -> L7
        return null;
    L9:
        ApplicationInfo r2 = Wrappers.packageManager(r1.zzaZ()).getApplicationInfo(r1.zzaZ().getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L7
        if (r2 != null) goto L14;
        r1.zzaW().zzb().zza("Failed to load metadata: ApplicationInfo is null");     // Catch: PackageManager.NameNotFoundException -> L7
        return null;
    L14:
        return r2.metaData;
    L7:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to load metadata: Package name not found", e);
        return null;
    }

    public final Boolean zzr(String r3) {
        Preconditions.checkNotEmpty(r3);
        Bundle r0 = zzq();
        if (r0 != null) goto L7;
        this.zzu.zzaW().zzb().zza("Failed to load metadata: Metadata bundle is null");
        return null;
    L7:
        if (r0.containsKey(r3) == true) goto L10;
        return null;
    L10:
        return Boolean.valueOf(r0.getBoolean(r3));
    }

    public final List zzs(String r4) {
        Preconditions.checkNotEmpty("analytics.safelisted_events");
        Bundle r0 = zzq();
        if (r0 != null) goto L7;
        this.zzu.zzaW().zzb().zza("Failed to load metadata: Metadata bundle is null");
    L5:
        Integer r42 = null;
    L10:
        if (r42 != null) goto L19;
    L18:
        return null;
    L19:
        String[] r43 = this.zzu.zzaZ().getResources().getStringArray(r42.intValue());     // Catch: Resources.NotFoundException -> L16
        if (r43 != null) goto L14;
        return null;
    L14:
        return Arrays.asList(r43);
    L16:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to load string array from metadata: resource not found", e);
        goto L18
    L7:
        if (r0.containsKey("analytics.safelisted_events") == false) goto L5;
        r42 = Integer.valueOf(r0.getInt("analytics.safelisted_events"));
        goto L10
    }

    public final boolean zzt() {
        this.zzu.zzaV();
        Boolean r1 = zzr("firebase_analytics_collection_deactivated");
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzu() {
        Boolean r1 = zzr("google_analytics_adid_collection_enabled");
        if (r1 != null) goto L5;
        return true;
    L5:
        if (r1.booleanValue() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final boolean zzv() {
        Boolean r1 = zzr("google_analytics_automatic_screen_reporting_enabled");
        if (r1 != null) goto L5;
        return true;
    L5:
        if (r1.booleanValue() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final zzji zzw(String r3, boolean r4) {
        Preconditions.checkNotEmpty(r3);
        zzic r0 = this.zzu;
        Bundle r2 = zzq();
        if (r2 != null) goto L5;
        r0.zzaW().zzb().zza("Failed to load metadata: Metadata bundle is null");
        Object r22 = null;
    L6:
        if (r22 != null) goto L10;
        return zzji.zza;
    L10:
        if (Boolean.TRUE.equals(r22) == false) goto L14;
        return zzji.zzd;
    L14:
        if (Boolean.FALSE.equals(r22) == true) goto L16;
        if (r4 == true) goto L19;
    L22:
        r0.zzaW().zze().zzb("Invalid manifest metadata for", r3);
        return zzji.zza;
    L19:
        if ("eu_consent_policy".equals(r22) == false) goto L22;
        return zzji.zzb;
    L16:
        return zzji.zzc;
    L5:
        r22 = r2.get(r3);
        goto L6
    }

    public final boolean zzx() {
        Boolean r1 = zzr("google_analytics_sgtm_upload_enabled");
        if (r1 != null) goto L7;
        return false;
    L7:
        return r1.booleanValue();
    }

    public final void zzy(String r1) {
        this.zzb = r1;
    }

    public final String zzz() {
        return this.zzb;
    }
}
