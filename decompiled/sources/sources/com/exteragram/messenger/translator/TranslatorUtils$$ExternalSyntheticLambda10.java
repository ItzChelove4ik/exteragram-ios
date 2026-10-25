package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.TranslatorUtils;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ TranslatorUtils.TranslateCallback f$0;
    public final /* synthetic */ TLRPC.TL_messages_translateResult f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$3;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda10(TranslatorUtils.TranslateCallback r1, TLRPC.TL_messages_translateResult r2, TLRPC.TL_error r3, TLRPC.TL_textWithEntities r4, TLRPC.TL_textWithEntities r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslatorUtils.$r8$lambda$1dOyQLk62lB6Qv0B49yZEIsZQYw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
