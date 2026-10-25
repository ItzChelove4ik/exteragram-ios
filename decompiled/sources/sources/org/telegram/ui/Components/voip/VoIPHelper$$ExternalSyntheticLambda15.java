package org.telegram.ui.Components.voip;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPHelper$$ExternalSyntheticLambda15 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ TLRPC.Chat f$0;
    public final /* synthetic */ TLRPC.InputPeer f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ AccountInstance f$3;

    public /* synthetic */ VoIPHelper$$ExternalSyntheticLambda15(TLRPC.Chat r1, TLRPC.InputPeer r2, BaseFragment r3, AccountInstance r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r4) {
        VoIPHelper.m13962$r8$lambda$kMm0kcmkBGJnCiOp19D94oJaDk(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
