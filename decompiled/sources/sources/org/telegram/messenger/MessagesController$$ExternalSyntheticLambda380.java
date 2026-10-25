package org.telegram.messenger;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda380 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ TL_bots.BotInfo[] f$8;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda380(MessagesController r1, BaseFragment r2, Browser.Progress r3, boolean[] r4, TLRPC.User r5, String r6, boolean r7, boolean r8, TL_bots.BotInfo[] r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$79n4rXHfBhwk19p5ve6pGqebE7I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
