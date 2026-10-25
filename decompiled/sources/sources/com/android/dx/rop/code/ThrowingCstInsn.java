package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ThrowingCstInsn extends CstInsn {
    private final TypeList catches;

    public ThrowingCstInsn(Rop r7, SourcePosition r8, RegisterSpecList r9, TypeList r10, Constant r11) {
        super(r7, r8, null, r9, r11);
        if (r7.getBranchingness() != 6) goto L9;
        if (r10 == null) goto L7;
        this.catches = r10;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("catches == null");
        throw null;
    L9:
        IntegerType$$ExternalSyntheticBUOutline0.m("opcode with invalid branchingness: ", r7.getBranchingness());
        throw null;
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitThrowingCstInsn(this);
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return this.catches;
    }

    @Override // com.android.dx.rop.code.CstInsn, com.android.dx.rop.code.Insn
    public String getInlineString() {
        Constant r0 = getConstant();
        String r1 = r0.toHuman();
        if ((r0 instanceof CstString) == false) goto L6;
        r1 = ((CstString) r0).toQuoted();
    L6:
        return r1 + " " + ThrowingInsn.toCatchString(this.catches);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r7) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), getSources(), this.catches.withAddedType(r7), getConstant());
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r7, RegisterSpecList r8) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), r8, this.catches, getConstant());
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r7) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), getSources().withOffset(r7), this.catches, getConstant());
    }
}
