package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzab {
    private zzaa zza;
    private zzaa zzb;
    private final List zzc;

    public zzab() {
        this.zza = new zzaa(_UrlKt.FRAGMENT_ENCODE_SET, 0, null);
        this.zzb = new zzaa(_UrlKt.FRAGMENT_ENCODE_SET, 0, null);
        this.zzc = new ArrayList();
    }

    public zzab(zzaa r1) {
        this.zza = r1;
        this.zzb = r1.zzg();
        this.zzc = new ArrayList();
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        zzab r0 = new zzab(this.zza.zzg());
        Iterator r3 = this.zzc.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        zzaa r1 = (zzaa) r3.next();
        r0.zzc.add(r1.zzg());
        goto L4
    L6:
        return r0;
    }

    public final zzaa zza() {
        return this.zza;
    }

    public final void zzb(zzaa r1) {
        this.zza = r1;
        this.zzb = r1.zzg();
        this.zzc.clear();
    }

    public final zzaa zzc() {
        return this.zzb;
    }

    public final void zzd(zzaa r1) {
        this.zzb = r1;
    }

    public final void zze(String r6, long r7, Map r9) {
        HashMap r0 = new HashMap();
        Iterator r1 = r9.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        String r2 = (String) r1.next();
        r0.put(r2, zzaa.zzh(r2, this.zza.zze(r2), r9.get(r2)));
        goto L4
    L6:
        this.zzc.add(new zzaa(r6, r7, r0));
    }

    public final List zzf() {
        return this.zzc;
    }
}
