package com.android.dx.util;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class ListIntSet implements IntSet {
    final IntList ints;

    /* renamed from: com.android.dx.util.ListIntSet$1, reason: invalid class name */
    public class AnonymousClass1 implements IntIterator {
        private int idx;
        final /* synthetic */ ListIntSet this$0;

        public AnonymousClass1(ListIntSet r1) {
            this.this$0 = r1;
            this.idx = 0;
        }

        @Override // com.android.dx.util.IntIterator
        public boolean hasNext() {
            if (this.idx >= this.this$0.ints.size()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.android.dx.util.IntIterator
        public int next() {
            if (hasNext() == false) goto L6;
            IntList r0 = this.this$0.ints;
            int r1 = this.idx;
            this.idx = r1 + 1;
            return r0.get(r1);
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return 0;
        }
    }

    public ListIntSet() {
        IntList r0 = new IntList();
        this.ints = r0;
        r0.sort();
    }

    @Override // com.android.dx.util.IntSet
    public void add(int r2) {
        int r0 = this.ints.binarysearch(r2);
        if (r0 >= 0) goto L6;
        this.ints.insert(-(r0 + 1), r2);
        return;
    }

    @Override // com.android.dx.util.IntSet
    public int elements() {
        return this.ints.size();
    }

    @Override // com.android.dx.util.IntSet
    public boolean has(int r1) {
        if (this.ints.indexOf(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.util.IntSet
    public IntIterator iterator() {
        return new AnonymousClass1(this);
    }

    @Override // com.android.dx.util.IntSet
    public void merge(IntSet r7) {
        int r1 = 0;
        if ((r7 instanceof ListIntSet) == false) goto L22;
        ListIntSet r72 = (ListIntSet) r7;
        int r0 = this.ints.size();
        int r2 = r72.ints.size();
        int r3 = 0;
    L5:
        if (r1 >= r2) goto L17;
        if (r3 >= r0) goto L17;
    L7:
        if (r1 >= r2) goto L11;
        if (r72.ints.get(r1) >= this.ints.get(r3)) goto L11;
        add(r72.ints.get(r1));
        r1 = r1 + 1;
    L11:
        if (r1 == r2) goto L17;
    L13:
        if (r3 >= r0) goto L5;
        if (r72.ints.get(r1) < this.ints.get(r3)) goto L5;
        r3 = r3 + 1;
    L17:
        if (r1 >= r2) goto L19;
        add(r72.ints.get(r1));
        r1 = r1 + 1;
        goto L17
    L19:
        this.ints.sort();
        return;
    L22:
        if ((r7 instanceof BitIntSet) == false) goto L29;
        BitIntSet r73 = (BitIntSet) r7;
    L24:
        IntList r02 = this.ints;
        if (r1 < 0) goto L27;
        r02.add(r1);
        r1 = Bits.findFirst(r73.bits, r1 + 1);
        goto L24
    L27:
        r02.sort();
        return;
    L29:
        IntIterator r74 = r7.iterator();
    L31:
        if (r74.hasNext() == false) goto L33;
        add(r74.next());
        goto L31
    }

    @Override // com.android.dx.util.IntSet
    public void remove(int r2) {
        int r22 = this.ints.indexOf(r2);
        if (r22 < 0) goto L6;
        this.ints.removeIndex(r22);
        return;
    }

    public String toString() {
        return this.ints.toString();
    }
}
