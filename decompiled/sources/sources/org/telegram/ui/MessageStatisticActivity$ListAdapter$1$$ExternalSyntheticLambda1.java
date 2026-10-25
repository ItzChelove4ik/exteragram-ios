package org.telegram.ui;

import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.MessageStatisticActivity;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MessageStatisticActivity.ListAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ ChartData f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ StatisticActivity.ZoomCancelable f$3;

    public /* synthetic */ MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda1(MessageStatisticActivity.ListAdapter.AnonymousClass1 r1, ChartData r2, String r3, StatisticActivity.ZoomCancelable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessageStatisticActivity.ListAdapter.AnonymousClass1.m16217$r8$lambda$rPS94xomKNV6Pt0hx7ipdNAAyA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
