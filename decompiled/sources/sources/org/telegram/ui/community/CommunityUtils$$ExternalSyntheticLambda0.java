package org.telegram.ui.community;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog[] f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ CommunityUtils$$ExternalSyntheticLambda0(AlertDialog[] r1, BaseFragment r2, int r3, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        CommunityUtils.m21563$r8$lambda$nraCHsVClHd7uy09LZY5LtrLA0(this.f$0, this.f$1, this.f$2, this.f$3, (ArrayList) r7, (TLRPC.TL_error) r8);
    }
}
