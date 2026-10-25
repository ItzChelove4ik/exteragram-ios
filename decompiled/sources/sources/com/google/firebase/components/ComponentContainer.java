package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

/* loaded from: classes.dex */
public interface ComponentContainer {
    default <T> T get(Qualified<T> r1) {
        Provider<T> r0 = getProvider(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get();
    }

    default <T> T get(Class<T> r1) {
        return (T) get(Qualified.unqualified(r1));
    }

    <T> Deferred<T> getDeferred(Qualified<T> r1);

    default <T> Deferred<T> getDeferred(Class<T> r1) {
        return getDeferred(Qualified.unqualified(r1));
    }

    <T> Provider<T> getProvider(Qualified<T> r1);

    default <T> Provider<T> getProvider(Class<T> r1) {
        return getProvider(Qualified.unqualified(r1));
    }

    default <T> Set<T> setOf(Qualified<T> r1) {
        return setOfProvider(r1).get();
    }

    default <T> Set<T> setOf(Class<T> r1) {
        return setOf(Qualified.unqualified(r1));
    }

    <T> Provider<Set<T>> setOfProvider(Qualified<T> r1);
}
