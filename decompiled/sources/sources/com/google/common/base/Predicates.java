package com.google.common.base;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class Predicates {

    /* renamed from: com.google.common.base.Predicates$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class AndPredicate<T> implements Predicate<T>, Serializable {
        private final List<Predicate<? super T>> components;

        private AndPredicate(List<Predicate<? super T>> r1) {
            this.components = r1;
        }

        public /* synthetic */ AndPredicate(List r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T r4) {
            int r1 = 0;
        L4:
            if (r1 >= this.components.size()) goto L9;
            if (this.components.get(r1).apply(r4) == false) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return false;
        L9:
            return true;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof AndPredicate) == true) goto L5;
            return false;
        L5:
            return this.components.equals(((AndPredicate) r2).components);
        }

        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        public String toString() {
            return Predicates.access$800("and", this.components);
        }
    }

    public static /* synthetic */ String access$800(String r0, Iterable r1) {
        return toStringHelper(r0, r1);
    }

    public static <T> Predicate<T> and(Predicate<? super T> r3, Predicate<? super T> r4) {
        return new AndPredicate(Arrays.asList(new Predicate[]{(Predicate) Preconditions.checkNotNull(r3), (Predicate) Preconditions.checkNotNull(r4)}), null);
    }

    private static String toStringHelper(String r2, Iterable<?> r3) {
        StringBuilder r0 = new StringBuilder("Predicates.");
        r0.append(r2);
        r0.append('(');
        Iterator<?> r22 = r3.iterator();
        boolean r32 = true;
    L4:
        if (r22.hasNext() == false) goto L9;
        Object r1 = r22.next();
        if (r32 == true) goto L8;
        r0.append(',');
    L8:
        r0.append(r1);
        r32 = false;
        goto L4
    L9:
        r0.append(')');
        return r0.toString();
    }
}
