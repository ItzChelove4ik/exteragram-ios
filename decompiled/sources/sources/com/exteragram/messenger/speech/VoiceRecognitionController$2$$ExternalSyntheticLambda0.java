package com.exteragram.messenger.speech;

import com.exteragram.messenger.speech.VoiceRecognitionController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class VoiceRecognitionController$2$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ VoiceRecognitionController.AnonymousClass2 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ VoiceRecognitionController.RecognitionCallback f$2;

    public /* synthetic */ VoiceRecognitionController$2$$ExternalSyntheticLambda0(VoiceRecognitionController.AnonymousClass2 r1, String r2, VoiceRecognitionController.RecognitionCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        VoiceRecognitionController.AnonymousClass2.$r8$lambda$8ZL3l8dB5ScLrRgJ4T3RmflmM5U(this.f$0, this.f$1, this.f$2, (String) r3);
    }
}
