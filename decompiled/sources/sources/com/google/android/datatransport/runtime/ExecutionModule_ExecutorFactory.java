package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class ExecutionModule_ExecutorFactory implements Factory<Executor> {

    public static final class InstanceHolder {
        private static final ExecutionModule_ExecutorFactory INSTANCE = null;

        static {
            INSTANCE = new ExecutionModule_ExecutorFactory();
        }

        public static /* synthetic */ ExecutionModule_ExecutorFactory access$000() {
            return INSTANCE;
        }
    }

    public ExecutionModule_ExecutorFactory() {
    }

    public static ExecutionModule_ExecutorFactory create() {
        return InstanceHolder.access$000();
    }

    public static Executor executor() {
        return (Executor) Preconditions.checkNotNullFromProvides(ExecutionModule.executor());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }

    @Override // javax.inject.Provider
    public Executor get() {
        return executor();
    }
}
