package org.telegram.ui.Components;

import java.util.function.Consumer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$2$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ EmojiView.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ EmojiView$2$$ExternalSyntheticLambda2(EmojiView.AnonymousClass2 r1, TLRPC.Document r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object r3) {
        EmojiView.AnonymousClass2.m10461$r8$lambda$bDiqQ2MVFaoNsqhNd3lYslN8h4(this.f$0, this.f$1, this.f$2, (String) r3);
    }
}
