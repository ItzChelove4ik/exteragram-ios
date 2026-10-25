package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda8 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaPoll f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ Long f$3;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda8(ChatAttachAlertPollLayout r1, TLRPC.TL_messageMediaPoll r2, ArrayList r3, Long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r8, int r9, int r10) {
        ChatAttachAlertPollLayout.$r8$lambda$DnIrJP0mIWPWc0hQYkCmrvhHPCg(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
