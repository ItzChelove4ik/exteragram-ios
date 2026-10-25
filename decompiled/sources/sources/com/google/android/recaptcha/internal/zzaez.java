package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class zzaez extends IOException {
    public zzaez(long r2, long r4, int r6, Throwable r7) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(r2), Long.valueOf(r4), Integer.valueOf(r6)})), r7);
    }

    public zzaez(Throwable r2) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", r2);
    }
}
