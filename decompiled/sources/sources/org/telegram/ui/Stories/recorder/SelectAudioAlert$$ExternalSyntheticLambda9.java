package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SelectAudioAlert$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ SelectAudioAlert f$0;

    public /* synthetic */ SelectAudioAlert$$ExternalSyntheticLambda9(SelectAudioAlert r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        SelectAudioAlert.m20202$r8$lambda$YqeyMgGWL7SzGX6FMopWveEmqA(this.f$0, r1, r2);
    }
}
