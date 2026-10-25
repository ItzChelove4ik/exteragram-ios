package org.telegram.ui;

import java.util.LinkedHashSet;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda32 implements Utilities.Callback {
    public final /* synthetic */ LinkedHashSet f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda32(LinkedHashSet r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        SelectAnimatedEmojiDialog.$r8$lambda$Uqn0EM85MByBpD0DweJ2uY4FGXA(this.f$0, this.f$1, (TLRPC.TL_emojiList) r2);
    }
}
