package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzsv implements zzrt {
    private final zzafc zza;
    private zzro[] zzb;

    private zzsv(zzafc r1) {
        this.zza = r1;
    }

    public static zzsv zzb(zzafc r1) {
        return new zzsv(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r5) {
        Uri r0 = zzsu.zza(r5.zzb());
        List r1 = r5.zzd(r5.zza().zzj(r0));
        zzro[] r2 = this.zzb;
        if (r2 == null) goto L37;
        r2[0].zza(r1);
    L37:
        OutputStream r12 = (OutputStream) r1.get(0);     // Catch: Exception -> L15
        this.zza.zzce(r12);     // Catch: Throwable -> L10
        zzro[] r4 = this.zzb;     // Catch: Throwable -> L10
        if (r4 == null) goto L12;
        r4[0].zzb();     // Catch: Throwable -> L10
    L12:
        if (r12 == null) goto L17;
        r12.close();     // Catch: Exception -> L15
    L17:
        r5.zza().zzl(r0, r5.zzb());
        return null;
    L10:
        th = move-exception;
        if (r12 != null) goto L33;
    L24:
        throw th;     // Catch: Exception -> L15
    L33:
        r12.close();     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        r5.zza().zzk(r0);     // Catch: FileNotFoundException -> L32
    L27:
        if ((e instanceof IOException) == false) goto L31;
        throw ((IOException) e);
    L31:
        throw new IOException(e);
    }

    public final zzsv zzc(zzro... r1) {
        this.zzb = r1;
        return this;
    }
}
