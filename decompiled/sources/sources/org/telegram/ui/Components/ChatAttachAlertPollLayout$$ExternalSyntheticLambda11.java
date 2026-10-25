package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda11 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaToDo f$1;
    public final /* synthetic */ Long f$2;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda11(ChatAttachAlertPollLayout r1, TLRPC.TL_messageMediaToDo r2, Long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r7, int r8, int r9) {
        ChatAttachAlertPollLayout.m9838$r8$lambda$rc5eewhVL8hEIqjksDxD7wSDfw(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
