package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
class LazySet<T> implements Provider<Set<T>> {
    private volatile Set<T> actualSet;
    private volatile Set<Provider<T>> providers;

    public LazySet(Collection<Provider<T>> r2) {
        this.actualSet = null;
        this.providers = Collections.newSetFromMap(new ConcurrentHashMap());
        this.providers.addAll(r2);
    }

    public static LazySet<?> fromCollection(Collection<Provider<?>> r1) {
        return new LazySet((Set) r1);
    }

    private synchronized void updateSet() {
        monitor-enter(this);
        Iterator<Provider<T>> r0 = this.providers.iterator();     // Catch: Throwable -> L7
    L5:
        if (r0.hasNext() == false) goto L9;
        Provider<T> r1 = r0.next();     // Catch: Throwable -> L7
        this.actualSet.add(r1.get());     // Catch: Throwable -> L7
        goto L5
    L9:
        this.providers = null;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized void add(Provider<T> r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.actualSet != null) goto L8;
        this.providers.add(r2);     // Catch: Throwable -> L6
    L9:
        monitor-exit(this);
        return;
    L8:
        this.actualSet.add(r2.get());     // Catch: Throwable -> L6
        goto L9
    }

    @Override // com.google.firebase.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }

    @Override // com.google.firebase.inject.Provider
    public Set<T> get() {
        if (this.actualSet != null) goto L15;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.actualSet != null) goto L10;
        this.actualSet = Collections.newSetFromMap(new ConcurrentHashMap());     // Catch: Throwable -> L8
        updateSet();     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
    L15:
        return Collections.unmodifiableSet(this.actualSet);
    }
}
