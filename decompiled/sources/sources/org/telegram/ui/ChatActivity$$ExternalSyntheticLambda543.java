package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda543 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda543(ChatActivity r1, Browser.Progress r2, TLObject r3, Utilities.Callback2 r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$sL9dw9DjGuuITYoUiYM4DQArLvk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
