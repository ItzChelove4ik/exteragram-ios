package com.exteragram.messenger.speech.ui;

import com.exteragram.messenger.speech.VoiceRecognitionController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class RecognitionModelDialogs$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ VoiceRecognitionController.RecognitionModel f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ BaseFragment f$2;

    public /* synthetic */ RecognitionModelDialogs$$ExternalSyntheticLambda1(VoiceRecognitionController.RecognitionModel r1, Utilities.Callback r2, BaseFragment r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        RecognitionModelDialogs.$r8$lambda$JZVluijCixlfmQt3ZxCjOuc9eSc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
