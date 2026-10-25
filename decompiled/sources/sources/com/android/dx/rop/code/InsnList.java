package com.android.dx.rop.code;

import com.android.dx.rop.code.Insn;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes.dex */
public final class InsnList extends FixedSizeList {
    public InsnList(int r1) {
        super(r1);
    }

    public boolean contentEquals(InsnList r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r1 = size();
        if (r1 == r6.size()) goto L8;
        return false;
    L8:
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L14;
        if (get(r2).contentEquals(r6.get(r2)) == false) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    public void forEach(Insn.Visitor r4) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        get(r1).accept(r4);
        r1 = r1 + 1;
        goto L3
    }

    public Insn get(int r1) {
        return (Insn) get0(r1);
    }

    public Insn getLast() {
        return get(size() - 1);
    }

    public void set(int r1, Insn r2) {
        set0(r1, r2);
    }

    public InsnList withRegisterOffset(int r5) {
        int r0 = size();
        InsnList r1 = new InsnList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        Insn r3 = (Insn) get0(r2);
        if (r3 == null) goto L7;
        r1.set0(r2, r3.withRegisterOffset(r5));
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        if (isImmutable() == false) goto L11;
        r1.setImmutable();
    L11:
        return r1;
    }
}
