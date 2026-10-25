package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;

/* loaded from: classes.dex */
public abstract class CstInsn extends Insn {
    private final Constant cst;

    public CstInsn(Rop r1, SourcePosition r2, RegisterSpec r3, RegisterSpecList r4, Constant r5) {
        super(r1, r2, r3, r4);
        if (r5 == null) goto L6;
        this.cst = r5;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        throw null;
    }

    @Override // com.android.dx.rop.code.Insn
    public boolean contentEquals(Insn r2) {
        if (super.contentEquals(r2) == true) goto L5;
        return false;
    L5:
        if (this.cst.equals(((CstInsn) r2).getConstant()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public Constant getConstant() {
        return this.cst;
    }

    @Override // com.android.dx.rop.code.Insn
    public String getInlineString() {
        return this.cst.toHuman();
    }
}
