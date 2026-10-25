package com.android.dx.util;

import java.util.Arrays;

/* loaded from: classes.dex */
public class LabeledList extends FixedSizeList {
    private final IntList labelToIndex;

    public LabeledList(int r2) {
        super(r2);
        this.labelToIndex = new IntList(r2);
    }

    public LabeledList(LabeledList r4) {
        super(r4.size());
        this.labelToIndex = r4.labelToIndex.mutableCopy();
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Object r2 = r4.get0(r1);
        if (r2 == null) goto L7;
        set0(r1, r2);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void addLabelIndex(int r5, int r6) {
        int r0 = this.labelToIndex.size();
        int r1 = 0;
    L3:
        int r2 = r5 - r0;
        IntList r3 = this.labelToIndex;
        if (r1 > r2) goto L6;
        r3.add(-1);
        r1 = r1 + 1;
        goto L3
    L6:
        r3.set(r5, r6);
    }

    private void rebuildLabelToIndex() {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        LabeledItem r2 = (LabeledItem) get0(r1);
        if (r2 == null) goto L7;
        this.labelToIndex.set(r2.getLabel(), r1);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void removeLabel(int r2) {
        this.labelToIndex.set(r2, -1);
    }

    public final int[] getLabelsInOrder() {
        int r0 = size();
        int[] r1 = new int[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        LabeledItem r3 = (LabeledItem) get0(r2);
        if (r3 == null) goto L7;
        r1[r2] = r3.getLabel();
        r2 = r2 + 1;
        goto L3
    L7:
        LabeledList$$ExternalSyntheticBUOutline0.m("null at index ", r2);
        return null;
    L9:
        Arrays.sort(r1);
        return r1;
    }

    public final int getMaxLabel() {
        int r0 = this.labelToIndex.size() - 1;
    L3:
        if (r0 < 0) goto L7;
        if (this.labelToIndex.get(r0) >= 0) goto L7;
        r0 = r0 - 1;
    L7:
        int r02 = r0 + 1;
        this.labelToIndex.shrink(r02);
        return r02;
    }

    public final int indexOfLabel(int r2) {
        if (r2 < this.labelToIndex.size()) goto L7;
        return -1;
    L7:
        return this.labelToIndex.get(r2);
    }

    public void set(int r2, LabeledItem r3) {
        LabeledItem r0 = (LabeledItem) getOrNull0(r2);
        set0(r2, r3);
        if (r0 == null) goto L5;
        removeLabel(r0.getLabel());
    L5:
        if (r3 == null) goto L8;
        addLabelIndex(r3.getLabel(), r2);
        return;
    }

    @Override // com.android.dx.util.FixedSizeList
    public void shrinkToFit() {
        super.shrinkToFit();
        rebuildLabelToIndex();
    }
}
