package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.MessageStatisticActivity;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessageStatisticActivity.ListAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ StatisticActivity.ZoomCancelable f$2;

    public /* synthetic */ MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda0(MessageStatisticActivity.ListAdapter.AnonymousClass1 r1, String r2, StatisticActivity.ZoomCancelable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessageStatisticActivity.ListAdapter.AnonymousClass1.$r8$lambda$m2bJFpMzz7a1yoLG0V7OiZP8Aww(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
