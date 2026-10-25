package com.google.common.collect;

import java.util.ListIterator;

/* loaded from: classes.dex */
public abstract class UnmodifiableListIterator<E> extends UnmodifiableIterator<E> implements ListIterator<E> {
    public UnmodifiableListIterator() {
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void add(E r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void set(E r1) {
        throw new UnsupportedOperationException();
    }
}
