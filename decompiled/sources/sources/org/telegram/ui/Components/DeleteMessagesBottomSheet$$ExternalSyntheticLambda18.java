package org.telegram.ui.Components;

import java.util.function.Function;
import org.telegram.messenger.MessageObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda18 implements Function {
    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda18() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return Integer.valueOf(((MessageObject) r1).getId());
    }
}
