package org.telegram.ui.Components;

import android.util.Pair;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class FolderBottomSheet$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ FolderBottomSheet f$0;
    public final /* synthetic */ Pair f$1;

    public /* synthetic */ FolderBottomSheet$$ExternalSyntheticLambda12(FolderBottomSheet r1, Pair r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        FolderBottomSheet.m10706$r8$lambda$OwUM9QuAfhbW3sNLNqv8_XJh8(this.f$0, this.f$1, r2, r3);
    }
}
