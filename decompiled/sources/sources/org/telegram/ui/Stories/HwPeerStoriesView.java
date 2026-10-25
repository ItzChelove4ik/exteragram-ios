package org.telegram.ui.Stories;

import android.annotation.SuppressLint;
import android.content.Context;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
abstract class HwPeerStoriesView extends PeerStoriesView {
    public HwPeerStoriesView(Context r1, StoryViewer r2, PeerStoriesView.SharedResources r3, Theme.ResourcesProvider r4) {
        super(r1, r2, r3, r4);
    }

    @Override // android.view.View
    public void invalidate() {
        if (HwFrameLayout.hwEnabled == false) goto L6;
        HwFrameLayout.hwViews.add(this);
        return;
    L6:
        super.invalidate();
    }

    @Override // android.view.View
    public void invalidate(int r2, int r3, int r4, int r5) {
        if (HwFrameLayout.hwEnabled == false) goto L6;
        HwFrameLayout.hwViews.add(this);
        return;
    L6:
        super.invalidate(r2, r3, r4, r5);
    }
}
