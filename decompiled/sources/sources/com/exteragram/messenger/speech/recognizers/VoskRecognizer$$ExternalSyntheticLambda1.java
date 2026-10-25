package com.exteragram.messenger.speech.recognizers;

import java.util.function.Consumer;
import org.vosk.Model;

/* loaded from: classes4.dex */
public final /* synthetic */ class VoskRecognizer$$ExternalSyntheticLambda1 implements Consumer {
    public /* synthetic */ VoskRecognizer$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object r1) {
        ((Model) r1).close();
    }
}
