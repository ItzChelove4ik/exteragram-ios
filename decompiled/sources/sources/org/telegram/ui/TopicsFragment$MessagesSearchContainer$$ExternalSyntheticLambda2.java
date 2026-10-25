package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ TopicsFragment.MessagesSearchContainer f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda2(TopicsFragment.MessagesSearchContainer r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        TopicsFragment.MessagesSearchContainer.$r8$lambda$Q47Uc8jFAbkSODfGSLArtjxZVwM(this.f$0, this.f$1, r2, r3);
    }
}
