package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ View[] f$0;
    public final /* synthetic */ TLRPC.TL_reportResultAddComment f$1;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda15(View[] r1, TLRPC.TL_reportResultAddComment r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReportBottomSheet.$r8$lambda$PPSjjvmWMonRZbPSMqlQPPL9uOM(this.f$0, this.f$1);
    }
}
