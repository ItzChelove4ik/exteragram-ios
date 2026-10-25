package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.SimpleInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class Form12x extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form12x();
    }

    private Form12x() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 1;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn r8) {
        RegisterSpecList r7 = r8.getRegisters();
        BitSet r82 = new BitSet(2);
        int r2 = r7.get(0).getReg();
        int r4 = r7.get(1).getReg();
        int r5 = r7.size();
        if (r5 != 2) goto L5;
        r82.set(0, InsnFormat.unsignedFitsInNibble(r2));
        r82.set(1, InsnFormat.unsignedFitsInNibble(r4));
        return r82;
    L5:
        if (r5 != 3) goto L11;
        if (r2 == r4) goto L8;
        r82.set(0, false);
        r82.set(1, false);
    L9:
        r82.set(2, InsnFormat.unsignedFitsInNibble(r7.get(2).getReg()));
        return r82;
    L8:
        boolean r22 = InsnFormat.unsignedFitsInNibble(r4);
        r82.set(0, r22);
        r82.set(1, r22);
        goto L9
    L11:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r3) {
        RegisterSpecList r2 = r3.getRegisters();
        int r32 = r2.size();
        return r2.get(r32 - 2).regString() + ", " + r2.get(r32 - 1).regString();
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r5) {
        if ((r5 instanceof SimpleInsn) == true) goto L5;
        return false;
    L5:
        RegisterSpecList r4 = r5.getRegisters();
        int r52 = r4.size();
        if (r52 != 2) goto L8;
        RegisterSpec r53 = r4.get(0);
        RegisterSpec r1 = r4.get(1);
    L15:
        if (InsnFormat.unsignedFitsInNibble(r53.getReg()) == true) goto L17;
    L19:
        return false;
    L17:
        if (InsnFormat.unsignedFitsInNibble(r1.getReg()) == false) goto L19;
        return true;
    L8:
        if (r52 == 3) goto L10;
        return false;
    L10:
        r53 = r4.get(1);
        r1 = r4.get(2);
        if (r53.getReg() == r4.get(0).getReg()) goto L15;
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r3, DalvInsn r4) {
        RegisterSpecList r2 = r4.getRegisters();
        int r0 = r2.size();
        InsnFormat.write(r3, InsnFormat.opcodeUnit(r4, InsnFormat.makeByte(r2.get(r0 - 2).getReg(), r2.get(r0 - 1).getReg())));
    }
}
