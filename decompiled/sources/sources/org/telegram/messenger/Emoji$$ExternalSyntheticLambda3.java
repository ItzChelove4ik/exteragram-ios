package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class Emoji$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ Emoji$$ExternalSyntheticLambda3(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        Emoji.m3992$r8$lambda$N2UxhO_RpBZMrAKOXY3qveXLI(this.f$0, (TLRPC.TL_messages_stickerSet) r1);
    }
}
