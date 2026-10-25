package com.android.dx.util;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class BitIntSet implements IntSet {
    int[] bits;

    /* renamed from: com.android.dx.util.BitIntSet$1, reason: invalid class name */
    public class AnonymousClass1 implements IntIterator {
        private int idx;
        final /* synthetic */ BitIntSet this$0;

        public AnonymousClass1(BitIntSet r2) {
            this.this$0 = r2;
            this.idx = Bits.findFirst(r2.bits, 0);
        }

        @Override // com.android.dx.util.IntIterator
        public boolean hasNext() {
            if (this.idx < 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.android.dx.util.IntIterator
        public int next() {
            if (hasNext() == false) goto L6;
            int r0 = this.idx;
            this.idx = Bits.findFirst(this.this$0.bits, r0 + 1);
            return r0;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return 0;
        }
    }

    public BitIntSet(int r1) {
        this.bits = Bits.makeBitSet(r1);
    }

    private void ensureCapacity(int r4) {
        if (r4 < Bits.getMax(this.bits)) goto L6;
        int[] r42 = Bits.makeBitSet(Math.max(r4 + 1, Bits.getMax(this.bits) * 2));
        int[] r0 = this.bits;
        System.arraycopy(r0, 0, r42, 0, r0.length);
        this.bits = r42;
        return;
    }

    @Override // com.android.dx.util.IntSet
    public void add(int r2) {
        ensureCapacity(r2);
        Bits.set(this.bits, r2, true);
    }

    @Override // com.android.dx.util.IntSet
    public int elements() {
        return Bits.bitCount(this.bits);
    }

    @Override // com.android.dx.util.IntSet
    public boolean has(int r2) {
        if (r2 < Bits.getMax(this.bits)) goto L5;
        return false;
    L5:
        if (Bits.get(this.bits, r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.android.dx.util.IntSet
    public IntIterator iterator() {
        return new AnonymousClass1(this);
    }

    @Override // com.android.dx.util.IntSet
    public void merge(IntSet r5) {
        if ((r5 instanceof BitIntSet) == false) goto L7;
        BitIntSet r52 = (BitIntSet) r5;
        ensureCapacity(Bits.getMax(r52.bits) + 1);
        Bits.or(this.bits, r52.bits);
        return;
    L7:
        if ((r5 instanceof ListIntSet) == false) goto L15;
        ListIntSet r53 = (ListIntSet) r5;
        int r0 = r53.ints.size();
        if (r0 <= 0) goto L11;
        ensureCapacity(r53.ints.get(r0 - 1));
    L11:
        int r02 = 0;
    L13:
        if (r02 >= r53.ints.size()) goto L19;
        Bits.set(this.bits, r53.ints.get(r02), true);
        r02 = r02 + 1;
        goto L13
    L19:
        return;
    L15:
        IntIterator r54 = r5.iterator();
    L17:
        if (r54.hasNext() == false) goto L22;
        add(r54.next());
        goto L17
    }

    @Override // com.android.dx.util.IntSet
    public void remove(int r2) {
        if (r2 >= Bits.getMax(this.bits)) goto L6;
        Bits.set(this.bits, r2, false);
        return;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("{");
        int r1 = Bits.findFirst(this.bits, 0);
        boolean r3 = true;
    L3:
        if (r1 < 0) goto L7;
        if (r3 == true) goto L6;
        r0.append(", ");
    L6:
        r0.append(r1);
        r1 = Bits.findFirst(this.bits, r1 + 1);
        r3 = false;
        goto L3
    L7:
        r0.append('}');
        return r0.toString();
    }
}
