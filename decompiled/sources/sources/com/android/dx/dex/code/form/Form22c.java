package com.android.dx.dex.code.form;

import com.android.dx.dex.code.CstInsn;
import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form22c extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form22c();
    }

    private Form22c() {
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
        return r3.get(0).regString() + ", " + r3.get(1).regString() + ", " + r4.cstString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        if (r2 == true) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        return r1.cstComment();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r4) {
        RegisterSpecList r3 = r4.getRegisters();
        if ((r4 instanceof CstInsn) == true) goto L5;
    L21:
        return false;
    L5:
        if (r3.size() != 2) goto L21;
        if (InsnFormat.unsignedFitsInNibble(r3.get(0).getReg()) == false) goto L21;
        if (InsnFormat.unsignedFitsInNibble(r3.get(1).getReg()) == false) goto L21;
        CstInsn r42 = (CstInsn) r4;
        if (InsnFormat.unsignedFitsInShort(r42.getIndex()) == true) goto L14;
        return false;
    L14:
        Constant r32 = r42.getConstant();
        if ((r32 instanceof CstType) == false) goto L17;
    L20:
        return true;
    L17:
        if ((r32 instanceof CstFieldRef) == true) goto L20;
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r4, DalvInsn r5) {
        RegisterSpecList r3 = r5.getRegisters();
        int r0 = ((CstInsn) r5).getIndex();
        InsnFormat.write(r4, InsnFormat.opcodeUnit(r5, InsnFormat.makeByte(r3.get(0).getReg(), r3.get(1).getReg())), (short) r0);
    }
}
