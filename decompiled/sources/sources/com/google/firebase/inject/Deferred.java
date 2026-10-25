package com.google.firebase.inject;

/* loaded from: classes.dex */
public interface Deferred<T> {

    public interface DeferredHandler<T> {
        void handle(Provider<T> r1);
    }

    void whenAvailable(DeferredHandler<T> r1);
}
