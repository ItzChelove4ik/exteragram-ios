package org.telegram.ui.Stars;

import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda80 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TL_stars.StarsSubscription f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BottomSheet[] f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda80(ButtonWithCounterView r1, TL_stars.StarsSubscription r2, int r3, BottomSheet[] r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsIntroActivity.m19179$r8$lambda$jdcOnrH0PgPxKNXVE1hEWcFE0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
