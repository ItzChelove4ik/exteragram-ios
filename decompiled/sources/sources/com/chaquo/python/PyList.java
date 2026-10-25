package com.chaquo.python;

import java.util.AbstractList;

/* loaded from: classes.dex */
class PyList extends AbstractList<PyObject> {
    private final MethodCache methods;
    private final PyObject obj;

    public PyList(PyObject r2) {
        this.obj = r2;
        MethodCache r0 = new MethodCache(r2);
        this.methods = r0;
        r0.get("__getitem__");
        r0.get("__len__");
    }

    private void checkLowerBound(int r1) {
        if (r1 < 0) goto L5;
        return;
    L5:
        throw outOfBounds(r1);
    }

    private RuntimeException maybeOutOfBounds(int r3, PyException r4) {
        if (r4.getMessage().startsWith("IndexError:") == true) goto L5;
        return r4;
    L5:
        return outOfBounds(r3);
    }

    private IndexOutOfBoundsException outOfBounds(int r4) {
        return new IndexOutOfBoundsException("Invalid index " + r4 + ", size is " + size());
    }

    public void add(int r2, PyObject r3) {
        checkLowerBound(r2);
        if (r2 > size()) goto L7;
        this.methods.get("insert").call(new Object[]{Integer.valueOf(r2), r3});
        return;
    L7:
        throw outOfBounds(r2);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ void add(int r1, Object r2) {
        add(r1, (PyObject) r2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.methods.get("clear").call(new Object[0]);
    }

    @Override // java.util.AbstractList, java.util.List
    public PyObject get(int r3) {
        checkLowerBound(r3);
        return this.methods.get("__getitem__").call(new Object[]{Integer.valueOf(r3)});
    L5:
        e = move-exception;
        throw this.maybeOutOfBounds(r3, e);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public PyObject remove(int r3) {
        checkLowerBound(r3);
        return this.methods.get("pop").call(new Object[]{Integer.valueOf(r3)});
    L5:
        e = move-exception;
        throw this.maybeOutOfBounds(r3, e);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object remove(int r1) {
        return remove(r1);
    }

    public PyObject set(int r3, PyObject r4) {
        PyObject r0 = get(r3);
        this.methods.get("__setitem__").call(new Object[]{Integer.valueOf(r3), r4});
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object set(int r1, Object r2) {
        return set(r1, (PyObject) r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.methods.get("__len__").call(new Object[0]).toInt();
    }
}
