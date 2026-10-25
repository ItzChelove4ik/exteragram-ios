package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class FolderBottomSheet$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ FolderBottomSheet f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ FolderBottomSheet$$ExternalSyntheticLambda11(FolderBottomSheet r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        FolderBottomSheet.m10710$r8$lambda$aBafx7DGQvMd4EpKKgZS_yMW80(this.f$0, this.f$1, r2, r3);
    }
}
