package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import com.google.android.gms.cast.internal.Logger;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzo {
    public static long zza;
    private static final Logger zzq = null;
    public String zzb;
    public String zzc;
    public long zzd;
    public int zze;
    public String zzf;
    public int zzg;
    public String zzh;
    public String zzi;
    public String zzj;
    public String zzk;
    public String zzl;
    public String zzm;
    public int zzn;
    public boolean zzo;
    public int zzp;
    private final zzax zzr;

    static {
        zzq = new Logger("ApplicationAnalyticsSession");
        zza = System.currentTimeMillis();
    }

    private zzo(zzax r3) {
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzl = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzn = 0;
        this.zzd = zza;
        this.zze = 1;
        this.zzr = r3;
    }

    public static zzo zza(zzax r5) {
        zzo r0 = new zzo(r5);
        zza++;
        return r0;
    }

    public static zzo zzc(SharedPreferences r5, zzax r6) {
        if (r5 == null) goto L22;
        zzo r0 = new zzo(r6);
        r0.zzo = r5.getBoolean("is_output_switcher_enabled", false);
        if (r5.contains("application_id") == false) goto L16;
        r0.zzb = r5.getString("application_id", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r5.contains("receiver_metrics_id") == false) goto L18;
        r0.zzc = r5.getString("receiver_metrics_id", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r5.contains("analytics_session_id") == false) goto L19;
        r0.zzd = r5.getLong("analytics_session_id", 0);
        if (r5.contains("event_sequence_number") == false) goto L20;
        r0.zze = r5.getInt("event_sequence_number", 0);
        if (r5.contains("receiver_session_id") == false) goto L21;
        r0.zzf = r5.getString("receiver_session_id", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzg = r5.getInt("device_capabilities", 0);
        r0.zzh = r5.getString("device_model_name", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzi = r5.getString("manufacturer", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzj = r5.getString("product_name", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzk = r5.getString("build_type", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzl = r5.getString("cast_build_version", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzm = r5.getString("system_build_number", _UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzn = r5.getInt("device_category", 0);
        r0.zzp = r5.getInt("analytics_session_start_type", 0);
        return r0;
    L21:
        return null;
    L20:
        return null;
    L19:
        return null;
    L18:
        return null;
    L16:
        return null;
    L22:
        return null;
    }

    public final boolean zzb() {
        return this.zzr.zze();
    }

    public final void zzd(SharedPreferences r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        zzq.d("Save the ApplicationAnalyticsSession to SharedPreferences %s", new Object[]{r4});
        SharedPreferences.Editor r42 = r4.edit();
        r42.putString("application_id", this.zzb);
        r42.putString("receiver_metrics_id", this.zzc);
        r42.putLong("analytics_session_id", this.zzd);
        r42.putInt("event_sequence_number", this.zze);
        r42.putString("receiver_session_id", this.zzf);
        r42.putInt("device_capabilities", this.zzg);
        r42.putString("device_model_name", this.zzh);
        r42.putString("manufacturer", this.zzi);
        r42.putString("product_name", this.zzj);
        r42.putString("build_type", this.zzk);
        r42.putString("cast_build_version", this.zzl);
        r42.putString("system_build_number", this.zzm);
        r42.putInt("device_category", this.zzn);
        r42.putInt("analytics_session_start_type", this.zzp);
        r42.putBoolean("is_output_switcher_enabled", this.zzo);
        r42.apply();
    }
}
