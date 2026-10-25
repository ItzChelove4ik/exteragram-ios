package org.telegram.ui.Components.Premium.boosts;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLRPC.payments_GiveawayInfo f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.TL_messageMediaGiveaway f$4;
    public final /* synthetic */ BaseFragment f$5;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda4(TLRPC.payments_GiveawayInfo r1, boolean r2, String r3, long r4, TLRPC.TL_messageMediaGiveaway r6, BaseFragment r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BoostDialogs.$r8$lambda$jfoikiIAXhVcYEZJhjU0Mp9LFow(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
