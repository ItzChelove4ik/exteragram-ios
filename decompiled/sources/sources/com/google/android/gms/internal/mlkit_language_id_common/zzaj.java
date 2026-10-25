package com.google.android.gms.internal.mlkit_language_id_common;

import java.io.OutputStream;

/* loaded from: classes4.dex */
final class zzaj extends OutputStream {
    private long zza;

    public zzaj() {
        this.zza = 0;
    }

    @Override // java.io.OutputStream
    public final void write(int r5) {
        this.zza++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] r5) {
        this.zza += r5.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] r3, int r4, int r5) {
        if (r4 < 0) goto L12;
        int r32 = r3.length;
        if (r4 > r32) goto L12;
        if (r5 < 0) goto L12;
        int r42 = r4 + r5;
        if (r42 > r32) goto L12;
        if (r42 < 0) goto L12;
        this.zza += r5;
        return;
    L12:
        throw new IndexOutOfBoundsException();
    }

    public final long zza() {
        return this.zza;
    }
}
