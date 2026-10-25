package org.telegram.ui;

import java.util.function.ToLongFunction;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$$ExternalSyntheticLambda4 implements ToLongFunction {
    public /* synthetic */ StatisticActivity$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object r1) {
        return ((StatisticActivity.RecentPostInfo) r1).getDate();
    }
}
