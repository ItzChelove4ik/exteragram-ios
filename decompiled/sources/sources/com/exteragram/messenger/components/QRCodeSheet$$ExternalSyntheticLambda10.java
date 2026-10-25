package com.exteragram.messenger.components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class QRCodeSheet$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ QRCodeSheet f$0;

    public /* synthetic */ QRCodeSheet$$ExternalSyntheticLambda10(QRCodeSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        QRCodeSheet.$r8$lambda$9Pqkc7nOqaUvVlzP8V9elIcmB0c(this.f$0, r1, r2);
    }
}
