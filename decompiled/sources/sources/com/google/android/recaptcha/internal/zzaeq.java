package com.google.android.recaptcha.internal;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class zzaeq extends OutputStream {
    private static final byte[] zza = null;
    private final int zzb;
    private final ArrayList zzc;
    private int zzd;
    private byte[] zze;
    private int zzf;

    static {
        zza = new byte[0];
    }

    public zzaeq(int r2) {
        this.zzb = 128;
        this.zzc = new ArrayList();
        this.zze = new byte[128];
    }

    private final void zzc(int r3) {
        zzaep r0 = new zzaep(this.zze);
        this.zzc.add(r0);
        int r02 = this.zzd + this.zze.length;
        this.zzd = r02;
        this.zze = new byte[Math.max(this.zzb, Math.max(r3, r02 >>> 1))];
        this.zzf = 0;
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString.Output@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zzb())});
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int r4) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.zzf != this.zze.length) goto L8;
        zzc(1);     // Catch: Throwable -> L6
    L8:
        byte[] r0 = this.zze;     // Catch: Throwable -> L6
        int r1 = this.zzf;     // Catch: Throwable -> L6
        this.zzf = r1 + 1;     // Catch: Throwable -> L6
        r0[r1] = (byte) r4;     // Catch: Throwable -> L6
        monitor-exit(this);
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] r4, int r5, int r6) {
        monitor-enter(this);
        byte[] r0 = this.zze;     // Catch: Throwable -> L8
        int r1 = r0.length;     // Catch: Throwable -> L8
        int r2 = this.zzf;     // Catch: Throwable -> L8
        int r12 = r1 - r2;     // Catch: Throwable -> L8
        if (r6 > r12) goto L10;
        System.arraycopy(r4, r5, r0, r2, r6);     // Catch: Throwable -> L8
        this.zzf += r6;
        monitor-exit(this);
        return;
    L10:
        System.arraycopy(r4, r5, r0, r2, r12);     // Catch: Throwable -> L8
        int r62 = r6 - r12;     // Catch: Throwable -> L8
        zzc(r62);     // Catch: Throwable -> L8
        System.arraycopy(r4, r5 + r12, this.zze, 0, r62);     // Catch: Throwable -> L8
        this.zzf = r62;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final synchronized zzaer zza() {
        monitor-enter(this);
        int r0 = this.zzf;     // Catch: Throwable -> L7
        byte[] r1 = this.zze;     // Catch: Throwable -> L7
        if (r0 >= r1.length) goto L9;
        if (r0 <= 0) goto L10;
        this.zzc.add(new zzaep(Arrays.copyOf(r1, r0)));     // Catch: Throwable -> L7
    L10:
        this.zzd += this.zzf;
        this.zzf = 0;     // Catch: Throwable -> L7
        zzaer r02 = zzaer.zzw(this.zzc);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r02;
    L9:
        this.zzc.add(new zzaep(this.zze));     // Catch: Throwable -> L7
        this.zze = zza;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized int zzb() {
        monitor-enter(this);
        int r0 = this.zzd + this.zzf;
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
