package com.exteragram.messenger.ai;

import com.exteragram.messenger.ai.data.Service;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
public final /* synthetic */ class AiController$$ExternalSyntheticLambda2 implements Predicate {
    public /* synthetic */ AiController$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return ((Service) r1).isSelected();
    }
}
