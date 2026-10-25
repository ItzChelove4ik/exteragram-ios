package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private static final Provider<Object> EMPTY_PROVIDER = null;
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = null;
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;

    public static /* synthetic */ Object $r8$lambda$42DdGLxm8XDmnYxP5XsnqhLiudg() {
        return null;
    }

    /* renamed from: $r8$lambda$SG6J0sYOwGLatJJ8BJZBQ-OrQm8, reason: not valid java name */
    public static /* synthetic */ void m1922$r8$lambda$SG6J0sYOwGLatJJ8BJZBQOrQm8(Deferred.DeferredHandler r0, Deferred.DeferredHandler r1, Provider r2) {
        r0.handle(r2);
        r1.handle(r2);
    }

    public static /* synthetic */ void $r8$lambda$ytZhMnMbf78qjlsbWoYlWtBGovI(Provider r0) {
    }

    static {
        NOOP_HANDLER = new OptionalProvider$$ExternalSyntheticLambda0();
        EMPTY_PROVIDER = new OptionalProvider$$ExternalSyntheticLambda1();
    }

    private OptionalProvider(Deferred.DeferredHandler<T> r1, Provider<T> r2) {
        this.handler = r1;
        this.delegate = r2;
    }

    public static <T> OptionalProvider<T> empty() {
        return new OptionalProvider(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    public static <T> OptionalProvider<T> of(Provider<T> r2) {
        return new OptionalProvider(null, r2);
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        return this.delegate.get();
    }

    public void set(Provider<T> r3) {
        if (this.delegate != EMPTY_PROVIDER) goto L12;
        monitor-enter(this);
        Deferred.DeferredHandler<T> r0 = this.handler;     // Catch: Throwable -> L9
        this.handler = null;     // Catch: Throwable -> L9
        this.delegate = r3;     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        r0.handle(r3);
        return;
    L9:
        th = move-exception;
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("provide() can be called only once.");
    }

    @Override // com.google.firebase.inject.Deferred
    public void whenAvailable(Deferred.DeferredHandler<T> r4) {
        Provider<T> r0 = this.delegate;
        Provider<Object> r1 = EMPTY_PROVIDER;
        if (r0 == r1) goto L6;
        r4.handle(r0);
        return;
    L6:
        monitor-enter(this);
        Provider<T> r02 = this.delegate;     // Catch: Throwable -> L15
        if (r02 == r1) goto L10;
        Provider<T> r12 = r02;
    L11:
        monitor-exit(this);     // Catch: Throwable -> L15
        if (r12 == null) goto L20;
        r4.handle(r02);
        return;
    L20:
        return;
    L10:
        this.handler = new OptionalProvider$$ExternalSyntheticLambda2(this.handler, r4);     // Catch: Throwable -> L15
        r12 = null;
    L15:
        th = move-exception;
        throw th;
    }
}
