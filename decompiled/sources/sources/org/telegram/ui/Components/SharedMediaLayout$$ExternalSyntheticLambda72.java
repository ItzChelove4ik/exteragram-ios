package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda72 implements RequestDelegate {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$3;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda72(SharedMediaLayout r1, AlertDialog[] r2, int r3, TLRPC.TL_messages_editMessage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        SharedMediaLayout.m12766$r8$lambda$G8znxryR3au8SgcKAAz5h7sRf4(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
