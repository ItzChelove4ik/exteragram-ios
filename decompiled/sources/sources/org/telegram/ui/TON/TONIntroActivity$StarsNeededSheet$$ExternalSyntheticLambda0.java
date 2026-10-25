package org.telegram.ui.TON;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.TON.TONIntroActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ TONIntroActivity.StarsNeededSheet f$0;

    public /* synthetic */ TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda0(TONIntroActivity.StarsNeededSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
