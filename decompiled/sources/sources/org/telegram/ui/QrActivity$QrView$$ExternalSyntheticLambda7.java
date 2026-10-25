package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.QrActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class QrActivity$QrView$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ QrActivity.QrView f$0;
    public final /* synthetic */ TLRPC.TL_exportedContactToken f$1;

    public /* synthetic */ QrActivity$QrView$$ExternalSyntheticLambda7(QrActivity.QrView r1, TLRPC.TL_exportedContactToken r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QrActivity.QrView.$r8$lambda$YbTSJvzn4fMm4qc9mX0pMZwswrE(this.f$0, this.f$1);
    }
}
