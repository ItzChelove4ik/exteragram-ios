package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.TargetInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class Form21t extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form21t();
    }

    private Form21t() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean branchFits(TargetInsn r1) {
        int r0 = r1.getTargetOffset();
        if (r0 != 0) goto L5;
        return false;
    L5:
        if (InsnFormat.signedFitsInShort(r0) == false) goto L10;
        return true;
    L10:
        return false;
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
        return r3.getRegisters().get(0).regString() + ", " + InsnFormat.branchString(r3);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.branchComment(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r5) {
        RegisterSpecList r0 = r5.getRegisters();
        if ((r5 instanceof TargetInsn) == true) goto L5;
    L14:
        return false;
    L5:
        if (r0.size() != 1) goto L14;
        if (InsnFormat.unsignedFitsInByte(r0.get(0).getReg()) == false) goto L14;
        TargetInsn r52 = (TargetInsn) r5;
        if (r52.hasTargetOffset() == true) goto L12;
        return true;
    L12:
        return branchFits(r52);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((TargetInsn) r4).getTargetOffset();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), (short) r0);
    }
}
