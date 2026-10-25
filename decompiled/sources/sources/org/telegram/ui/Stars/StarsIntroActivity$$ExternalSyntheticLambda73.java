package org.telegram.ui.Stars;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda73 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TL_stars.StarsSubscription f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BottomSheet[] f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;
    public final /* synthetic */ boolean[] f$5;
    public final /* synthetic */ Context f$6;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda73(ButtonWithCounterView r1, TL_stars.StarsSubscription r2, int r3, BottomSheet[] r4, Theme.ResourcesProvider r5, boolean[] r6, Context r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r9) {
        StarsIntroActivity.$r8$lambda$ipbcP8b7a053N5xSEE2QTd_sqIg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
