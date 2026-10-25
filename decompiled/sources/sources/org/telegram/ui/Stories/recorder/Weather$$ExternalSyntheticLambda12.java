package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ double f$2;
    public final /* synthetic */ double f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ Weather$$ExternalSyntheticLambda12(int[] r1, Utilities.Callback r2, double r3, double r5, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        Weather.m20597$r8$lambda$vVhMXNvxnYiRF_qAQBPejR2K5g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11);
    }
}
