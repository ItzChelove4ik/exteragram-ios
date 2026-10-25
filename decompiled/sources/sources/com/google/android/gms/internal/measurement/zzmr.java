package com.google.android.gms.internal.measurement;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.DataFormatException;

/* loaded from: classes4.dex */
final class zzmr extends InputStream implements InputStreamRetargetInterface {
    final /* synthetic */ zzmu zza;

    public zzmr(zzmu r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.io.InputStream
    public final int read() {
        byte[] r1 = new byte[1];
        if (read(r1, 0, 1) != (-1)) goto L6;
        return -1;
    L6:
        return r1[0];
    }

    @Override // java.io.InputStream
    public final int read(byte[] r3, int r4, int r5) {
        zzmu r0 = this.zza;     // Catch: DataFormatException -> L14
        int r32 = r0.zzd().inflate(r3, r4, r5);     // Catch: DataFormatException -> L14
        if (r32 <= 0) goto L5;
        return r32;
    L5:
        if (r5 == 0) goto L12;
        if (r0.zzd().getRemaining() != 0) goto L10;
        return -1;
    L10:
        int r2 = this.zza.zzd().getRemaining();
        int r42 = String.valueOf(r5).length();
        StringBuilder r02 = new StringBuilder((r42 + 70) + String.valueOf(r2).length());
        r02.append("Read no bytes (requested up to ");
        r02.append(r5);
        r02.append(") but did not reach end of stream, had ");
        r02.append(r2);
        throw new IOException(r02.toString());
    L12:
        return 0;
    L14:
        e = move-exception;
        throw new IOException(e);
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
