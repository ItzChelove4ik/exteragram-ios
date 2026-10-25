package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final class zzrp extends zzsn {
    private final List zza;

    private zzrp(InputStream r1, List r2) {
        super(r1);
        this.zza = r2;
    }

    public static zzrp zza(List r2, Uri r3, InputStream r4) {
        ArrayList r32 = new ArrayList();
        Iterator r22 = r2.iterator();
        if (r22.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.next());
        throw null;
    L5:
        if (r32.isEmpty() == false) goto L7;
        return null;
    L7:
        return new zzrp(r4, r32);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator r0 = this.zza.iterator();
    L4:
        if (r0.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
        throw null;     // Catch: Throwable -> L9
    L5:
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() {
        int r0 = ((FilterInputStream) this).in.read();
        if (r0 == (-1)) goto L9;
        Iterator r2 = this.zza.iterator();
        if (r2.hasNext() == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());
        throw null;
    L9:
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzsn, java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] r2) {
        int r22 = ((FilterInputStream) this).in.read(r2);
        if (r22 == (-1)) goto L9;
        Iterator r1 = this.zza.iterator();
        if (r1.hasNext() == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    L9:
        return r22;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] r2, int r3, int r4) {
        int r22 = ((FilterInputStream) this).in.read(r2, r3, r4);
        if (r22 == (-1)) goto L9;
        Iterator r1 = this.zza.iterator();
        if (r1.hasNext() == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    L9:
        return r22;
    }
}
