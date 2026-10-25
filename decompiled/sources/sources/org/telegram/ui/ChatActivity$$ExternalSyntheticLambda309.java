package org.telegram.ui;

import android.text.style.CharacterStyle;
import com.exteragram.messenger.proxy.IpAddressInfoController;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda309 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ ChatMessageCell f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ IpAddressInfoController.IpAddressInfo f$4;
    public final /* synthetic */ CharacterStyle f$5;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda309(ChatActivity r1, Browser.Progress r2, ChatMessageCell r3, String r4, IpAddressInfoController.IpAddressInfo r5, CharacterStyle r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$D1PTRaQEfMHGeOOMMte8EPmwZkM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
