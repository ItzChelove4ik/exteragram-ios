package com.android.dx.rop.code;

import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.FixedSizeList;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class RegisterSpecList extends FixedSizeList implements TypeList {
    public static final RegisterSpecList EMPTY = null;

    /* renamed from: com.android.dx.rop.code.RegisterSpecList$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Expander {
        private int base;
        private final BitSet compatRegs;
        private boolean duplicateFirst;
        private final RegisterSpecList regSpecList;
        private final RegisterSpecList result;

        private Expander(RegisterSpecList r1, BitSet r2, int r3, boolean r4) {
            this.regSpecList = r1;
            this.compatRegs = r2;
            this.base = r3;
            this.result = new RegisterSpecList(r1.size());
            this.duplicateFirst = r4;
        }

        public /* synthetic */ Expander(RegisterSpecList r1, BitSet r2, int r3, boolean r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        public static /* synthetic */ void access$100(Expander r0, int r1) {
            r0.expandRegister(r1);
        }

        public static /* synthetic */ RegisterSpecList access$200(Expander r0) {
            return r0.getResult();
        }

        private void expandRegister(int r2) {
            expandRegister(r2, (RegisterSpec) RegisterSpecList.access$300(this.regSpecList, r2));
        }

        private void expandRegister(int r3, RegisterSpec r4) {
            BitSet r0 = this.compatRegs;
            if (r0 != null) goto L6;
        L7:
            r4 = r4.withReg(this.base);
            if (this.duplicateFirst == true) goto L10;
            this.base += r4.getCategory();
        L10:
            this.duplicateFirst = false;
            RegisterSpecList.access$400(this.result, r3, r4);
            return;
        L6:
            if (r0.get(r3) == true) goto L10;
            goto L7
        }

        private RegisterSpecList getResult() {
            if (this.regSpecList.isImmutable() == false) goto L6;
            this.result.setImmutable();
        L6:
            return this.result;
        }
    }

    static {
        EMPTY = new RegisterSpecList(0);
    }

    public RegisterSpecList(int r1) {
        super(r1);
    }

    public static /* synthetic */ Object access$300(RegisterSpecList r0, int r1) {
        return r0.get0(r1);
    }

    public static /* synthetic */ void access$400(RegisterSpecList r0, int r1, Object r2) {
        r0.set0(r1, r2);
    }

    public static RegisterSpecList make(RegisterSpec r2) {
        RegisterSpecList r0 = new RegisterSpecList(1);
        r0.set(0, r2);
        return r0;
    }

    public static RegisterSpecList make(RegisterSpec r2, RegisterSpec r3) {
        RegisterSpecList r0 = new RegisterSpecList(2);
        r0.set(0, r2);
        r0.set(1, r3);
        return r0;
    }

    public static RegisterSpecList make(RegisterSpec r2, RegisterSpec r3, RegisterSpec r4) {
        RegisterSpecList r0 = new RegisterSpecList(3);
        r0.set(0, r2);
        r0.set(1, r3);
        r0.set(2, r4);
        return r0;
    }

    public static RegisterSpecList make(RegisterSpec r2, RegisterSpec r3, RegisterSpec r4, RegisterSpec r5) {
        RegisterSpecList r0 = new RegisterSpecList(4);
        r0.set(0, r2);
        r0.set(1, r3);
        r0.set(2, r4);
        r0.set(3, r5);
        return r0;
    }

    public RegisterSpec get(int r1) {
        return (RegisterSpec) get0(r1);
    }

    public int getRegistersSize() {
        int r0 = size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L10;
        RegisterSpec r3 = (RegisterSpec) get0(r1);
        if (r3 == null) goto L9;
        int r32 = r3.getNextReg();
        if (r32 <= r2) goto L9;
        r2 = r32;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    @Override // com.android.dx.rop.type.TypeList
    public Type getType(int r1) {
        return get(r1).getType().getType();
    }

    @Override // com.android.dx.rop.type.TypeList
    public int getWordCount() {
        int r0 = size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = r2 + getType(r1).getCategory();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    public int indexOfRegister(int r4) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (get(r1).getReg() == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public void set(int r1, RegisterSpec r2) {
        set0(r1, r2);
    }

    public RegisterSpec specForRegister(int r5) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        RegisterSpec r2 = get(r1);
        if (r2.getReg() == r5) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    public RegisterSpecList subset(BitSet r5) {
        int r0 = size() - r5.cardinality();
        if (r0 == 0) goto L5;
        RegisterSpecList r1 = new RegisterSpecList(r0);
        int r02 = 0;
        int r2 = 0;
    L8:
        if (r02 >= size()) goto L14;
        if (r5.get(r02) == true) goto L12;
        r1.set0(r2, get0(r02));
        r2 = r2 + 1;
    L12:
        r02 = r02 + 1;
        goto L8
    L14:
        if (isImmutable() == false) goto L16;
        r1.setImmutable();
    L16:
        return r1;
    L5:
        return EMPTY;
    }

    @Override // com.android.dx.rop.type.TypeList
    public TypeList withAddedType(Type r1) {
        throw new UnsupportedOperationException("unsupported");
    }

    public RegisterSpecList withExpandedRegisters(int r8, boolean r9, BitSet r10) {
        int r0 = size();
        if (r0 != 0) goto L5;
        return this;
    L5:
        Expander r1 = new Expander(this, r10, r8, r9, null);
        int r7 = 0;
    L6:
        if (r7 >= r0) goto L9;
        Expander.access$100(r1, r7);
        r7 = r7 + 1;
        goto L6
    L9:
        return Expander.access$200(r1);
    }

    public RegisterSpecList withFirst(RegisterSpec r6) {
        int r0 = size();
        RegisterSpecList r1 = new RegisterSpecList(r0 + 1);
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        int r4 = r3 + 1;
        r1.set0(r4, get0(r3));
        r3 = r4;
        goto L3
    L5:
        r1.set0(0, r6);
        if (isImmutable() == false) goto L8;
        r1.setImmutable();
    L8:
        return r1;
    }

    public RegisterSpecList withOffset(int r5) {
        int r0 = size();
        if (r0 != 0) goto L5;
        return this;
    L5:
        RegisterSpecList r1 = new RegisterSpecList(r0);
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L12;
        RegisterSpec r3 = (RegisterSpec) get0(r2);
        if (r3 == null) goto L10;
        r1.set0(r2, r3.withOffset(r5));
    L10:
        r2 = r2 + 1;
        goto L6
    L12:
        if (isImmutable() == false) goto L14;
        r1.setImmutable();
    L14:
        return r1;
    }

    public RegisterSpecList withoutFirst() {
        int r0 = size() - 1;
        if (r0 == 0) goto L5;
        RegisterSpecList r1 = new RegisterSpecList(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L10;
        int r3 = r2 + 1;
        r1.set0(r2, get0(r3));
        r2 = r3;
        goto L7
    L10:
        if (isImmutable() == false) goto L12;
        r1.setImmutable();
    L12:
        return r1;
    L5:
        return EMPTY;
    }

    public RegisterSpecList withoutLast() {
        int r0 = size() - 1;
        if (r0 == 0) goto L5;
        RegisterSpecList r1 = new RegisterSpecList(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L10;
        r1.set0(r2, get0(r2));
        r2 = r2 + 1;
        goto L7
    L10:
        if (isImmutable() == false) goto L12;
        r1.setImmutable();
    L12:
        return r1;
    L5:
        return EMPTY;
    }
}
