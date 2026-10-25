package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.TargetInsn;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public final class Form30t extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form30t();
    }

    private Form30t() {
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
    public String insnArgString(DalvInsn r1) {
        return InsnFormat.branchString(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return InsnFormat.branchComment(r1);
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r1) {
        if ((r1 instanceof TargetInsn) == true) goto L5;
        return false;
    L5:
        if (r1.getRegisters().size() != 0) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r2, DalvInsn r3) {
        int r1 = ((TargetInsn) r3).getTargetOffset();
        InsnFormat.write(r2, InsnFormat.opcodeUnit(r3, 0), r1);
    }
}
