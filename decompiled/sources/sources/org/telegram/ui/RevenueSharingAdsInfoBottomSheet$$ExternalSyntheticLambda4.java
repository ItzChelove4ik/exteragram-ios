package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ RevenueSharingAdsInfoBottomSheet f$0;

    public /* synthetic */ RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda4(RevenueSharingAdsInfoBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
