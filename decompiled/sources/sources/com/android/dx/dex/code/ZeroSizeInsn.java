package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public abstract class ZeroSizeInsn extends DalvInsn {
    public ZeroSizeInsn(SourcePosition r3) {
        super(Dops.SPECIAL_FORMAT, r3, RegisterSpecList.EMPTY);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final int codeSize() {
        return 0;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final DalvInsn withOpcode(Dop r1) {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int r2) {
        return withRegisters(getRegisters().withOffset(r2));
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final void writeTo(AnnotatedOutput r1) {
    }
}
