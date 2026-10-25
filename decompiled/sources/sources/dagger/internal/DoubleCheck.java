package dagger.internal;

/* loaded from: classes5.dex */
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

    private synchronized Object getSynchronized() {
        monitor-enter(this);
        Object r0 = this.instance;     // Catch: Throwable -> L7
        if (r0 != UNINITIALIZED) goto L9;
        r0 = this.provider.get();     // Catch: Throwable -> L7
        this.instance = reentrantCheck(this.instance, r0);     // Catch: Throwable -> L7
        this.provider = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public static <T> Provider<T> provider(Provider<T> r1) {
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
        if (r0 == UNINITIALIZED) goto L5;
        return r0;
    L5:
        return (T) getSynchronized();
    }
}
