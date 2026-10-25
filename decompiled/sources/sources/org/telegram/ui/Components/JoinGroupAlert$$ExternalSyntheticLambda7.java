package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinGroupAlert$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ JoinGroupAlert f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_messages_importChatInvite f$3;

    public /* synthetic */ JoinGroupAlert$$ExternalSyntheticLambda7(JoinGroupAlert r1, long r2, boolean r4, TLRPC.TL_messages_importChatInvite r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        JoinGroupAlert.m11180$r8$lambda$E2dLngcMEDzAnVjelhBYswlESU(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
