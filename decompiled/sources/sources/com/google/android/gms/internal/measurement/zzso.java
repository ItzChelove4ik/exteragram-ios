package com.google.android.gms.internal.measurement;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public abstract class zzso extends FilterOutputStream {
    public zzso(OutputStream r1) {
        super(r1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r1) {
        ((FilterOutputStream) this).out.write(r1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r1, int r2, int r3) {
        ((FilterOutputStream) this).out.write(r1, r2, r3);
    }
}
