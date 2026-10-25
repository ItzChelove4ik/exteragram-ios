package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda127 implements Utilities.Callback3 {
    public final /* synthetic */ ChatActivityEnterView f$0;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda127(ChatActivityEnterView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r1, Object r2, Object r3) {
        this.f$0.insertAiResponse((String) r1, (CharSequence) r2, (TL_iv.RichMessage) r3);
    }
}
