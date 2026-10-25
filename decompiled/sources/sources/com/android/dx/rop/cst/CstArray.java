package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public class CstArray extends Constant {
    private final List list;

    public static final class List extends FixedSizeList implements Comparable<List> {
        public List(int r1) {
            super(r1);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(List r8) {
            int r0 = size();
            int r1 = r8.size();
            if (r0 >= r1) goto L5;
            int r2 = r0;
        L6:
            int r4 = 0;
        L7:
            if (r4 >= r2) goto L12;
            int r5 = ((Constant) get0(r4)).compareTo2((Constant) r8.get0(r4));
            if (r5 != 0) goto L10;
            r4 = r4 + 1;
            goto L7
        L10:
            return r5;
        L12:
            if (r0 >= r1) goto L15;
            return -1;
        L15:
            if (r0 <= r1) goto L18;
            return 1;
        L18:
            return 0;
        L5:
            r2 = r1;
            goto L6
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(List r1) {
            return compareTo2(r1);
        }

        public Constant get(int r1) {
            return (Constant) get0(r1);
        }

        public void set(int r1, Constant r2) {
            set0(r1, r2);
        }
    }

    public CstArray(List r1) {
        if (r1 == null) goto L6;
        r1.throwIfMutable();
        this.list = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("list == null");
        throw null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return this.list.compareTo2(((CstArray) r1).list);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof CstArray) == true) goto L7;
        return false;
    L7:
        return this.list.equals(((CstArray) r2).list);
    }

    public List getList() {
        return this.list;
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.list.toHuman("{", ", ", "}");
    }

    public String toString() {
        return this.list.toString("array{", ", ", "}");
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "array";
    }
}
