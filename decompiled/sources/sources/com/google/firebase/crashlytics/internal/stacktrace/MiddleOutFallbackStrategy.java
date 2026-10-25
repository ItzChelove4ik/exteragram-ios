package com.google.firebase.crashlytics.internal.stacktrace;

/* loaded from: classes.dex */
public class MiddleOutFallbackStrategy implements StackTraceTrimmingStrategy {
    private final int maximumStackSize;
    private final MiddleOutStrategy middleOutStrategy;
    private final StackTraceTrimmingStrategy[] trimmingStrategies;

    public MiddleOutFallbackStrategy(int r1, StackTraceTrimmingStrategy... r2) {
        this.maximumStackSize = r1;
        this.trimmingStrategies = r2;
        this.middleOutStrategy = new MiddleOutStrategy(r1);
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] r8) {
        if (r8.length > this.maximumStackSize) goto L5;
        return r8;
    L5:
        StackTraceTrimmingStrategy[] r0 = this.trimmingStrategies;
        int r1 = r0.length;
        int r2 = 0;
        StackTraceElement[] r3 = r8;
    L6:
        if (r2 >= r1) goto L12;
        StackTraceTrimmingStrategy r4 = r0[r2];
        if (r3.length <= this.maximumStackSize) goto L12;
        r3 = r4.getTrimmedStackTrace(r8);
        r2 = r2 + 1;
    L12:
        if (r3.length > this.maximumStackSize) goto L14;
        return r3;
    L14:
        return this.middleOutStrategy.getTrimmedStackTrace(r3);
    }
}
