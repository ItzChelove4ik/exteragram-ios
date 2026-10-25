package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ DeleteMessagesBottomSheet f$0;
    public final /* synthetic */ TLRPC.InputPeer f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int[] f$3;

    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda6(DeleteMessagesBottomSheet r1, TLRPC.InputPeer r2, int r3, int[] r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        DeleteMessagesBottomSheet.m10116$r8$lambda$mIyG_zoj8xyA_KT6unVuhohYXE(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
