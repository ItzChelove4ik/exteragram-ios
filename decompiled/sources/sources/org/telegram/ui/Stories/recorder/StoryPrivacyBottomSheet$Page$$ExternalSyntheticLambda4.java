package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda4(StoryPrivacyBottomSheet.Page r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.onSearch((String) r1);
    }
}
