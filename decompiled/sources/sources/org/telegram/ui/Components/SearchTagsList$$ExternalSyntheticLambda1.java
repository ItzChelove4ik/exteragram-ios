package org.telegram.ui.Components;

import android.view.View;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SearchTagsList$$ExternalSyntheticLambda1 implements RecyclerListView.OnItemLongClickListener {
    public final /* synthetic */ SearchTagsList f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ SearchTagsList$$ExternalSyntheticLambda1(SearchTagsList r1, int r2, BaseFragment r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener
    public final boolean onItemClick(View r7, int r8) {
        return SearchTagsList.$r8$lambda$_JussU2fLaejB8XohbmLqyp52kw(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
