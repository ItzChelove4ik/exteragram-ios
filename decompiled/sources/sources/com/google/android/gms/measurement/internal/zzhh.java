package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes5.dex */
final class zzhh extends zzjf {
    static final Pair zza = null;
    public zzhf zzb;
    public final zzhe zzc;
    public final zzhe zzd;
    public final zzhg zze;
    public final zzhe zzf;
    public final zzhc zzg;
    public final zzhg zzh;
    public final zzhd zzi;
    public final zzhc zzj;
    public final zzhe zzk;
    public final zzhe zzl;
    public boolean zzm;
    public final zzhc zzn;
    public final zzhc zzo;
    public final zzhe zzp;
    public final zzhg zzq;
    public final zzhg zzr;
    public final zzhe zzs;
    public final zzhd zzt;
    private SharedPreferences zzv;
    private SharedPreferences zzw;
    private String zzx;
    private boolean zzy;
    private long zzz;

    static {
        zza = new Pair(_UrlKt.FRAGMENT_ENCODE_SET, 0L);
    }

    public zzhh(zzic r6) {
        super(r6);
        this.zzf = new zzhe(this, "session_timeout", 1800000);
        this.zzg = new zzhc(this, "start_new_session", true);
        this.zzk = new zzhe(this, "last_pause_time", 0);
        this.zzl = new zzhe(this, "session_id", 0);
        this.zzh = new zzhg(this, "non_personalized_ads", null);
        this.zzi = new zzhd(this, "last_received_uri_timestamps_by_source", null);
        this.zzj = new zzhc(this, "allow_remote_dynamite", false);
        this.zzc = new zzhe(this, "first_open_time", 0);
        this.zzd = new zzhe(this, "app_install_time", 0);
        this.zze = new zzhg(this, "app_instance_id", null);
        this.zzn = new zzhc(this, "app_backgrounded", false);
        this.zzo = new zzhc(this, "deep_link_retrieval_complete", false);
        this.zzp = new zzhe(this, "deep_link_retrieval_attempts", 0);
        this.zzq = new zzhg(this, "firebase_feature_rollouts", null);
        this.zzr = new zzhg(this, "deferred_attribution_cache", null);
        this.zzs = new zzhe(this, "deferred_attribution_cache_timestamp", 0);
        this.zzt = new zzhd(this, "default_event_parameters", null);
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final boolean zza() {
        return true;
    }

    public final Pair zzb(String r8) {
        zzg();
        if (zzl().zzo(zzjk.zza) == false) goto L23;
        zzic r0 = this.zzu;
        long r2 = r0.zzba().elapsedRealtime();
        String r4 = this.zzx;
        if (r4 != null) goto L7;
    L11:
        this.zzz = r2 + r0.zzc().zzl(r8, zzfy.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        AdvertisingIdClient.Info r82 = AdvertisingIdClient.getAdvertisingIdInfo(r0.zzaZ());     // Catch: Exception -> L15
        this.zzx = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L15
        String r02 = r82.getId();     // Catch: Exception -> L15
        if (r02 == null) goto L17;
        this.zzx = r02;     // Catch: Exception -> L15
    L17:
        this.zzy = r82.isLimitAdTrackingEnabled();     // Catch: Exception -> L15
    L20:
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzx, Boolean.valueOf(this.zzy));
    L15:
        e = move-exception;
        this.zzu.zzaW().zzj().zzb("Unable to get advertising id", e);
        this.zzx = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L7:
        if (r2 >= this.zzz) goto L11;
        return new Pair(r4, Boolean.valueOf(this.zzy));
    L23:
        return new Pair(_UrlKt.FRAGMENT_ENCODE_SET, Boolean.FALSE);
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    public final void zzbb() {
        zzic r0 = this.zzu;
        SharedPreferences r1 = r0.zzaZ().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzv = r1;
        boolean r12 = r1.getBoolean("has_been_opened", false);
        this.zzm = r12;
        if (r12 == true) goto L5;
        SharedPreferences.Editor r13 = this.zzv.edit();
        r13.putBoolean("has_been_opened", true);
        r13.apply();
    L5:
        r0.zzc();
        this.zzb = new zzhf(this, "health_monitor", Math.max(0, ((Long) zzfy.zzc.zzb(null)).longValue()), null);
    }

    public final SharedPreferences zzd() {
        zzg();
        zzw();
        Preconditions.checkNotNull(this.zzv);
        return this.zzv;
    }

    public final SharedPreferences zze() {
        zzg();
        zzw();
        if (this.zzw != null) goto L6;
        zzic r0 = this.zzu;
        String r1 = String.valueOf(r0.zzaZ().getPackageName());
        zzgs r2 = r0.zzaW().zzk();
        String r12 = r1.concat("_preferences");
        r2.zzb("Default prefs file", r12);
        this.zzw = r0.zzaZ().getSharedPreferences(r12, 0);
    L6:
        return this.zzw;
    }

    public final SparseArray zzf() {
        Bundle r0 = this.zzi.zza();
        int[] r1 = r0.getIntArray("uriSources");
        long[] r02 = r0.getLongArray("uriTimestamps");
        if (r1 == null) goto L16;
        if (r02 == null) goto L16;
        int r2 = r02.length;
        if (r1.length == r2) goto L10;
        this.zzu.zzaW().zzb().zza("Trigger URI source and timestamp array lengths do not match");
        return new SparseArray();
    L10:
        SparseArray r6 = new SparseArray();
        int r22 = 0;
    L12:
        if (r22 >= r1.length) goto L14;
        r6.put(r1[r22], Long.valueOf(r02[r22]));
        r22 = r22 + 1;
        goto L12
    L14:
        return r6;
    L16:
        return new SparseArray();
    }

    public final void zzh(Boolean r2) {
        zzg();
        SharedPreferences.Editor r1 = zzd().edit();
        if (r2 == null) goto L5;
        r1.putBoolean("measurement_enabled", r2.booleanValue());
    L6:
        r1.apply();
        return;
    L5:
        r1.remove("measurement_enabled");
        goto L6
    }

    public final Boolean zzi() {
        zzg();
        if (zzd().contains("measurement_enabled") == true) goto L5;
        return null;
    L5:
        return Boolean.valueOf(zzd().getBoolean("measurement_enabled", true));
    }

    public final zzba zzj() {
        zzg();
        return zzba.zzg(zzd().getString("dma_consent_settings", null));
    }

    public final boolean zzk(int r3) {
        return zzjl.zzu(r3, zzd().getInt("consent_source", 100));
    }

    public final zzjl zzl() {
        zzg();
        return zzjl.zzf(zzd().getString("consent_settings", "G1"), zzd().getInt("consent_source", 100));
    }

    public final void zzm(boolean r4) {
        zzg();
        this.zzu.zzaW().zzk().zzb("App measurement setting deferred collection", Boolean.valueOf(r4));
        SharedPreferences.Editor r3 = zzd().edit();
        r3.putBoolean("deferred_analytics_collection", r4);
        r3.apply();
    }

    public final boolean zzn() {
        SharedPreferences r1 = this.zzv;
        if (r1 != null) goto L7;
        return false;
    L7:
        return r1.contains("deferred_analytics_collection");
    }

    public final boolean zzo(long r3) {
        if ((r3 - this.zzf.zza()) <= this.zzk.zza()) goto L6;
        return true;
    L6:
        return false;
    }
}
