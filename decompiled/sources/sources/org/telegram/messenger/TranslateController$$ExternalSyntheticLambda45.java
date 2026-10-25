package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda45 implements RequestDelegate {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TranslateController.MessageKey f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$6;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda45(TranslateController r1, MessageObject r2, String r3, TranslateController.MessageKey r4, Runnable r5, long r6, TLRPC.TL_textWithEntities r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        TranslateController.$r8$lambda$eh7OkDmPplCNgzNP6FqH1c3PZE0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
