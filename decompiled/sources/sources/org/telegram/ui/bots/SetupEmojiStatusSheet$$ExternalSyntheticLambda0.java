package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda0(Utilities.Callback2 r1, int r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        SetupEmojiStatusSheet.$r8$lambda$VVMpj7sTa0tcE252yeQCFSf86BA(this.f$0, this.f$1, this.f$2, (TLRPC.UserFull) r3);
    }
}
