package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.TargetInsn;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public final class Form20t extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form20t();
    }

    private Form20t() {
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
    public String insnArgString(DalvInsn r1) {
        return InsnFormat.branchString(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.branchComment(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r2) {
        if ((r2 instanceof TargetInsn) == true) goto L5;
        return false;
    L5:
        if (r2.getRegisters().size() != 0) goto L15;
        TargetInsn r22 = (TargetInsn) r2;
        if (r22.hasTargetOffset() == true) goto L10;
        return true;
    L10:
        return branchFits(r22);
    L15:
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r2, DalvInsn r3) {
        int r1 = ((TargetInsn) r3).getTargetOffset();
        InsnFormat.write(r2, InsnFormat.opcodeUnit(r3, 0), (short) r1);
    }
}
