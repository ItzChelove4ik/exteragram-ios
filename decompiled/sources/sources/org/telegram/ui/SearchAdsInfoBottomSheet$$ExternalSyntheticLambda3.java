package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class SearchAdsInfoBottomSheet$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ SearchAdsInfoBottomSheet f$0;

    public /* synthetic */ SearchAdsInfoBottomSheet$$ExternalSyntheticLambda3(SearchAdsInfoBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
