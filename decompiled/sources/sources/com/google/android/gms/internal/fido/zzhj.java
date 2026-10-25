package com.google.android.gms.internal.fido;

import java.io.IOException;

/* loaded from: classes4.dex */
public final class zzhj extends IOException {
    public zzhj(String r1) {
        super(r1);
    }

    public zzhj(String r1, Throwable r2) {
        super("Error in decoding CborValue from bytes", r2);
    }
}
