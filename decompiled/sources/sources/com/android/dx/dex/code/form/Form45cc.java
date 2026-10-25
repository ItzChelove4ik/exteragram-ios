package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.MultiCstInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.type.Type;
import com.android.dx.util.AnnotatedOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form45cc extends InsnFormat {
    private static final int MAX_NUM_OPS = 5;
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form45cc();
    }

    private Form45cc() {
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
        return 4;
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
    public boolean isCompatible(DalvInsn r4) {
        if ((r4 instanceof MultiCstInsn) == true) goto L5;
        return false;
    L5:
        MultiCstInsn r42 = (MultiCstInsn) r4;
        if (r42.getNumberOfConstants() == 2) goto L8;
        return false;
    L8:
        int r3 = r42.getIndex(0);
        int r2 = r42.getIndex(1);
        if (InsnFormat.unsignedFitsInShort(r3) == true) goto L11;
    L22:
        return false;
    L11:
        if (InsnFormat.unsignedFitsInShort(r2) == false) goto L22;
        if ((r42.getConstant(0) instanceof CstMethodRef) == true) goto L17;
        return false;
    L17:
        if ((r42.getConstant(1) instanceof CstProtoRef) == true) goto L20;
        return false;
    L20:
        if (wordCount(r42.getRegisters()) < 0) goto L22;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r10, DalvInsn r11) {
        MultiCstInsn r9 = (MultiCstInsn) r11;
        int r0 = 0;
        short r1 = (short) r9.getIndex(0);
        short r92 = (short) r9.getIndex(1);
        RegisterSpecList r3 = explicitize(r11.getRegisters());
        int r4 = r3.size();
        if (r4 <= 0) goto L5;
        int r5 = r3.get(0).getReg();
    L6:
        if (r4 <= 1) goto L8;
        int r2 = r3.get(1).getReg();
    L10:
        if (r4 <= 2) goto L12;
        int r6 = r3.get(2).getReg();
    L14:
        if (r4 <= 3) goto L16;
        int r7 = r3.get(3).getReg();
    L18:
        if (r4 <= 4) goto L20;
        r0 = r3.get(4).getReg();
    L20:
        InsnFormat.write(r10, InsnFormat.opcodeUnit(r11, InsnFormat.makeByte(r0, r4)), r1, InsnFormat.codeUnit(r5, r2, r6, r7), r92);
        return;
    L16:
        r7 = 0;
        goto L18
    L12:
        r6 = 0;
        goto L14
    L8:
        r2 = 0;
        goto L10
    L5:
        r5 = 0;
        goto L6
    }
}
