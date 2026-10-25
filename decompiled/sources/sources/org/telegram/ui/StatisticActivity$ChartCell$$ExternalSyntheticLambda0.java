package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$ChartCell$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StatisticActivity.ChartCell f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ StatisticActivity.ZoomCancelable f$2;

    public /* synthetic */ StatisticActivity$ChartCell$$ExternalSyntheticLambda0(StatisticActivity.ChartCell r1, String r2, StatisticActivity.ZoomCancelable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StatisticActivity.ChartCell.$r8$lambda$BENT6UP3jyKGcCzUpqnD82nSX4k(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
