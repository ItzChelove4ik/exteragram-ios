package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ AIEditorAlert$$ExternalSyntheticLambda1(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        AIEditorAlert.$r8$lambda$HTfHzDmT0dyTrwboeNH1gbHEPmc(this.f$0, (TLRPC.TL_composedMessageWithAI) r1);
    }
}
