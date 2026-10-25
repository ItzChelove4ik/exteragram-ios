package com.android.dx.rop.code;

import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.type.Type;

/* loaded from: classes4.dex */
public final class DexTranslationAdvice implements TranslationAdvice {
    private static final int MIN_INVOKE_IN_ORDER = 6;
    public static final DexTranslationAdvice NO_SOURCES_IN_ORDER = null;
    public static final DexTranslationAdvice THE_ONE = null;
    private final boolean disableSourcesInOrder;

    static {
        THE_ONE = new DexTranslationAdvice();
        NO_SOURCES_IN_ORDER = new DexTranslationAdvice(true);
    }

    private DexTranslationAdvice() {
        this.disableSourcesInOrder = false;
    }

    private DexTranslationAdvice(boolean r1) {
        this.disableSourcesInOrder = r1;
    }

    private int totalRopWidth(RegisterSpecList r4) {
        int r3 = r4.size();
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r3) goto L5;
        r1 = r1 + r4.get(r0).getCategory();
        r0 = r0 + 1;
        goto L3
    L5:
        return r1;
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public int getMaxOptimalRegisterCount() {
        return 16;
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public boolean hasConstantOperation(Rop r3, RegisterSpec r4, RegisterSpec r5) {
        if (r4.getType() == Type.INT) goto L6;
        return false;
    L6:
        if ((r5.getTypeBearer() instanceof CstInteger) == false) goto L8;
        CstInteger r2 = (CstInteger) r5.getTypeBearer();
        switch(r3.getOpcode()) {
            case 14: goto L22;
            case 15: goto L20;
            case 16: goto L22;
            case 17: goto L22;
            case 18: goto L22;
            case 19: goto L16;
            case 20: goto L22;
            case 21: goto L22;
            case 22: goto L22;
            case 23: goto L18;
            case 24: goto L18;
            case 25: goto L18;
            default: goto L16;
        };
    L16:
        return false;
    L18:
        return r2.fitsIn8Bits();
    L20:
        return CstInteger.make(-r2.getValue()).fitsIn16Bits();
    L22:
        return r2.fitsIn16Bits();
    L8:
        if ((r4.getTypeBearer() instanceof CstInteger) == true) goto L10;
    L13:
        return false;
    L10:
        if (r3.getOpcode() != 15) goto L13;
        return ((CstInteger) r4.getTypeBearer()).fitsIn16Bits();
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public boolean requiresSourcesInOrder(Rop r2, RegisterSpecList r3) {
        if (this.disableSourcesInOrder == false) goto L5;
        return false;
    L5:
        if (r2.isCallLike() == true) goto L7;
        return false;
    L7:
        if (totalRopWidth(r3) < 6) goto L13;
        return true;
    L13:
        return false;
    }
}
