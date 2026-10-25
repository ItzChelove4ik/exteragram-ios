package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.SimpleInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form23x extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form23x();
    }

    private Form23x() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        BitSet r32 = new BitSet(3);
        r32.set(0, InsnFormat.unsignedFitsInByte(r2.get(0).getReg()));
        r32.set(1, InsnFormat.unsignedFitsInByte(r2.get(1).getReg()));
        r32.set(2, InsnFormat.unsignedFitsInByte(r2.get(2).getReg()));
        return r32;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        return r2.get(0).regString() + ", " + r2.get(1).regString() + ", " + r2.get(2).regString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        if ((r3 instanceof SimpleInsn) == true) goto L5;
    L13:
        return false;
    L5:
        if (r2.size() != 3) goto L13;
        if (InsnFormat.unsignedFitsInByte(r2.get(0).getReg()) == false) goto L13;
        if (InsnFormat.unsignedFitsInByte(r2.get(1).getReg()) == false) goto L13;
        if (InsnFormat.unsignedFitsInByte(r2.get(2).getReg()) == false) goto L13;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, r2.get(0).getReg()), InsnFormat.codeUnit(r2.get(1).getReg(), r2.get(2).getReg()));
    }
}
