package org.telegram.ui.Stories.recorder;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SelectAudioAlert$$ExternalSyntheticLambda4 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ SelectAudioAlert f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ SelectAudioAlert$$ExternalSyntheticLambda4(SelectAudioAlert r1, Utilities.Callback r2, Theme.ResourcesProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r3, int r4) {
        SelectAudioAlert.$r8$lambda$KoFM21HWHN6by2wCdO_mjMaTwcc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
