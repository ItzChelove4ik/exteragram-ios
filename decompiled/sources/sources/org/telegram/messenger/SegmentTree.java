package org.telegram.messenger;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes5.dex */
public class SegmentTree {
    private long[] array;
    private Node[] heap;

    public static class Node {
        int from;
        long max;
        long min;
        Integer pendingVal;
        long sum;
        int to;

        public Node() {
            this.pendingVal = null;
        }

        public int size() {
            return (this.to - this.from) + 1;
        }
    }

    public SegmentTree(long[] r7) {
        this.array = r7;
        if (r7.length >= 30) goto L5;
        return;
    L5:
        this.heap = new Node[(int) (Math.pow(2.0d, Math.floor((Math.log(r7.length) / Math.log(2.0d)) + 1.0d)) * 2.0d)];
        build(1, 0, r7.length);
    }

    private void build(int r8, int r9, int r10) {
        this.heap[r8] = new Node();
        Node r0 = this.heap[r8];
        r0.from = r9;
        r0.to = (r9 + r10) - 1;
        if (r10 != 1) goto L6;
        long r82 = this.array[r9];
        r0.sum = r82;
        r0.max = r82;
        r0.min = r82;
        return;
    L6:
        int r02 = r8 * 2;
        int r1 = r10 / 2;
        build(r02, r9, r1);
        int r2 = r02 + 1;
        build(r2, r9 + r1, r10 - r1);
        Node[] r92 = this.heap;
        Node r102 = r92[r8];
        Node r12 = r92[r02];
        long r3 = r12.sum;
        Node r93 = r92[r2];
        r102.sum = r3 + r93.sum;
        r102.max = Math.max(r12.max, r93.max);
        Node[] r7 = this.heap;
        Node r83 = r7[r8];
        r83.min = Math.min(r7[r02].min, r7[r2].min);
    }

    private void change(Node r3, int r4) {
        r3.pendingVal = Integer.valueOf(r4);
        r3.sum = r3.size() * r4;
        long r0 = r4;
        r3.max = r0;
        r3.min = r0;
        this.array[r3.from] = r0;
    }

    private boolean contains(int r1, int r2, int r3, int r4) {
        if (r3 < r1) goto L6;
        if (r4 > r2) goto L8;
        return true;
    L8:
        return false;
    L6:
        return false;
    }

    private boolean intersects(int r1, int r2, int r3, int r4) {
        if (r1 > r3) goto L4;
        if (r2 < r3) goto L4;
        return true;
    L4:
        if (r1 < r3) goto L8;
        if (r1 > r4) goto L11;
        return true;
    L11:
        return false;
    L8:
        return false;
    }

    private void propagate(int r4) {
        Node[] r0 = this.heap;
        Node r1 = r0[r4];
        Integer r2 = r1.pendingVal;
        if (r2 == null) goto L6;
        int r42 = r4 * 2;
        change(r0[r42], r2.intValue());
        change(this.heap[r42 + 1], r1.pendingVal.intValue());
        r1.pendingVal = null;
        return;
    }

    private long rMaxQ(int r4, int r5, int r6) {
        Node r0 = this.heap[r4];
        if (r0.pendingVal == null) goto L9;
        if (contains(r0.from, r0.to, r5, r6) == false) goto L9;
        return r0.pendingVal.intValue();
    L9:
        if (contains(r5, r6, r0.from, r0.to) == false) goto L13;
        return this.heap[r4].max;
    L13:
        if (intersects(r5, r6, r0.from, r0.to) == false) goto L16;
        propagate(r4);
        int r42 = r4 * 2;
        return Math.max(rMaxQ(r42, r5, r6), rMaxQ(r42 + 1, r5, r6));
    L16:
        return 0;
    }

    private long rMinQ(int r4, int r5, int r6) {
        Node r0 = this.heap[r4];
        if (r0.pendingVal == null) goto L9;
        if (contains(r0.from, r0.to, r5, r6) == false) goto L9;
        return r0.pendingVal.intValue();
    L9:
        if (contains(r5, r6, r0.from, r0.to) == false) goto L13;
        return this.heap[r4].min;
    L13:
        if (intersects(r5, r6, r0.from, r0.to) == false) goto L16;
        propagate(r4);
        int r42 = r4 * 2;
        return Math.min(rMinQ(r42, r5, r6), rMinQ(r42 + 1, r5, r6));
    L16:
        return 2147483647L;
    }

    public long rMaxQ(int r6, int r7) {
        long[] r0 = this.array;
        if (r0.length >= 30) goto L17;
        if (r6 >= 0) goto L7;
        r6 = 0;
    L7:
        if (r7 <= (r0.length - 1)) goto L9;
        r7 = r0.length - 1;
    L9:
        long r02 = Long.MIN_VALUE;
    L10:
        if (r6 > r7) goto L15;
        long r3 = this.array[r6];
        if (r3 <= r02) goto L14;
        r02 = r3;
    L14:
        r6 = r6 + 1;
        goto L10
    L15:
        return r02;
    L17:
        return rMaxQ(1, r6, r7);
    }

    public long rMinQ(int r6, int r7) {
        long[] r0 = this.array;
        if (r0.length >= 30) goto L17;
        if (r6 >= 0) goto L7;
        r6 = 0;
    L7:
        if (r7 <= (r0.length - 1)) goto L9;
        r7 = r0.length - 1;
    L9:
        long r02 = LongCompanionObject.MAX_VALUE;
    L10:
        if (r6 > r7) goto L15;
        long r3 = this.array[r6];
        if (r3 >= r02) goto L14;
        r02 = r3;
    L14:
        r6 = r6 + 1;
        goto L10
    L15:
        return r02;
    L17:
        return rMinQ(1, r6, r7);
    }
}
