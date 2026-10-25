package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class InnerClassList extends FixedSizeList {

    public static class Item {
        private final int accessFlags;
        private final CstType innerClass;
        private final CstString innerName;
        private final CstType outerClass;

        public Item(CstType r1, CstType r2, CstString r3, int r4) {
            if (r1 == null) goto L6;
            this.innerClass = r1;
            this.outerClass = r2;
            this.innerName = r3;
            this.accessFlags = r4;
            return;
        L6:
            f$$ExternalSyntheticBUOutline2.m("innerClass == null");
            throw null;
        }

        public int getAccessFlags() {
            return this.accessFlags;
        }

        public CstType getInnerClass() {
            return this.innerClass;
        }

        public CstString getInnerName() {
            return this.innerName;
        }

        public CstType getOuterClass() {
            return this.outerClass;
        }
    }

    public InnerClassList(int r1) {
        super(r1);
    }

    public Item get(int r1) {
        return (Item) get0(r1);
    }

    public void set(int r2, CstType r3, CstType r4, CstString r5, int r6) {
        set0(r2, new Item(r3, r4, r5, r6));
    }
}
