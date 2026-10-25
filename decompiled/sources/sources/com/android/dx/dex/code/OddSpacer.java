package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes4.dex */
public final class OddSpacer extends VariableSizeInsn {
    public OddSpacer(SourcePosition r2) {
        super(r2, RegisterSpecList.EMPTY);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public int codeSize() {
        return getAddress() & 1;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r1) {
        if (codeSize() != 0) goto L6;
        return null;
    L6:
        return "nop // spacer";
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r1) {
        return new OddSpacer(getPosition());
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public void writeTo(AnnotatedOutput r1) {
        if (codeSize() == 0) goto L6;
        r1.writeShort(InsnFormat.codeUnit(0, 0));
        return;
    }
}
