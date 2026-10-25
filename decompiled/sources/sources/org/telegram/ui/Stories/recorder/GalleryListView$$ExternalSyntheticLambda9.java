package org.telegram.ui.Stories.recorder;

import java.util.ArrayList;
import java.util.Comparator;
import org.telegram.messenger.MediaController;

/* loaded from: classes7.dex */
public final /* synthetic */ class GalleryListView$$ExternalSyntheticLambda9 implements Comparator {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ GalleryListView$$ExternalSyntheticLambda9(ArrayList r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return GalleryListView.$r8$lambda$loNHKpXmiTQFIt6B9vuy9b9fjLQ(this.f$0, (MediaController.AlbumEntry) r1, (MediaController.AlbumEntry) r2);
    }
}
