package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda55 implements Utilities.Callback {
    public final /* synthetic */ PaintView f$0;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda55(PaintView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.onAudioSelect((MessageObject) r1);
    }
}
