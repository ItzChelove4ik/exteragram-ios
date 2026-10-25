package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ DeleteMessagesBottomSheet f$0;

    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda3(DeleteMessagesBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        DeleteMessagesBottomSheet.$r8$lambda$WfRAz7HB3tQPK1RX3c4526Xsr_I(this.f$0, (TL_communities.ParticipantJoinedChats) r1, (TLRPC.TL_error) r2);
    }
}
