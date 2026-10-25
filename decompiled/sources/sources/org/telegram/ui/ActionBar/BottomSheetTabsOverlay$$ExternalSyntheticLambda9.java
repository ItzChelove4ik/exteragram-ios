package org.telegram.ui.ActionBar;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.view.PixelCopy;
import android.view.Surface;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class BottomSheetTabsOverlay$$ExternalSyntheticLambda9 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ Surface f$2;
    public final /* synthetic */ SurfaceTexture f$3;

    public /* synthetic */ BottomSheetTabsOverlay$$ExternalSyntheticLambda9(Utilities.Callback r1, Bitmap r2, Surface r3, SurfaceTexture r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int r4) {
        BottomSheetTabsOverlay.$r8$lambda$XU58vQduemRbiaLID3mmX5TDEEs(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
