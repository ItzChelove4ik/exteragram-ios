package org.telegram.ui.Components.Paint.Views;

import android.graphics.Bitmap;
import com.google.android.gms.tasks.OnFailureListener;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerMakerView$$ExternalSyntheticLambda7 implements OnFailureListener {
    public final /* synthetic */ StickerMakerView f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ StickerMakerView$$ExternalSyntheticLambda7(StickerMakerView r1, Bitmap r2, int r3, Utilities.Callback r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r7) {
        StickerMakerView.$r8$lambda$g4nNohWAaVQsfIuXosd7zDqFQ6g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
