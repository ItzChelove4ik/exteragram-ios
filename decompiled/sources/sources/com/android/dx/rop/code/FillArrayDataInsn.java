package com.android.dx.rop.code;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class FillArrayDataInsn extends Insn {
    private final Constant arrayType;
    private final ArrayList<Constant> initValues;

    public FillArrayDataInsn(Rop r2, SourcePosition r3, RegisterSpecList r4, ArrayList<Constant> r5, Constant r6) {
        super(r2, r3, null, r4);
        if (r2.getBranchingness() != 1) goto L6;
        this.initValues = r5;
        this.arrayType = r6;
        return;
    L6:
        IntegerType$$ExternalSyntheticBUOutline0.m("opcode with invalid branchingness: ", r2.getBranchingness());
        throw null;
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitFillArrayDataInsn(this);
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return StdTypeList.EMPTY;
    }

    public Constant getConstant() {
        return this.arrayType;
    }

    public ArrayList<Constant> getInitValues() {
        return this.initValues;
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r1) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r7, RegisterSpecList r8) {
        return new FillArrayDataInsn(getOpcode(), getPosition(), r8, this.initValues, this.arrayType);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r7) {
        return new FillArrayDataInsn(getOpcode(), getPosition(), getSources().withOffset(r7), this.initValues, this.arrayType);
    }
}
