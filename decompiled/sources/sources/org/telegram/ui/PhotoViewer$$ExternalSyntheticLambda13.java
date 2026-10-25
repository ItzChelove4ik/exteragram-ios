package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.ui.ChooseDownloadQualityLayout;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda13 implements ChooseDownloadQualityLayout.Callback {
    public final /* synthetic */ PhotoViewer f$0;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda13(PhotoViewer r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ChooseDownloadQualityLayout.Callback
    public final void onQualitySelected(MessageObject r1, VideoPlayer.Quality r2) {
        PhotoViewer.$r8$lambda$PBPgDp_An2dzdM9EkIRP08xkK3E(this.f$0, r1, r2);
    }
}
