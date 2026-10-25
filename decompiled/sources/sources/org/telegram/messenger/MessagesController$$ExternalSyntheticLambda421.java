package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda421 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda421(MessagesController r1, long r2, long r4, ArrayList r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MessagesController.m4638$r8$lambda$tpGlMUPOjfAbRUHuUqNHiHQc(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10);
    }
}
