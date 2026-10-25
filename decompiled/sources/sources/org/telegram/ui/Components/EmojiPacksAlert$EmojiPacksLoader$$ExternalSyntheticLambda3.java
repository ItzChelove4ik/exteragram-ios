package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiPacksAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ EmojiPacksAlert.EmojiPacksLoader f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;

    public /* synthetic */ EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda3(EmojiPacksAlert.EmojiPacksLoader r1, TLRPC.TL_error r2, TLObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EmojiPacksAlert.EmojiPacksLoader.$r8$lambda$qmWWiUhonMgGoiaIFrYrF4yRqS4(this.f$0, this.f$1, this.f$2);
    }
}
