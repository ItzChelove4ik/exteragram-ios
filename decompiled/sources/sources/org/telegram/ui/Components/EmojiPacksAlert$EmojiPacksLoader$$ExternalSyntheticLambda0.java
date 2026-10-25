package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiPacksAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ EmojiPacksAlert.EmojiPacksLoader f$0;

    public /* synthetic */ EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda0(EmojiPacksAlert.EmojiPacksLoader r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        EmojiPacksAlert.EmojiPacksLoader.$r8$lambda$3e6gROIDHYk_KnGjlYJyrtD4w7Y(this.f$0, r1, r2);
    }
}
