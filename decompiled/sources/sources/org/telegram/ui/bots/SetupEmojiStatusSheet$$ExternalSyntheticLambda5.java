package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ TLRPC.UserFull f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda5(TLRPC.UserFull r1, Utilities.Callback2 r2, int r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SetupEmojiStatusSheet.$r8$lambda$84d1K1TcTg3Uc_f8QlS39OyHBJQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
