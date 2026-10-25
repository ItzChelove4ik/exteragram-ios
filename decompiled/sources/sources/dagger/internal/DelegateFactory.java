package dagger.internal;

import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class DelegateFactory<T> implements Provider {
    private Provider<T> delegate;

    public DelegateFactory() {
    }

    public static <T> void setDelegate(Provider<T> r0, Provider<T> r1) {
        setDelegateInternal((DelegateFactory) r0, r1);
    }

    private static <T> void setDelegateInternal(DelegateFactory<T> r1, Provider<T> r2) {
        Preconditions.checkNotNull(r2);
        if (((DelegateFactory) r1).delegate != null) goto L6;
        ((DelegateFactory) r1).delegate = r2;
        return;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    @Override // javax.inject.Provider
    public T get() {
        Provider<T> r0 = this.delegate;
        if (r0 != null) goto L5;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return r0.get();
    }
}
