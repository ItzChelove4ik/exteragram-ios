package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ThrowingInsn extends Insn {
    private final TypeList catches;

    public ThrowingInsn(Rop r2, SourcePosition r3, RegisterSpecList r4, TypeList r5) {
        super(r2, r3, null, r4);
        if (r2.getBranchingness() != 6) goto L9;
        if (r5 == null) goto L7;
        this.catches = r5;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("catches == null");
        throw null;
    L9:
        IntegerType$$ExternalSyntheticBUOutline0.m("opcode with invalid branchingness: ", r2.getBranchingness());
        throw null;
    }

    public static String toCatchString(TypeList r4) {
        StringBuilder r0 = new StringBuilder(100);
        r0.append("catch");
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append(" ");
        r0.append(r4.getType(r2).toHuman());
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitThrowingInsn(this);
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return this.catches;
    }

    @Override // com.android.dx.rop.code.Insn
    public String getInlineString() {
        return toCatchString(this.catches);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r5) {
        return new ThrowingInsn(getOpcode(), getPosition(), getSources(), this.catches.withAddedType(r5));
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r3, RegisterSpecList r4) {
        return new ThrowingInsn(getOpcode(), getPosition(), r4, this.catches);
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r5) {
        return new ThrowingInsn(getOpcode(), getPosition(), getSources().withOffset(r5), this.catches);
    }
}
