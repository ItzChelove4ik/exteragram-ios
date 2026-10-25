package com.google.firebase.concurrent;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class FirebaseExecutors {
    public static Executor newSequentialExecutor(Executor r1) {
        return new SequentialExecutor(r1);
    }
}
