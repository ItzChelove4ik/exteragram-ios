package com.google.android.gms.ads.identifier;

import android.net.Uri;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zza extends Thread {
    final /* synthetic */ Map zza;

    public zza(AdvertisingIdClient r1, Map r2) {
        this.zza = r2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Map r4 = this.zza;
        Uri.Builder r0 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        Iterator r1 = r4.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        String r2 = (String) r1.next();
        r0.appendQueryParameter(r2, (String) r4.get(r2));
        goto L4
    L6:
        zzc.zza(r0.build().toString());
    }
}
