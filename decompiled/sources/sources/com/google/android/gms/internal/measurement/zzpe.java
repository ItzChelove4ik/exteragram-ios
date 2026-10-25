package com.google.android.gms.internal.measurement;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzpe {
    private final ConcurrentMap zza;

    public /* synthetic */ zzpe(byte[] r1) {
        this.zza = new ConcurrentHashMap();
    }

    private final void zzd(zzlk r2, zzon r3) {
        zzql.zza(r2.zzc(), new zzoz(this), new zzpa(this));
    }

    public final /* synthetic */ void zza(String r1) {
        zzoo r0 = (zzoo) this.zza.get(r1);
        if (r0 == null) goto L6;
        r0.zzc(zzpc.zza);
        return;
    }

    public final /* synthetic */ boolean zzb(Collection r4) {
        boolean r0 = false;
        if (r4 != null) goto L5;
    L13:
        return r0;
    L5:
        if (r4.isEmpty() == false) goto L7;
        return false;
    L7:
        Iterator r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L13;
        String r1 = (String) r42.next();
        zzoo r12 = (zzoo) this.zza.get(r1);
        if (r12 == null) goto L9;
        r0 = r0 | r12.zzb();
        goto L9
    }

    public final zzoo zzc(zzlk r5, zzon r6, String r7) {
        zzpd r72 = new zzpd(null);
        zzoo r0 = (zzoo) this.zza.computeIfAbsent(r6.zza(r5.zzc()), new zzpb(r5, r6, _UrlKt.FRAGMENT_ENCODE_SET, r72));
        if (r72.zza() == false) goto L5;
        zzd(r5, r6);
    L5:
        return r0;
    }
}
