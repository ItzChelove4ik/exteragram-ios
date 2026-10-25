package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda126 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda126(StarGiftSheet r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StarGiftSheet.$r8$lambda$p6pSx3BZRHQoAOKCoEoqZM_v1J4(this.f$0, this.f$1, r2, r3);
    }
}
