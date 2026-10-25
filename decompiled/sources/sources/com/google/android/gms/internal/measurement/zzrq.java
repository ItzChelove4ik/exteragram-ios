package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final class zzrq extends zzso {
    private final List zza;

    private zzrq(OutputStream r1, List r2) {
        super(r1);
        this.zza = r2;
    }

    public static zzrq zza(List r2, Uri r3, OutputStream r4) {
        ArrayList r32 = new ArrayList();
        Iterator r22 = r2.iterator();
        if (r22.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.next());
        throw null;
    L5:
        if (r32.isEmpty() == false) goto L7;
        return null;
    L7:
        return new zzrq(r4, r32);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator r0 = this.zza.iterator();
    L4:
        if (r0.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
        throw null;     // Catch: Throwable -> L9
    L5:
        super.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int r2) {
        ((FilterOutputStream) this).out.write(r2);
        Iterator r1 = this.zza.iterator();
        if (r1.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzso, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] r2) {
        ((FilterOutputStream) this).out.write(r2);
        Iterator r1 = this.zza.iterator();
        if (r1.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        int r12 = r2.length;
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzso, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] r2, int r3, int r4) {
        ((FilterOutputStream) this).out.write(r2, r3, r4);
        Iterator r1 = this.zza.iterator();
        if (r1.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }
}
