package com.google.firebase.concurrent;

import android.os.Process;
import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
class CustomThreadFactory implements ThreadFactory {
    private static final ThreadFactory DEFAULT = null;
    private final String namePrefix;
    private final StrictMode.ThreadPolicy policy;
    private final int priority;
    private final AtomicLong threadCount;

    public static /* synthetic */ void $r8$lambda$XB8AY3Hcio74byWuTzgVEC3Hiek(CustomThreadFactory r1, Runnable r2) {
        Process.setThreadPriority(r1.priority);
        StrictMode.ThreadPolicy r12 = r1.policy;
        if (r12 == null) goto L5;
        StrictMode.setThreadPolicy(r12);
    L5:
        r2.run();
    }

    static {
        DEFAULT = Executors.defaultThreadFactory();
    }

    public CustomThreadFactory(String r2, int r3, StrictMode.ThreadPolicy r4) {
        this.threadCount = new AtomicLong();
        this.namePrefix = r2;
        this.priority = r3;
        this.policy = r4;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable r5) {
        Thread r52 = DEFAULT.newThread(new CustomThreadFactory$$ExternalSyntheticLambda0(this, r5));
        r52.setName(String.format(Locale.ROOT, "%s Thread #%d", new Object[]{this.namePrefix, Long.valueOf(this.threadCount.getAndIncrement())}));
        return r52;
    }
}
