package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Type;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class LocalVariableList extends FixedSizeList {
    public static final LocalVariableList EMPTY = null;

    public static class Item {
        private final CstString descriptor;
        private final int index;
        private final int length;
        private final CstString name;
        private final CstString signature;
        private final int startPc;

        public Item(int r2, int r3, CstString r4, CstString r5, CstString r6, int r7) {
            if (r2 < 0) goto L20;
            if (r3 < 0) goto L18;
            if (r4 == null) goto L16;
            if (r5 != null) goto L11;
            if (r6 != null) goto L11;
            f$$ExternalSyntheticBUOutline2.m("(descriptor == null) && (signature == null)");
            throw null;
        L11:
            if (r7 < 0) goto L14;
            this.startPc = r2;
            this.length = r3;
            this.name = r4;
            this.descriptor = r5;
            this.signature = r6;
            this.index = r7;
            return;
        L14:
            f$$ExternalSyntheticBUOutline1.m("index < 0");
            throw null;
        L16:
            f$$ExternalSyntheticBUOutline2.m("name == null");
            throw null;
        L18:
            f$$ExternalSyntheticBUOutline1.m("length < 0");
            throw null;
        L20:
            f$$ExternalSyntheticBUOutline1.m("startPc < 0");
            throw null;
        }

        public static /* synthetic */ CstString access$000(Item r0) {
            return r0.getSignature();
        }

        private CstString getSignature() {
            return this.signature;
        }

        public CstString getDescriptor() {
            return this.descriptor;
        }

        public int getIndex() {
            return this.index;
        }

        public int getLength() {
            return this.length;
        }

        public LocalItem getLocalItem() {
            return LocalItem.make(this.name, this.signature);
        }

        public int getStartPc() {
            return this.startPc;
        }

        public Type getType() {
            return Type.intern(this.descriptor.getString());
        }

        public boolean matchesAllButType(Item r3) {
            if (this.startPc == r3.startPc) goto L5;
            return false;
        L5:
            if (this.length == r3.length) goto L7;
            return false;
        L7:
            if (this.index == r3.index) goto L9;
            return false;
        L9:
            if (this.name.equals(r3.name) == false) goto L16;
            return true;
        L16:
            return false;
        }

        public boolean matchesPcAndIndex(int r2, int r3) {
            if (r3 != this.index) goto L10;
            int r32 = this.startPc;
            if (r2 >= r32) goto L7;
            return false;
        L7:
            if (r2 >= (r32 + this.length)) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        public Item withSignature(CstString r8) {
            return new Item(this.startPc, this.length, this.name, this.descriptor, r8, this.index);
        }
    }

    static {
        EMPTY = new LocalVariableList(0);
    }

    public LocalVariableList(int r1) {
        super(r1);
    }

    public static LocalVariableList concat(LocalVariableList r6, LocalVariableList r7) {
        if (r6 != EMPTY) goto L5;
        return r7;
    L5:
        int r0 = r6.size();
        int r1 = r7.size();
        LocalVariableList r2 = new LocalVariableList(r0 + r1);
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
        r2.setImmutable();
        return r2;
    }

    public static LocalVariableList mergeDescriptorsAndSignatures(LocalVariableList r5, LocalVariableList r6) {
        int r0 = r5.size();
        LocalVariableList r1 = new LocalVariableList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        Item r3 = r5.get(r2);
        Item r4 = r6.itemToLocal(r3);
        if (r4 == null) goto L7;
        r3 = r3.withSignature(Item.access$000(r4));
    L7:
        r1.set(r2, r3);
        r2 = r2 + 1;
        goto L3
    L8:
        r1.setImmutable();
        return r1;
    }

    public Item get(int r1) {
        return (Item) get0(r1);
    }

    public Item itemToLocal(Item r5) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        Item r2 = (Item) get0(r1);
        if (r2 == null) goto L9;
        if (r2.matchesAllButType(r5) == false) goto L9;
        return r2;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return null;
    }

    public Item pcAndIndexToLocal(int r5, int r6) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        Item r2 = (Item) get0(r1);
        if (r2 == null) goto L9;
        if (r2.matchesPcAndIndex(r5, r6) == false) goto L9;
        return r2;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return null;
    }

    public void set(int r8, int r9, int r10, CstString r11, CstString r12, CstString r13, int r14) {
        set0(r8, new Item(r9, r10, r11, r12, r13, r14));
    }

    public void set(int r1, Item r2) {
        if (r2 == null) goto L5;
        set0(r1, r2);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("item == null");
    }
}
