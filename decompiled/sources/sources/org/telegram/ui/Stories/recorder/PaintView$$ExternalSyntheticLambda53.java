package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda53 implements Utilities.Callback2 {
    public final /* synthetic */ PaintView f$0;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda53(PaintView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        PaintView.m20078$r8$lambda$o6kmXnagtqO3YCjdFewSG6MlQY(this.f$0, (TLRPC.MessageMedia) r1, (TL_stories.MediaArea) r2);
    }
}
