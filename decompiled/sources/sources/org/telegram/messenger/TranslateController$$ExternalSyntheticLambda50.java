package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda50 implements RequestDelegate {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TranslateController.PendingPollTranslation f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda50(TranslateController r1, TranslateController.PendingPollTranslation r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        TranslateController.$r8$lambda$PoGYF4nwN9A4UedUBg1YfN5r2Jw(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
