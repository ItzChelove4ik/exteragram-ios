package org.telegram.ui.Components.poll.sheets;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;

/* loaded from: classes7.dex */
public final /* synthetic */ class PollStatisticsBottomSheet$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ PollStatisticsBottomSheet$$ExternalSyntheticLambda1(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        PollStatisticsBottomSheet.$r8$lambda$CPtCpfixfQfvgZoEvcjkmP193dU(this.f$0, (TL_stats.TL_statsPollStats) r1, (TLRPC.TL_error) r2);
    }
}
