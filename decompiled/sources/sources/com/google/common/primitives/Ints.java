package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class Ints extends IntsMethodsForWeb {

    public static final class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        final int[] array;
        final int end;
        final int start;

        public IntArrayAsList(int[] r3) {
            this(r3, 0, r3.length);
        }

        public IntArrayAsList(int[] r1, int r2, int r3) {
            this.array = r1;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object r3) {
            if ((r3 instanceof Integer) == true) goto L5;
            return false;
        L5:
            if (Ints.access$000(this.array, ((Integer) r3).intValue(), this.start, this.end) == (-1)) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object r8) {
            if (r8 != this) goto L6;
            return true;
        L6:
            if ((r8 instanceof IntArrayAsList) == false) goto L18;
            IntArrayAsList r82 = (IntArrayAsList) r8;
            int r1 = size();
            if (r82.size() == r1) goto L10;
            return false;
        L10:
            int r2 = 0;
        L11:
            if (r2 >= r1) goto L16;
            if (this.array[this.start + r2] != r82.array[r82.start + r2]) goto L14;
            r2 = r2 + 1;
            goto L11
        L14:
            return false;
        L16:
            return true;
        L18:
            return super.equals(r8);
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int r2) {
            Preconditions.checkElementIndex(r2, size());
            return Integer.valueOf(this.array[this.start + r2]);
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
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

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object r4) {
            if ((r4 instanceof Integer) == false) goto L8;
            int r42 = Ints.access$000(this.array, ((Integer) r4).intValue(), this.start, this.end);
            if (r42 >= 0) goto L7;
            return -1;
        L7:
            return r42 - this.start;
        L8:
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object r4) {
            if ((r4 instanceof Integer) == false) goto L8;
            int r42 = Ints.access$100(this.array, ((Integer) r4).intValue(), this.start, this.end);
            if (r42 >= 0) goto L7;
            return -1;
        L7:
            return r42 - this.start;
        L8:
            return -1;
        }

        public Integer set(int r3, Integer r4) {
            Preconditions.checkElementIndex(r3, size());
            int[] r0 = this.array;
            int r2 = this.start;
            int r1 = r0[r2 + r3];
            r0[r2 + r3] = ((Integer) Preconditions.checkNotNull(r4)).intValue();
            return Integer.valueOf(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object set(int r1, Object r2) {
            return set(r1, (Integer) r2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List
        public Spliterator.OfInt spliterator() {
            return Spliterators.spliterator(this.array, this.start, this.end, 0);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List
        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return spliterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Integer> subList(int r3, int r4) {
            Preconditions.checkPositionIndexes(r3, r4, size());
            if (r3 == r4) goto L5;
            int[] r1 = this.array;
            int r2 = this.start;
            return new IntArrayAsList(r1, r3 + r2, r2 + r4);
        L5:
            return Collections.EMPTY_LIST;
        }

        public int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder r0 = new StringBuilder(size() * 5);
            r0.append('[');
            r0.append(this.array[this.start]);
            int r1 = this.start;
        L3:
            r1 = r1 + 1;
            if (r1 >= this.end) goto L6;
            r0.append(", ");
            r0.append(this.array[r1]);
            goto L3
        L6:
            r0.append(']');
            return r0.toString();
        }
    }

    public static /* synthetic */ int access$000(int[] r0, int r1, int r2, int r3) {
        return indexOf(r0, r1, r2, r3);
    }

    public static /* synthetic */ int access$100(int[] r0, int r1, int r2, int r3) {
        return lastIndexOf(r0, r1, r2, r3);
    }

    public static List<Integer> asList(int... r1) {
        if (r1.length != 0) goto L7;
        return Collections.EMPTY_LIST;
    L7:
        return new IntArrayAsList(r1);
    }

    private static int checkNoOverflow(long r3) {
        int r0 = (int) r3;
        if (r3 != r0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "the total number of elements (%s) in the arrays must fit in an int", r3);
        return r0;
    L5:
        r1 = false;
        goto L6
    }

    public static int checkedCast(long r3) {
        int r0 = (int) r3;
        if (r0 != r3) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "Out of range: %s", r3);
        return r0;
    L5:
        r1 = false;
        goto L6
    }

    public static int[] concat(int[]... r7) {
        long r1 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r7.length) goto L5;
        r1 = r1 + r7[r4].length;
        r4 = r4 + 1;
        goto L3
    L5:
        int[] r0 = new int[checkNoOverflow(r1)];
        int r12 = r7.length;
        int r2 = 0;
        int r42 = 0;
    L6:
        if (r2 >= r12) goto L8;
        int[] r5 = r7[r2];
        System.arraycopy(r5, 0, r0, r42, r5.length);
        r42 = r42 + r5.length;
        r2 = r2 + 1;
        goto L6
    L8:
        return r0;
    }

    public static int constrainToRange(int r2, int r3, int r4) {
        if (r3 > r4) goto L4;
        boolean r0 = true;
    L5:
        Preconditions.checkArgument(r0, "min (%s) must be less than or equal to max (%s)", r3, r4);
        return Math.min(Math.max(r2, r3), r4);
    L4:
        r0 = false;
        goto L5
    }

    public static int fromByteArray(byte[] r6) {
        if (r6.length < 4) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "array too small: %s < %s", r6.length, 4);
        return fromBytes(r6[0], r6[1], r6[2], r6[3]);
    L5:
        r0 = false;
        goto L6
    }

    public static int fromBytes(byte r0, byte r1, byte r2, byte r3) {
        return (((r0 << 24) | ((r1 & UByte.MAX_VALUE) << 16)) | ((r2 & UByte.MAX_VALUE) << 8)) | (r3 & UByte.MAX_VALUE);
    }

    public static int indexOf(int[] r2, int r3) {
        return indexOf(r2, r3, 0, r2.length);
    }

    private static int indexOf(int[] r1, int r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L7;
        if (r1[r3] == r2) goto L5;
        r3 = r3 + 1;
        goto L2
    L5:
        return r3;
    L7:
        return -1;
    }

    private static int lastIndexOf(int[] r1, int r2, int r3, int r4) {
        int r42 = r4 - 1;
    L3:
        if (r42 < r3) goto L8;
        if (r1[r42] == r2) goto L6;
        r42 = r42 - 1;
        goto L3
    L6:
        return r42;
    L8:
        return -1;
    }

    public static int saturatedCast(long r2) {
        if (r2 <= 2147483647L) goto L7;
        return Integer.MAX_VALUE;
    L7:
        if (r2 >= (-2147483648L)) goto L11;
        return Integer.MIN_VALUE;
    L11:
        return (int) r2;
    }

    public static int[] toArray(Collection<? extends Number> r4) {
        if ((r4 instanceof IntArrayAsList) == true) goto L5;
        Object[] r42 = r4.toArray();
        int r0 = r42.length;
        int[] r1 = new int[r0];
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        r1[r2] = ((Number) Preconditions.checkNotNull(r42[r2])).intValue();
        r2 = r2 + 1;
        goto L7
    L9:
        return r1;
    L5:
        return ((IntArrayAsList) r4).toIntArray();
    }

    public static Integer tryParse(String r1) {
        return tryParse(r1, 10);
    }

    public static Integer tryParse(String r4, int r5) {
        Long r42 = Longs.tryParse(r4, r5);
        if (r42 != null) goto L5;
        return null;
    L5:
        if (r42.longValue() == r42.intValue()) goto L8;
        return null;
    L8:
        return Integer.valueOf(r42.intValue());
    }
}
