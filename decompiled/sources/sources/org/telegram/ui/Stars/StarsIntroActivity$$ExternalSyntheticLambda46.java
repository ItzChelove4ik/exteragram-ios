package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda46 implements Utilities.Callback {
    public final /* synthetic */ BottomSheet f$0;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda46(BottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.dismiss(((Boolean) r1).booleanValue());
    }
}
