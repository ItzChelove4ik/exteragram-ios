package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda404 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ Utilities.Callback2 f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ Browser.Progress f$6;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda404(ChatActivity r1, TLRPC.TL_error r2, TLObject r3, TLObject r4, Utilities.Callback2 r5, String r6, Browser.Progress r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7538$r8$lambda$qeeGjXjsOVSMoNH7CrKDpGbpVg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
