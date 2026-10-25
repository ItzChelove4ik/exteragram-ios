package org.telegram.ui;

import android.view.View;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.CachedMediaLayout;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class CachedMediaLayout$2$$ExternalSyntheticLambda0 implements RecyclerListView.OnItemLongClickListenerExtended {
    public final /* synthetic */ CachedMediaLayout.AnonymousClass2 f$0;
    public final /* synthetic */ RecyclerListView f$1;
    public final /* synthetic */ BaseFragment f$2;

    public /* synthetic */ CachedMediaLayout$2$$ExternalSyntheticLambda0(CachedMediaLayout.AnonymousClass2 r1, RecyclerListView r2, BaseFragment r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
    public final boolean onItemClick(View r8, int r9, float r10, float r11) {
        return CachedMediaLayout.AnonymousClass2.$r8$lambda$gfOOSj4mYZOZCvVD81PZrlLJxF8(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
