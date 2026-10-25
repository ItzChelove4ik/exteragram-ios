package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.TL_help_appConfig f$1;

    public /* synthetic */ MessagesController$1$$ExternalSyntheticLambda1(int r1, TLRPC.TL_help_appConfig r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.AnonymousClass1.$r8$lambda$3C9uL85dfSsEYcOSo_LyIWzvBC4(this.f$0, this.f$1);
    }
}
