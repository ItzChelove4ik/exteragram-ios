package com.android.dx.rop.code;

/* loaded from: classes4.dex */
public interface TranslationAdvice {
    int getMaxOptimalRegisterCount();

    boolean hasConstantOperation(Rop r1, RegisterSpec r2, RegisterSpec r3);

    boolean requiresSourcesInOrder(Rop r1, RegisterSpecList r2);
}
