package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ EmojiView.StickersSearchGridAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_messages_getStickers f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda13(EmojiView.StickersSearchGridAdapter.AnonymousClass1 r1, TLRPC.TL_messages_getStickers r2, TLObject r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EmojiView.StickersSearchGridAdapter.AnonymousClass1.$r8$lambda$Tnb2GcfnNpVfN8vnf2a6UnvhFqY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
