package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.TargetInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class Form31t extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form31t();
    }

    private Form31t() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean branchFits(TargetInsn r1) {
        return true;
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
        return r3.getRegisters().get(0).regString() + ", " + InsnFormat.branchString(r3);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.branchComment(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        if ((r3 instanceof TargetInsn) == true) goto L5;
    L10:
        return false;
    L5:
        if (r2.size() != 1) goto L10;
        if (InsnFormat.unsignedFitsInByte(r2.get(0).getReg()) == false) goto L10;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((TargetInsn) r4).getTargetOffset();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), r0);
    }
}
