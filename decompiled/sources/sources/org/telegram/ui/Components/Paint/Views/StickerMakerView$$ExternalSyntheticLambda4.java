package org.telegram.ui.Components.Paint.Views;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Paint.Views.StickerMakerView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerMakerView$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ StickerMakerView f$0;
    public final /* synthetic */ StickerMakerView.StickerUploader f$1;

    public /* synthetic */ StickerMakerView$$ExternalSyntheticLambda4(StickerMakerView r1, StickerMakerView.StickerUploader r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StickerMakerView.$r8$lambda$1hSqXjaUxTUkJ0aaHgVEGmFHtxg(this.f$0, this.f$1, r2, r3);
    }
}
