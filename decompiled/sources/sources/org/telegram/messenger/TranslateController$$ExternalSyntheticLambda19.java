package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$3;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$4;
    public final /* synthetic */ TranslateController.MessageKey f$5;
    public final /* synthetic */ Runnable f$6;
    public final /* synthetic */ long f$7;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda19(TranslateController r1, MessageObject r2, String r3, TLRPC.TL_textWithEntities r4, TLRPC.TL_textWithEntities r5, TranslateController.MessageKey r6, Runnable r7, long r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslateController.$r8$lambda$ADcmI5GJloTF_8gOssBk1iJ91hw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
