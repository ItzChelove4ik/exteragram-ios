package org.telegram.ui;

import org.telegram.messenger.browser.Browser;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ Browser.Progress f$0;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16(Browser.Progress r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.end();
    }
}
