package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public final class zzgz extends zzos {
    public zzgz(zzpg r1) {
        super(r1);
    }

    public final boolean zzb() {
        zzay();
        ConnectivityManager r1 = (ConnectivityManager) this.zzu.zzaZ().getSystemService("connectivity");
        NetworkInfo r0 = null;
        if (r1 != null) goto L13;
    L5:
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isConnected() == false) goto L15;
        return true;
    L15:
        return false;
    L13:
        r0 = r1.getActiveNetworkInfo();     // Catch: SecurityException -> L12
        goto L5
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        return false;
    }

    public final void zzc(String r10, zzot r11, com.google.android.gms.internal.measurement.zzib r12, zzgw r13) {
        zzg();
        zzay();
        URL r5 = new URI(r11.zza()).toURL();     // Catch: Throwable -> L7
        this.zzg.zzp();     // Catch: Throwable -> L7
        byte[] r6 = r12.zzcd();     // Catch: Throwable -> L7
        zzhz r122 = this.zzu.zzaX();     // Catch: Throwable -> L7
        zzgz r3 = this;
        String r4 = r10;
        r122.zzm(new zzgy(r3, r4, r5, r6, r11.zzb(), r13));     // Catch: Throwable -> L10
        return;
    L8:
        r3.zzu.zzaW().zzb().zzc("Failed to parse URL. Not uploading MeasurementBatch. appId", zzgu.zzl(r4), r11.zza());
        return;
    L7:
        r3 = this;
        r4 = r10;
        goto L8
    }

    public final void zzd(zzh r10, Map r11, zzgw r12) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r10);
        Preconditions.checkNotNull(r12);
        zzou r0 = this.zzg.zzf();
        Uri.Builder r1 = new Uri.Builder();
        String r2 = r10.zzf();
        Uri.Builder r22 = r1.scheme((String) zzfy.zze.zzb(null)).encodedAuthority((String) zzfy.zzf.zzb(null)).path("config/app/".concat(String.valueOf(r2))).appendQueryParameter("platform", "android");
        r0.zzu.zzc().zzi();
        r22.appendQueryParameter("gmp_version", String.valueOf(161000)).appendQueryParameter("runtime_version", MVEL.VERSION_SUB);
        String r02 = r1.build().toString();
        URL r5 = new URI(r02).toURL();     // Catch: Throwable -> L7
        zzhz r13 = this.zzu.zzaX();     // Catch: Throwable -> L7
        zzgz r3 = this;
        r13.zzm(new zzgy(r3, r10.zzc(), r5, null, r11, r12));     // Catch: Throwable -> L10
        return;
    L8:
        r3.zzu.zzaW().zzb().zzc("Failed to parse config URL. Not fetching. appId", zzgu.zzl(r10.zzc()), r02);
        return;
    L7:
        r3 = this;
        goto L8
    }
}
