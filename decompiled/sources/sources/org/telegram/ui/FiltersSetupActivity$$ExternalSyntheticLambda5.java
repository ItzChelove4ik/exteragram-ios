package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class FiltersSetupActivity$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ FiltersSetupActivity f$0;
    public final /* synthetic */ TLRPC.TL_messages_toggleDialogFilterTags f$1;

    public /* synthetic */ FiltersSetupActivity$$ExternalSyntheticLambda5(FiltersSetupActivity r1, TLRPC.TL_messages_toggleDialogFilterTags r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        FiltersSetupActivity.$r8$lambda$momTES3DrrhcaDP_m7wnE6dYZYs(this.f$0, this.f$1, r2, r3);
    }
}
