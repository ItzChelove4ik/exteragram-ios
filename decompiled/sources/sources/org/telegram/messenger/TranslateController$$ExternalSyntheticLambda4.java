package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TranslateController.PendingPollTranslation f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda4(TranslateController r1, TranslateController.PendingPollTranslation r2, TLObject r3, TLRPC.TL_error r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslateController.$r8$lambda$1M2Tlh8xzM5vpXml4yptXSURxhc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
