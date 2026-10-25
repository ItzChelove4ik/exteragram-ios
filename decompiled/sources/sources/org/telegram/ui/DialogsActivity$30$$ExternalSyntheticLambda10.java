package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$30$$ExternalSyntheticLambda10 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ DialogsActivity$30$$ExternalSyntheticLambda10(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r1) {
        return DialogsActivity.AnonymousClass30.m14635$r8$lambda$6QAWGFYN_hifaceXl3AGN9LOoE(this.f$0, r1);
    }
}
