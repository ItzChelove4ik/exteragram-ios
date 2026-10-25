package com.google.android.datatransport;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public final class Encoding {
    private final String name;

    private Encoding(String r1) {
        if (r1 == null) goto L6;
        this.name = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("name is null");
        throw null;
    }

    public static Encoding of(String r1) {
        return new Encoding(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Encoding) == true) goto L10;
        return false;
    L10:
        return this.name.equals(((Encoding) r2).name);
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Encoding{name=\"" + this.name + "\"}";
    }
}
