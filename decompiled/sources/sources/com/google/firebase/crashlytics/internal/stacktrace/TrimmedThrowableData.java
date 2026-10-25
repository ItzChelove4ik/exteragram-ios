package com.google.firebase.crashlytics.internal.stacktrace;

import java.util.Stack;

/* loaded from: classes5.dex */
public class TrimmedThrowableData {
    public final TrimmedThrowableData cause;
    public final String className;
    public final String localizedMessage;
    public final StackTraceElement[] stacktrace;

    private TrimmedThrowableData(String r1, String r2, StackTraceElement[] r3, TrimmedThrowableData r4) {
        this.localizedMessage = r1;
        this.className = r2;
        this.stacktrace = r3;
        this.cause = r4;
    }

    public static TrimmedThrowableData makeTrimmedThrowableData(Throwable r5, StackTraceTrimmingStrategy r6) {
        Stack r0 = new Stack();
    L3:
        if (r5 == null) goto L5;
        r0.push(r5);
        r5 = r5.getCause();
        goto L3
    L5:
        TrimmedThrowableData r52 = null;
    L7:
        if (r0.isEmpty() == true) goto L9;
        Throwable r1 = (Throwable) r0.pop();
        r52 = new TrimmedThrowableData(r1.getLocalizedMessage(), r1.getClass().getName(), r6.getTrimmedStackTrace(r1.getStackTrace()), r52);
        goto L7
    L9:
        return r52;
    }
}
