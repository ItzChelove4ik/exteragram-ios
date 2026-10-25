package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.StickersAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StickersAlert.AnonymousClass1 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ StickersAlert$1$$ExternalSyntheticLambda0(StickersAlert.AnonymousClass1 r1, boolean r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StickersAlert.AnonymousClass1.$r8$lambda$u6tDrbQ_KoWhHD1zASonD3rz5A0(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
