package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallMessagesController$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ GroupCallMessagesController f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ GroupCallMessage f$2;

    public /* synthetic */ GroupCallMessagesController$$ExternalSyntheticLambda7(GroupCallMessagesController r1, Runnable r2, GroupCallMessage r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        GroupCallMessagesController.m5274$r8$lambda$BBVlTPteaRtHqy0HClJaEGYjgA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
