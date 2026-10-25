package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class ImmutableIntArray implements Serializable {
    private static final ImmutableIntArray EMPTY = null;
    private final int[] array;
    private final int end;
    private final transient int start;

    static {
        EMPTY = new ImmutableIntArray(new int[0]);
    }

    private ImmutableIntArray(int[] r3) {
        this(r3, 0, r3.length);
    }

    private ImmutableIntArray(int[] r1, int r2, int r3) {
        this.array = r1;
        this.start = r2;
        this.end = r3;
    }

    public static ImmutableIntArray copyOf(int[] r2) {
        if (r2.length != 0) goto L7;
        return EMPTY;
    L7:
        return new ImmutableIntArray(Arrays.copyOf(r2, r2.length));
    }

    public static ImmutableIntArray of() {
        return EMPTY;
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof ImmutableIntArray) == true) goto L8;
        return false;
    L8:
        ImmutableIntArray r62 = (ImmutableIntArray) r6;
        if (length() == r62.length()) goto L11;
        return false;
    L11:
        int r1 = 0;
    L13:
        if (r1 >= length()) goto L18;
        if (get(r1) != r62.get(r1)) goto L16;
        r1 = r1 + 1;
        goto L13
    L16:
        return false;
    L18:
        return true;
    }

    public int get(int r2) {
        Preconditions.checkElementIndex(r2, length());
        return this.array[this.start + r2];
    }

    public int hashCode() {
        int r0 = this.start;
        int r1 = 1;
    L4:
        if (r0 >= this.end) goto L6;
        r1 = (r1 * 31) + Integer.hashCode(this.array[r0]);
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    public boolean isEmpty() {
        if (this.end != this.start) goto L6;
        return true;
    L6:
        return false;
    }

    public int length() {
        return this.end - this.start;
    }

    public String toString() {
        if (isEmpty() == false) goto L6;
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    L6:
        StringBuilder r0 = new StringBuilder(length() * 5);
        r0.append('[');
        r0.append(this.array[this.start]);
        int r1 = this.start;
    L7:
        r1 = r1 + 1;
        if (r1 >= this.end) goto L10;
        r0.append(", ");
        r0.append(this.array[r1]);
        goto L7
    L10:
        r0.append(']');
        return r0.toString();
    }
}
