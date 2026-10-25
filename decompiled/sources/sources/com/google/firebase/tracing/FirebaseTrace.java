package com.google.firebase.tracing;

import android.os.Trace;

/* loaded from: classes.dex */
public abstract class FirebaseTrace {
    public static void popTrace() {
        Trace.endSection();
    }

    public static void pushTrace(String r0) {
        Trace.beginSection(r0);
    }
}
