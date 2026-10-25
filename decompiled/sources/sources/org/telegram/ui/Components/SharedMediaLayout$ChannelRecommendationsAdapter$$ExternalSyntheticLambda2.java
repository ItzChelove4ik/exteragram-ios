package org.telegram.ui.Components;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ SharedMediaLayout.ChannelRecommendationsAdapter f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda2(SharedMediaLayout.ChannelRecommendationsAdapter r1, TLRPC.Chat r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        SharedMediaLayout.ChannelRecommendationsAdapter.m12940$r8$lambda$fdNZEr6du8hCdyZ1uZWXe2VwC4(this.f$0, this.f$1, this.f$2, r3);
    }
}
