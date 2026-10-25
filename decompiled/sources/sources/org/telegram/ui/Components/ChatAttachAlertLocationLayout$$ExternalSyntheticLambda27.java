package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertLocationLayout$$ExternalSyntheticLambda27 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatAttachAlertLocationLayout f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaGeo f$1;
    public final /* synthetic */ Long f$2;

    public /* synthetic */ ChatAttachAlertLocationLayout$$ExternalSyntheticLambda27(ChatAttachAlertLocationLayout r1, TLRPC.TL_messageMediaGeo r2, Long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r7, int r8, int r9) {
        ChatAttachAlertLocationLayout.m9637$r8$lambda$nrKfJcufyE6jCuFN3eG7eDyrAc(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
