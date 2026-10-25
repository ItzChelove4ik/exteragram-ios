package org.telegram.ui;

import org.telegram.messenger.MediaController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.Paint.Views.StickerMakerView;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda113 implements Utilities.Callback {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MediaController.MediaEditState f$2;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda113(PhotoViewer r1, boolean r2, MediaController.MediaEditState r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        PhotoViewer.$r8$lambda$yDoYaYX5P1AvfdUrfjGYK8BGY1A(this.f$0, this.f$1, this.f$2, (StickerMakerView.SegmentedObject) r3);
    }
}
