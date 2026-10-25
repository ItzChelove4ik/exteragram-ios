package com.google.android.gms.internal.measurement;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzms extends InputStream implements InputStreamRetargetInterface {
    final /* synthetic */ zzacv zza;

    public zzms(zzmu r1, zzacv r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // java.io.InputStream
    public final int read() {
        byte[] r1 = new byte[1];
        if (this.zza.zzK(r1, 0, 1) != (-1)) goto L6;
        return -1;
    L6:
        return r1[0];
    }

    @Override // java.io.InputStream
    public final int read(byte[] r1, int r2, int r3) {
        return this.zza.zzK(r1, r2, r3);
    }

    @Override // java.io.InputStream
    public final long skip(long r4) {
        if (r4 > 0) goto L6;
        return 0;
    L6:
        if (r4 <= 2147483647L) goto L8;
        int r42 = Integer.MAX_VALUE;
    L9:
        this.zza.zzL(r42);
        return r42;
    L8:
        r42 = (int) r4;
        goto L9
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
