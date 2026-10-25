package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda158 implements Utilities.Callback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda158(MessagesController r1, AlertDialog r2, BaseFragment r3, Runnable r4, long r5, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        MessagesController.m4545$r8$lambda$Ok95vhluHcrFQly5Q_ALdXZypg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TL_account.contentSettings) r9);
    }
}
