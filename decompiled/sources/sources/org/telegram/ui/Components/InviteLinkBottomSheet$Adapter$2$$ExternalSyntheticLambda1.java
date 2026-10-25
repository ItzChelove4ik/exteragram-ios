package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.InviteLinkBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ InviteLinkBottomSheet.Adapter.AnonymousClass2 f$0;

    public /* synthetic */ InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda1(InviteLinkBottomSheet.Adapter.AnonymousClass2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        InviteLinkBottomSheet.Adapter.AnonymousClass2.m11081$r8$lambda$4cLiA6WotNUeTI0Qgw6BwnCj_k(this.f$0, r1, r2);
    }
}
