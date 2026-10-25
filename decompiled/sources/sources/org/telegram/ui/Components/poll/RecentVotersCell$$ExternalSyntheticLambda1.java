package org.telegram.ui.Components.poll;

import org.telegram.ui.Components.poll.RecentVotersCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class RecentVotersCell$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ RecentVotersCell.VotesList f$0;

    public /* synthetic */ RecentVotersCell$$ExternalSyntheticLambda1(RecentVotersCell.VotesList r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.load();
    }
}
