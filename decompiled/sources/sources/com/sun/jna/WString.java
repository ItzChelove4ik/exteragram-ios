package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public final class WString implements CharSequence, Comparable {
    private String string;

    public WString(String r1) {
        if (r1 == null) goto L6;
        this.string = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("String initializer must be non-null");
        throw null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int r1) {
        return toString().charAt(r1);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object r1) {
        return toString().compareTo(r1.toString());
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof WString) == true) goto L5;
        return false;
    L5:
        if (toString().equals(r2.toString()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return toString().length();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int r1, int r2) {
        return toString().subSequence(r1, r2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.string;
    }
}
