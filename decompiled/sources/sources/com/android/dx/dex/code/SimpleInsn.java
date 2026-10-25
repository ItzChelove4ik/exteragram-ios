package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;

/* loaded from: classes.dex */
public final class SimpleInsn extends FixedSizeInsn {
    public SimpleInsn(Dop r1, SourcePosition r2, RegisterSpecList r3) {
        super(r1, r2, r3);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop r3) {
        return new SimpleInsn(r3, getPosition(), getRegisters());
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r3) {
        return new SimpleInsn(getOpcode(), getPosition(), r3);
    }
}
