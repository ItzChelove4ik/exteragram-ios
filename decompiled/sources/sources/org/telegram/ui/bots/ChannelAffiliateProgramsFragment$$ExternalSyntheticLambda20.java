package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ BottomSheet f$3;
    public final /* synthetic */ TL_payments.starRefProgram f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ Context f$7;
    public final /* synthetic */ Theme.ResourcesProvider f$8;
    public final /* synthetic */ TLRPC.User f$9;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda20(ButtonWithCounterView r1, int r2, long r3, BottomSheet r5, TL_payments.starRefProgram r6, long r7, boolean r9, Context r10, Theme.ResourcesProvider r11, TLRPC.User r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r9;
        this.f$7 = r10;
        this.f$8 = r11;
        this.f$9 = r12;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        ChannelAffiliateProgramsFragment.m21442$r8$lambda$CNUSHoXxs3X85hx4PJhdmecPzw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r15, r16);
    }
}
