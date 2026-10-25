package org.vosk.android;

import java.io.IOException;
import org.vosk.android.SpeechStreamService;

/* loaded from: classes6.dex */
public final /* synthetic */ class SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ SpeechStreamService.RecognizerThread f$0;
    public final /* synthetic */ IOException f$1;

    public /* synthetic */ SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda2(SpeechStreamService.RecognizerThread r1, IOException r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SpeechStreamService.RecognizerThread.$r8$lambda$QXk3dc4A3NIJpKRkvy8Iaz_fFPw(this.f$0, this.f$1);
    }
}
