package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ View[] f$0;
    public final /* synthetic */ TLRPC.TL_channels_sponsoredMessageReportResultChooseOption f$1;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda14(View[] r1, TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReportBottomSheet.$r8$lambda$E8gLE8NDPDAmCdIxkyetrnDw60s(this.f$0, this.f$1);
    }
}
