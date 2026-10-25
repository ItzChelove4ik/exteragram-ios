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
public final class Form22b extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form22b();
    }

    private Form22b() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        BitSet r32 = new BitSet(2);
        r32.set(0, InsnFormat.unsignedFitsInByte(r2.get(0).getReg()));
        r32.set(1, InsnFormat.unsignedFitsInByte(r2.get(1).getReg()));
        return r32;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        return r3.get(0).regString() + ", " + r3.get(1).regString() + ", " + InsnFormat.literalBitsString((CstLiteralBits) ((CstInsn) r4).getConstant());
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.literalBitsComment((CstLiteralBits) ((CstInsn) r1).getConstant(), 8);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        if ((r4 instanceof CstInsn) == true) goto L5;
    L19:
        return false;
    L5:
        if (r3.size() != 2) goto L19;
        if (InsnFormat.unsignedFitsInByte(r3.get(0).getReg()) == false) goto L19;
        if (InsnFormat.unsignedFitsInByte(r3.get(1).getReg()) == false) goto L19;
        Constant r32 = ((CstInsn) r4).getConstant();
        if ((r32 instanceof CstLiteralBits) == true) goto L14;
        return false;
    L14:
        CstLiteralBits r33 = (CstLiteralBits) r32;
        if (r33.fitsInInt() == false) goto L19;
        if (InsnFormat.signedFitsInByte(r33.getIntBits()) == false) goto L19;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((CstLiteralBits) ((CstInsn) r4).getConstant()).getIntBits();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), InsnFormat.codeUnit(r2.get(1).getReg(), r0 & 255));
    }
}
