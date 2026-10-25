package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class FolderBottomSheet$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ FolderBottomSheet f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ ArrayList f$2;

    public /* synthetic */ FolderBottomSheet$$ExternalSyntheticLambda6(FolderBottomSheet r1, BaseFragment r2, ArrayList r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        FolderBottomSheet.m10715$r8$lambda$yAVj0ONEU8CKNFdOhez583aV5Y(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
