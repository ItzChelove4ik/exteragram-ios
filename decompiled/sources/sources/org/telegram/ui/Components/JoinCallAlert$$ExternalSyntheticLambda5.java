package org.telegram.ui.Components;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda5 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ JoinCallAlert f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda5(JoinCallAlert r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r2, int r3) {
        JoinCallAlert.m11165$r8$lambda$uf4jepFKFTyn3DW4hAmZu2Khw(this.f$0, this.f$1, r2, r3);
    }
}
