package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.FixedSizeList;
import com.android.dx.util.IntList;

/* loaded from: classes4.dex */
public final class ByteCatchList extends FixedSizeList {
    public static final ByteCatchList EMPTY = null;

    public static class Item {
        private final int endPc;
        private final CstType exceptionClass;
        private final int handlerPc;
        private final int startPc;

        public Item(int r1, int r2, int r3, CstType r4) {
            if (r1 < 0) goto L12;
            if (r2 < r1) goto L10;
            if (r3 < 0) goto L8;
            this.startPc = r1;
            this.endPc = r2;
            this.handlerPc = r3;
            this.exceptionClass = r4;
            return;
        L8:
            f$$ExternalSyntheticBUOutline1.m("handlerPc < 0");
            throw null;
        L10:
            f$$ExternalSyntheticBUOutline1.m("endPc < startPc");
            throw null;
        L12:
            f$$ExternalSyntheticBUOutline1.m("startPc < 0");
            throw null;
        }

        public boolean covers(int r2) {
            if (r2 >= this.startPc) goto L5;
            return false;
        L5:
            if (r2 >= this.endPc) goto L10;
            return true;
        L10:
            return false;
        }

        public int getEndPc() {
            return this.endPc;
        }

        public CstType getExceptionClass() {
            CstType r0 = this.exceptionClass;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return CstType.OBJECT;
        }

        public int getHandlerPc() {
            return this.handlerPc;
        }

        public int getStartPc() {
            return this.startPc;
        }
    }

    static {
        EMPTY = new ByteCatchList(0);
    }

    public ByteCatchList(int r1) {
        super(r1);
    }

    private static boolean typeNotFound(Item r4, Item[] r5, int r6) {
        CstType r42 = r4.getExceptionClass();
        int r1 = 0;
    L3:
        if (r1 >= r6) goto L11;
        CstType r2 = r5[r1].getExceptionClass();
        if (r2 == r42) goto L10;
        if (r2 == CstType.OBJECT) goto L10;
        r1 = r1 + 1;
    L10:
        return false;
    L11:
        return true;
    }

    public int byteLength() {
        return (size() * 8) + 2;
    }

    public Item get(int r1) {
        return (Item) get0(r1);
    }

    public ByteCatchList listFor(int r8) {
        int r0 = size();
        Item[] r1 = new Item[r0];
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r0) goto L10;
        Item r5 = get(r3);
        if (r5.covers(r8) == false) goto L9;
        if (typeNotFound(r5, r1, r4) == false) goto L9;
        r1[r4] = r5;
        r4 = r4 + 1;
    L9:
        r3 = r3 + 1;
        goto L3
    L10:
        if (r4 == 0) goto L12;
        ByteCatchList r7 = new ByteCatchList(r4);
    L14:
        if (r2 >= r4) goto L16;
        r7.set(r2, r1[r2]);
        r2 = r2 + 1;
        goto L14
    L16:
        r7.setImmutable();
        return r7;
    L12:
        return EMPTY;
    }

    public void set(int r2, int r3, int r4, int r5, CstType r6) {
        set0(r2, new Item(r3, r4, r5, r6));
    }

    public void set(int r1, Item r2) {
        if (r2 == null) goto L5;
        set0(r1, r2);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("item == null");
    }

    public TypeList toRopCatchList() {
        int r0 = size();
        if (r0 == 0) goto L5;
        StdTypeList r1 = new StdTypeList(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        r1.set(r2, get(r2).getExceptionClass().getClassType());
        r2 = r2 + 1;
        goto L7
    L9:
        r1.setImmutable();
        return r1;
    L5:
        return StdTypeList.EMPTY;
    }

    public IntList toTargetList(int r6) {
        if (r6 < (-1)) goto L22;
        int r0 = 0;
        if (r6 < 0) goto L7;
        int r1 = 1;
    L8:
        int r2 = size();
        if (r2 != 0) goto L15;
        if (r1 == 0) goto L14;
        return IntList.makeImmutable(r6);
    L14:
        return IntList.EMPTY;
    L15:
        IntList r3 = new IntList(r2 + r1);
    L16:
        if (r0 >= r2) goto L18;
        r3.add(get(r0).getHandlerPc());
        r0 = r0 + 1;
        goto L16
    L18:
        if (r1 == 0) goto L20;
        r3.add(r6);
    L20:
        r3.setImmutable();
        return r3;
    L7:
        r1 = 0;
        goto L8
    L22:
        f$$ExternalSyntheticBUOutline1.m("noException < -1");
        return null;
    }
}
