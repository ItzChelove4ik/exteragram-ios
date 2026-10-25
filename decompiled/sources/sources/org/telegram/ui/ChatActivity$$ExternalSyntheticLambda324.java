package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda324 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Browser.Progress f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda324(ChatActivity r1, TLObject r2, String r3, Browser.Progress r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7439$r8$lambda$HL5ShRnQA_lvq8e_v1DEFwTi0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
