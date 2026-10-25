package com.google.android.gms.internal.measurement;

import java.io.Closeable;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* loaded from: classes4.dex */
public final class zzmu implements Closeable {
    private final Inflater zza;

    private zzmu() {
        this.zza = new Inflater(true);
    }

    public static zzmu zza() {
        return new zzmu();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.zza.end();
    }

    public final Object zzb(byte[] r1, zzmt r2) {
        this.zza.setInput(r1);
        zzmw r12 = zzmw.zzd(zzacv.zzM(new zzmr(this), 4096));     // Catch: Throwable -> L6
        this.zza.reset();
        return r12;
    L6:
        th = move-exception;
        this.zza.reset();
        throw th;
    }

    public final Object zzc(zzacv r4, zzmt r5) {
        int r52 = r4.zzF();
        if (r52 >= 0) goto L5;
        int r53 = 4096;
    L12:
        zzmw r42 = zzmw.zzd(zzacv.zzM(new InflaterInputStream(new zzms(this, r4), this.zza, r53), 4096));     // Catch: Throwable -> L9
        this.zza.reset();
        return r42;
    L9:
        th = move-exception;
        this.zza.reset();
        throw th;
    L5:
        r53 = Math.min(r52, 4096);
        goto L12
    }

    public final /* synthetic */ Inflater zzd() {
        return this.zza;
    }
}
