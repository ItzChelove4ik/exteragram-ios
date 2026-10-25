package org.telegram.ui.Components;

import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatNotificationsPopupWrapper;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatNotificationsPopupWrapper$$ExternalSyntheticLambda10 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ChatNotificationsPopupWrapper.Callback f$1;

    public /* synthetic */ ChatNotificationsPopupWrapper$$ExternalSyntheticLambda10(int r1, ChatNotificationsPopupWrapper.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        ChatNotificationsPopupWrapper.m9953$r8$lambda$ulLMLuGNd29IMfjHSiZbA83a0c(this.f$0, this.f$1, r2, r3, r4);
    }
}
