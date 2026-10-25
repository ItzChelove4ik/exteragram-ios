package com.google.firebase.messaging.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public interface ExecutorFactory {
    ExecutorService newSingleThreadExecutor(ThreadFactory r1, ThreadPriority r2);
}
