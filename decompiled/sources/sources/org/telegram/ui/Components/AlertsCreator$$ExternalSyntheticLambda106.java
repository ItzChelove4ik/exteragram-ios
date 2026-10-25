package org.telegram.ui.Components;

import java.util.function.ToLongFunction;
import org.telegram.messenger.MessageObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda106 implements ToLongFunction {
    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda106() {
    }

    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object r1) {
        return ((MessageObject) r1).getFromChatId();
    }
}
