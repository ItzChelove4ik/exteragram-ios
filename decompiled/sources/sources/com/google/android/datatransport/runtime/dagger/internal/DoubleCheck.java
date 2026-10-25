package com.google.android.datatransport.runtime.dagger.internal;

import dagger.internal.DoubleCheck$$ExternalSyntheticBUOutline0;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DoubleCheck<T> implements Provider<T> {
    private static final Object UNINITIALIZED = null;
    private volatile Object instance;
    private volatile Provider<T> provider;

    static {
        UNINITIALIZED = new Object();
    }

    private DoubleCheck(Provider<T> r2) {
        this.instance = UNINITIALIZED;
        this.provider = r2;
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P r1) {
        Preconditions.checkNotNull(r1);
        if ((r1 instanceof DoubleCheck) == false) goto L6;
        return r1;
    L6:
        return new DoubleCheck(r1);
    }

    private static Object reentrantCheck(Object r3, Object r4) {
        if (r3 == UNINITIALIZED) goto L8;
        if (r3 != r4) goto L6;
        return r4;
    L6:
        DoubleCheck$$ExternalSyntheticBUOutline0.m("Scoped provider was invoked recursively returning different results: ", r3, " & ", r4, ". This is likely due to a circular dependency.");
        return null;
    L8:
        return r4;
    }

    @Override // javax.inject.Provider
    public T get() {
        T r0 = (T) this.instance;
        Object r1 = UNINITIALIZED;
        if (r0 != r1) goto L14;
        monitor-enter(this);
        T r02 = (T) this.instance;
        if (r02 != r1) goto L10;
        r02 = this.provider.get();     // Catch: Throwable -> L8
        this.instance = reentrantCheck(this.instance, r02);     // Catch: Throwable -> L8
        this.provider = null;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        return r02;
    L8:
        th = move-exception;
        throw th;
    L14:
        return r0;
    }
}
