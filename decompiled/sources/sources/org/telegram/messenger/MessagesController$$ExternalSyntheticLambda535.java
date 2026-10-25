package org.telegram.messenger;

import android.os.Bundle;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda535 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ Bundle f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda535(MessagesController r1, AlertDialog r2, Browser.Progress r3, TLObject r4, BaseFragment r5, Bundle r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$jkvrLqC_etGMGg4LcpLObEFNMuw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
