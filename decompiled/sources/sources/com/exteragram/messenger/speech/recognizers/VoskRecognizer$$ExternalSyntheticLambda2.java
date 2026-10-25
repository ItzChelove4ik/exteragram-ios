package com.exteragram.messenger.speech.recognizers;

import com.exteragram.messenger.speech.VoiceRecognitionController;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
public final /* synthetic */ class VoskRecognizer$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ VoskRecognizer$$ExternalSyntheticLambda2(String r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return VoskRecognizer.$r8$lambda$24CEprXJp1zOKeZzj9XgmV9Hp6M(this.f$0, (VoiceRecognitionController.RecognitionModel) r1);
    }
}
