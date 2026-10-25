package com.google.android.gms.internal.measurement;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public abstract class zzsn extends FilterInputStream implements InputStreamRetargetInterface {
    public zzsn(InputStream r1) {
        super(r1);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] r1) {
        return ((FilterInputStream) this).in.read(r1);
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
