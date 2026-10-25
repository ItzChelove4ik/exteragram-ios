package org.telegram.ui.Components.Paint.Views;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Paint.Views.StickerMakerView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerMakerView$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ StickerMakerView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ StickerMakerView.StickerUploader f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ StickerMakerView$$ExternalSyntheticLambda12(StickerMakerView r1, TLObject r2, StickerMakerView.StickerUploader r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickerMakerView.$r8$lambda$fBCQPfH91jrG6r4XEgMvfiZ_Ps4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
