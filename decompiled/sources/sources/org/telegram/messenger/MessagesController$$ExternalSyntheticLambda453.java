package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda453 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_channels_editAdmin f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda453(MessagesController r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_channels_editAdmin r4, boolean r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4547$r8$lambda$P0aCGQ3hj9fYbbIUMd6MGhL9fw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
