package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatRightsEditActivity$$ExternalSyntheticLambda20 implements MessagesStorage.LongCallback {
    public final /* synthetic */ ChatRightsEditActivity f$0;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$1;
    public final /* synthetic */ TwoStepVerificationActivity f$2;

    public /* synthetic */ ChatRightsEditActivity$$ExternalSyntheticLambda20(ChatRightsEditActivity r1, TLRPC.InputCheckPasswordSRP r2, TwoStepVerificationActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r3) {
        ChatRightsEditActivity.$r8$lambda$T2v2sK8BEQiwFGDBwvmDrclCybE(this.f$0, this.f$1, this.f$2, r3);
    }
}
