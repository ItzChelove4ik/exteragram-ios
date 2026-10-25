package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzyi;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/* loaded from: classes4.dex */
public abstract class zzxi<API extends zzyi<API>> {
    private final zzzf zza;

    public zzxi(zzzf r1) {
        this.zza = r1;
    }

    private static void zzd(String r6, zzzd r7) {
        StringBuilder r0 = new StringBuilder();
        Date r1 = new Date(r7.zzf() / 1000000);
        r0.append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(r1));
        r0.append(": logging error [");
        zzze.zza(1, r7.zzg(), r0);
        r0.append("]: ");
        r0.append(r6);
        System.err.println(r0);
        System.err.flush();
    }

    public final String zza() {
        return this.zza.zza();
    }

    public final boolean zzb(Level r1) {
        return this.zza.zzb(r1);
    }

    public final void zzc(zzzd r6) {
        zzabt r0 = zzabt.zzc();     // Catch: RuntimeException -> L11
    L6:
        th = move-exception;
        if (r0 != null) goto L29;
    L18:
        throw th;     // Catch: RuntimeException -> L11
    L29:
        r0.close();     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: RuntimeException -> L11
        goto L18
    L4:
        if (r0.zzb() > 100) goto L8;
        this.zza.zzc(r6);     // Catch: Throwable -> L6
    L9:
        r0.close();     // Catch: RuntimeException -> L11
        return;
    L8:
        zzd("unbounded recursion in log statement", r6);     // Catch: Throwable -> L6
    L11:
        e = move-exception;
        this.zza.zzd(e, r6);     // Catch: RuntimeException -> L21
        return;
    L21:
        e = move-exception;
        String r02 = e.getClass().getName();
        String r1 = e.getMessage();
        int r2 = r02.length();
        StringBuilder r4 = new StringBuilder((r2 + 2) + String.valueOf(r1).length());
        r4.append(r02);
        r4.append(": ");
        r4.append(r1);
        zzd(r4.toString(), r6);
        e.printStackTrace(System.err);     // Catch: RuntimeException -> L25
        return;
    }
}
