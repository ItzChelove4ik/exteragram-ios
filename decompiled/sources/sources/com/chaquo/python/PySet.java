package com.chaquo.python;

import java.util.AbstractSet;
import java.util.Iterator;

/* loaded from: classes4.dex */
class PySet extends AbstractSet<PyObject> {
    private final MethodCache methods;
    private final PyObject obj;

    /* renamed from: com.chaquo.python.PySet$1, reason: invalid class name */
    public class AnonymousClass1 extends PyIterator<PyObject> {
        final /* synthetic */ PySet this$0;

        public AnonymousClass1(PySet r1, MethodCache r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.chaquo.python.PyIterator
        public PyObject makeNext(PyObject r1) {
            return r1;
        }

        @Override // com.chaquo.python.PyIterator
        public /* bridge */ /* synthetic */ PyObject makeNext(PyObject r1) {
            return makeNext(r1);
        }
    }

    public PySet(PyObject r2) {
        this.obj = r2;
        MethodCache r0 = new MethodCache(r2);
        this.methods = r0;
        r0.get("__contains__");
        r0.get("__iter__");
        r0.get("__len__");
    }

    public boolean add(PyObject r3) {
        PyObject r0 = this.methods.get("add");
        if (contains(r3) == false) goto L6;
        return false;
    L6:
        r0.call(new Object[]{r3});
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public /* bridge */ /* synthetic */ boolean add(Object r1) {
        return add((PyObject) r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.methods.get("clear").call(new Object[0]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object r2) {
        return this.methods.get("__contains__").call(new Object[]{r2}).toBoolean();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<PyObject> iterator() {
        return new AnonymousClass1(this, this.methods);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object r2) {
        this.methods.get("remove").call(new Object[]{r2});     // Catch: PyException -> L5
        return true;
    L5:
        e = move-exception;
        if (e.getMessage().startsWith("KeyError:") == false) goto L10;
        return false;
    L10:
        throw e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.methods.get("__len__").call(new Object[0]).toInt();
    }
}
