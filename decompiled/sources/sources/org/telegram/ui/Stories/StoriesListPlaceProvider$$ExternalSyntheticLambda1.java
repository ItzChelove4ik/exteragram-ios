package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesListPlaceProvider$$ExternalSyntheticLambda1 implements StoryViewer.HolderDrawAbove {
    public final /* synthetic */ SharedPhotoVideoCell2 f$0;
    public final /* synthetic */ RecyclerListView.FastScroll f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ StoriesListPlaceProvider$$ExternalSyntheticLambda1(SharedPhotoVideoCell2 r1, RecyclerListView.FastScroll r2, int[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Stories.StoryViewer.HolderDrawAbove
    public final void draw(Canvas r8, RectF r9, float r10, boolean r11) {
        StoriesListPlaceProvider.$r8$lambda$DVGIpiWkCMGnkOFEE2Al1jnXguk(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
