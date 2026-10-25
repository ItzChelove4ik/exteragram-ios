package com.exteragram.messenger.translator.core;

import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.translator.core.TranslationDispatcher;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class HttpTranslator$$ExternalSyntheticLambda0 implements TranslationDispatcher.Completion {
    public final /* synthetic */ TranslatorUtils.TranslateCallback f$0;

    public /* synthetic */ HttpTranslator$$ExternalSyntheticLambda0(TranslatorUtils.TranslateCallback r1) {
        this.f$0 = r1;
    }

    @Override // com.exteragram.messenger.translator.core.TranslationDispatcher.Completion
    public final void onDone(List r1, TranslationError r2) {
        HttpTranslator.$r8$lambda$jSPAM0EKvxpcBjvYueCfZMWI2q4(this.f$0, r1, r2);
    }
}
