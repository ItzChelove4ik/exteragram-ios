package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.TranslatorUtils;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ CharSequence f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TranslatorUtils.TranslateCallback f$2;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$3;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda5(CharSequence r1, String r2, TranslatorUtils.TranslateCallback r3, TLRPC.TL_textWithEntities r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        TranslatorUtils.m1739$r8$lambda$d4RRdZ2YONVAqBl9t6YkN7hzKI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
