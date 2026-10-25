package org.telegram.ui.Components.Paint.Views;

import android.graphics.Bitmap;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes7.dex */
public final /* synthetic */ class PhotoView$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ PhotoView f$0;
    public final /* synthetic */ Bitmap f$1;

    public /* synthetic */ PhotoView$$ExternalSyntheticLambda2(PhotoView r1, Bitmap r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        PhotoView.$r8$lambda$JXnQgb3Ak96M8WBbQ8mdaqxb9U0(this.f$0, this.f$1, r2);
    }
}
