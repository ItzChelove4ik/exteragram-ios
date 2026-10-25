package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public class Joiner {
    private final String separator;

    private Joiner(String r1) {
        this.separator = (String) Preconditions.checkNotNull(r1);
    }

    public static Joiner on(char r1) {
        return new Joiner(String.valueOf(r1));
    }

    public static Joiner on(String r1) {
        return new Joiner(r1);
    }

    public <A extends Appendable> A appendTo(A r2, Iterator<?> r3) {
        Preconditions.checkNotNull(r2);
        if (r3.hasNext() == false) goto L8;
        r2.append(toString(r3.next()));
    L6:
        if (r3.hasNext() == false) goto L8;
        r2.append(this.separator);
        r2.append(toString(r3.next()));
    L8:
        return r2;
    }

    public final StringBuilder appendTo(StringBuilder r1, Iterable<?> r2) {
        return appendTo(r1, r2.iterator());
    }

    public final StringBuilder appendTo(StringBuilder r1, Iterator<?> r2) {
        appendTo(r1, r2);     // Catch: IOException -> L4
        return r1;
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public String join(Iterable<?> r1) {
        return join(r1.iterator());
    }

    public final String join(Iterator<?> r2) {
        return appendTo(new StringBuilder(), r2).toString();
    }

    public CharSequence toString(Object r1) {
        Objects.requireNonNull(r1);
        if ((r1 instanceof CharSequence) == false) goto L7;
        return (CharSequence) r1;
    L7:
        return r1.toString();
    }
}
