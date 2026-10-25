package dagger.internal;

/* loaded from: classes5.dex */
public final class SingleCheck<T> implements Provider<T> {
    private static final Object UNINITIALIZED = null;
    private volatile Object instance;
    private volatile Provider<T> provider;

    static {
        UNINITIALIZED = new Object();
    }

    private SingleCheck(Provider<T> r2) {
        this.instance = UNINITIALIZED;
        this.provider = r2;
    }

    public static <T> Provider<T> provider(Provider<T> r1) {
        if ((r1 instanceof SingleCheck) == false) goto L5;
        return r1;
    L5:
        if ((r1 instanceof DoubleCheck) == false) goto L8;
        return r1;
    L8:
        return new SingleCheck((Provider) Preconditions.checkNotNull(r1));
    }

    @Override // javax.inject.Provider
    public T get() {
        T r0 = (T) this.instance;
        if (r0 != UNINITIALIZED) goto L10;
        Provider<T> r02 = this.provider;
        if (r02 == null) goto L7;
        T r03 = r02.get();
        this.instance = r03;
        this.provider = null;
        return r03;
    L7:
        return (T) this.instance;
    L10:
        return r0;
    }
}
