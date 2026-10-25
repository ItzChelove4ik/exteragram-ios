package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class zzd extends zzq {
    private final List zzb;

    public zzd(String r1, String r2, String r3) {
        super(r1, "MediaControlChannel", null);
        this.zzb = Collections.synchronizedList(new ArrayList());
    }

    public final void zza() {
        List r3 = this.zzb;
        monitor-enter(r3);
        Iterator r0 = r3.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((zzav) r0.next()).zze(CastStatusCodes.CANCELED);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r3);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final List zzb() {
        return this.zzb;
    }

    public final void zzc(zzav r1) {
        this.zzb.add(r1);
    }
}
