package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ TLRPC.UserFull f$3;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda12(TLObject r1, boolean[] r2, Utilities.Callback2 r3, TLRPC.UserFull r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SetupEmojiStatusSheet.m21487$r8$lambda$HIic6tNxbal5Rj9WJRo00n2yQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
