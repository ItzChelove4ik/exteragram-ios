package com.google.android.gms.measurement.internal;

import android.content.pm.PackageManager;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zznn extends zzos {
    public final zzhe zza;
    public final zzhe zzb;
    public final zzhe zzc;
    public final zzhe zzd;
    public final zzhe zze;
    public final zzhe zzf;
    private final Map zzh;

    public zznn(zzpg r5) {
        super(r5);
        this.zzh = new HashMap();
        zzhh r0 = this.zzu.zzd();
        Objects.requireNonNull(r0);
        this.zza = new zzhe(r0, "last_delete_stale", 0);
        zzhh r02 = this.zzu.zzd();
        Objects.requireNonNull(r02);
        this.zzb = new zzhe(r02, "last_delete_stale_batch", 0);
        zzhh r03 = this.zzu.zzd();
        Objects.requireNonNull(r03);
        this.zzc = new zzhe(r03, "backoff", 0);
        zzhh r04 = this.zzu.zzd();
        Objects.requireNonNull(r04);
        this.zzd = new zzhe(r04, "last_upload", 0);
        zzhh r05 = this.zzu.zzd();
        Objects.requireNonNull(r05);
        this.zze = new zzhe(r05, "last_upload_attempt", 0);
        zzhh r06 = this.zzu.zzd();
        Objects.requireNonNull(r06);
        this.zzf = new zzhe(r06, "midnight_offset", 0);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        return false;
    }

    public final Pair zzc(zzr r3, zzjl r4) {
        String r0 = r3.zza;
        Preconditions.checkNotEmpty(r0);
        if (r4.zzo(zzjk.zza) == false) goto L10;
        if (r3.zzn == false) goto L10;
        return zzd(r0);
    L10:
        return new Pair(_UrlKt.FRAGMENT_ENCODE_SET, Boolean.FALSE);
    }

    @Deprecated
    public final Pair zzd(String r13) {
        zzg();
        zzic r1 = this.zzu;
        long r2 = r1.zzba().elapsedRealtime();
        zznm r4 = (zznm) this.zzh.get(r13);
        if (r4 != null) goto L5;
    L9:
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long r5 = r1.zzc().zzl(r13, zzfy.zza) + r2;
        AdvertisingIdClient.Info r12 = AdvertisingIdClient.getAdvertisingIdInfo(r1.zzaZ());     // Catch: Exception -> L12 PackageManager.NameNotFoundException -> L14
    L20:
        if (r12 == null) goto L22;
        String r22 = r12.getId();     // Catch: Exception -> L12
        if (r22 == null) goto L26;
        zznm r3 = new zznm(r22, r12.isLimitAdTrackingEnabled(), r5);     // Catch: Exception -> L12
    L29:
        this.zzh.put(r13, r3);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(r3.zza, Boolean.valueOf(r3.zzb));
    L26:
        r3 = new zznm(_UrlKt.FRAGMENT_ENCODE_SET, r12.isLimitAdTrackingEnabled(), r5);     // Catch: Exception -> L12
        goto L29
    L22:
        return new Pair("00000000-0000-0000-0000-000000000000", Boolean.FALSE);
    L14:
        r12 = null;
        if (r4 == null) goto L20;
        if (r2 >= (r4.zzc + this.zzu.zzc().zzl(r13, zzfy.zzb))) goto L20;
        return new Pair(r4.zza, Boolean.valueOf(r4.zzb));
    L12:
        e = move-exception;
        this.zzu.zzaW().zzj().zzb("Unable to get advertising id", e);
        r3 = new zznm(_UrlKt.FRAGMENT_ENCODE_SET, false, r5);
        goto L29
    L5:
        if (r2 >= r4.zzc) goto L9;
        return new Pair(r4.zza, Boolean.valueOf(r4.zzb));
    }

    public final String zzf(zzr r3, zzjl r4) {
        String r0 = r3.zza;
        Preconditions.checkNotEmpty(r0);
        if (r4.zzo(zzjk.zza) == true) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r3.zzn == false) goto L15;
        zzg();
        String r2 = (String) zzd(r0).first;
        MessageDigest r32 = zzpp.zzQ();
        if (r32 != null) goto L12;
        return null;
    L12:
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r32.digest(r2.getBytes()))});
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
