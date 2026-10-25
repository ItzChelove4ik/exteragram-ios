package com.exteragram.messenger.translator;

import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda17 implements LanguageDetector.StringCallback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda17(String r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.LanguageDetector.StringCallback
    public final void run(String r2) {
        TranslatorUtils.$r8$lambda$2xKhWwpI4GW7E2vW__OfQr9ITvc(this.f$0, this.f$1, r2);
    }
}
