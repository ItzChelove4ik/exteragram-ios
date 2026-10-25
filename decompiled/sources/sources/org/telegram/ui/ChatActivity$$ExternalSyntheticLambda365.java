package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda365 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_document f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Object f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda365(ChatActivity r1, TLRPC.TL_document r2, String r3, Object r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r8, int r9, int r10) {
        ChatActivity.$r8$lambda$goNLpqxOSQ1sq1QYfruCIxvNrqI(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
