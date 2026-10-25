package org.telegram.ui.Stars;

import android.view.View;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda72 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TL_stars.StarsSubscription f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BottomSheet[] f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLObject f$6;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda72(ButtonWithCounterView r1, TL_stars.StarsSubscription r2, int r3, BottomSheet[] r4, boolean r5, boolean r6, TLObject r7) {
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
        StarsIntroActivity.m19175$r8$lambda$bMLbQQVepNFPt99ZGB3iaHKeVg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
