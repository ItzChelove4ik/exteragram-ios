package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes6.dex */
public final /* synthetic */ class PostSuggestionsEditActivity$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ PostSuggestionsEditActivity f$0;
    public final /* synthetic */ TL_stars.updatePaidMessagesPrice f$1;

    public /* synthetic */ PostSuggestionsEditActivity$$ExternalSyntheticLambda6(PostSuggestionsEditActivity r1, TL_stars.updatePaidMessagesPrice r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PostSuggestionsEditActivity.$r8$lambda$mm4rRljUkcN5Mb_TvGjtx_ict_I(this.f$0, this.f$1, r2, r3);
    }
}
