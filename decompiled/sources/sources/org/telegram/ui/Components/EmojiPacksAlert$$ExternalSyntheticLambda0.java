package org.telegram.ui.Components;

import android.view.View;
import java.util.ArrayList;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$$ExternalSyntheticLambda0 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ EmojiPacksAlert f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ EmojiPacksAlert$$ExternalSyntheticLambda0(EmojiPacksAlert r1, ArrayList r2, BaseFragment r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r7, int r8) {
        EmojiPacksAlert.$r8$lambda$R19NU60NbCzdqLNxTziiKFZWtu0(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
