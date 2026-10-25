package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$ChartViewData$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StatisticActivity.ChartViewData f$0;
    public final /* synthetic */ Utilities.Callback0Return f$1;

    public /* synthetic */ StatisticActivity$ChartViewData$$ExternalSyntheticLambda0(StatisticActivity.ChartViewData r1, Utilities.Callback0Return r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StatisticActivity.ChartViewData.$r8$lambda$drfzCkZoqNOROH7As0VXuIv64Wc(this.f$0, this.f$1, r2, r3);
    }
}
