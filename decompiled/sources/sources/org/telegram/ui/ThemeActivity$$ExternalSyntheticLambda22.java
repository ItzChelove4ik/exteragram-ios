package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$$ExternalSyntheticLambda22 implements Utilities.Callback {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Utilities.Callback f$6;
    public final /* synthetic */ BottomSheet[] f$7;

    public /* synthetic */ ThemeActivity$$ExternalSyntheticLambda22(ButtonWithCounterView r1, MessagesController r2, int r3, Context r4, Theme.ResourcesProvider r5, int r6, Utilities.Callback r7, BottomSheet[] r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r10) {
        ThemeActivity.$r8$lambda$688AsBZjZXDcD1S0Yj_iDabMF5Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (Long) r10);
    }
}
