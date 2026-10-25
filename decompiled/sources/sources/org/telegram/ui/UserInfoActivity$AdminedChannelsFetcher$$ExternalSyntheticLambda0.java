package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.UserInfoActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class UserInfoActivity$AdminedChannelsFetcher$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ UserInfoActivity.AdminedChannelsFetcher f$0;

    public /* synthetic */ UserInfoActivity$AdminedChannelsFetcher$$ExternalSyntheticLambda0(UserInfoActivity.AdminedChannelsFetcher r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        UserInfoActivity.AdminedChannelsFetcher.m21098$r8$lambda$I41mOLoj8lOldSfqFLggIwlEQ(this.f$0, r1, r2);
    }
}
