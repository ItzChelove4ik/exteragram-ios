package com.google.firebase.components;

import java.lang.annotation.Annotation;

/* loaded from: classes.dex */
public final class Qualified<T> {
    private final Class<? extends Annotation> qualifier;
    private final Class<T> type;

    public @interface Unqualified {
    }

    public Qualified(Class<? extends Annotation> r1, Class<T> r2) {
        this.qualifier = r1;
        this.type = r2;
    }

    public static <T> Qualified<T> qualified(Class<? extends Annotation> r1, Class<T> r2) {
        return new Qualified(r1, r2);
    }

    public static <T> Qualified<T> unqualified(Class<T> r2) {
        return new Qualified(Unqualified.class, r2);
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L8;
    L15:
        return false;
    L8:
        if (Qualified.class != r4.getClass()) goto L15;
        Qualified r42 = (Qualified) r4;
        if (this.type.equals(r42.type) == true) goto L14;
        return false;
    L14:
        return this.qualifier.equals(r42.qualifier);
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.qualifier.hashCode();
    }

    public String toString() {
        if (this.qualifier != Unqualified.class) goto L7;
        return this.type.getName();
    L7:
        return "@" + this.qualifier.getName() + " " + this.type.getName();
    }
}
