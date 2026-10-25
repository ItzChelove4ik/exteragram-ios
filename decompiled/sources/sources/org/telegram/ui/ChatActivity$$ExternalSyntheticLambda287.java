package org.telegram.ui;

import android.view.View;
import java.util.HashMap;
import java.util.function.ToIntFunction;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda287 implements ToIntFunction {
    public final /* synthetic */ HashMap f$0;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda287(HashMap r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return ((Integer) this.f$0.get((View) r1)).intValue();
    }
}
