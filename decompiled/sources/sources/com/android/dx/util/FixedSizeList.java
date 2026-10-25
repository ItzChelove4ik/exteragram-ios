package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes.dex */
public class FixedSizeList extends MutabilityControl implements ToHuman {
    private Object[] arr;

    public FixedSizeList(int r2) {
        if (r2 == 0) goto L4;
        boolean r0 = true;
    L5:
        super(r0);
        this.arr = new Object[r2];     // Catch: NegativeArraySizeException -> L8
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("size < 0");
        throw null;
    L4:
        r0 = false;
        goto L5
    }

    private Object throwIndex(int r1) {
        if (r1 >= 0) goto L6;
        throw new IndexOutOfBoundsException("n < 0");
    L6:
        throw new IndexOutOfBoundsException("n >= size()");
    }

    private String toString0(String r4, String r5, String r6, boolean r7) {
        int r0 = this.arr.length;
        StringBuilder r1 = new StringBuilder((r0 * 10) + 10);
        if (r4 == null) goto L5;
        r1.append(r4);
    L5:
        int r42 = 0;
    L6:
        if (r42 >= r0) goto L15;
        if (r42 == 0) goto L10;
        if (r5 == null) goto L10;
        r1.append(r5);
    L10:
        Object[] r2 = this.arr;
        if (r7 == false) goto L13;
        r1.append(((ToHuman) r2[r42]).toHuman());
    L14:
        r42 = r42 + 1;
        goto L6
    L13:
        r1.append(r2[r42]);
        goto L14
    L15:
        if (r6 == null) goto L18;
        r1.append(r6);
    L18:
        return r1.toString();
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (getClass() == r3.getClass()) goto L10;
        return false;
    L10:
        return Arrays.equals(this.arr, ((FixedSizeList) r3).arr);
    }

    public final Object get0(int r4) {
        Object r1 = this.arr[r4];     // Catch: ArrayIndexOutOfBoundsException -> L8
        if (r1 == null) goto L7;
        return r1;
    L7:
        throw new NullPointerException("unset: " + r4);     // Catch: ArrayIndexOutOfBoundsException -> L8
    L9:
        return throwIndex(r4);
    }

    public final Object getOrNull0(int r1) {
        return this.arr[r1];
    }

    public int hashCode() {
        return Arrays.hashCode(this.arr);
    }

    public final void set0(int r2, Object r3) {
        throwIfImmutable();
        this.arr[r2] = r3;     // Catch: ArrayIndexOutOfBoundsException -> L5
        return;
    L5:
        throwIndex(r2);
    }

    public void shrinkToFit() {
        int r0 = this.arr.length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.arr[r2] == null) goto L7;
        r3 = r3 + 1;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r0 == r3) goto L27;
        throwIfImmutable();
        Object[] r22 = new Object[r3];
        int r4 = 0;
    L11:
        if (r1 >= r0) goto L16;
        Object r5 = this.arr[r1];
        if (r5 == null) goto L15;
        r22[r4] = r5;
        r4 = r4 + 1;
    L15:
        r1 = r1 + 1;
        goto L11
    L16:
        this.arr = r22;
        if (r3 != 0) goto L26;
        setImmutable();
        return;
    L26:
        return;
    }

    public final int size() {
        return this.arr.length;
    }

    public String toHuman() {
        String r0 = getClass().getName();
        return toString0(r0.substring(r0.lastIndexOf(46) + 1).concat("{"), ", ", "}", true);
    }

    public String toHuman(String r2, String r3, String r4) {
        return toString0(r2, r3, r4, true);
    }

    public String toString() {
        String r0 = getClass().getName();
        return toString0(r0.substring(r0.lastIndexOf(46) + 1).concat("{"), ", ", "}", false);
    }

    public String toString(String r2, String r3, String r4) {
        return toString0(r2, r3, r4, false);
    }
}
