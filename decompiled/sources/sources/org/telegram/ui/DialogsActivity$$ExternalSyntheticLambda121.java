package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda121 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda121(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r1) {
        return DialogsActivity.$r8$lambda$B5Qy2nwztgDMMnVNlhDMmOmqjBQ(this.f$0, r1);
    }
}
