package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form21c extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form21c();
    }

    private Form21c() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r5) {
        RegisterSpecList r4 = r5.getRegisters();
        int r52 = r4.size();
        BitSet r0 = new BitSet(r52);
        boolean r2 = InsnFormat.unsignedFitsInByte(r4.get(0).getReg());
        if (r52 != 1) goto L7;
        r0.set(0, r2);
        return r0;
    L7:
        if (r4.get(0).getReg() != r4.get(1).getReg()) goto L9;
        r0.set(0, r2);
        r0.set(1, r2);
    L9:
        return r0;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r3) {
        return r3.getRegisters().get(0).regString() + ", " + r3.cstString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        if (r2 == true) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        return r1.cstComment();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r5) {
        if ((r5 instanceof CstInsn) == true) goto L5;
        return false;
    L5:
        RegisterSpecList r4 = r5.getRegisters();
        int r1 = r4.size();
        if (r1 != 1) goto L8;
        RegisterSpec r12 = r4.get(0);
    L15:
        if (InsnFormat.unsignedFitsInByte(r12.getReg()) == true) goto L17;
        return false;
    L17:
        CstInsn r52 = (CstInsn) r5;
        int r42 = r52.getIndex();
        Constant r53 = r52.getConstant();
        if (InsnFormat.unsignedFitsInShort(r42) == true) goto L21;
        return false;
    L21:
        if ((r53 instanceof CstType) == false) goto L23;
    L32:
        return true;
    L23:
        if ((r53 instanceof CstFieldRef) == true) goto L32;
        if ((r53 instanceof CstString) == true) goto L32;
        if ((r53 instanceof CstMethodHandle) == true) goto L32;
        if ((r53 instanceof CstProtoRef) == true) goto L32;
        return false;
    L8:
        if (r1 == 2) goto L10;
        return false;
    L10:
        r12 = r4.get(0);
        if (r12.getReg() == r4.get(1).getReg()) goto L15;
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = ((CstInsn) r4).getIndex();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), (short) r0);
    }
}
