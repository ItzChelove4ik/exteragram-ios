package org.telegram.messenger;

import android.os.Bundle;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda105 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ Bundle f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda105(MessagesController r1, AlertDialog r2, Browser.Progress r3, BaseFragment r4, Bundle r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagesController.$r8$lambda$SVAzQrpchvurrK9paC9DvoNOPb8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
