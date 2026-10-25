package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.IntList;

/* loaded from: classes4.dex */
public final class SwitchInsn extends Insn {
    private final IntList cases;

    public SwitchInsn(Rop r1, SourcePosition r2, RegisterSpec r3, RegisterSpecList r4, IntList r5) {
        super(r1, r2, r3, r4);
        if (r1.getBranchingness() != 5) goto L9;
        if (r5 == null) goto L7;
        this.cases = r5;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("cases == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("bogus branchingness");
        throw null;
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitSwitchInsn(this);
    }

    @Override // com.android.dx.rop.code.Insn
    public boolean contentEquals(Insn r1) {
        return false;
    }

    public IntList getCases() {
        return this.cases;
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return StdTypeList.EMPTY;
    }

    @Override // com.android.dx.rop.code.Insn
    public String getInlineString() {
        return this.cases.toString();
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r1) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r7, RegisterSpecList r8) {
        return new SwitchInsn(getOpcode(), getPosition(), r7, r8, this.cases);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r7) {
        return new SwitchInsn(getOpcode(), getPosition(), getResult().withOffset(r7), getSources().withOffset(r7), this.cases);
    }
}
