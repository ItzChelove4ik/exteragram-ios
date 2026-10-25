package com.chaquo.python;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
class PyMap extends AbstractMap<PyObject, PyObject> {
    private final MethodCache methods;
    private final PyObject obj;

    /* renamed from: com.chaquo.python.PyMap$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractSet<Map.Entry<PyObject, PyObject>> {
        final /* synthetic */ PyMap this$0;

        /* renamed from: com.chaquo.python.PyMap$1$1, reason: invalid class name and collision with other inner class name */
        public class C00131 extends PyIterator<Map.Entry<PyObject, PyObject>> {
            final /* synthetic */ AnonymousClass1 this$1;

            /* renamed from: com.chaquo.python.PyMap$1$1$1, reason: invalid class name and collision with other inner class name */
            public class C00141 implements Map.Entry<PyObject, PyObject> {
                final /* synthetic */ C00131 this$2;
                final /* synthetic */ PyObject val$key;

                public C00141(C00131 r1, PyObject r2) {
                    this.this$2 = r1;
                    this.val$key = r2;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Map.Entry
                public PyObject getKey() {
                    return this.val$key;
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ PyObject getKey() {
                    return getKey();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Map.Entry
                public PyObject getValue() {
                    return this.this$2.this$1.this$0.get(this.val$key);
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ PyObject getValue() {
                    return getValue();
                }

                /* renamed from: setValue, reason: avoid collision after fix types in other method */
                public PyObject setValue2(PyObject r2) {
                    return this.this$2.this$1.this$0.put(this.val$key, r2);
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ PyObject setValue(PyObject r1) {
                    return setValue2(r1);
                }
            }

            public C00131(AnonymousClass1 r1, MethodCache r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // com.chaquo.python.PyIterator
            public /* bridge */ /* synthetic */ Map.Entry<PyObject, PyObject> makeNext(PyObject r1) {
                return makeNext2(r1);
            }

            @Override // com.chaquo.python.PyIterator
            /* renamed from: makeNext, reason: avoid collision after fix types in other method */
            public Map.Entry<PyObject, PyObject> makeNext2(PyObject r2) {
                return new C00141(this, r2);
            }
        }

        public AnonymousClass1(PyMap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            PyMap.access$000(this.this$0).get("clear").call(new Object[0]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<PyObject, PyObject>> iterator() {
            return new C00131(this, PyMap.access$000(this.this$0));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return PyMap.access$000(this.this$0).get("__len__").call(new Object[0]).toInt();
        }
    }

    public PyMap(PyObject r2) {
        this.obj = r2;
        MethodCache r0 = new MethodCache(r2);
        this.methods = r0;
        r0.get("__contains__");
        r0.get("__getitem__");
        r0.get("__iter__");
        r0.get("__len__");
    }

    public static /* synthetic */ MethodCache access$000(PyMap r0) {
        return r0.methods;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object r2) {
        return this.methods.get("__contains__").call(new Object[]{r2}).toBoolean();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<PyObject, PyObject>> entrySet() {
        return new AnonymousClass1(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public PyObject get(Object r2) {
        return this.methods.get("__getitem__").call(new Object[]{r2});
    L4:
        e = move-exception;
        if (e.getMessage().startsWith("KeyError:") == false) goto L9;
        return null;
    L9:
        throw e;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object get(Object r1) {
        return get(r1);
    }

    public PyObject put(PyObject r3, PyObject r4) {
        PyObject r0 = get(r3);
        this.methods.get("__setitem__").call(new Object[]{r3, r4});
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
        return put((PyObject) r1, (PyObject) r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public PyObject remove(Object r2) {
        return this.methods.get("pop").call(new Object[]{r2, null});
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object remove(Object r1) {
        return remove(r1);
    }
}
