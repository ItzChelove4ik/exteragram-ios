package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.PollCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollCreateActivity$2$$ExternalSyntheticLambda1 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ PollCreateActivity.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaPoll f$1;
    public final /* synthetic */ ArrayList f$2;

    public /* synthetic */ PollCreateActivity$2$$ExternalSyntheticLambda1(PollCreateActivity.AnonymousClass2 r1, TLRPC.TL_messageMediaPoll r2, ArrayList r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r7, int r8, int r9) {
        PollCreateActivity.AnonymousClass2.$r8$lambda$yE72CWMhcndsqOdG5pmvZuDFQA0(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
