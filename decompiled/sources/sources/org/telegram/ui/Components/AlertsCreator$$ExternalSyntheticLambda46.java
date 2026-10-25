package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda46 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda46(int r1, ButtonWithCounterView r2, BottomSheet r3, long r4, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        AlertsCreator.$r8$lambda$nFrkTaoIXA41rNJtEXwubx1yF_I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
