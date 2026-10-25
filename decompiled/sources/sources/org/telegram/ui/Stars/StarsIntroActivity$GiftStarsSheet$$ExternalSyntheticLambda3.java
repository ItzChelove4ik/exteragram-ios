package org.telegram.ui.Stars;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ StarsIntroActivity.GiftStarsSheet f$0;

    public /* synthetic */ StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda3(StarsIntroActivity.GiftStarsSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
