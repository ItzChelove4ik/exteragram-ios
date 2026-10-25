package org.telegram.ui.Components.Premium.boosts;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReassignBoostBottomSheet$$ExternalSyntheticLambda1 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ ReassignBoostBottomSheet f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ ReassignBoostBottomSheet$$ExternalSyntheticLambda1(ReassignBoostBottomSheet r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r2, int r3) {
        ReassignBoostBottomSheet.$r8$lambda$oprTr1MlV0LfBeVFdyRg2NWzEQY(this.f$0, this.f$1, r2, r3);
    }
}
