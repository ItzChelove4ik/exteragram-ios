package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda337 implements Utilities.Callback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ boolean[] f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda337(MessagesController r1, Browser.Progress r2, AlertDialog[] r3, BaseFragment r4, boolean[] r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        MessagesController.$r8$lambda$4rHJ76ILXNTsHCkQBInvSttkiLI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Long) r8);
    }
}
