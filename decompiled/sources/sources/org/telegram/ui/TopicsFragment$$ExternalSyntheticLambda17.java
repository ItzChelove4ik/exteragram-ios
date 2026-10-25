package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsFragment$$ExternalSyntheticLambda17 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ TopicsFragment f$0;

    public /* synthetic */ TopicsFragment$$ExternalSyntheticLambda17(TopicsFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r1) {
        return TopicsFragment.$r8$lambda$w9MVWORz_4TzUrChZDlKAc2Tqug(this.f$0, r1);
    }
}
