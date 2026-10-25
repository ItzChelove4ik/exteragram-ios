package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda93 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ButtonWithCounterView f$2;
    public final /* synthetic */ BottomSheet f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda93(TLObject r1, int r2, ButtonWithCounterView r3, BottomSheet r4, long r5, TLRPC.TL_error r7, Theme.ResourcesProvider r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertsCreator.m8744$r8$lambda$Wz_ES5UyWWV5sQsDiw9Ct85x4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
