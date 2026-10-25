package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.CstLiteral64;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class Form51l extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form51l();
    }

    private Form51l() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 5;
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
        return InsnFormat.literalBitsComment((CstLiteralBits) ((CstInsn) r1).getConstant(), 64);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        if ((r4 instanceof CstInsn) == true) goto L5;
    L11:
        return false;
    L5:
        if (r3.size() != 1) goto L11;
        if (InsnFormat.unsignedFitsInByte(r3.get(0).getReg()) == false) goto L11;
        return ((CstInsn) r4).getConstant() instanceof CstLiteral64;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r4, DalvInsn r5) {
        RegisterSpecList r3 = r5.getRegisters();
        long r0 = ((CstLiteral64) ((CstInsn) r5).getConstant()).getLongBits();
        InsnFormat.write(r4, InsnFormat.opcodeUnit(r5, r3.get(0).getReg()), r0);
    }
}
