package org.telegram.ui.Components;

import android.graphics.SurfaceTexture;
import org.telegram.ui.Components.FilterGLThread;

/* loaded from: classes7.dex */
public final /* synthetic */ class VideoEditTextureView$$ExternalSyntheticLambda1 implements FilterGLThread.FilterGLThreadVideoDelegate {
    public final /* synthetic */ VideoEditTextureView f$0;

    public /* synthetic */ VideoEditTextureView$$ExternalSyntheticLambda1(VideoEditTextureView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.FilterGLThread.FilterGLThreadVideoDelegate
    public final void onVideoSurfaceCreated(SurfaceTexture r1) {
        VideoEditTextureView.$r8$lambda$kpI0GSMx1TrqdQHaLZudJkEC9sg(this.f$0, r1);
    }
}
