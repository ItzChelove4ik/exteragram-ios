package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.MultiCstInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.util.AnnotatedOutput;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form4rcc extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form4rcc();
    }

    private Form4rcc() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 4;
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
    public boolean isCompatible(DalvInsn r4) {
        if ((r4 instanceof MultiCstInsn) == true) goto L5;
        return false;
    L5:
        MultiCstInsn r42 = (MultiCstInsn) r4;
        int r3 = r42.getIndex(0);
        int r2 = r42.getIndex(1);
        if (InsnFormat.unsignedFitsInShort(r3) == true) goto L8;
    L28:
        return false;
    L8:
        if (InsnFormat.unsignedFitsInShort(r2) == false) goto L28;
        if ((r42.getConstant(0) instanceof CstMethodRef) == true) goto L14;
        return false;
    L14:
        if ((r42.getConstant(1) instanceof CstProtoRef) == true) goto L16;
        return false;
    L16:
        RegisterSpecList r32 = r42.getRegisters();
        int r43 = r32.size();
        if (r43 != 0) goto L20;
        return true;
    L20:
        if (InsnFormat.unsignedFitsInByte(r32.getWordCount()) == false) goto L28;
        if (InsnFormat.unsignedFitsInShort(r43) == false) goto L28;
        if (InsnFormat.unsignedFitsInShort(r32.get(0).getReg()) == false) goto L28;
        if (InsnFormat.isRegListSequential(r32) == false) goto L28;
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r5, DalvInsn r6) {
        MultiCstInsn r4 = (MultiCstInsn) r6;
        short r0 = 0;
        short r1 = (short) r4.getIndex(0);
        short r42 = (short) r4.getIndex(1);
        RegisterSpecList r2 = r6.getRegisters();
        if (r2.size() <= 0) goto L5;
        r0 = (short) r2.get(0).getReg();
    L5:
        InsnFormat.write(r5, InsnFormat.opcodeUnit(r6, r2.getWordCount()), r1, r0, r42);
    }
}
