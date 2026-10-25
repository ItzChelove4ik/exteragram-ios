package org.telegram.ui.bots;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UItem;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuggestedAffiliateProgramsFragment$$ExternalSyntheticLambda1 implements Utilities.Callback5 {
    public final /* synthetic */ SuggestedAffiliateProgramsFragment f$0;

    public /* synthetic */ SuggestedAffiliateProgramsFragment$$ExternalSyntheticLambda1(SuggestedAffiliateProgramsFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5
    public final void run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        this.f$0.onItemClick((UItem) r1, (View) r2, ((Integer) r3).intValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue());
    }
}
