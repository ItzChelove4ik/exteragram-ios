package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda91 implements RequestDelegate {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda91(ButtonWithCounterView r1, BottomSheet[] r2, int r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarsIntroActivity.m19167$r8$lambda$OLgeKZGLGJ1_KH2V72D3vBLhwE(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
