package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.android.dx.util.LabeledItem;

/* loaded from: classes4.dex */
public final class ByteBlock implements LabeledItem {
    private final ByteCatchList catches;
    private final int end;
    private final int label;
    private final int start;
    private final IntList successors;

    public ByteBlock(int r5, int r6, int r7, IntList r8, ByteCatchList r9) {
        if (r5 < 0) goto L25;
        if (r6 < 0) goto L23;
        if (r7 <= r6) goto L21;
        if (r8 == null) goto L19;
        int r1 = r8.size();
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L14;
        if (r8.get(r2) < 0) goto L12;
        r2 = r2 + 1;
        goto L8
    L12:
        Dex$$ExternalSyntheticBUOutline1.m("successors[", r2, "] == ", r8.get(r2));
        throw null;
    L14:
        if (r9 == null) goto L17;
        this.label = r5;
        this.start = r6;
        this.end = r7;
        this.successors = r8;
        this.catches = r9;
        return;
    L17:
        f$$ExternalSyntheticBUOutline2.m("catches == null");
        throw null;
    L19:
        f$$ExternalSyntheticBUOutline2.m("targets == null");
        throw null;
    L21:
        f$$ExternalSyntheticBUOutline1.m("end <= start");
        throw null;
    L23:
        f$$ExternalSyntheticBUOutline1.m("start < 0");
        throw null;
    L25:
        f$$ExternalSyntheticBUOutline1.m("label < 0");
        throw null;
    }

    public ByteCatchList getCatches() {
        return this.catches;
    }

    public int getEnd() {
        return this.end;
    }

    @Override // com.android.dx.util.LabeledItem
    public int getLabel() {
        return this.label;
    }

    public int getStart() {
        return this.start;
    }

    public IntList getSuccessors() {
        return this.successors;
    }

    public String toString() {
        return "{" + Hex.u2(this.label) + ": " + Hex.u2(this.start) + ".." + Hex.u2(this.end) + '}';
    }
}
