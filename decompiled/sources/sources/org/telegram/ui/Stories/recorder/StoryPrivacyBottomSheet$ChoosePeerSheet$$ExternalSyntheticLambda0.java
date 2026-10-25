package org.telegram.ui.Stories.recorder;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda0 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ StoryPrivacyBottomSheet.ChoosePeerSheet f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda0(StoryPrivacyBottomSheet.ChoosePeerSheet r1, Utilities.Callback r2, Theme.ResourcesProvider r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r7, int r8) {
        StoryPrivacyBottomSheet.ChoosePeerSheet.$r8$lambda$pagQsZYAH4gSipVzled2d6Q35Ss(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
