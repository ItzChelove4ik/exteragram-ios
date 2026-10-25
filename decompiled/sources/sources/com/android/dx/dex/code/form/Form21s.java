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
public final class Form21s extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form21s();
    }

    private Form21s() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
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
        return InsnFormat.literalBitsComment((CstLiteralBits) ((CstInsn) r1).getConstant(), 16);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        if ((r4 instanceof CstInsn) == true) goto L5;
    L17:
        return false;
    L5:
        if (r3.size() != 1) goto L17;
        if (InsnFormat.unsignedFitsInByte(r3.get(0).getReg()) == false) goto L17;
        Constant r32 = ((CstInsn) r4).getConstant();
        if ((r32 instanceof CstLiteralBits) == true) goto L12;
        return false;
    L12:
        CstLiteralBits r33 = (CstLiteralBits) r32;
        if (r33.fitsInInt() == false) goto L17;
        if (InsnFormat.signedFitsInShort(r33.getIntBits()) == false) goto L17;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((CstLiteralBits) ((CstInsn) r4).getConstant()).getIntBits();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), (short) r0);
    }
}
