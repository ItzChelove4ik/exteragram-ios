package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelColorActivity$$ExternalSyntheticLambda1 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ ChannelColorActivity f$0;
    public final /* synthetic */ TLRPC.ChatFull f$1;

    public /* synthetic */ ChannelColorActivity$$ExternalSyntheticLambda1(ChannelColorActivity r1, TLRPC.ChatFull r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r2, int r3) {
        ChannelColorActivity.$r8$lambda$GuvliqQjZCRs_a9IL8uvMj4_2FE(this.f$0, this.f$1, r2, r3);
    }
}
