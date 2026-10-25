package com.android.dx.dex.code.form;

import com.android.dx.dex.code.DalvInsn;
import com.android.dx.dex.code.InsnFormat;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public final class SpecialFormat extends InsnFormat {
    public static final InsnFormat THE_ONE = null;

    static {
        THE_ONE = new SpecialFormat();
    }

    private SpecialFormat() {
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public int codeSize() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn r1) {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn r1, boolean r2) {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn r1) {
        return true;
    }

    @Override // com.android.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput r1, DalvInsn r2) {
        throw new RuntimeException("unsupported");
    }
}
