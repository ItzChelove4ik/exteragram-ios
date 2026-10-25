package org.telegram.ui.Stars;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda92 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TL_stars.StarsSubscription f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLObject f$6;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda92(ButtonWithCounterView r1, BottomSheet[] r2, int r3, boolean r4, TL_stars.StarsSubscription r5, boolean r6, TLObject r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsIntroActivity.$r8$lambda$HXjjolat772q5MuwE4enDUo5kC4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
