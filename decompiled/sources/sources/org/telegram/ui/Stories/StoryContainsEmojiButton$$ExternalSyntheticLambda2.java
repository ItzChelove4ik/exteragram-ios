package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryContainsEmojiButton$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_getAttachedStickers f$2;
    public final /* synthetic */ RequestDelegate f$3;

    public /* synthetic */ StoryContainsEmojiButton$$ExternalSyntheticLambda2(Object r1, int r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StoryContainsEmojiButton.$r8$lambda$jYnsKX_xKpmcvsJtYXJsLyWT_po(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
