package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$GifSearchPreloader$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ EmojiView.GifSearchPreloader f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ EmojiView$GifSearchPreloader$$ExternalSyntheticLambda0(EmojiView.GifSearchPreloader r1, String r2, String r3, boolean r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        EmojiView.GifSearchPreloader.m10520$r8$lambda$OBwyNN8qtZHx4jP1AjKi8GUF6E(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
