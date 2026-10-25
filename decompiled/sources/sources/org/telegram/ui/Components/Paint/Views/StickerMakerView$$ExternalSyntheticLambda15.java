package org.telegram.ui.Components.Paint.Views;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Paint.Views.StickerMakerView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerMakerView$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ StickerMakerView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ StickerMakerView.StickerUploader f$2;

    public /* synthetic */ StickerMakerView$$ExternalSyntheticLambda15(StickerMakerView r1, int r2, StickerMakerView.StickerUploader r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StickerMakerView.$r8$lambda$mAO7lvCMRR7hb9vt1uB_UjjzWtc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
