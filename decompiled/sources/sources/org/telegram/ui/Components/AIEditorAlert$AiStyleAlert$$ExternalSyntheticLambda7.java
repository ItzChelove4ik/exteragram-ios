package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert.AiStyleAlert f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda7(AIEditorAlert.AiStyleAlert r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        AIEditorAlert.AiStyleAlert.$r8$lambda$n_I8DsMzIqTqbiypLPpKh4dCBsw(this.f$0, this.f$1, (TL_aicompose.aiComposeToneExample) r2, (TLRPC.TL_error) r3);
    }
}
