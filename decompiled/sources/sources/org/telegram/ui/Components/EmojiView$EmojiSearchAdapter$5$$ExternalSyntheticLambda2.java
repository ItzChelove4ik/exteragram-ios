package org.telegram.ui.Components;

import java.util.LinkedHashSet;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ LinkedHashSet f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda2(LinkedHashSet r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        EmojiView.EmojiSearchAdapter.AnonymousClass5.$r8$lambda$aYmFiSEV_zPNdYdH42thmnPihWA(this.f$0, this.f$1, (TLRPC.TL_emojiList) r2);
    }
}
