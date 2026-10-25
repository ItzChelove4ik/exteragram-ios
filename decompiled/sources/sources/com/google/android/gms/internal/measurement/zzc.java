package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final class zzc {
    final zzf zza;
    zzg zzb;
    final zzab zzc;
    private final zzz zzd;

    public zzc() {
        zzf r0 = new zzf();
        this.zza = r0;
        this.zzb = r0.zzb.zzc();
        this.zzc = new zzab();
        this.zzd = new zzz();
        zzb r1 = new zzb(this);
        r0.zzd.zza("internal.registerCallback", r1);
        zza r12 = new zza(this);
        r0.zzd.zza("internal.eventLogger", r12);
    }

    public final void zza(String r1, Callable r2) {
        this.zza.zzd.zza(r1, r2);
    }

    public final boolean zzb(zzaa r6) {
        zzab r0 = this.zzc;     // Catch: Throwable -> L11
        r0.zzb(r6);     // Catch: Throwable -> L11
        this.zza.zzc.zze("runtime.counter", new zzah(Double.valueOf(0.0d)));     // Catch: Throwable -> L11
        this.zzd.zzb(this.zzb.zzc(), r0);     // Catch: Throwable -> L11
        if (zzc() == true) goto L9;
        if (zzd() == true) goto L16;
        return false;
    L16:
        return true;
    L9:
        return true;
    L11:
        th = move-exception;
        throw new zzd(th);
    }

    public final boolean zzc() {
        zzab r1 = this.zzc;
        if (r1.zzc().equals(r1.zza()) == true) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzd() {
        if (this.zzc.zzf().isEmpty() == true) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzab zze() {
        return this.zzc;
    }

    public final void zzf(zzja r9) {
        zzf r0 = this.zza;     // Catch: Throwable -> L29
        this.zzb = r0.zzb.zzc();     // Catch: Throwable -> L29
        List r1 = r9.zza();     // Catch: Throwable -> L29
        if ((r0.zza(this.zzb, (zzje[]) r1.toArray(new zzje[0])) instanceof zzag) == true) goto L28;
        Iterator r92 = r9.zzb().zza().iterator();     // Catch: Throwable -> L29
    L6:
        if (r92.hasNext() == false) goto L26;
        zziy r12 = (zziy) r92.next();     // Catch: Throwable -> L29
        List r2 = r12.zzb();     // Catch: Throwable -> L29
        String r13 = r12.zza();     // Catch: Throwable -> L29
        Iterator r22 = r2.iterator();     // Catch: Throwable -> L29
    L9:
        if (r22.hasNext() == false) goto L6;
        zzje r3 = (zzje) r22.next();     // Catch: Throwable -> L29
        zzao r32 = r0.zza(this.zzb, new zzje[]{r3});     // Catch: Throwable -> L29
        if ((r32 instanceof zzal) == false) goto L25;
        zzg r5 = this.zzb;     // Catch: Throwable -> L29
        if (r5.zzd(r13) == true) goto L15;
        zzai r52 = null;
    L18:
        if (r52 == null) goto L21;
        r52.zza(this.zzb, Collections.singletonList(r32));     // Catch: Throwable -> L29
        goto L9
    L21:
        throw new IllegalStateException("Rule function is undefined: ".concat(String.valueOf(r13)));     // Catch: Throwable -> L29
    L15:
        zzao r53 = r5.zzh(r13);     // Catch: Throwable -> L29
        if ((r53 instanceof zzai) == false) goto L23;
        r52 = (zzai) r53;     // Catch: Throwable -> L29
        goto L18
    L23:
        throw new IllegalStateException("Invalid function name: ".concat(String.valueOf(r13)));     // Catch: Throwable -> L29
    L25:
        throw new IllegalArgumentException("Invalid rule definition");     // Catch: Throwable -> L29
    L26:
        return;
    L28:
        throw new IllegalStateException("Program loading failed");     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        throw new zzd(th);
    }

    public final /* synthetic */ zzai zzg() {
        return new zzv(this.zzd);
    }
}
