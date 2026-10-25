package com.google.common.base;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes5.dex */
public abstract class Suppliers {

    public static final class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        private transient Object lock;
        transient T value;

        public MemoizingSupplier(Supplier<T> r2) {
            this.lock = new Object();
            this.delegate = (Supplier) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (this.initialized == true) goto L18;
            Object r0 = this.lock;
            monitor-enter(r0);
        L11:
            th = move-exception;
            throw th;
        L7:
            if (this.initialized == true) goto L13;
            T r1 = this.delegate.get();     // Catch: Throwable -> L11
            this.value = r1;     // Catch: Throwable -> L11
            this.initialized = true;     // Catch: Throwable -> L11
            monitor-exit(r0);     // Catch: Throwable -> L11
            return r1;
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L11
        L18:
            return (T) NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("Suppliers.memoize(");
            if (this.initialized == false) goto L5;
            Object r3 = "<supplier that returned " + this.value + ">";
        L6:
            r0.append(r3);
            r0.append(")");
            return r0.toString();
        L5:
            r3 = this.delegate;
            goto L6
        }
    }

    public static final class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        private volatile Supplier<T> delegate;
        private final Object lock;
        private T value;

        public NonSerializableMemoizingSupplier(Supplier<T> r2) {
            this.lock = new Object();
            this.delegate = (Supplier) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (this.delegate == null) goto L18;
            Object r0 = this.lock;
            monitor-enter(r0);
        L11:
            th = move-exception;
            throw th;
        L7:
            if (this.delegate == null) goto L13;
            T r1 = this.delegate.get();     // Catch: Throwable -> L11
            this.value = r1;     // Catch: Throwable -> L11
            this.delegate = null;     // Catch: Throwable -> L11
            monitor-exit(r0);     // Catch: Throwable -> L11
            return r1;
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L11
        L18:
            return (T) NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object r0 = this.delegate;
            StringBuilder r1 = new StringBuilder("Suppliers.memoize(");
            if (r0 != null) goto L5;
            r0 = "<supplier that returned " + this.value + ">";
        L5:
            r1.append(r0);
            r1.append(")");
            return r1.toString();
        }
    }

    public static final class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        final T instance;

        public SupplierOfInstance(T r1) {
            this.instance = r1;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof SupplierOfInstance) == true) goto L5;
            return false;
        L5:
            return Objects.equals(this.instance, ((SupplierOfInstance) r2).instance);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.instance;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.instance});
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.instance + ")";
        }
    }

    public static <T> Supplier<T> memoize(Supplier<T> r1) {
        if ((r1 instanceof NonSerializableMemoizingSupplier) == false) goto L5;
        return r1;
    L5:
        if ((r1 instanceof MemoizingSupplier) == false) goto L8;
        return r1;
    L8:
        if ((r1 instanceof Serializable) == false) goto L12;
        return new MemoizingSupplier(r1);
    L12:
        return new NonSerializableMemoizingSupplier(r1);
    }

    public static <T> Supplier<T> ofInstance(T r1) {
        return new SupplierOfInstance(r1);
    }
}
