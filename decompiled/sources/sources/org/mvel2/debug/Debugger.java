package org.mvel2.debug;

/* loaded from: classes5.dex */
public interface Debugger {
    public static final int CONTINUE = 0;
    public static final int STEP = 1;
    public static final int STEP_OVER = 1;

    int onBreak(Frame r1);
}
