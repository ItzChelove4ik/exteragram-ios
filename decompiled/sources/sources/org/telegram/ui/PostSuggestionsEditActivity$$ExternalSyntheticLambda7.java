package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes6.dex */
public final /* synthetic */ class PostSuggestionsEditActivity$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ PostSuggestionsEditActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_stars.updatePaidMessagesPrice f$3;

    public /* synthetic */ PostSuggestionsEditActivity$$ExternalSyntheticLambda7(PostSuggestionsEditActivity r1, TLRPC.TL_error r2, TLObject r3, TL_stars.updatePaidMessagesPrice r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PostSuggestionsEditActivity.$r8$lambda$3dOgvMoCwp_9k3l32FJp4y3igGg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
