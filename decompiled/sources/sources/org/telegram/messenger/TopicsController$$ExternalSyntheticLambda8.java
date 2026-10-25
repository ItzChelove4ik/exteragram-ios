package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ TLRPC.TL_forumTopic f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda8(TopicsController r1, TLRPC.TL_forumTopic r2, long r3, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        TopicsController.$r8$lambda$7ig3rg9w2y3_NMFTYn2DSg2j4tY(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10);
    }
}
