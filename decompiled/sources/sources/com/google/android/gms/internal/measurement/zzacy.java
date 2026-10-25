package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class zzacy extends IOException {
    public zzacy(long r2, long r4, int r6, Throwable r7) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(r2), Long.valueOf(r4), Integer.valueOf(r6)})), r7);
    }

    public zzacy(Throwable r2) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", r2);
    }
}
