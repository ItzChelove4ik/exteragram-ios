package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.Charts.data.ChartData;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageStatisticActivity$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ MessageStatisticActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ ChartData f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TL_stats.TL_loadAsyncGraph f$4;

    public /* synthetic */ MessageStatisticActivity$$ExternalSyntheticLambda13(MessageStatisticActivity r1, TLRPC.TL_error r2, ChartData r3, String r4, TL_stats.TL_loadAsyncGraph r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessageStatisticActivity.$r8$lambda$FY6G72oFyVU6ZzJZ0Yxu8RZoQxo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
