package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;

/* loaded from: classes.dex */
public abstract class VariableSizeInsn extends DalvInsn {
    public VariableSizeInsn(SourcePosition r2, RegisterSpecList r3) {
        super(Dops.SPECIAL_FORMAT, r2, r3);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final DalvInsn withOpcode(Dop r1) {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final DalvInsn withRegisterOffset(int r2) {
        return withRegisters(getRegisters().withOffset(r2));
    }
}
