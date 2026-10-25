package org.telegram.ui;

import org.telegram.messenger.MediaController;
import org.telegram.ui.Components.FilterGLThread;
import org.telegram.ui.Components.VideoEditTextureView;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda120 implements VideoEditTextureView.VideoEditTextureViewDelegate {
    public final /* synthetic */ MediaController.SavedFilterState f$0;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda120(MediaController.SavedFilterState r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.VideoEditTextureView.VideoEditTextureViewDelegate
    public final void onEGLThreadAvailable(FilterGLThread r1) {
        PhotoViewer.$r8$lambda$zTIKizWtJGDaD_5DAecdQ9AqpyA(this.f$0, r1);
    }
}
