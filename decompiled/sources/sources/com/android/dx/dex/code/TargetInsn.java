package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;

/* loaded from: classes.dex */
public final class TargetInsn extends FixedSizeInsn {
    private CodeAddress target;

    public TargetInsn(Dop r1, SourcePosition r2, RegisterSpecList r3, CodeAddress r4) {
        super(r1, r2, r3);
        if (r4 == null) goto L6;
        this.target = r4;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("target == null");
        throw null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        CodeAddress r0 = this.target;
        if (r0 != null) goto L7;
        return "????";
    L7:
        return r0.identifierString();
    }

    public CodeAddress getTarget() {
        return this.target;
    }

    public int getTargetAddress() {
        return this.target.getAddress();
    }

    public int getTargetOffset() {
        return this.target.getAddress() - getAddress();
    }

    public boolean hasTargetOffset() {
        if (hasAddress() == true) goto L5;
        return false;
    L5:
        if (this.target.hasAddress() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public TargetInsn withNewTargetAndReversed(CodeAddress r4) {
        return new TargetInsn(getOpcode().getOppositeTest(), getPosition(), getRegisters(), r4);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop r4) {
        return new TargetInsn(r4, getPosition(), getRegisters(), this.target);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r4) {
        return new TargetInsn(getOpcode(), getPosition(), r4, this.target);
    }
}
