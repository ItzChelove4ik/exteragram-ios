package org.telegram.ui.bots;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda15 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ long[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TL_payments.starRefProgram f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ Context f$7;
    public final /* synthetic */ Theme.ResourcesProvider f$8;
    public final /* synthetic */ TLRPC.User f$9;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda15(ButtonWithCounterView r1, long[] r2, int r3, TL_payments.starRefProgram r4, BottomSheet r5, long r6, boolean r8, Context r9, Theme.ResourcesProvider r10, TLRPC.User r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r13) {
        ChannelAffiliateProgramsFragment.m21448$r8$lambda$XpRUTWcD8Qf60LYRYKywfdbQTg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r13);
    }
}
