package org.telegram.ui;

import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ PhotoViewer.FrameLayoutDrawer f$0;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda19(PhotoViewer.FrameLayoutDrawer r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
