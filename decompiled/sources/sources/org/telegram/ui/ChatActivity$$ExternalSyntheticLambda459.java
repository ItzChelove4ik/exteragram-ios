package org.telegram.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.LanguageDetector;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda459 implements LanguageDetector.ExceptionCallback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ AtomicReference f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda459(AtomicBoolean r1, AtomicReference r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.LanguageDetector.ExceptionCallback
    public final void run(Exception r2) {
        ChatActivity.m7503$r8$lambda$eL8El1D3y4Im3i0QfEcv3rAZVU(this.f$0, this.f$1, r2);
    }
}
