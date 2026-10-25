package org.telegram.ui.Stories;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda38 implements Utilities.Callback {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TL_stories.TL_premium_boostsStatus f$3;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda38(StoriesController r1, Utilities.Callback r2, long r3, TL_stories.TL_premium_boostsStatus r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        StoriesController.$r8$lambda$Ocr8ImeJ81BiRms34CtBrXEHODo(this.f$0, this.f$1, this.f$2, this.f$3, (ChannelBoostsController.CanApplyBoost) r7);
    }
}
