package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.TargetInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* loaded from: classes.dex */
public final class Form22t extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form22t();
    }

    private Form22t() {
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
    public BitSet compatibleRegs(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        BitSet r32 = new BitSet(2);
        r32.set(0, InsnFormat.unsignedFitsInNibble(r2.get(0).getReg()));
        r32.set(1, InsnFormat.unsignedFitsInNibble(r2.get(1).getReg()));
        return r32;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        return r3.get(0).regString() + ", " + r3.get(1).regString() + ", " + InsnFormat.branchString(r4);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.branchComment(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r5) {
        RegisterSpecList r0 = r5.getRegisters();
        if ((r5 instanceof TargetInsn) == true) goto L5;
    L16:
        return false;
    L5:
        if (r0.size() != 2) goto L16;
        if (InsnFormat.unsignedFitsInNibble(r0.get(0).getReg()) == false) goto L16;
        if (InsnFormat.unsignedFitsInNibble(r0.get(1).getReg()) == false) goto L16;
        TargetInsn r52 = (TargetInsn) r5;
        if (r52.hasTargetOffset() == true) goto L14;
        return true;
    L14:
        return branchFits(r52);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r4, DalvInsn r5) {
        RegisterSpecList r3 = r5.getRegisters();
        int r0 = ((TargetInsn) r5).getTargetOffset();
        InsnFormat.write(r4, InsnFormat.opcodeUnit(r5, InsnFormat.makeByte(r3.get(0).getReg(), r3.get(1).getReg())), (short) r0);
    }
}
