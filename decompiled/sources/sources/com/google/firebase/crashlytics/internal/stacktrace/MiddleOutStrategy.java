package com.google.firebase.crashlytics.internal.stacktrace;

/* loaded from: classes.dex */
public class MiddleOutStrategy implements StackTraceTrimmingStrategy {
    private final int trimmedSize;

    public MiddleOutStrategy(int r1) {
        this.trimmedSize = r1;
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] r4) {
        int r0 = r4.length;
        int r3 = this.trimmedSize;
        if (r0 > r3) goto L5;
        return r4;
    L5:
        int r02 = r3 / 2;
        int r1 = r3 - r02;
        StackTraceElement[] r32 = new StackTraceElement[r3];
        System.arraycopy(r4, 0, r32, 0, r1);
        System.arraycopy(r4, r4.length - r02, r32, r1, r02);
        return r32;
    }
}
