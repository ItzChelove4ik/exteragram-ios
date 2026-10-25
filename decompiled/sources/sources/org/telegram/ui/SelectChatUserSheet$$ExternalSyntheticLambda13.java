package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda13 implements MessagesStorage.LongCallback {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$2;
    public final /* synthetic */ TwoStepVerificationActivity f$3;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda13(SelectChatUserSheet r1, TLRPC.User r2, TLRPC.InputCheckPasswordSRP r3, TwoStepVerificationActivity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r7) {
        SelectChatUserSheet.$r8$lambda$ukUd8PnNXWaSzWS6l66e5oGsPC8(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
