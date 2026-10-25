package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.QrActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class QrActivity$QrView$$ExternalSyntheticLambda6 implements Utilities.Callback {
    public final /* synthetic */ QrActivity.QrView f$0;

    public /* synthetic */ QrActivity$QrView$$ExternalSyntheticLambda6(QrActivity.QrView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        QrActivity.QrView.m18431$r8$lambda$aiQpmDAnQfHmwODqgCF38RvsI(this.f$0, (TLRPC.TL_exportedContactToken) r1);
    }
}
