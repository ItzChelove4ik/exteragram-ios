package org.telegram.ui.Components.poll;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.poll.RecentVotersCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class RecentVotersCell$$ExternalSyntheticLambda2 implements Utilities.Callback2 {
    public final /* synthetic */ RecentVotersCell.VotesList f$0;

    public /* synthetic */ RecentVotersCell$$ExternalSyntheticLambda2(RecentVotersCell.VotesList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
