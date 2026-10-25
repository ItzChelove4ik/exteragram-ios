package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public class BootstrapMethodsList extends FixedSizeList {
    public static final BootstrapMethodsList EMPTY = null;

    public static class Item {
        private final BootstrapMethodArgumentsList bootstrapMethodArgumentsList;
        private final CstMethodHandle bootstrapMethodHandle;
        private final CstType declaringClass;

        public Item(CstType r2, CstMethodHandle r3, BootstrapMethodArgumentsList r4) {
            if (r2 == null) goto L12;
            if (r3 == null) goto L10;
            if (r4 == null) goto L8;
            this.bootstrapMethodHandle = r3;
            this.bootstrapMethodArgumentsList = r4;
            this.declaringClass = r2;
            return;
        L8:
            f$$ExternalSyntheticBUOutline2.m("bootstrapMethodArguments == null");
            throw null;
        L10:
            f$$ExternalSyntheticBUOutline2.m("bootstrapMethodHandle == null");
            throw null;
        L12:
            f$$ExternalSyntheticBUOutline2.m("declaringClass == null");
            throw null;
        }

        public BootstrapMethodArgumentsList getBootstrapMethodArguments() {
            return this.bootstrapMethodArgumentsList;
        }

        public CstMethodHandle getBootstrapMethodHandle() {
            return this.bootstrapMethodHandle;
        }

        public CstType getDeclaringClass() {
            return this.declaringClass;
        }
    }

    static {
        EMPTY = new BootstrapMethodsList(0);
    }

    public BootstrapMethodsList(int r1) {
        super(r1);
    }

    public static BootstrapMethodsList concat(BootstrapMethodsList r6, BootstrapMethodsList r7) {
        BootstrapMethodsList r0 = EMPTY;
        if (r6 != r0) goto L5;
        return r7;
    L5:
        if (r7 != r0) goto L7;
        return r6;
    L7:
        int r02 = r6.size();
        int r1 = r7.size();
        BootstrapMethodsList r2 = new BootstrapMethodsList(r02 + r1);
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r4 >= r02) goto L10;
        r2.set(r4, r6.get(r4));
        r4 = r4 + 1;
    L10:
        if (r3 >= r1) goto L12;
        r2.set(r02 + r3, r7.get(r3));
        r3 = r3 + 1;
        goto L10
    L12:
        return r2;
    }

    public Item get(int r1) {
        return (Item) get0(r1);
    }

    public void set(int r1, Item r2) {
        if (r2 == null) goto L5;
        set0(r1, r2);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("item == null");
    }

    public void set(int r2, CstType r3, CstMethodHandle r4, BootstrapMethodArgumentsList r5) {
        set(r2, new Item(r3, r4, r5));
    }
}
