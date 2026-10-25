package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.TranslatorUtils;
import java.util.ArrayList;
import org.telegram.messenger.LanguageDetector;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda12 implements LanguageDetector.ExceptionCallback {
    public final /* synthetic */ CharSequence f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TranslatorUtils.TranslateCallback f$3;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda12(CharSequence r1, String r2, ArrayList r3, TranslatorUtils.TranslateCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.LanguageDetector.ExceptionCallback
    public final void run(Exception r4) {
        TranslatorUtils.m1740$r8$lambda$ewBzqNshujzN4u5khqF8JrQ9SQ(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
