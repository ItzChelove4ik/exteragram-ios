package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form3rc extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form3rc();
    }

    private Form3rc() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r2) {
        return InsnFormat.regRangeString(r2.getRegisters()) + ", " + r2.cstString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        if (r2 == true) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        return r1.cstComment();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r3) {
        if ((r3 instanceof CstInsn) == true) goto L5;
        return false;
    L5:
        CstInsn r32 = (CstInsn) r3;
        int r2 = r32.getIndex();
        Constant r1 = r32.getConstant();
        if (InsnFormat.unsignedFitsInShort(r2) == true) goto L9;
        return false;
    L9:
        if ((r1 instanceof CstMethodRef) == false) goto L11;
    L15:
        RegisterSpecList r22 = r32.getRegisters();
        r22.size();
        if (r22.size() != 0) goto L18;
        return true;
    L18:
        if (InsnFormat.isRegListSequential(r22) == true) goto L20;
    L24:
        return false;
    L20:
        if (InsnFormat.unsignedFitsInShort(r22.get(0).getReg()) == false) goto L24;
        if (InsnFormat.unsignedFitsInByte(r22.getWordCount()) == false) goto L24;
        return true;
    L11:
        if ((r1 instanceof CstType) == true) goto L15;
        if ((r1 instanceof CstCallSiteRef) == true) goto L15;
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r4, DalvInsn r5) {
        RegisterSpecList r3 = r5.getRegisters();
        int r0 = ((CstInsn) r5).getIndex();
        int r2 = 0;
        if (r3.size() == 0) goto L6;
        r2 = r3.get(0).getReg();
    L6:
        InsnFormat.write(r4, InsnFormat.opcodeUnit(r5, r3.getWordCount()), (short) r0, (short) r2);
    }
}
