package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda27 implements Utilities.Callback {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TL_payments.connectedBotStarRef f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda27(int r1, TL_payments.connectedBotStarRef r2, BottomSheet r3, Context r4, long r5, Theme.ResourcesProvider r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        ChannelAffiliateProgramsFragment.$r8$lambda$U5M0dEYFsmK8eGou6IsN1ErQ8PI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TL_payments.connectedBotStarRef) r9);
    }
}
