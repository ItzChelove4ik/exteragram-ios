package org.telegram.ui;

import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$CaptionTextView$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ PhotoViewer.CaptionTextView f$0;

    public /* synthetic */ PhotoViewer$CaptionTextView$$ExternalSyntheticLambda3(PhotoViewer.CaptionTextView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.clearLinks();
    }
}
