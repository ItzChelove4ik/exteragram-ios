package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda418 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda418(MessagesController r1, long r2, Utilities.Callback r4, TLRPC.User r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MessagesController.m4505$r8$lambda$6z4FPZ_3biaa6LuTrJHwC75PSs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
