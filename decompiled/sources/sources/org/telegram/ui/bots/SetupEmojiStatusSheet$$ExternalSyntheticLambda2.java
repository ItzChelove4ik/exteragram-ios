package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda2 implements AnimatedEmojiDrawable.ReceivedDocument {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda2(int r1, TLRPC.User r2, int r3, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.ReceivedDocument
    public final void run(TLRPC.Document r4) {
        SetupEmojiStatusSheet.$r8$lambda$8KJI1aMWJ2V4l1nA0nvHuA_Qvqc(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
