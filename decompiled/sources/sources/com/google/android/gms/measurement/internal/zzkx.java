package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkx implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Uri zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzky zze;

    public zzkx(zzky r1, boolean r2, Uri r3, String r4, String r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzky r1 = this.zze;
        zzlj r2 = r1.zza;
        r2.zzg();
        String r3 = this.zzd;
        Uri r4 = this.zzb;
        zzic r5 = r2.zzu;     // Catch: RuntimeException -> L26
        zzpp r6 = r5.zzk();     // Catch: RuntimeException -> L26
        if (TextUtils.isEmpty(r3) == false) goto L8;
    L6:
        Bundle r62 = null;
    L31:
        String r7 = this.zzc;
        if (this.zza == false) goto L43;
        Bundle r0 = r5.zzk().zzi(r4);     // Catch: RuntimeException -> L26
        if (r0 == null) goto L43;
        r0.putString("_cis", "intent");     // Catch: RuntimeException -> L26
        if (r0.containsKey("gclid") == true) goto L41;
        if (r62 == null) goto L41;
        if (r62.containsKey("gclid") == false) goto L41;
        r0.putString("_cer", String.format("gclid=%s", new Object[]{r62.getString("gclid")}));     // Catch: RuntimeException -> L26
    L41:
        r2.zzE(r7, "_cmp", r0);     // Catch: RuntimeException -> L26
        r2.zzb.zzb(r7, r0);     // Catch: RuntimeException -> L26
    L43:
        if (TextUtils.isEmpty(r3) == true) goto L74;
        r5.zzaW().zzj().zzb("Activity created with referrer", r3);     // Catch: RuntimeException -> L26
        if (r5.zzc().zzp(null, zzfy.zzaG) == false) goto L54;
        if (r62 == null) goto L50;
        r2.zzE(r7, "_cmp", r62);     // Catch: RuntimeException -> L26
        r2.zzb.zzb(r7, r62);     // Catch: RuntimeException -> L26
    L51:
        r2.zzJ("auto", "_ldl", null, true);     // Catch: RuntimeException -> L26
        return;
    L50:
        r5.zzaW().zzj().zzb("Referrer does not contain valid parameters", r3);     // Catch: RuntimeException -> L26
        goto L51
    L54:
        if (r3.contains("gclid") == true) goto L56;
    L69:
        r5.zzaW().zzj().zza("Activity created with data 'referrer' without required params");     // Catch: RuntimeException -> L26
        return;
    L56:
        if (r3.contains("utm_campaign") == true) goto L66;
        if (r3.contains("utm_source") == true) goto L66;
        if (r3.contains("utm_medium") == true) goto L66;
        if (r3.contains("utm_term") == true) goto L66;
        if (r3.contains("utm_content") == false) goto L69;
    L66:
        if (TextUtils.isEmpty(r3) == true) goto L75;
        r2.zzJ("auto", "_ldl", r3, true);     // Catch: RuntimeException -> L26
        return;
    L75:
        return;
    L74:
        return;
    L8:
        if (r3.contains("gclid") == false) goto L10;
    L28:
        r62 = r6.zzi(Uri.parse("https://google.com/search?".concat(r3)));     // Catch: RuntimeException -> L26
        if (r62 == null) goto L31;
        r62.putString("_cis", "referrer");     // Catch: RuntimeException -> L26
        goto L31
    L10:
        if (r3.contains("gbraid") == true) goto L28;
        if (r3.contains("utm_campaign") == true) goto L28;
        if (r3.contains("utm_source") == true) goto L28;
        if (r3.contains("utm_medium") == true) goto L28;
        if (r3.contains("utm_id") == true) goto L28;
        if (r3.contains("dclid") == true) goto L28;
        if (r3.contains("srsltid") == true) goto L28;
        if (r3.contains("sfmc_id") == true) goto L28;
        r6.zzu.zzaW().zzj().zza("Activity created with data 'referrer' without required params");     // Catch: RuntimeException -> L26
    L26:
        e = move-exception;
        r1.zza.zzu.zzaW().zzb().zzb("Throwable caught in handleReferrerForOnActivityCreated", e);
    }
}
