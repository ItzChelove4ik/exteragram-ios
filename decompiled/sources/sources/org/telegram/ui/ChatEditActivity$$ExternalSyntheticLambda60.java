package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$$ExternalSyntheticLambda60 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ ChatEditActivity f$0;

    public /* synthetic */ ChatEditActivity$$ExternalSyntheticLambda60(ChatEditActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        ChatEditActivity.m8252$r8$lambda$HwfupOISpa1cSrTFROY9pnttHQ(this.f$0, r1, r2, r3, r4, r5);
    }
}
