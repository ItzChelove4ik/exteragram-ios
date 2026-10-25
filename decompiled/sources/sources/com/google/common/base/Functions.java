package com.google.common.base;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes5.dex */
public abstract class Functions {

    public static final class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private final E value;

        public ConstantFunction(E r1) {
            this.value = r1;
        }

        @Override // com.google.common.base.Function
        public E apply(Object r1) {
            return this.value;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object r2) {
            if ((r2 instanceof ConstantFunction) == true) goto L5;
            return false;
        L5:
            return Objects.equals(this.value, ((ConstantFunction) r2).value);
        }

        public int hashCode() {
            E r0 = this.value;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    public static <E> Function<Object, E> constant(E r1) {
        return new ConstantFunction(r1);
    }
}
