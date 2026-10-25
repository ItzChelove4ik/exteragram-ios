package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public final /* synthetic */ class UniversalRecyclerView$$ExternalSyntheticLambda5 implements RecyclerListView.OnItemLongClickListenerExtended {
    public final /* synthetic */ UniversalRecyclerView f$0;
    public final /* synthetic */ Utilities.Callback5Return f$1;

    public /* synthetic */ UniversalRecyclerView$$ExternalSyntheticLambda5(UniversalRecyclerView r1, Utilities.Callback5Return r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
    public final boolean onItemClick(View r7, int r8, float r9, float r10) {
        return UniversalRecyclerView.m13480$r8$lambda$_GCRbihvXsZB7pfhHKEzmEVHLE(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
