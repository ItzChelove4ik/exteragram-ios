package org.telegram.ui.Components.poll;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.poll.RecentVotersCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class RecentVotersCell$VotesList$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ RecentVotersCell.VotesList f$0;

    public /* synthetic */ RecentVotersCell$VotesList$$ExternalSyntheticLambda1(RecentVotersCell.VotesList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        RecentVotersCell.VotesList.$r8$lambda$4OJByseeIpYcoIIDtI20t_oAxtM(this.f$0, (TLRPC.TL_messages_votesList) r1, (TLRPC.TL_error) r2);
    }
}
