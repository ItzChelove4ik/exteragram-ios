package org.telegram.ui.Stories.recorder;

import android.view.View;
import org.telegram.messenger.MediaController;
import org.telegram.ui.Stories.recorder.GalleryListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class GalleryListView$Adapter$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ GalleryListView.Adapter f$0;
    public final /* synthetic */ MediaController.PhotoEntry f$1;
    public final /* synthetic */ GalleryListView.Cell f$2;

    public /* synthetic */ GalleryListView$Adapter$$ExternalSyntheticLambda2(GalleryListView.Adapter r1, MediaController.PhotoEntry r2, GalleryListView.Cell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        GalleryListView.Adapter.m20030$r8$lambda$J4tnmc6qeuODI61kpqbqOsOGMU(this.f$0, this.f$1, this.f$2, r3);
    }
}
