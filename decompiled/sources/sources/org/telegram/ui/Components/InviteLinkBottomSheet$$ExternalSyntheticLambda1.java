package org.telegram.ui.Components;

import java.util.List;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteLinkBottomSheet$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ InviteLinkBottomSheet f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ InviteLinkBottomSheet$$ExternalSyntheticLambda1(InviteLinkBottomSheet r1, List r2, boolean r3, boolean r4, boolean r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        InviteLinkBottomSheet.$r8$lambda$0NUEhTFE1_Lwjnc3FGgTiBgtv6M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
