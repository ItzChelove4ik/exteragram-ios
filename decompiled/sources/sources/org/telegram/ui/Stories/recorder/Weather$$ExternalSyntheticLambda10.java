package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda10 implements Utilities.Callback {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ Weather$$ExternalSyntheticLambda10(boolean r1, AlertDialog r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        Weather.$r8$lambda$Fj5il8a0wiK9X50oPwXxScWnjE0(this.f$0, this.f$1, this.f$2, (Weather.State) r3);
    }
}
