package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatNotificationsPopupWrapper$$ExternalSyntheticLambda18 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChatNotificationsPopupWrapper$$ExternalSyntheticLambda18(int r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        ChatNotificationsPopupWrapper.$r8$lambda$opmPkaODgju1UTHqqmVkZMVRdmg(this.f$0, this.f$1, r2, r3, r4);
    }
}
