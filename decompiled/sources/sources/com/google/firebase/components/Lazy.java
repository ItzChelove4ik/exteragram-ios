package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public class Lazy<T> implements Provider<T> {
    private static final Object UNINITIALIZED = null;
    private volatile Object instance;
    private volatile Provider<T> provider;

    static {
        UNINITIALIZED = new Object();
    }

    public Lazy(Provider<T> r2) {
        this.instance = UNINITIALIZED;
        this.provider = r2;
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        T r0 = (T) this.instance;
        Object r1 = UNINITIALIZED;
        if (r0 != r1) goto L14;
        monitor-enter(this);
        T r02 = (T) this.instance;
        if (r02 != r1) goto L10;
        r02 = this.provider.get();     // Catch: Throwable -> L8
        this.instance = r02;     // Catch: Throwable -> L8
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
