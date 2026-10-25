package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda289 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Browser.Progress f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda289(ChatActivity r1, TLObject r2, Utilities.Callback2 r3, String r4, Browser.Progress r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatActivity.m7397$r8$lambda$4WawPC9wa1Fwj4YW9qOpvBTpmE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
