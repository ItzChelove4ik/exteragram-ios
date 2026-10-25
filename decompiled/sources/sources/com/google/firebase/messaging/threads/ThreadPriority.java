package com.google.firebase.messaging.threads;

/* loaded from: classes.dex */
public enum ThreadPriority extends Enum<ThreadPriority> {
    private static final /* synthetic */ ThreadPriority[] $VALUES = null;
    public static final ThreadPriority HIGH_SPEED = null;
    public static final ThreadPriority LOW_POWER = null;

    private static /* synthetic */ ThreadPriority[] $values() {
        return new ThreadPriority[]{LOW_POWER, HIGH_SPEED};
    }

    static {
        LOW_POWER = new ThreadPriority("LOW_POWER", 0);
        HIGH_SPEED = new ThreadPriority("HIGH_SPEED", 1);
        $VALUES = $values();
    }

    ThreadPriority(String r1, int r2) {
    }

    public static ThreadPriority valueOf(String r1) {
        return (ThreadPriority) Enum.valueOf(ThreadPriority.class, r1);
    }

    public static ThreadPriority[] values() {
        return (ThreadPriority[]) $VALUES.clone();
    }
}
