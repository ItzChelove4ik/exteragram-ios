package com.google.common.base;

import c.f$$ExternalSyntheticBUOutline2;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class MoreObjects {

    /* renamed from: com.google.common.base.MoreObjects$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitEmptyValues;
        private boolean omitNullValues;

        public static class ValueHolder {
            String name;
            ValueHolder next;
            Object value;

            public ValueHolder() {
            }
        }

        private ToStringHelper(String r2) {
            ValueHolder r0 = new ValueHolder();
            this.holderHead = r0;
            this.holderTail = r0;
            this.omitNullValues = false;
            this.omitEmptyValues = false;
            this.className = (String) Preconditions.checkNotNull(r2);
        }

        public /* synthetic */ ToStringHelper(String r1, AnonymousClass1 r2) {
            this(r1);
        }

        private ValueHolder addHolder() {
            ValueHolder r0 = new ValueHolder();
            this.holderTail.next = r0;
            this.holderTail = r0;
            return r0;
        }

        private ToStringHelper addHolder(Object r2) {
            addHolder().value = r2;
            return this;
        }

        private static boolean isEmpty(Object r3) {
            if ((r3 instanceof CharSequence) == false) goto L9;
            if (((CharSequence) r3).length() != 0) goto L7;
            return true;
        L7:
            return false;
        L9:
            if ((r3 instanceof Collection) == false) goto L13;
            return ((Collection) r3).isEmpty();
        L13:
            if ((r3 instanceof Map) == false) goto L17;
            return ((Map) r3).isEmpty();
        L17:
            if ((r3 instanceof Optional) == false) goto L21;
            return !((Optional) r3).isPresent();
        L21:
            if (r3.getClass().isArray() == true) goto L23;
        L25:
            return false;
        L23:
            if (Array.getLength(r3) != 0) goto L25;
            return true;
        }

        public ToStringHelper addValue(Object r1) {
            return addHolder(r1);
        }

        public String toString() {
            boolean r0 = this.omitNullValues;
            boolean r1 = this.omitEmptyValues;
            StringBuilder r2 = new StringBuilder(32);
            r2.append(this.className);
            r2.append('{');
            ValueHolder r6 = this.holderHead.next;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L3:
            if (r6 == null) goto L21;
            Object r4 = r6.value;
            if (r4 != null) goto L8;
            if (r0 == true) goto L20;
        L11:
            r2.append(r3);
            String r32 = r6.name;
            if (r32 == null) goto L14;
            r2.append(r32);
            r2.append(SignatureVisitor.INSTANCEOF);
        L14:
            if (r4 != null) goto L16;
        L18:
            r2.append(r4);
        L19:
            r3 = ", ";
            goto L20
        L16:
            if (r4.getClass().isArray() == false) goto L18;
            String r33 = Arrays.deepToString(new Object[]{r4});
            r2.append(r33, 1, r33.length() - 1);
        L20:
            r6 = r6.next;
            goto L3
        L8:
            if (r1 == false) goto L11;
            if (isEmpty(r4) == true) goto L20;
        L21:
            r2.append('}');
            return r2.toString();
        }
    }

    public static <T> T firstNonNull(T r0, T r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        if (r1 == null) goto L6;
        return r1;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Both parameters are null");
        return null;
    }

    public static ToStringHelper toStringHelper(Object r2) {
        return new ToStringHelper(r2.getClass().getSimpleName(), null);
    }
}
