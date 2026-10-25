package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class CaptionContainerView$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ CaptionContainerView f$0;

    public /* synthetic */ CaptionContainerView$$ExternalSyntheticLambda2(CaptionContainerView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.updateKeyboard(((Integer) r1).intValue());
    }
}
