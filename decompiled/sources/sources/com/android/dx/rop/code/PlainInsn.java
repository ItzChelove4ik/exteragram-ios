package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.rop.type.TypeList;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class PlainInsn extends Insn {
    public PlainInsn(Rop r1, SourcePosition r2, RegisterSpec r3, RegisterSpec r4) {
        this(r1, r2, r3, RegisterSpecList.make(r4));
    }

    public PlainInsn(Rop r1, SourcePosition r2, RegisterSpec r3, RegisterSpecList r4) {
        super(r1, r2, r3, r4);
        int r0 = r1.getBranchingness();
        if (r0 != 5) goto L5;
    L13:
        IntegerType$$ExternalSyntheticBUOutline0.m("opcode with invalid branchingness: ", r1.getBranchingness());
        throw null;
    L5:
        if (r0 == 6) goto L13;
        if (r3 != null) goto L8;
        return;
    L8:
        if (r1.getBranchingness() == 1) goto L15;
        f$$ExternalSyntheticBUOutline1.m("can't mix branchingness with result");
        throw null;
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitPlainInsn(this);
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return StdTypeList.EMPTY;
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r1) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r3, RegisterSpecList r4) {
        return new PlainInsn(getOpcode(), getPosition(), r3, r4);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r5) {
        return new PlainInsn(getOpcode(), getPosition(), getResult().withOffset(r5), getSources().withOffset(r5));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.dx.rop.code.Insn
    public Insn withSourceLiteral() {
        RegisterSpecList r0 = getSources();
        int r1 = r0.size();
        if (r1 == 0) goto L22;
        TypeBearer r2 = r0.get(r1 - 1).getTypeBearer();
        if (r2.isConstant() == true) goto L13;
        TypeBearer r22 = r0.get(0).getTypeBearer();
        if (r1 != 2) goto L22;
        if (r22.isConstant() == false) goto L22;
        Constant r8 = (Constant) r22;
        RegisterSpecList r7 = r0.withoutFirst();
        return new PlainCstInsn(Rops.ropFor(getOpcode().getOpcode(), getResult(), r7, r8), getPosition(), getResult(), r7, r8);
    L13:
        Constant r23 = (Constant) r2;
        RegisterSpecList r72 = r0.withoutLast();
        int r02 = getOpcode().getOpcode();     // Catch: IllegalArgumentException -> L23
        if (r02 == 15) goto L17;
    L19:
        Constant r82 = r23;
        return new PlainCstInsn(Rops.ropFor(r02, getResult(), r72, r82), getPosition(), getResult(), r72, r82);
    L17:
        if ((r23 instanceof CstInteger) == false) goto L19;
        r23 = CstInteger.make(-((CstInteger) r23).getValue());     // Catch: IllegalArgumentException -> L23
        r02 = 14;
    L22:
        return this;
    }
}
