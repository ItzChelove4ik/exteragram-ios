package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class PermanentLinkBottomSheet$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ PermanentLinkBottomSheet f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PermanentLinkBottomSheet$$ExternalSyntheticLambda5(PermanentLinkBottomSheet r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PermanentLinkBottomSheet.$r8$lambda$eJOE5M5hTGEYwuPBIM4xBLLFUCs(this.f$0, this.f$1, r2, r3);
    }
}
