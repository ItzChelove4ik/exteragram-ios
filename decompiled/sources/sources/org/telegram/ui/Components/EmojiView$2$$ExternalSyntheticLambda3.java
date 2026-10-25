package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$2$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ EmojiView.AnonymousClass2 f$0;

    public /* synthetic */ EmojiView$2$$ExternalSyntheticLambda3(EmojiView.AnonymousClass2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        EmojiView.AnonymousClass2.$r8$lambda$GHIiFHwD4O_zzTOmI6fCgNPDviU(this.f$0, r1, r2);
    }
}
