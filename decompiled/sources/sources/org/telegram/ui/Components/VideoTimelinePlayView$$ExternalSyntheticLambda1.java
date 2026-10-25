package org.telegram.ui.Components;

import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;

/* loaded from: classes7.dex */
public final /* synthetic */ class VideoTimelinePlayView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ParcelFileDescriptor f$0;
    public final /* synthetic */ MediaMetadataRetriever f$1;

    public /* synthetic */ VideoTimelinePlayView$$ExternalSyntheticLambda1(ParcelFileDescriptor r1, MediaMetadataRetriever r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VideoTimelinePlayView.$r8$lambda$lO28n9U7HZZR_HTVz9YpJoaNosk(this.f$0, this.f$1);
    }
}
