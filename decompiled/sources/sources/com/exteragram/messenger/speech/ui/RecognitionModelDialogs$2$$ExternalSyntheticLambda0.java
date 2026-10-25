package com.exteragram.messenger.speech.ui;

import com.exteragram.messenger.speech.VoiceRecognitionController;
import com.exteragram.messenger.speech.ui.RecognitionModelDialogs;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class RecognitionModelDialogs$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ VoiceRecognitionController.RecognitionModel f$1;
    public final /* synthetic */ BaseFragment f$2;

    public /* synthetic */ RecognitionModelDialogs$2$$ExternalSyntheticLambda0(Utilities.Callback r1, VoiceRecognitionController.RecognitionModel r2, BaseFragment r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RecognitionModelDialogs.AnonymousClass2.$r8$lambda$iCzzdvgxoGUtZCylTyNKqb7oGIc(this.f$0, this.f$1, this.f$2);
    }
}
