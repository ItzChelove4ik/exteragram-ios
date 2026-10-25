package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form35c extends InsnFormat {
    private static final int MAX_NUM_OPS = 5;
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form35c();
    }

    private Form35c() {
    }

    private static RegisterSpecList explicitize(RegisterSpecList r7) {
        int r0 = wordCount(r7);
        int r1 = r7.size();
        if (r0 != r1) goto L5;
        return r7;
    L5:
        RegisterSpecList r2 = new RegisterSpecList(r0);
        int r02 = 0;
        int r3 = 0;
    L6:
        if (r02 >= r1) goto L12;
        RegisterSpec r4 = r7.get(r02);
        r2.set(r3, r4);
        if (r4.getCategory() != 2) goto L10;
        r2.set(r3 + 1, RegisterSpec.make(r4.getReg() + 1, Type.VOID));
        r3 = r3 + 2;
    L11:
        r02 = r02 + 1;
        goto L6
    L10:
        r3 = r3 + 1;
        goto L11
    L12:
        r2.setImmutable();
        return r2;
    }

    private static int wordCount(RegisterSpecList r7) {
        int r0 = r7.size();
        if (r0 <= 5) goto L5;
        return -1;
    L5:
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r3 >= r0) goto L11;
        r4 = r4 + r7.get(r3).getCategory();
        if (InsnFormat.unsignedFitsInNibble((r5.getReg() + r5.getCategory()) - 1) == false) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return -1;
    L11:
        if (r4 > 5) goto L13;
        return r4;
    L13:
        return -1;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r5) {
        RegisterSpecList r4 = r5.getRegisters();
        int r52 = r4.size();
        BitSet r0 = new BitSet(r52);
        int r1 = 0;
    L3:
        if (r1 >= r52) goto L5;
        RegisterSpec r2 = r4.get(r1);
        r0.set(r1, InsnFormat.unsignedFitsInNibble((r2.getReg() + r2.getCategory()) - 1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r2) {
        return InsnFormat.regListString(explicitize(r2.getRegisters())) + ", " + r2.cstString();
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
        if (InsnFormat.unsignedFitsInShort(r32.getIndex()) == true) goto L8;
        return false;
    L8:
        Constant r2 = r32.getConstant();
        if ((r2 instanceof CstMethodRef) == true) goto L16;
        if ((r2 instanceof CstType) == true) goto L16;
        if ((r2 instanceof CstCallSiteRef) == true) goto L16;
        return false;
    L16:
        if (wordCount(r32.getRegisters()) < 0) goto L19;
        return true;
    L19:
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r9, DalvInsn r10) {
        int r8 = ((CstInsn) r10).getIndex();
        RegisterSpecList r0 = explicitize(r10.getRegisters());
        int r1 = r0.size();
        int r2 = 0;
        if (r1 <= 0) goto L5;
        int r3 = r0.get(0).getReg();
    L7:
        if (r1 <= 1) goto L9;
        int r4 = r0.get(1).getReg();
    L11:
        if (r1 <= 2) goto L13;
        int r5 = r0.get(2).getReg();
    L15:
        if (r1 <= 3) goto L17;
        int r6 = r0.get(3).getReg();
    L19:
        if (r1 <= 4) goto L21;
        r2 = r0.get(4).getReg();
    L21:
        InsnFormat.write(r9, InsnFormat.opcodeUnit(r10, InsnFormat.makeByte(r2, r1)), (short) r8, InsnFormat.codeUnit(r3, r4, r5, r6));
        return;
    L17:
        r6 = 0;
        goto L19
    L13:
        r5 = 0;
        goto L15
    L9:
        r4 = 0;
        goto L11
    L5:
        r3 = 0;
        goto L7
    }
}
