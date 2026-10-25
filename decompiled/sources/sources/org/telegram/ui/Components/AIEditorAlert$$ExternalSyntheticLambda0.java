package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert f$0;
    public final /* synthetic */ SimpleTextView f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_messages_composeMessageWithAI f$3;

    public /* synthetic */ AIEditorAlert$$ExternalSyntheticLambda0(AIEditorAlert r1, SimpleTextView r2, int r3, TLRPC.TL_messages_composeMessageWithAI r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        AIEditorAlert.$r8$lambda$hL9Ihujg2ElScJ3DSpdGaIiPreM(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_composedMessageWithAI) r7, (TLRPC.TL_error) r8);
    }
}
