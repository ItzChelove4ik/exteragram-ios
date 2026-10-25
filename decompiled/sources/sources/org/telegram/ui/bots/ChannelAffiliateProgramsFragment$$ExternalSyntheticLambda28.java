package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda28 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.User f$10;
    public final /* synthetic */ TLRPC.TL_error f$11;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ TL_payments.starRefProgram f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ Context f$8;
    public final /* synthetic */ Theme.ResourcesProvider f$9;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda28(ButtonWithCounterView r1, TLObject r2, int r3, long r4, BottomSheet r6, TL_payments.starRefProgram r7, long r8, boolean r10, Context r11, Theme.ResourcesProvider r12, TLRPC.User r13, TLRPC.TL_error r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r10;
        this.f$8 = r11;
        this.f$9 = r12;
        this.f$10 = r13;
        this.f$11 = r14;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelAffiliateProgramsFragment.$r8$lambda$LRr52sRYPPx8YPyfkS8PVzQPZoY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
