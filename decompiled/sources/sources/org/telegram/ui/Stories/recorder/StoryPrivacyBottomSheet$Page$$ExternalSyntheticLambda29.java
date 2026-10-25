package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Components.CreateRtmpStreamBottomSheet;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda29 implements Utilities.Callback {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ CreateRtmpStreamBottomSheet[] f$1;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda29(StoryPrivacyBottomSheet.Page r1, CreateRtmpStreamBottomSheet[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        StoryPrivacyBottomSheet.Page.$r8$lambda$0D_68wto3HB4nBmb6C01pDEfP70(this.f$0, this.f$1, (Browser.Progress) r2);
    }
}
