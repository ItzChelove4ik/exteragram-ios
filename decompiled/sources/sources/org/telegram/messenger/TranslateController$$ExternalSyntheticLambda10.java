package org.telegram.messenger;

import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda10 implements LanguageDetector.StringCallback {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TranslateController.MessageKey f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda10(TranslateController r1, MessageObject r2, TranslateController.MessageKey r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.LanguageDetector.StringCallback
    public final void run(String r4) {
        TranslateController.$r8$lambda$qHnxYWteY93QUFtKwAHa4CrS05E(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
