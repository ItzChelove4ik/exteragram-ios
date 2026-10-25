package com.sun.jna;

/* loaded from: classes5.dex */
public class CallbackThreadInitializer {
    private boolean daemon;
    private boolean detach;
    private ThreadGroup group;
    private String name;

    public CallbackThreadInitializer() {
        this(true);
    }

    public CallbackThreadInitializer(boolean r2) {
        this(r2, false);
    }

    public CallbackThreadInitializer(boolean r2, boolean r3) {
        this(r2, r3, null);
    }

    public CallbackThreadInitializer(boolean r2, boolean r3, String r4) {
        this(r2, r3, r4, null);
    }

    public CallbackThreadInitializer(boolean r1, boolean r2, String r3, ThreadGroup r4) {
        this.daemon = r1;
        this.detach = r2;
        this.name = r3;
        this.group = r4;
    }

    public boolean detach(Callback r1) {
        return this.detach;
    }

    public String getName(Callback r1) {
        return this.name;
    }

    public ThreadGroup getThreadGroup(Callback r1) {
        return this.group;
    }

    public boolean isDaemon(Callback r1) {
        return this.daemon;
    }
}
