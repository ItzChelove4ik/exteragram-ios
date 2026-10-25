package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ BottomSheet f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda24(BottomSheet r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        ChannelAffiliateProgramsFragment.m21446$r8$lambda$K8ZQB56DMnKndeDZ5XKYwGV050(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.UserFull) r8);
    }
}
