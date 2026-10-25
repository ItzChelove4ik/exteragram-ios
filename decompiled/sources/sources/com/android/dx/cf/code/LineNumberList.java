package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class LineNumberList extends FixedSizeList {
    public static final LineNumberList EMPTY = null;

    public static class Item {
        private final int lineNumber;
        private final int startPc;

        public Item(int r1, int r2) {
            if (r1 < 0) goto L9;
            if (r2 < 0) goto L7;
            this.startPc = r1;
            this.lineNumber = r2;
            return;
        L7:
            f$$ExternalSyntheticBUOutline1.m("lineNumber < 0");
            throw null;
        L9:
            f$$ExternalSyntheticBUOutline1.m("startPc < 0");
            throw null;
        }

        public int getLineNumber() {
            return this.lineNumber;
        }

        public int getStartPc() {
            return this.startPc;
        }
    }

    static {
        EMPTY = new LineNumberList(0);
    }

    public LineNumberList(int r1) {
        super(r1);
    }

    public static LineNumberList concat(LineNumberList r6, LineNumberList r7) {
        if (r6 != EMPTY) goto L5;
        return r7;
    L5:
        int r0 = r6.size();
        int r1 = r7.size();
        LineNumberList r2 = new LineNumberList(r0 + r1);
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r0) goto L8;
        r2.set(r4, r6.get(r4));
        r4 = r4 + 1;
    L8:
        if (r3 >= r1) goto L10;
        r2.set(r0 + r3, r7.get(r3));
        r3 = r3 + 1;
        goto L8
    L10:
        return r2;
    }

    public Item get(int r1) {
        return (Item) get0(r1);
    }

    public int pcToLine(int r7) {
        int r0 = size();
        int r1 = -1;
        int r3 = 0;
        int r2 = -1;
    L3:
        if (r3 >= r0) goto L12;
        Item r4 = get(r3);
        int r5 = r4.getStartPc();
        if (r5 > r7) goto L11;
        if (r5 <= r1) goto L11;
        int r12 = r4.getLineNumber();
        if (r5 == r7) goto L9;
        r2 = r12;
        r1 = r5;
        goto L11
    L9:
        return r12;
    L11:
        r3 = r3 + 1;
        goto L3
    L12:
        return r2;
    }

    public void set(int r2, int r3, int r4) {
        set0(r2, new Item(r3, r4));
    }

    public void set(int r1, Item r2) {
        if (r2 == null) goto L5;
        set0(r1, r2);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("item == null");
    }
}
