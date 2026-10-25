package org.telegram.ui.Components;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$22$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ EmojiView.AnonymousClass22 f$0;

    public /* synthetic */ EmojiView$22$$ExternalSyntheticLambda0(EmojiView.AnonymousClass22 r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return EmojiView.AnonymousClass22.m10462$r8$lambda$RADhpOnu_yDVkC8RVNteK4XjI(this.f$0, (TLRPC.TL_messages_stickerSet) r1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
