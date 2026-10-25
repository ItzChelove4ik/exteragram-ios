package com.exteragram.messenger.plugins.utils;

import java.util.function.Function;
import org.mvel2.MVEL;

/* loaded from: classes4.dex */
public final /* synthetic */ class ClassProxy$$ExternalSyntheticLambda2 implements Function {
    public /* synthetic */ ClassProxy$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return MVEL.compileExpression((String) r1);
    }
}
