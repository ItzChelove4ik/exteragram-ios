package org.telegram.ui.Components;

import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AutoDeletePopupWrapper;

/* loaded from: classes7.dex */
public final /* synthetic */ class AutoDeletePopupWrapper$$ExternalSyntheticLambda8 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ AutoDeletePopupWrapper.Callback f$0;

    public /* synthetic */ AutoDeletePopupWrapper$$ExternalSyntheticLambda8(AutoDeletePopupWrapper.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r1, int r2, int r3) {
        AutoDeletePopupWrapper.$r8$lambda$ig1Q43uSiMGmQ1UTE_nPDQaIV70(this.f$0, r1, r2, r3);
    }
}
