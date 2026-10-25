package com.google.firebase.sessions.dagger.internal;

/* loaded from: classes.dex */
public final class InstanceFactory<T> implements Factory<T> {
    private static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = null;
    private final T instance;

    static {
        NULL_INSTANCE_FACTORY = new InstanceFactory(null);
    }

    private InstanceFactory(T r1) {
        this.instance = r1;
    }

    public static <T> Factory<T> create(T r2) {
        return new InstanceFactory(Preconditions.checkNotNull(r2, "instance cannot be null"));
    }

    @Override // javax.inject.Provider
    public T get() {
        return this.instance;
    }
}
