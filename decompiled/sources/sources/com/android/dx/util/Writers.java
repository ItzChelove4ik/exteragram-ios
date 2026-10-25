package com.android.dx.util;

import java.io.PrintWriter;
import java.io.Writer;

/* loaded from: classes4.dex */
public final class Writers {
    private Writers() {
    }

    public static PrintWriter printWriterFor(Writer r1) {
        if ((r1 instanceof PrintWriter) == false) goto L7;
        return (PrintWriter) r1;
    L7:
        return new PrintWriter(r1);
    }
}
