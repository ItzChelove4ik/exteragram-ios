package com.android.dx.rop.code;

/* loaded from: classes4.dex */
public final class ConservativeTranslationAdvice implements TranslationAdvice {
    public static final ConservativeTranslationAdvice THE_ONE = null;

    static {
        THE_ONE = new ConservativeTranslationAdvice();
    }

    private ConservativeTranslationAdvice() {
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public int getMaxOptimalRegisterCount() {
        return Integer.MAX_VALUE;
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public boolean hasConstantOperation(Rop r1, RegisterSpec r2, RegisterSpec r3) {
        return false;
    }

    @Override // com.android.dx.rop.code.TranslationAdvice
    public boolean requiresSourcesInOrder(Rop r1, RegisterSpecList r2) {
        return false;
    }
}
