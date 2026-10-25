package org.telegram.ui.Cells;

import java.util.function.ToIntFunction;
import org.telegram.messenger.MessageObject;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogCell$$ExternalSyntheticLambda4 implements ToIntFunction {
    public /* synthetic */ DialogCell$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return ((MessageObject) r1).getId();
    }
}
