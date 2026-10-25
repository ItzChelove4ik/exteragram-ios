package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.Paint.Views.EntityView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda59 implements Utilities.Callback2 {
    public final /* synthetic */ PaintView f$0;
    public final /* synthetic */ EntityView f$1;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda59(PaintView r1, EntityView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        PaintView.m20070$r8$lambda$WhxUXjInUzxzSblDc7ey9UGB1k(this.f$0, this.f$1, (TLRPC.MessageMedia) r2, (TL_stories.MediaArea) r3);
    }
}
