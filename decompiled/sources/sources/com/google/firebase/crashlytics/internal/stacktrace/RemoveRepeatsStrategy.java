package com.google.firebase.crashlytics.internal.stacktrace;

import java.util.HashMap;

/* loaded from: classes.dex */
public class RemoveRepeatsStrategy implements StackTraceTrimmingStrategy {
    private final int maxRepetitions;

    public RemoveRepeatsStrategy(int r1) {
        this.maxRepetitions = r1;
    }

    private static boolean isRepeatingSequence(StackTraceElement[] r5, int r6, int r7) {
        int r0 = r7 - r6;
        if ((r7 + r0) <= r5.length) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        if (r5[r6 + r1].equals(r5[r7 + r1]) == false) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    private static StackTraceElement[] trimRepeats(StackTraceElement[] r10, int r11) {
        HashMap r0 = new HashMap();
        StackTraceElement[] r1 = new StackTraceElement[r10.length];
        int r4 = 0;
        int r5 = 0;
        int r6 = 1;
    L4:
        if (r4 >= r10.length) goto L16;
        StackTraceElement r7 = r10[r4];
        Integer r8 = (Integer) r0.get(r7);
        if (r8 != null) goto L8;
    L14:
        r1[r5] = r10[r4];
        r5 = r5 + 1;
        r6 = 1;
        int r82 = r4;
    L15:
        r0.put(r7, Integer.valueOf(r4));
        r4 = r82 + 1;
        goto L4
    L8:
        if (isRepeatingSequence(r10, r8.intValue(), r4) == false) goto L14;
        int r83 = r4 - r8.intValue();
        if (r6 >= r11) goto L13;
        System.arraycopy(r10, r4, r1, r5, r83);
        r5 = r5 + r83;
        r6 = r6 + 1;
    L13:
        r82 = (r83 - 1) + r4;
        goto L15
    L16:
        StackTraceElement[] r102 = new StackTraceElement[r5];
        System.arraycopy(r1, 0, r102, 0, r5);
        return r102;
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] r3) {
        StackTraceElement[] r2 = trimRepeats(r3, this.maxRepetitions);
        if (r2.length >= r3.length) goto L5;
        return r2;
    L5:
        return r3;
    }
}
