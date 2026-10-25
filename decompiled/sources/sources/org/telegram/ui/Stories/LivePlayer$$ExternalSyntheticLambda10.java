package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegateTimestamp;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LivePlayer$$ExternalSyntheticLambda10 implements RequestDelegateTimestamp {
    public final /* synthetic */ LivePlayer f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ LivePlayer$$ExternalSyntheticLambda10(LivePlayer r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegateTimestamp
    public final void run(TLObject r8, TLRPC.TL_error r9, long r10) {
        LivePlayer.$r8$lambda$N4nqFS_XECxcX0uAp6yuHQ5TuX0(this.f$0, this.f$1, r8, r9, r10);
    }
}
