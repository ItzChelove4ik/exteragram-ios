package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public abstract class FixedSizeInsn extends DalvInsn {
    public FixedSizeInsn(Dop r1, SourcePosition r2, RegisterSpecList r3) {
        super(r1, r2, r3);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final int codeSize() {
        return getOpcode().getFormat().codeSize();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final String listingString0(boolean r2) {
        return getOpcode().getFormat().listingString(this, r2);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final DalvInsn withRegisterOffset(int r2) {
        return withRegisters(getRegisters().withOffset(r2));
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final void writeTo(AnnotatedOutput r2) {
        getOpcode().getFormat().writeTo(r2, this);
    }
}
