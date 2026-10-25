package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class Form31i extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form31i();
    }

    private Form31i() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r2) {
        RegisterSpecList r1 = r2.getRegisters();
        BitSet r22 = new BitSet(1);
        r22.set(0, InsnFormat.unsignedFitsInByte(r1.get(0).getReg()));
        return r22;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r3) {
        return r3.getRegisters().get(0).regString() + ", " + InsnFormat.literalBitsString((CstLiteralBits) ((CstInsn) r3).getConstant());
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.literalBitsComment((CstLiteralBits) ((CstInsn) r1).getConstant(), 32);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        if ((r4 instanceof CstInsn) == true) goto L5;
    L14:
        return false;
    L5:
        if (r3.size() != 1) goto L14;
        if (InsnFormat.unsignedFitsInByte(r3.get(0).getReg()) == false) goto L14;
        Constant r32 = ((CstInsn) r4).getConstant();
        if ((r32 instanceof CstLiteralBits) == true) goto L13;
        return false;
    L13:
        return ((CstLiteralBits) r32).fitsInInt();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((CstLiteralBits) ((CstInsn) r4).getConstant()).getIntBits();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), r0);
    }
}
