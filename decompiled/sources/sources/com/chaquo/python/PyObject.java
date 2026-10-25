package com.chaquo.python;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class PyObject extends AbstractMap<String, PyObject> implements AutoCloseable {
    private static final Map<Long, Ref> cache = null;
    public long addr;
    private final Ref ref;

    /* renamed from: com.chaquo.python.PyObject$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 extends AbstractSet<Map.Entry<String, PyObject>> {
        final /* synthetic */ PyObject this$0;

        /* renamed from: com.chaquo.python.PyObject$1$1, reason: invalid class name and collision with other inner class name */
        public class C00151 implements Iterator<Map.Entry<String, PyObject>> {
            int i;
            List<String> keys;
            final /* synthetic */ AnonymousClass1 this$1;

            /* renamed from: com.chaquo.python.PyObject$1$1$1, reason: invalid class name and collision with other inner class name */
            public class C00161 implements Map.Entry<String, PyObject> {
                String key;
                final /* synthetic */ C00151 this$2;

                public C00161(C00151 r2) {
                    this.this$2 = r2;
                    this.key = r2.keys.get(r2.i);
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ String getKey() {
                    return getKey2();
                }

                @Override // java.util.Map.Entry
                /* renamed from: getKey, reason: avoid collision after fix types in other method */
                public String getKey2() {
                    return this.key;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Map.Entry
                public PyObject getValue() {
                    return this.this$2.this$1.this$0.get(this.key);
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ PyObject getValue() {
                    return getValue();
                }

                /* renamed from: setValue, reason: avoid collision after fix types in other method */
                public PyObject setValue2(PyObject r2) {
                    return this.this$2.this$1.this$0.put(this.key, r2);
                }

                @Override // java.util.Map.Entry
                public /* bridge */ /* synthetic */ PyObject setValue(PyObject r1) {
                    return setValue2(r1);
                }
            }

            public C00151(AnonymousClass1 r1) {
                this.this$1 = r1;
                this.keys = PyObject.access$300(r1.this$0);
                this.i = 0;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.i >= this.keys.size()) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // java.util.Iterator
            public /* bridge */ /* synthetic */ Map.Entry<String, PyObject> next() {
                return next2();
            }

            @Override // java.util.Iterator
            /* renamed from: next, reason: avoid collision after fix types in other method */
            public Map.Entry<String, PyObject> next2() {
                if (hasNext() == false) goto L6;
                C00161 r0 = new C00161(this);
                this.i++;
                return r0;
            L6:
                Utils$$ExternalSyntheticBUOutline0.m();
                return null;
            }

            @Override // java.util.Iterator
            public void remove() {
                this.this$1.this$0.remove(this.keys.get(this.i - 1));
            }
        }

        public AnonymousClass1(PyObject r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<String, PyObject>> iterator() {
            return new C00151(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return PyObject.access$300(this.this$0).size();
        }
    }

    /* loaded from: classes4.dex */
    public static final class Ref extends PhantomReference<PyObject> {
        private static final int BATCH_SIZE = 1024;
        private static Ref head;
        private static final ReferenceQueue<PyObject> queue = null;
        private static Thread releaser;
        final long addr;
        long count;
        private Ref next;
        private Ref prev;
        final WeakReference<PyObject> weak;

        /* renamed from: com.chaquo.python.PyObject$Ref$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Ref this$0;

            public AnonymousClass1(Ref r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Ref.access$000();
            }
        }

        static {
            queue = new ReferenceQueue();
        }

        public Ref(PyObject r3) {
            super(r3, queue);
            this.count = 1;
            this.addr = r3.addr;
            this.weak = new WeakReference(r3);
        }

        public static /* synthetic */ void access$000() {
            releaseLoop();
        }

        private static void releaseLoop() {
            long[] r1 = new long[2048];
        L26:
            Reference<? extends PyObject> r2 = queue.remove();     // Catch: InterruptedException -> L23 Throwable -> L18
            Map r3 = PyObject.access$100();     // Catch: InterruptedException -> L23 Throwable -> L18
            monitor-enter(r3);     // Catch: InterruptedException -> L23 Throwable -> L18
            int r4 = 0;
        L24:
            Ref r22 = (Ref) r2;     // Catch: Throwable -> L9
            long r5 = r22.take();     // Catch: Throwable -> L9
            if (r5 == 0) goto L11;
            int r7 = r4 + 1;     // Catch: Throwable -> L9
            r1[r4] = r22.addr;     // Catch: Throwable -> L9
            r4 = r4 + 2;     // Catch: Throwable -> L9
            r1[r7] = r5;     // Catch: Throwable -> L9
        L11:
            if (r4 >= 2048) goto L14;
            r2 = queue.poll();     // Catch: Throwable -> L9
            if (r2 != null) goto L24;
        L14:
            monitor-exit(r3);     // Catch: Throwable -> L9
            if (r4 == 0) goto L26;
            PyObject.access$200(r1, r4 / 2);     // Catch: InterruptedException -> L23 Throwable -> L18
            goto L26
        L9:
            th = move-exception;
            throw th;     // Catch: InterruptedException -> L23 Throwable -> L18
        L18:
            th = move-exception;
            th.printStackTrace();
            goto L26
        }

        public void add() {
            Ref r0 = head;
            this.next = r0;
            if (r0 == null) goto L5;
            r0.prev = this;
        L5:
            head = this;
            if (releaser != null) goto L9;
            Thread r02 = new Thread(new AnonymousClass1(this), "PyObject releaser");
            r02.setDaemon(true);
            r02.start();
            releaser = r02;
            return;
        }

        public long take() {
            clear();
            long r0 = this.count;
            if (r0 == 0) goto L14;
            this.count = 0;
            Ref r2 = this.prev;
            Ref r3 = this.next;
            if (r2 == null) goto L7;
            r2.next = r3;
        L8:
            Ref r32 = this.next;
            if (r32 == null) goto L11;
            r32.prev = r2;
        L11:
            this.next = null;
            this.prev = null;
            if (PyObject.access$100().get(Long.valueOf(this.addr)) != this) goto L14;
            PyObject.access$100().remove(Long.valueOf(this.addr));
            goto L14
        L7:
            head = r3;
        L14:
            return r0;
        }
    }

    static {
        cache = new HashMap();
    }

    private PyObject(long r1) {
        this.addr = r1;
        this.ref = new Ref(this);
    }

    public static PyObject _chaquopyCall(StaticProxy r0, String r1, Object... r2) {
        return fromJava(r0).callAttrThrows(r1, r2);
    L7:
        e = move-exception;
        throw e;
    L4:
        th = move-exception;
        throw new UndeclaredThrowableException(th);
    }

    public static /* synthetic */ Map access$100() {
        return cache;
    }

    public static /* synthetic */ void access$200(long[] r0, int r1) {
        releaseNative(r0, r1);
    }

    public static /* synthetic */ List access$300(PyObject r0) {
        return r0.dir();
    }

    private native long callAttrThrowsNative(String r1, Object... r2);

    private native long callThrowsNative(Object... r1);

    private static native void closeNative(long r0, long r2);

    private native boolean containsKeyNative(String r1);

    private native List<String> dir();

    public static PyObject fromJava(Object r2) {
        return getInstance(fromJavaNative(r2));
    }

    private static native long fromJavaNative(Object r0);

    public static PyObject getInstance(long r5) {
        if (r5 != 0) goto L6;
        return null;
    L6:
        Map<Long, Ref> r0 = cache;
        monitor-enter(r0);
        Ref r1 = r0.get(Long.valueOf(r5));     // Catch: Throwable -> L15
        if (r1 == null) goto L17;
        PyObject r2 = r1.weak.get();     // Catch: Throwable -> L15
        if (r2 == null) goto L17;
        r1.count++;
        monitor-exit(r0);     // Catch: Throwable -> L15
        return r2;
    L17:
        PyObject r12 = new PyObject(r5);     // Catch: Throwable -> L15
        r0.put(Long.valueOf(r5), r12.ref);     // Catch: Throwable -> L15
        r12.ref.add();     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        return r12;
    L15:
        th = move-exception;
        throw th;
    }

    private native long getNative(String r1);

    private native long putNative(String r1, Object r2);

    private static native void releaseNative(long[] r0, int r1);

    private native long removeNative(String r1);

    private native long typeNative();

    public List<PyObject> asList() {
        return new PyList(this);
    }

    public Map<PyObject, PyObject> asMap() {
        return new PyMap(this);
    }

    public Set<PyObject> asSet() {
        return new PySet(this);
    }

    public PyObject call(Object... r1) {
        return callThrows(r1);
    L7:
        e = move-exception;
        throw e;
    L4:
        th = move-exception;
        throw new PyException(th);
    }

    public PyObject callAttr(String r1, Object... r2) {
        return callAttrThrows(r1, r2);
    L7:
        e = move-exception;
        throw e;
    L4:
        th = move-exception;
        throw new PyException(th);
    }

    public PyObject callAttrThrows(String r1, Object... r2) {
        return getInstance(callAttrThrowsNative(r1, r2));
    }

    public PyObject callThrows(Object... r1) {
        return getInstance(callThrowsNative(r1));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.addr != 0) goto L5;
        return;
    L5:
        Map<Long, Ref> r0 = cache;
        monitor-enter(r0);
        long r4 = this.ref.take();     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r4 == 0) goto L12;
        closeNative(this.ref.addr, r4);
    L12:
        this.addr = 0;
        return;
    L14:
        th = move-exception;
        throw th;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object r1) {
        return containsKeyNative((String) r1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object r1) {
        return super.containsValue(fromJava(r1));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, PyObject>> entrySet() {
        return new AnonymousClass1(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public native boolean equals(Object r1);

    @Override // java.util.AbstractMap, java.util.Map
    public PyObject get(Object r1) {
        return getInstance(getNative((String) r1));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object get(Object r1) {
        return get(r1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public native int hashCode();

    public native long id();

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<String> keySet() {
        return super.keySet();
    }

    public PyObject put(String r1, PyObject r2) {
        return put(r1, r2);
    }

    public PyObject put(String r1, Object r2) {
        return getInstance(putNative(r1, r2));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
        return put((String) r1, (PyObject) r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public PyObject remove(Object r1) {
        return getInstance(removeNative((String) r1));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object remove(Object r1) {
        return remove(r1);
    }

    public native String repr();

    public native boolean toBoolean();

    public native byte toByte();

    public native char toChar();

    public native double toDouble();

    public native float toFloat();

    public native int toInt();

    public native <T> T toJava(Class<T> r1);

    public native long toLong();

    public native short toShort();

    @Override // java.util.AbstractMap
    public native String toString();

    public PyObject type() {
        PyObject r2 = getInstance(typeNative());
        Objects.requireNonNull(r2);
        return r2;
    }
}
