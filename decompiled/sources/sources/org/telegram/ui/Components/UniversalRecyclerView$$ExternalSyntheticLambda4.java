package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public final /* synthetic */ class UniversalRecyclerView$$ExternalSyntheticLambda4 implements RecyclerListView.OnItemClickListenerExtended {
    public final /* synthetic */ UniversalRecyclerView f$0;
    public final /* synthetic */ Utilities.Callback5 f$1;

    public /* synthetic */ UniversalRecyclerView$$ExternalSyntheticLambda4(UniversalRecyclerView r1, Utilities.Callback5 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended
    public final void onItemClick(View r7, int r8, float r9, float r10) {
        UniversalRecyclerView.$r8$lambda$hq9uB7g_KzJNItBrWJmoTCDIBuU(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
