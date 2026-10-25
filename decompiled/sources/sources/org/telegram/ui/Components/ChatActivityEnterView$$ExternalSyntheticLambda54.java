package org.telegram.ui.Components;

import android.graphics.Canvas;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda54 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivityEnterView f$0;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda54(ChatActivityEnterView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.drawMessageEditText((Canvas) r1, (Utilities.Callback0Return) r2);
    }
}
