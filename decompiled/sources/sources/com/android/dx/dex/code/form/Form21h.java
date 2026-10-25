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
public final class Form21h extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form21h();
    }

    private Form21h() {
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
        RegisterSpecList r0 = r1.getRegisters();
        CstLiteralBits r12 = (CstLiteralBits) ((CstInsn) r1).getConstant();
        if (r0.get(0).getCategory() != 1) goto L5;
        int r02 = 32;
    L7:
        return InsnFormat.literalBitsComment(r12, r02);
    L5:
        r02 = 64;
        goto L7
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r6) {
        RegisterSpecList r5 = r6.getRegisters();
        if ((r6 instanceof CstInsn) == true) goto L5;
    L21:
        return false;
    L5:
        if (r5.size() != 1) goto L21;
        if (InsnFormat.unsignedFitsInByte(r5.get(0).getReg()) == false) goto L21;
        Constant r62 = ((CstInsn) r6).getConstant();
        if ((r62 instanceof CstLiteralBits) == true) goto L12;
        return false;
    L12:
        CstLiteralBits r63 = (CstLiteralBits) r62;
        if (r5.get(0).getCategory() != 1) goto L19;
        if ((r63.getIntBits() & 65535) != 0) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ((r63.getLongBits() & 281474976710655L) != 0) goto L21;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r5, DalvInsn r6) {
        RegisterSpecList r4 = r6.getRegisters();
        CstLiteralBits r0 = (CstLiteralBits) ((CstInsn) r6).getConstant();
        if (r4.get(0).getCategory() != 1) goto L6;
        int r02 = r0.getIntBits() >>> 16;
    L5:
        short r03 = (short) r02;
        InsnFormat.write(r5, InsnFormat.opcodeUnit(r6, r4.get(0).getReg()), r03);
        return;
    L6:
        r02 = (int) (r0.getLongBits() >>> 48);
        goto L5
    }
}
