package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.dex.code.SimpleInsn;
import com.android.dx.util.AnnotatedOutput;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class Form10x extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new Form10x();
    }

    private Form10x() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        return 1;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r1) {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r1) {
        if ((r1 instanceof SimpleInsn) == true) goto L5;
        return false;
    L5:
        if (r1.getRegisters().size() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r1, DalvInsn r2) {
        InsnFormat.write(r1, InsnFormat.opcodeUnit(r2, 0));
    }
}
