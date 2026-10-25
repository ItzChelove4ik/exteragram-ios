package com.chaquo.python;

import java.util.Iterator;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class PyIterator<T> implements Iterator<T> {
    private boolean hasNextElem;
    private PyObject iter;
    private PyObject nextElem;

    public PyIterator(MethodCache r2) {
        this.hasNextElem = true;
        this.iter = r2.get("__iter__").call(new Object[0]);
        updateNext();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNextElem;
    }

    public abstract T makeNext(PyObject r1);

    @Override // java.util.Iterator
    public T next() {
        if (hasNext() == false) goto L6;
        T r0 = makeNext(this.nextElem);
        updateNext();
        return r0;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Python does not support removing from a container while iterating over it");
    }

    public void updateNext() {
        this.nextElem = this.iter.callAttr("__next__", new Object[0]);     // Catch: PyException -> L5
        return;
    L5:
        e = move-exception;
        if (e.getMessage().startsWith("StopIteration:") == false) goto L10;
        this.hasNextElem = false;
        this.nextElem = null;
        return;
    L10:
        throw e;
    }
}
