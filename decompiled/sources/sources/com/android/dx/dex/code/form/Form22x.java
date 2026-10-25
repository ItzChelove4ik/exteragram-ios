package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.SimpleInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form22x extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form22x();
    }

    private Form22x() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        BitSet r32 = new BitSet(2);
        r32.set(0, InsnFormat.unsignedFitsInByte(r2.get(0).getReg()));
        r32.set(1, InsnFormat.unsignedFitsInShort(r2.get(1).getReg()));
        return r32;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r2) {
        RegisterSpecList r1 = r2.getRegisters();
        return r1.get(0).regString() + ", " + r1.get(1).regString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        if ((r3 instanceof SimpleInsn) == true) goto L5;
    L11:
        return false;
    L5:
        if (r2.size() != 2) goto L11;
        if (InsnFormat.unsignedFitsInByte(r2.get(0).getReg()) == false) goto L11;
        if (InsnFormat.unsignedFitsInShort(r2.get(1).getReg()) == false) goto L11;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r2, DalvInsn r3) {
        RegisterSpecList r1 = r3.getRegisters();
        InsnFormat.write(r2, InsnFormat.opcodeUnit(r3, r1.get(0).getReg()), (short) r1.get(1).getReg());
    }
}
