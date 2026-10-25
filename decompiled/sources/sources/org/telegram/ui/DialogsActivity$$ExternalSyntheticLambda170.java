package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda170 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda170(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r1) {
        return DialogsActivity.m14391$r8$lambda$ffslDi3c2P20GY_W_Ju4ZWIru4(this.f$0, r1);
    }
}
