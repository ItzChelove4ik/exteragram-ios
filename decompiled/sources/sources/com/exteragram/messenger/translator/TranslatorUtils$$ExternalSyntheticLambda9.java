package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.TranslatorUtils;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ TranslatorUtils.TranslateCallback f$0;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda9(TranslatorUtils.TranslateCallback r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onFailed();
    }
}
