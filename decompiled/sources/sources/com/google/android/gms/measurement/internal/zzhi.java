package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzhi implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzbs zza;
    final /* synthetic */ ServiceConnection zzb;
    final /* synthetic */ zzhj zzc;

    public zzhi(zzhj r1, com.google.android.gms.internal.measurement.zzbs r2, ServiceConnection r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhj r0 = this.zzc;
        zzhk r1 = r0.zza;
        zzic r2 = r1.zza;
        r2.zzaX().zzg();
        Bundle r3 = new Bundle();
        String r02 = r0.zza();
        r3.putString("package_name", r02);
        Bundle r32 = this.zza.zze(r3);     // Catch: Exception -> L7
        if (r32 != null) goto L9;
        r2.zzaW().zzb().zza("Install Referrer Service returned a null response");     // Catch: Exception -> L7
    L6:
        r32 = null;
    L9:
        zzic r12 = r1.zza;
        r12.zzaX().zzg();
        zzic.zzL();
        if (r32 == null) goto L38;
        long r8 = r32.getLong("install_begin_timestamp_seconds", 0) * 1000;
        if (r8 != 0) goto L15;
        r12.zzaW().zze().zza("Service response is missing Install Referrer install timestamp");
        goto L38
    L15:
        String r22 = r32.getString("install_referrer");
        if (r22 != null) goto L18;
    L37:
        r12.zzaW().zzb().zza("No referrer defined in Install Referrer response");
        goto L38
    L18:
        if (r22.isEmpty() == true) goto L37;
        r12.zzaW().zzk().zzb("InstallReferrer API result", r22);
        Bundle r23 = r12.zzk().zzi(Uri.parse("?".concat(r22)));
        if (r23 != null) goto L23;
        r12.zzaW().zzb().zza("No campaign params defined in Install Referrer result");
        goto L38
    L23:
        List r4 = Arrays.asList(((String) zzfy.zzbb.zzb(null)).split(","));
        Iterator<String> r5 = r23.keySet().iterator();
    L25:
        if (r5.hasNext() == false) goto L32;
        if (r4.contains(r5.next()) == false) goto L25;
        long r33 = r32.getLong("referrer_click_timestamp_server_seconds", 0) * 1000;
        if (r33 <= 0) goto L32;
        r23.putLong("click_timestamp", r33);
    L32:
        if (r8 != r12.zzd().zzd.zza()) goto L35;
        r12.zzaW().zzk().zza("Logging Install Referrer campaign from module while it may have already been logged.");
    L35:
        if (r12.zzB() == false) goto L38;
        r12.zzd().zzd.zzb(r8);
        r12.zzaW().zzk().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
        r23.putString("_cis", "referrer API v2");
        r12.zzj().zzH("auto", "_cmp", r23, r02);
    L38:
        ConnectionTracker.getInstance().unbindService(r12.zzaZ(), this.zzb);
        return;
    L7:
        e = move-exception;
        r1.zza.zzaW().zzb().zzb("Exception occurred while retrieving the Install Referrer", e.getMessage());
        goto L6
    }
}
