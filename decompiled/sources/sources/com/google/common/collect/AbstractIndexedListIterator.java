package com.google.common.collect;

import com.google.common.base.Preconditions;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    public AbstractIndexedListIterator(int r1, int r2) {
        Preconditions.checkPositionIndex(r2, r1);
        this.size = r1;
        this.position = r2;
    }

    public abstract E get(int r1);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        if (this.position >= this.size) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.position <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (hasNext() == false) goto L6;
        int r0 = this.position;
        this.position = r0 + 1;
        return get(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (hasPrevious() == false) goto L6;
        int r0 = this.position - 1;
        this.position = r0;
        return get(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }
}
