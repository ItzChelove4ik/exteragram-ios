package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.PollCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollCreateActivity$2$$ExternalSyntheticLambda0 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ PollCreateActivity.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaToDo f$1;

    public /* synthetic */ PollCreateActivity$2$$ExternalSyntheticLambda0(PollCreateActivity.AnonymousClass2 r1, TLRPC.TL_messageMediaToDo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        PollCreateActivity.AnonymousClass2.$r8$lambda$x8oLSvPJK3fDgcB66mVOViQ4Ak8(this.f$0, this.f$1, r2, r3, r4);
    }
}
