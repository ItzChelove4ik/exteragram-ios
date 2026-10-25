package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda172 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda172(ChatActivity r1, String r2, Browser.Progress r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChatActivity.m7558$r8$lambda$x5ngcg00CsR3xpM1o1cwTjcu0(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
