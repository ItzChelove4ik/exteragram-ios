package org.telegram.ui.Components;

import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranslateAlert2$$ExternalSyntheticLambda1 implements LanguageDetector.ExceptionCallback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ TranslateAlert2$$ExternalSyntheticLambda1(String r1, String r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.LanguageDetector.ExceptionCallback
    public final void run(Exception r3) {
        TranslateAlert2.$r8$lambda$xA6D78R26uj49onSHze_InLCZVE(this.f$0, this.f$1, this.f$2, r3);
    }
}
