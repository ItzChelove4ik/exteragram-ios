package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$52$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ DialogsActivity.AnonymousClass52 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ DialogsActivity$52$$ExternalSyntheticLambda2(DialogsActivity.AnonymousClass52 r1, String r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        DialogsActivity.AnonymousClass52.$r8$lambda$usE2wlwZ9QzGSvSodtmCUh84dbY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
