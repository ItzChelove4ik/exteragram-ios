package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes7.dex */
public final /* synthetic */ class LiveCommentsView$$ExternalSyntheticLambda9 implements Utilities.Callback2 {
    public final /* synthetic */ LiveCommentsView f$0;
    public final /* synthetic */ TL_phone.getGroupCallStars f$1;

    public /* synthetic */ LiveCommentsView$$ExternalSyntheticLambda9(LiveCommentsView r1, TL_phone.getGroupCallStars r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        LiveCommentsView.m19340$r8$lambda$EjvO3DU6M9mVivO9ff3IEzcnnc(this.f$0, this.f$1, (TL_phone.groupCallStars) r2, (TLRPC.TL_error) r3);
    }
}
