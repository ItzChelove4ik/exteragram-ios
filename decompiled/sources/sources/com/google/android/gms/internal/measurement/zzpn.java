package com.google.android.gms.internal.measurement;

import android.util.Log;
import java.util.Map;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpn implements Runnable {
    private final /* synthetic */ zzlk zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzpn(zzlk r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Map r0 = zzpp.zza(this.zza.zzc());
        String r2 = this.zzb;
        if (r0.containsKey(r2) == true) goto L6;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 173);
        r1.append("Config package ");
        r1.append(r2);
        r1.append(" cannot use FILE backing without declarative registration. See go/phenotype-android-integration#phenotype for more information. This will lead to stale flags.");
        Log.e("FilePhenotypeFlags", r1.toString());
        return;
    }
}
