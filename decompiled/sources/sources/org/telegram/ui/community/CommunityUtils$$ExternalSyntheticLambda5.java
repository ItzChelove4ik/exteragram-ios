package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$$ExternalSyntheticLambda5 implements Utilities.Callback2 {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ CommunityUtils$$ExternalSyntheticLambda5(BaseFragment r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r4, Object r5) {
        CommunityUtils.$r8$lambda$F03GPPe9DR9O_vJh6ow94AUudyw(this.f$0, this.f$1, (TLRPC.Bool) r4, (TLRPC.TL_error) r5);
    }
}
