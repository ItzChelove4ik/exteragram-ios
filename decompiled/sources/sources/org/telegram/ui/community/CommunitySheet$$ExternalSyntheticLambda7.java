package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunitySheet$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ CommunitySheet f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ CommunitySheet$$ExternalSyntheticLambda7(CommunitySheet r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        CommunitySheet.$r8$lambda$PCTHkJ5lSNQ3AluSqkLxqTSWIY8(this.f$0, this.f$1, (TLRPC.Bool) r2, (TLRPC.TL_error) r3);
    }
}
